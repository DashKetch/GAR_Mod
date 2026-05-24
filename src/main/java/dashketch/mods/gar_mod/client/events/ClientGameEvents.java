package dashketch.mods.gar_mod.client.events;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.client.ui.gui.TeamSelectionScreen;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientGameEvents {

    @SubscribeEvent
    public static void onClientTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide() && event.getEntity() == Minecraft.getInstance().player) {
            PlayerRankData data = event.getEntity().getData(ModAttachments.PLAYER_RANK);
            if (data.team.equals("none") && Minecraft.getInstance().screen == null) {
                Minecraft.getInstance().setScreen(new TeamSelectionScreen());
            }
        }
    }

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        PlayerRankData data = event.getEntity().getData(ModAttachments.PLAYER_RANK);
        event.getRenderer().getModel().setAllVisible(!data.team.equals("raider"));
    }

    @SubscribeEvent
    public static void lockRepublicArmor(ScreenEvent.MouseButtonPressed.Pre event) {
        // 1. Check if the screen is the standard survival inventory
        if (event.getScreen() instanceof net.minecraft.client.gui.screens.inventory.InventoryScreen screen) {

            // 2. Get the client-side player
            net.minecraft.client.player.LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) return;

            // 3. Retrieve the player's rank/team data
            PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);

            // 4. ONLY proceed if the player is on the "republic" team
            if ("republic".equals(data.team)) {

                // 5. Get the slot the player's mouse is currently hovering over
                net.minecraft.world.inventory.Slot slot = screen.getSlotUnderMouse();

                if (slot != null && slot.hasItem()) {
                    // 6. In the survival inventory, the armor slots are ALWAYS index 5, 6, 7, and 8.
                    if (slot.index >= 5 && slot.index <= 8) {

                        // 7. Cancel the event
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}