package dashketch.mods.gar_mod.global;

import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
public class GlobalSkins {
    //@Unique
    public static final PlayerSkin FLIGHT_SUIT = new PlayerSkin(
            // Match your tree: assets/gar_mod/textures/player/entity/...
            ResourceLocation.fromNamespaceAndPath("gar_mod", "textures/player/entity/flight_suit_skin.png"),
            null,
            null,
            null,
            PlayerSkin.Model.WIDE,
            false
    );
}
