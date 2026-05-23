package dashketch.mods.gar_mod.global.registries;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.entity.BoltProjectile;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredRegister;
import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(Registries.ENTITY_TYPE, Gar_mod.MODID);

    public static final Supplier<EntityType<BoltProjectile>> BLASTER_BOLT = ENTITY_TYPES.register("blaster_bolt",
            () -> EntityType.Builder.of(BoltProjectile::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f) // The physical hitbox size
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("blaster_bolt"));
}