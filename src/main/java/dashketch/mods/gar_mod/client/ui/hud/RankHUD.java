package dashketch.mods.gar_mod.client.ui.hud;

import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public class RankHUD {
    public static boolean menuHudExpanded = false;

    private static final Minecraft mc = Minecraft.getInstance();

    int x = 10;
    int y = 10;

    private static final String POWER_TEXT = "Power: ";
    private static final String RANK_TEXT = " Rank: ";

    // Helper method to dynamically calculate text width based on current points
    private int getWidth(int points, int rank) {
        return menuHudExpanded ? mc.font.width(POWER_TEXT) + mc.font.width(Integer.toString(points)) + mc.font.width(RANK_TEXT) + mc.font.width(Integer.toString(rank)) : mc.font.width(POWER_TEXT) + mc.font.width(Integer.toString(points));
    }

    @SubscribeEvent
    public void onRenderGui(RenderGuiEvent.Post event) {
        // 1. Exit early if the player isn't loaded in a world yet
        if (mc.player == null) {
            return;
        }

        // 2. Fetch the data every frame so it stays updated
        PlayerRankData data = mc.player.getData(ModAttachments.PLAYER_RANK);
        int playerPoints = (data != null) ? data.points : 0;
        int playerRank = (data != null) ? data.rank : 1;

        GuiGraphics graphics = event.getGuiGraphics();

        // 3. Render using the live variables
        graphics.fill(x - 4, y - 4, x + getWidth(playerPoints, playerRank) + 14, y + 6, 0x99000000);
        graphics.drawString(mc.font, POWER_TEXT + playerPoints, ((float) x), y - 3.25f, 0xFFFFFF, true);
    }
}