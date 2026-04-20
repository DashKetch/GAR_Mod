package dashketch.mods.gar_mod.client.ui.gui.widget;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class TeamButton extends Button {
    private final String teamTitle;
    private final int color;

    public TeamButton(int x, int y, int width, int height, String teamTitle, int hexColor, OnPress onPress) {
        super(x, y, width, height, Component.literal(""), onPress, DEFAULT_NARRATION);
        this.teamTitle = teamTitle;
        this.color = hexColor;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // 1. Determine if hovered for the "glow" effect
        boolean hovered = this.isHoveredOrFocused();
        int borderColor = hovered ? 0xFFFFFFFF : 0xFF555555; // White if hovered, Gray if not

        // 2. Draw the Main Card Body (The Placeholder Icon Area)
        guiGraphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF222222); // Dark background

        // 3. Draw a colored header strip (Placeholder for the icon)
        guiGraphics.fill(getX() + 5, getY() + 5, getX() + width - 5, getY() + (height / 2), color);

        // 4. Draw the Border
        guiGraphics.renderOutline(getX(), getY(), width, height, borderColor);

        // 5. Draw the Text
        guiGraphics.drawCenteredString(Minecraft.getInstance().font, teamTitle,
                getX() + width / 2, getY() + (height / 2) + 10, 0xFFFFFF);

        if (hovered) {
            guiGraphics.drawCenteredString(Minecraft.getInstance().font, "Click to Join",
                    getX() + width / 2, getY() + height - 15, 0xAAAAAA);
        }
    }
}