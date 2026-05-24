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
    private static final Minecraft mc = Minecraft.getInstance();

    public static final String UTIL_CATEGORY = "key.categories.gar_mod_utils";
    public static final String GUN_CATEGORY = "key.categories.gar_mod_guns";

    public static KeyMapping RESET;
    public static KeyMapping SAFETY;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        RESET = new KeyMapping(
                "key.gar_mod.reset",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                UTIL_CATEGORY
        );

        SAFETY = new KeyMapping(
                "key.gar_mod.safety_no_change",
                InputConstants.Type.KEYSYM,
                mc.options.keyDrop.getKey().getValue(), //TODO: Make this refresh either everytime the drop key is changed or everytime the game loads
                GUN_CATEGORY
        );

        event.register(RESET);
        event.register(SAFETY);
    }
}