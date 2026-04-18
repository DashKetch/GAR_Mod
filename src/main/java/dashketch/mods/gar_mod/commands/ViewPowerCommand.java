package dashketch.mods.gar_mod.commands;

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
public class ViewPowerCommand {

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("ViewPlayerPower")
                        .requires(source -> source.hasPermission(1)) // Level 1 = Regular Player
                        .then(Commands.argument("target", EntityArgument.player())
                                        .executes(context -> {
                                            // 1. Get arguments from context
                                            ServerPlayer target = EntityArgument.getPlayer(context, "target");

                                            // 2. Fetch current data
                                            PlayerRankData playerRankData = target.getData(ModAttachments.PLAYER_RANK);

                                            // 5. Feedback
                                            context.getSource().sendSuccess(() ->
                                                    Component.literal(target.getScoreboardName() + " is Rank " + playerRankData.rank +
                                                            " with " + playerRankData.points + " Power."), true);

                                            return 1;
                                        })
                                )
        );
    }
}