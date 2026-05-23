package dashketch.mods.gar_mod.global.items;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import static dashketch.mods.gar_mod.Gar_mod.MODID;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);

    public static final DeferredItem<Item> BLASTER_RIFLE = ITEMS.registerItem("blaster_rifle",
            properties -> new Item(new Item.Properties().stacksTo(1)));
}
