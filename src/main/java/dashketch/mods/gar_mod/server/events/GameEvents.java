package dashketch.mods.gar_mod.server.events;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.global.items.ModArmor;
import dashketch.mods.gar_mod.network.packets.SyncPlayerRankPayload;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.CommandEvent;
import net.neoforged.neoforge.event.entity.living.LivingDropsEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import static dashketch.mods.gar_mod.global.items.ModItems.*;
import static dashketch.mods.gar_mod.server.logic.ChangeRepublicMorph.setMorph;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class GameEvents {

    private static final Supplier<Set<Item>> MOD_ARMOR_ITEMS = ModArmor::getAllRegisteredArmorItems;

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) return;

        ServerPlayer player = (ServerPlayer) event.getEntity();
        PlayerRankData oldData = player.getData(ModAttachments.PLAYER_RANK);

        if (Objects.equals(oldData.team, "republic")) {
            int newTicks = oldData.tickCounter + 1;
            int newPoints = oldData.points;
            int newRank = oldData.rank;

            if (newTicks >= 18000) {
                newPoints += 1;
                newTicks = 0;
                int pointsNeeded = getPointsNeededForNextRank(newRank);
                if (newPoints >= pointsNeeded) {
                    newRank++;
                    player.sendSystemMessage(Component.literal("§aCongratulations! You have reached Rank " + newRank + "!"));
                    setMorph(player);
                }
            }
            player.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(newRank, newPoints, newTicks, oldData.team));
        }
    }

    public static int getPointsNeededForNextRank(int currentRank) {
        return switch (currentRank) {
            case 0 -> 0; case 1 -> 2; case 2 -> 15;
            case 3 -> 40; case 4 -> 100; case 5 -> 150;
            case 6 -> 210;
            default -> Integer.MAX_VALUE;
        };
    }

    public static int getRifleModel(Item currentModel) {
        return currentModel == BLASTER_RIFLE.asItem() ? 0 : 1;
    }

    public static int getPistolModel(Item currentModel) {
        return currentModel == BLASTER_PISTOL.asItem() ? 0 : 1;
    }

    public static void syncPlayerData(ServerPlayer player) {
        PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);
        if (data != null) {
            PacketDistributor.sendToPlayersTrackingEntityAndSelf(player, new SyncPlayerRankPayload(
                    data.points,
                    data.rank,
                    data.tickCounter,
                    data.team,
                    player.getId()
            ));
        }
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            // 1. Sync their data down to the client first
            syncPlayerData(serverPlayer);

            // 2. Only give them the morph if they are actually a registered Republic member
            PlayerRankData data = serverPlayer.getData(ModAttachments.PLAYER_RANK);
            if (data != null && "republic".equals(data.team)) {
                setMorph(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            // 1. Sync data
            syncPlayerData(serverPlayer);

            // 2. Only restore morph if they are on the Republic team
            PlayerRankData data = serverPlayer.getData(ModAttachments.PLAYER_RANK);
            if (data != null && "republic".equals(data.team)) {
                setMorph(serverPlayer);
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer serverPlayer) {
            syncPlayerData(serverPlayer);
        }
    }

    @SubscribeEvent
    public static void onCommandEvent(CommandEvent event) {
        String command = event.getParseResults().getReader().getString();

        if (command.startsWith("op ")) {
            String targetPlayerName = command.substring(3).trim();
            CommandSourceStack source = event.getParseResults().getContext().getSource();

            if (source.getServer() != null) {
                ServerPlayer targetPlayer = source.getServer().getPlayerList().getPlayerByName(targetPlayerName);

                if (targetPlayer != null) {
                    PlayerRankData data = targetPlayer.getData(ModAttachments.PLAYER_RANK);

                    if (data != null) {
                        targetPlayer.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(
                                8,
                                data.points,
                                data.tickCounter,
                                data.team
                        ));

                        setMorph(targetPlayer);

                        syncPlayerData(targetPlayer);
                        Gar_mod.LOGGER.info("Automatically updated {}'s morph to Officer via /op intercept.", targetPlayerName);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerDrops(LivingDropsEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);

            if (data != null && "republic".equals(data.team)) {
                event.getDrops().removeIf(drop -> MOD_ARMOR_ITEMS.get().contains(drop.getItem().getItem()));
            }
        }
    }
}