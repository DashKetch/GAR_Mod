package dashketch.mods.gar_mod.utils.item;

import dashketch.mods.gar_mod.global.GlobalGuns;
import net.minecraft.world.item.Item;

public class GarItem extends Item {
    private final GlobalGuns.GunType gunType;

    public GarItem(Item.Properties properties, GlobalGuns.GunType gunType) {
        super(properties);
        this.gunType = gunType;
    }
}
