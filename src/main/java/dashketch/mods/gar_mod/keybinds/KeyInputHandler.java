package dashketch.mods.gar_mod.keybinds;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.client.ui.hud.RankHUD;
import dashketch.mods.gar_mod.network.packets.ResetPayload;
import dashketch.mods.gar_mod.network.packets.ToggleSafetyPayload;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static dashketch.mods.gar_mod.Gar_mod.LOGGER;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onResetKeyInput(InputEvent.Key event) {
        if (!KeyBindings.RESET.consumeClick()) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        PacketDistributor.sendToServer(new ResetPayload());
    }

    @SubscribeEvent
    public static void onSafetyKeyInput(InputEvent.Key event) {
        if (!KeyBindings.SAFETY.consumeClick()) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        PacketDistributor.sendToServer(new ToggleSafetyPayload());

        LOGGER.info("Safety packet sent to server!");
    }

    @SubscribeEvent
    public static void onExpandKeyInput(InputEvent.Key event) {
        if (!KeyBindings.EXPAND.consumeClick()) return;
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        RankHUD.menuHudExpanded = !RankHUD.menuHudExpanded;
    }
}