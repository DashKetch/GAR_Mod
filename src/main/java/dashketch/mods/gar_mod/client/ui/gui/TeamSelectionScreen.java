package dashketch.mods.gar_mod.client.ui.gui;

import dashketch.mods.gar_mod.client.ui.gui.widget.TeamButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class TeamSelectionScreen extends Screen {
    // I will replace these with the actual image paths later
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath("gar_mod", "textures/gui/team_background.png");

    public TeamSelectionScreen() {
        super(Component.literal("Select Your Team"));
    }

    @Override
    protected void init() {
        int cardWidth = 100;
        int cardHeight = 140;
        int spacing = 15;
        int totalWidth = (cardWidth * 3) + (spacing * 2);
        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - cardHeight) / 2;

        // Immigrant - Green Placeholder
        this.addRenderableWidget(new TeamButton(startX, startY, cardWidth, cardHeight, "Immigrant", 0xFF55FF55, (b) -> select("immigrant")));

        // Raider - Red Placeholder
        this.addRenderableWidget(new TeamButton(startX + cardWidth + spacing, startY, cardWidth, cardHeight, "Raider", 0xFFFF5555, (b) -> select("raider")));

        // Republic - Blue Placeholder
        this.addRenderableWidget(new TeamButton(startX + (cardWidth + spacing) * 2, startY, cardWidth, cardHeight, "Republic", 0xFF5555FF, (b) -> select("republic")));
    }

    private void select(String team) {
        System.out.println("Handshaking with server: Joining " + team);
        // Packet logic goes here next!
        this.onClose();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // 1. Draw a dark background tint
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // 2. Draw custom background image (when provided)
        // guiGraphics.blit(BG_TEXTURE, 0, 0, 0, 0, this.width, this.height, this.width, this.height);

        super.render(guiGraphics, mouseX, mouseY, partialTick);
    }

    // force the game NOT to pause when this menu is open
    @Override
    public boolean isPauseScreen() {
        boolean Server = false;
        if (Minecraft.getInstance().isSingleplayer()) {
            Server = true;

        } else if (!Minecraft.getInstance().isSingleplayer()) {
            Server = false;
        }
        return Server;
    }
}
