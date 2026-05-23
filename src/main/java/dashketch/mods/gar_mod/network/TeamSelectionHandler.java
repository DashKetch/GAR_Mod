package dashketch.mods.gar_mod.network;

import dashketch.mods.gar_mod.server.logic.ChangeRepublicMorph;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class TeamSelectionHandler {

    public static void handleSelectTeam(final ModNetworking.SelectTeamPayload payload, final IPayloadContext context) {

        // enqueueWork ensures this runs on the main server thread
        context.enqueueWork(() -> {

            // 1. Get the player who clicked the button
            ServerPlayer player = (ServerPlayer) context.player();

            // 2. Check which team they selected from the payload
            String selectedTeam = payload.team().toLowerCase();

            // 3. Apply the appropriate logic based on the team
            switch (selectedTeam) {
                case "republic" ->
                        ChangeRepublicMorph.setMorph(player);
                case "raider" -> {
                    // Nothing for now
                }
                case "immigrant" -> {
                }
                // Nothing for now
            }
        });
    }
}