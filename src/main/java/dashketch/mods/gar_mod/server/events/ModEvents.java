package dashketch.mods.gar_mod.server.events;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.client.ui.gui.TeamSelectionScreen;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) return;

        ServerPlayer player = (ServerPlayer) event.getEntity();
        PlayerRankData oldData = player.getData(ModAttachments.PLAYER_RANK);

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
            }
        }
        player.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(newRank, newPoints, newTicks, oldData.team));
    }

    @SubscribeEvent
    public static void onClientTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide() && event.getEntity() == Minecraft.getInstance().player) {
            PlayerRankData data = event.getEntity().getData(ModAttachments.PLAYER_RANK);
            if (data.team.equals("none") && Minecraft.getInstance().screen == null) {
                Minecraft.getInstance().setScreen(new TeamSelectionScreen());
            }
        }
    }

    private static int getPointsNeededForNextRank(int currentRank) {
        return switch (currentRank) {
            case 1 -> 2; case 2 -> 15; case 3 -> 40;
            case 4 -> 100; case 5 -> 150; case 6 -> 210;
            default -> Integer.MAX_VALUE;
        };
    }

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        PlayerRankData data = event.getEntity().getData(ModAttachments.PLAYER_RANK);
        event.getRenderer().getModel().setAllVisible(!data.team.equals("raider"));
    }
}