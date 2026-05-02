package dashketch.mods.gar_mod.client.ui.gui.widget;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class TeamButton extends Button {
    private final String teamTitle;
    private final int color;
    private final ResourceLocation teamIcon; // Store the icon path

    public TeamButton(int x, int y, int width, int height, String teamTitle, int hexColor, OnPress onPress, ResourceLocation teamIcon) {
        super(x, y, width, height, Component.literal(""), onPress, DEFAULT_NARRATION);
        this.teamTitle = teamTitle;
        this.color = hexColor;
        this.teamIcon = teamIcon;
    }

    @Override
    public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        boolean hovered = this.isHoveredOrFocused();
        int borderColor = hovered ? 0xFFFFFFFF : 0xFF555555;

        // 1. Draw the Main Card Body
        guiGraphics.fill(getX(), getY(), getX() + width, getY() + height, 0xFF222222);

        // 2. Draw the colored background for the icon
        int iconPadding = 5;
        int iconAreaHeight = height - 50;
        guiGraphics.fill(getX() + iconPadding, getY() + iconPadding, getX() + width - iconPadding, getY() + iconAreaHeight, color);

        // 3. Draw the Icon Texture
        RenderSystem.enableBlend();
        // Render the icon slightly inset from the colored background
        int textureSize = width - 5;
        int textureX = getX() + (width - textureSize) / 2;
        int textureY = getY() + (iconAreaHeight - textureSize) / 2;

        guiGraphics.blit(teamIcon, textureX + 1, textureY + 3, 0, 0, textureSize, textureSize, textureSize, textureSize + 14);
        RenderSystem.disableBlend();

        // 4. Draw the Border
        guiGraphics.renderOutline(getX(), getY(), width, height, borderColor);

        // 5. Draw the Text
        guiGraphics.drawCenteredString(Minecraft.getInstance().font, teamTitle,
                getX() + width / 2, getY() + height - 35, 0xFFFFFF);

        if (hovered) {
            guiGraphics.drawCenteredString(Minecraft.getInstance().font, "Click to Join",
                    getX() + width / 2, getY() + height - 15, 0xAAAAAA);
        }
    }
}