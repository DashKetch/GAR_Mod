package dashketch.mods.gar_mod.utils.item;

import dashketch.mods.gar_mod.global.items.ModItems;
import net.minecraft.world.item.Item;

public class GarItem extends Item {
    private final ModItems.GunType gunType;

    public GarItem(Item.Properties properties, ModItems.GunType gunType) {
        super(properties);
        this.gunType = gunType;
    }
}
