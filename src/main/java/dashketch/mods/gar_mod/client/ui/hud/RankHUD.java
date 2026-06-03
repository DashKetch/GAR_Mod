package dashketch.mods.gar_mod.client.ui.hud;

import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RenderGuiEvent;

public class RankHUD {
    @SuppressWarnings("FieldMayBeFinal")
    public static boolean menuHudExpanded = false;

    int x = 10;
    int y = 10;

    private static int getWidth() {
        int modifierOne = 14;
        int modifierTwo = 6;
        return menuHudExpanded ? (8 + modifierTwo) * 3 + modifierOne + (2 * 4) : 4 * 3 + modifierOne + (2 * 4);
    }

    @SubscribeEvent
    public void onRenderGui(RenderGuiEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();

        if (mc.player == null) {
            return;
        }

        GuiGraphics graphics = event.getGuiGraphics();

        PlayerRankData data = mc.player.getData(ModAttachments.PLAYER_RANK);

        int playerPoints = (data != null) ? data.points : 0;

        graphics.fill(x - 4, y - 4, x + getWidth() + 14, y + 6, 0x99000000);
        graphics.drawString(mc.font, "Power: " + playerPoints, ((float) x), y - 3.25f, 0xFFFFFF, true);
    }
}