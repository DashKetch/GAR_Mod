package dashketch.mods.gar_mod.global;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.client.ui.gui.TeamSelectionScreen;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

import java.sql.Time;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    // Helper method to define your exact point thresholds
    private static int getPointsNeededForNextRank(int currentRank) {
        return switch (currentRank) {
            case 1 -> 2;   // Points needed to reach Rank 2
            case 2 -> 15;  // Points needed to reach Rank 3
            case 3 -> 40;  // Points needed to reach Rank 4
            case 4 -> 100; // Points needed to reach Rank 5
            case 5 -> 150; // Points needed to reach Rank 6
            case 6 -> 210; // Points needed to reach Rank 7
            default -> Integer.MAX_VALUE; // Rank 7 is the max, so we set an impossible goal for Rank 8
        };
    }

    @SubscribeEvent
    public static void onPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide()) return;

        ServerPlayer player = (ServerPlayer) event.getEntity();
        PlayerRankData oldData = player.getData(ModAttachments.PLAYER_RANK);

        int newTicks = oldData.tickCounter + 1;
        int newPoints = oldData.points;
        int newRank = oldData.rank;

        // Has 15 minutes passed? (18,000 ticks)
        if (newTicks >= 18000) {
            newPoints += 1;
            newTicks = 0; // Reset the clock for the next point

            // Check if they hit the specific threshold for their NEXT rank
            int pointsNeeded = getPointsNeededForNextRank(newRank);

            if (newPoints >= pointsNeeded) {
                newRank++;
                // NOTE: We do NOT reset newPoints here. They keep their lifetime points!

                // Announce it to the player with a green message
                player.sendSystemMessage(Component.literal("§aCongratulations! You have reached Rank " + newRank + "!"));
            }
        }

        // Save the updated data back to the player
        player.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(newRank, newPoints, newTicks, oldData.team));
    }

    @SubscribeEvent
    public static void onClientTick(PlayerTickEvent.Post event) throws InterruptedException {
        if (event.getEntity().level().isClientSide()) {
            PlayerRankData data = event.getEntity().getData(ModAttachments.PLAYER_RANK);

            // If the player has no team yet, force the menu open
            if (data.team.equals("none") && Minecraft.getInstance().screen == null) {
                Minecraft.getInstance().setScreen(new TeamSelectionScreen());
            }
        }
    }

}