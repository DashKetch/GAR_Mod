package dashketch.mods.gar_mod.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.network.packets.SyncPlayerRankPayload;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static dashketch.mods.gar_mod.server.logic.ChangeRepublicMorph.setMorph;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class AddPowerCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("AddPlayerPower")
                        .requires(source -> source.hasPermission(2)) // Level 2 = OP/Admin
                        .then(Commands.argument("target", EntityArgument.player())
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0)) // Min value 0
                                        .executes(context -> {
                                            // 1. Get arguments from context
                                            ServerPlayer target = EntityArgument.getPlayer(context, "target");
                                            int powerAmount = IntegerArgumentType.getInteger(context, "amount");

                                            // 2. Fetch current data
                                            PlayerRankData oldData = target.getData(ModAttachments.PLAYER_RANK);
                                            int oldPower = oldData.points;

                                            // 3. Add power to current
                                            int newPowerAmount = oldPower + powerAmount;
                                            int calculatedRank = PlayerRankData.getRankForPoints(newPowerAmount);
                                            boolean rankChange = (calculatedRank != 7 && newPowerAmount < 320) || oldData.rank != calculatedRank;


                                            // 4. Update only the points (preserving rank and ticks) on server side attachment
                                            target.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(
                                                    calculatedRank,
                                                    newPowerAmount,
                                                    oldData.tickCounter,
                                                    oldData.team
                                            ));

                                            // 5. Feedback
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Added " + powerAmount + " power to " + target.getScoreboardName() + "'s power."), true);

                                            //Sync the changes to the player and anyone tracking them so HUDs and models refresh instantly
                                            PacketDistributor.sendToPlayersTrackingEntityAndSelf(target, new SyncPlayerRankPayload(
                                                    newPowerAmount,
                                                    calculatedRank,
                                                    oldData.tickCounter,
                                                    oldData.team,
                                                    target.getId()
                                            ));

                                            // 6. Set new morph
                                            if (rankChange) { setMorph(target); }
                                            return 1;
                                        })
                                )
                        )
        );
    }
}