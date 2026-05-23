package dashketch.mods.gar_mod.global.items;

import dashketch.mods.gar_mod.utils.item.GarItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dashketch.mods.gar_mod.Gar_mod.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public enum GunType {
        BLASTER_RIFLE,
        BLASTER_RIFLE_SAFETY,

        BLASTER_PISTOL,
        BLASTER_PISTOL_SAFETY
    }

    public static final DeferredItem<Item> BLASTER_RIFLE = ITEMS.registerItem("blaster_rifle",
            properties -> new GarItem(properties.stacksTo(1), GunType.BLASTER_RIFLE));
}
