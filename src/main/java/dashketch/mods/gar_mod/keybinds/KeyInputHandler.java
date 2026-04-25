package dashketch.mods.gar_mod.keybinds;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.network.ResetPayload;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class KeyInputHandler {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        if (!KeyBindings.RESET.consumeClick()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null) return;

        PacketDistributor.sendToServer(new ResetPayload());
    }
}