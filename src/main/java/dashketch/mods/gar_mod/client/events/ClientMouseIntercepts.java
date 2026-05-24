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
public class ClientMouseIntercepts{

    @SubscribeEvent
    public static void onMouseAttackInput(InputEvent.MouseButton.Pre event) {
        Minecraft mc = Minecraft.getInstance();

        // Return Immediately if a gui is open so mouse 1 gui interactions don't get suppressed
        if (mc.player == null || mc.options == null || mc.screen != null) return;

        // 1. Check if the player is holding a blaster item
        if (mc.player.getMainHandItem().getItem() instanceof GarItem) {

            // 2. Check if the pressed key matches the Attack/Break Block keybind
            Key breakKey = mc.options.keyAttack.getKey();

            if (event.getButton() == breakKey.getValue()) {

                // 3. consume the input buffer
                while (mc.options.keyAttack.consumeClick()) {
                    LOGGER.info("Mouse event {} consumed", breakKey.getName());
                }

                // 4. Force the key state to unpressed
                mc.options.keyAttack.setDown(false);

                // 5. Cancel the event so Minecraft vanilla logic skips it entirely
                event.setCanceled(true);
            }
        }
    }
}