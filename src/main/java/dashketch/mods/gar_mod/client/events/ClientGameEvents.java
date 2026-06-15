package dashketch.mods.gar_mod.client.events;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.client.ui.gui.TeamSelectionScreen;
import dashketch.mods.gar_mod.keybinds.KeyBindings;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.options.OptionsScreen;
import net.minecraft.client.player.LocalPlayer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.client.event.ScreenEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientGameEvents {

    private static boolean initialSyncDone = false;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (!initialSyncDone && KeyBindings.mc.options != null) {
            KeyBindings.syncSafetyToDropKey();
            initialSyncDone = true;
        }
    }

    @SubscribeEvent
    public static void onScreenClosing(ScreenEvent.Closing event) {
        if (event.getScreen() instanceof OptionsScreen) {
            KeyBindings.syncSafetyToDropKey();
        }
    }

    @SubscribeEvent
    public static void onClientPlayerTick(PlayerTickEvent.Post event) {
        if (event.getEntity().level().isClientSide() && event.getEntity() == Minecraft.getInstance().player) {
            PlayerRankData data = event.getEntity().getData(ModAttachments.PLAYER_RANK);

            if (data != null && data.team.equals("none") && Minecraft.getInstance().screen == null) {
                Minecraft.getInstance().setScreen(new TeamSelectionScreen());
            }
        }
    }

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        PlayerRankData data = event.getEntity().getData(ModAttachments.PLAYER_RANK);
        if (data != null) {
            event.getRenderer().getModel().setAllVisible(!data.team.equals("raider"));
        }
    }

    @SubscribeEvent
    public static void lockRepublicArmor(ScreenEvent.MouseButtonPressed.Pre event) {
        if (event.getScreen() instanceof net.minecraft.client.gui.screens.inventory.InventoryScreen screen) {
            LocalPlayer player = Minecraft.getInstance().player;
            if (player == null) return;

            PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);
            if (data != null && "republic".equals(data.team)) {
                net.minecraft.world.inventory.Slot slot = screen.getSlotUnderMouse();
                if (slot != null && slot.hasItem()) {
                    if (slot.index >= 5 && slot.index <= 8) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}