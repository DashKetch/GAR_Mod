package dashketch.mods.gar_mod.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.network.ModNetworking;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import java.util.Arrays;
import java.util.List;

import static dashketch.mods.gar_mod.server.events.ResetHandler.restoreTeamInventory;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ChangeTeamCommand {

    private static final List<String> VALID_TEAMS = Arrays.asList("immigrant", "raider", "republic", "none");

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        event.getDispatcher().register(
                Commands.literal("ChangePlayerTeam")
                        .requires(source -> source.hasPermission(2))
                        .then(Commands.argument("target", EntityArgument.player())
                                .then(Commands.argument("team", StringArgumentType.word())
                                        // Provides autocomplete in the chat UI
                                        .suggests((context, builder) -> SharedSuggestionProvider.suggest(VALID_TEAMS, builder))
                                        .executes(context -> {
                                            ServerPlayer target = EntityArgument.getPlayer(context, "target");
                                            String teamInput = StringArgumentType.getString(context, "team");

                                            // Validate the input
                                            if (!VALID_TEAMS.contains(teamInput)) {
                                                context.getSource().sendFailure(Component.literal("Invalid team! Use: none, immigrant, raider, or republic."));
                                                return 0;
                                            }

                                            PlayerRankData oldData = target.getData(ModAttachments.PLAYER_RANK);

                                            // Update the data with the new teamInput
                                            target.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(
                                                    oldData.rank,
                                                    oldData.points,
                                                    oldData.tickCounter,
                                                    teamInput
                                            ));

                                            PacketDistributor.sendToPlayer(target, new ModNetworking.SyncTeamPayload(target.getId(), teamInput));

                                            context.getSource().sendSuccess(() ->
                                                    Component.literal("Set " + target.getScoreboardName() + "'s team to " + teamInput), true);

                                            return 1;
                                        })
                                )
                        )
        );
    }
}