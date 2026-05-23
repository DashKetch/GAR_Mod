package dashketch.mods.gar_mod.server.logic;

import dashketch.mods.gar_mod.global.items.ModArmor;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

/**
 * Data-driven class: maps numeric rank -> ArmorSet to avoid many if/switch statements.
 */
public class changeRepublicMorph {

    // Simple holder for an armor set (head, chest, legs, feet)
        private record ArmorSet(Holder<Item> head, Holder<Item> chest, Holder<Item> legs, Holder<Item> feet) {
    }

    // Map rank ID -> ArmorSet. Uses the numeric rank values from PlayerRankData.
    private static final Map<Integer, ArmorSet> RANK_TO_ARMOR = Map.of(
            1, new ArmorSet(ModArmor.CADET_HELMET, ModArmor.CADET_CHESTPLATE, ModArmor.CADET_LEGGINGS, ModArmor.CADET_BOOTS),
            2, new ArmorSet(ModArmor.TROOPER_HELMET, ModArmor.TROOPER_CHESTPLATE, ModArmor.TROOPER_LEGGINGS, ModArmor.TROOPER_BOOTS),
            3, new ArmorSet(ModArmor.LANCE_HELMET, ModArmor.LANCE_CHESTPLATE, ModArmor.LANCE_LEGGINGS, ModArmor.LANCE_BOOTS),
            4, new ArmorSet(ModArmor.SERGEANT_HELMET, ModArmor.SERGEANT_CHESTPLATE, ModArmor.SERGEANT_LEGGINGS, ModArmor.SERGEANT_BOOTS),
            5, new ArmorSet(ModArmor.WO_HELMET, ModArmor.WO_CHESTPLATE, ModArmor.WO_LEGGINGS, ModArmor.WO_BOOTS),
            6, new ArmorSet(ModArmor.UWO_HELMET, ModArmor.UWO_CHESTPLATE, ModArmor.UWO_LEGGINGS, ModArmor.UWO_BOOTS),
            7, new ArmorSet(ModArmor.CWO_HELMET, ModArmor.CWO_CHESTPLATE, ModArmor.CWO_LEGGINGS, ModArmor.CWO_BOOTS),
            8, new ArmorSet(ModArmor.OFFICER_HELMET, ModArmor.OFFICER_CHESTPLATE, ModArmor.OFFICER_LEGGINGS, ModArmor.OFFICER_BOOTS)
    );

    public static void setMorph(ServerPlayer player) {
        int rank = getCustomRankFromSystem(player);
        // clear current armor
        player.getInventory().armor.clear();

        ArmorSet set = RANK_TO_ARMOR.get(rank);
        if (set == null) return; // no armor for rank

        // create ItemStacks from Holders and equip
        player.setItemSlot(EquipmentSlot.HEAD, new ItemStack(set.head));
        player.setItemSlot(EquipmentSlot.CHEST, new ItemStack(set.chest));
        player.setItemSlot(EquipmentSlot.LEGS, new ItemStack(set.legs));
        player.setItemSlot(EquipmentSlot.FEET, new ItemStack(set.feet));
    }

    // Return numeric rank directly; default 0 for unknown/null
    private static int getCustomRankFromSystem(ServerPlayer player) {
        PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);
        return data == null ? 0 : data.rank;
    }
}
