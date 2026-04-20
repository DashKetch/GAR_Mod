package dashketch.mods.gar_mod.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class SetPowerCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("SetPlayerPower")
                        .requires(source -> source.hasPermission(2)) // Level 2 = OP/Admin
                        .then(Commands.argument("target", EntityArgument.player())
                                .then(Commands.argument("amount", IntegerArgumentType.integer(0)) // Min value 0
                                        .executes(context -> {
                                            // 1. Get arguments from context
                                            ServerPlayer target = EntityArgument.getPlayer(context, "target");
                                            int newPower = IntegerArgumentType.getInteger(context, "amount");

                                            // 2. Fetch current data
                                            PlayerRankData oldData = target.getData(ModAttachments.PLAYER_RANK);
                                            int calculatedRank = PlayerRankData.getRankForPoints(newPower);

                                            // 3. Update only the points (preserving rank and ticks)
                                            // Or reset rank to 1 so the tick event re-calculates it based on new points

                                            target.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(
                                                    calculatedRank,
                                                    newPower,
                                                    oldData.tickCounter,
                                                    oldData.team
                                            ));

                                            // 4. Feedback
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Set " + target.getScoreboardName() + "'s power to " + newPower), true);

                                            return 1;
                                        })
                                )
                        )
        );
    }
}