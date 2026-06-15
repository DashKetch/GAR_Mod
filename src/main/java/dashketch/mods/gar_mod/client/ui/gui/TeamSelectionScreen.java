package dashketch.mods.gar_mod.client.ui.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import dashketch.mods.gar_mod.client.ui.gui.widget.TeamButton;
import dashketch.mods.gar_mod.network.ModNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.PacketDistributor;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.NotNull;

import static dashketch.mods.gar_mod.Gar_mod.MODID;
import static dashketch.mods.gar_mod.server.events.GameEvents.syncPlayerData;
import static dashketch.mods.gar_mod.server.logic.ChangeRepublicMorph.setMorph;

public class TeamSelectionScreen extends Screen {
    private static final ResourceLocation BG_TEXTURE = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/team_background.png");
    private static final ResourceLocation IM_TEXTURE = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/button/teambutton/immigrant_icon.png");
    private static final ResourceLocation REP_TEXTURE = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/button/teambutton/republic_icon.png");
    private static final ResourceLocation RDR_TEXTURE = ResourceLocation.fromNamespaceAndPath(MODID, "textures/gui/button/teambutton/raider_icon.png");

    public TeamSelectionScreen() {
        super(Component.literal("Select Your Team"));
    }

    public static void open() {
        Minecraft.getInstance().setScreen(new TeamSelectionScreen());
    }

    @Override
    protected void init() {
        // Scaled down slightly for a better fit on screen
        int cardWidth = 120;
        int cardHeight = 170;
        int spacing = 20;
        int totalWidth = (cardWidth * 3) + (spacing * 2);
        int startX = (this.width - totalWidth) / 2;
        int startY = (this.height - cardHeight) / 2;

        // Immigrant - Green Placeholder
        this.addRenderableWidget(new TeamButton(startX, startY, cardWidth, cardHeight, "Immigrant", 0xFF55FF55, (b) -> select("immigrant"), null, IM_TEXTURE));

        // Raider - Red Placeholder
        this.addRenderableWidget(new TeamButton(startX + cardWidth + spacing, startY, cardWidth, cardHeight, "Raider", 0xFFFF5555, (b) -> select("raider"), null, RDR_TEXTURE));

        // Republic - Blue Placeholder
        this.addRenderableWidget(new TeamButton(startX + (cardWidth + spacing) * 2, startY, cardWidth, cardHeight, "Republic", 0xFF5555FF, (b) -> select("republic"), null, REP_TEXTURE));
    }

    private void select(String team) {
        System.out.println("Handshaking with server: Joining " + team);
        PacketDistributor.sendToServer(new ModNetworking.SelectTeamPayload(team));
        this.onClose();
    }

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        // 1. Draw the default dark tint + blur first
        this.renderBackground(guiGraphics, mouseX, mouseY, partialTick);

        // 2. Render buttons and other widgets
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        // 3. Draw background image on top of the tint
        RenderSystem.enableBlend();
        RenderSystem.setShaderColor(1.0f, 1.0f, 1.0f, 1.0f); // Reset color to avoid darkness
        guiGraphics.blit(BG_TEXTURE, 0, 0, 0, 0, this.width, this.height, this.width, this.height);

        RenderSystem.disableBlend();
    }

    // force the game NOT to pause when this menu is open
    @Override
    public boolean isPauseScreen() {
        return false;
    }
}