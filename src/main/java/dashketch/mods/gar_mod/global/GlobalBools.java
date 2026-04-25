package dashketch.mods.gar_mod.global;

import dashketch.mods.gar_mod.utils.data.ModAttachments;
import net.minecraft.world.entity.player.Player;

public class GlobalBools {

    public static boolean isImmigrant(Player player) {
        return "immigrant".equals(player.getData(ModAttachments.PLAYER_RANK).team);
    }

    public static boolean isRaider(Player player) {
        return "raider".equals(player.getData(ModAttachments.PLAYER_RANK).team);
    }

    public static boolean isRepublic(Player player) {
        return "republic".equals(player.getData(ModAttachments.PLAYER_RANK).team);
    }
}