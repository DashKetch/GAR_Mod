package dashketch.mods.gar_mod.global.registries;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dashketch.mods.gar_mod.Gar_mod.LOGGER;
import static dashketch.mods.gar_mod.Gar_mod.MODID;

public class ModSounds {

    // Paths
    static ResourceLocation BLASTER_RIFLE_FIRE_LOCATION =
            ResourceLocation.fromNamespaceAndPath(MODID, "blaster_rifle_fire");

    static ResourceLocation BLASTER_PISTOL_FIRE_LOCATION =
            ResourceLocation.fromNamespaceAndPath(MODID, "blaster_pistol_fire");

    // Create the Sound Event Deferred Register
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, MODID);

    // Register individual sound events
    public static final DeferredHolder<SoundEvent, SoundEvent> BLASTER_RIFLE_FIRE =
            SOUND_EVENTS.register("item.blaster_rifle_fire", () ->
                    SoundEvent.createVariableRangeEvent(
                            BLASTER_RIFLE_FIRE_LOCATION
                    )
            );

    public static final DeferredHolder<SoundEvent, SoundEvent> BLASTER_PISTOL_FIRE =
            SOUND_EVENTS.register("item.blaster_pistol_fire", () ->
                    SoundEvent.createVariableRangeEvent(
                            BLASTER_PISTOL_FIRE_LOCATION
                    )
            );

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
        LOGGER.info("Registering Sound Events");
    }
}