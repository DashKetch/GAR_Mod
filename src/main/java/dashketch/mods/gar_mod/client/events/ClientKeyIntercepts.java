package dashketch.mods.gar_mod.client.events;

import dashketch.mods.gar_mod.Gar_mod;
import com.mojang.blaze3d.platform.InputConstants.Key;
import dashketch.mods.gar_mod.utils.item.GarItem;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;

import static dashketch.mods.gar_mod.Gar_mod.LOGGER;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME, value = Dist.CLIENT)
public class ClientKeyIntercepts {

    @SubscribeEvent
    public static void onKeyInput(InputEvent.Key event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.options == null) return;

        // 1. Check if the player is holding a blaster item
        if (mc.player.getMainHandItem().getItem() instanceof GarItem) {

            // 2. Check if the pressed key matches the Drop Item keybind
            Key dropKey = mc.options.keyDrop.getKey();

            if (event.getKey() == dropKey.getValue()) {

                // 3. consume the input
                while (mc.options.keyDrop.consumeClick()) {
                    LOGGER.info("Key event {} consumed", dropKey.getName());
                }

                // 4. Force the key state to unpressed just in case they hold it down
                mc.options.keyDrop.setDown(false);
            }
        }
    }
}