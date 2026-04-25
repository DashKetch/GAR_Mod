package dashketch.mods.gar_mod.keybinds;

import com.mojang.blaze3d.platform.InputConstants;
import dashketch.mods.gar_mod.Gar_mod;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import org.lwjgl.glfw.GLFW;

@SuppressWarnings("removal")
@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindings {
    public static final String CATEGORY = "key.categories.gar_mod_utils";
    public static KeyMapping RESET;

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent event) {
        RESET = new KeyMapping(
                "key.gar_mod.reset",
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                CATEGORY
        );

        event.register(RESET);
    }
}