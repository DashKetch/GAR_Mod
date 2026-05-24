package dashketch.mods.gar_mod.global.items;

import dashketch.mods.gar_mod.global.GlobalGuns;
import dashketch.mods.gar_mod.utils.item.GarItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dashketch.mods.gar_mod.Gar_mod.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<GarItem> BLASTER_RIFLE = ITEMS.registerItem("blaster_rifle",
            properties -> new GarItem(properties.stacksTo(1), GlobalGuns.GunType.BLASTER_RIFLE));

    public static final DeferredItem<GarItem> BLASTER_RIFLE_SAFETY = ITEMS.registerItem("blaster_rifle_safety",
            properties -> new GarItem(properties.stacksTo(1), GlobalGuns.GunType.BLASTER_RIFLE_SAFETY));

    public static final DeferredItem<GarItem> BLASTER_PISTOL = ITEMS.registerItem("blaster_pistol",
            properties -> new GarItem(properties.stacksTo(1), GlobalGuns.GunType.BLASTER_PISTOL));

    public static final DeferredItem<GarItem> BLASTER_RIFLE_PISTOL = ITEMS.registerItem("blaster_pistol_safety",
            properties -> new GarItem(properties.stacksTo(1), GlobalGuns.GunType.BLASTER_PISTOL_SAFETY));
}
