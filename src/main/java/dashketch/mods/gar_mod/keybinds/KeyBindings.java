package dashketch.mods.gar_mod.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import dashketch.mods.gar_mod.Gar_mod;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindings {
    public static final Minecraft mc = Minecraft.getInstance();

    public static final String UTIL_CATEGORY = "key.categories.gar_mod_utils";
    public static final String GUN_CATEGORY = "key.categories.gar_mod_guns";
    public static final String MENU_CATEGORY = "key.categories.gar_mod_menus";

    public static KeyMapping RESET;
    public static KeyMapping SAFETY;
    public static KeyMapping EXPAND;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        EXPAND = new KeyMapping(
                "key.gar_mod.expand",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_G,
                MENU_CATEGORY
        );

        RESET = new KeyMapping(
                "key.gar_mod.reset",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                UTIL_CATEGORY
        );

        // Initialize with a default (Q)
        SAFETY = new KeyMapping(
                "key.gar_mod.safety_no_change",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_Q,
                GUN_CATEGORY
        );

        event.register(RESET);
        event.register(SAFETY);
        event.register(EXPAND);
    }

    public static void syncSafetyToDropKey() {
        if (mc.options != null && SAFETY != null) {
            InputConstants.Key dropKey = mc.options.keyDrop.getKey();
            SAFETY.setKey(dropKey);
            mc.options.save();
        }
    }
}