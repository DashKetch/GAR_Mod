package dashketch.mods.gar_mod.server.logic;

import dashketch.mods.gar_mod.global.ModItems;
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
            1, new ArmorSet(ModItems.CADET_HELMET, ModItems.CADET_CHESTPLATE, ModItems.CADET_LEGGINGS, ModItems.CADET_BOOTS),
            2, new ArmorSet(ModItems.TROOPER_HELMET, ModItems.TROOPER_CHESTPLATE, ModItems.TROOPER_LEGGINGS, ModItems.TROOPER_BOOTS),
            3, new ArmorSet(ModItems.LANCE_HELMET, ModItems.LANCE_CHESTPLATE, ModItems.LANCE_LEGGINGS, ModItems.LANCE_BOOTS),
            4, new ArmorSet(ModItems.SERGEANT_HELMET, ModItems.SERGEANT_CHESTPLATE, ModItems.SERGEANT_LEGGINGS, ModItems.SERGEANT_BOOTS),
            5, new ArmorSet(ModItems.WO_HELMET, ModItems.WO_CHESTPLATE, ModItems.WO_LEGGINGS, ModItems.WO_BOOTS),
            6, new ArmorSet(ModItems.UWO_HELMET, ModItems.UWO_CHESTPLATE, ModItems.UWO_LEGGINGS, ModItems.UWO_BOOTS),
            7, new ArmorSet(ModItems.CWO_HELMET, ModItems.CWO_CHESTPLATE, ModItems.CWO_LEGGINGS, ModItems.CWO_BOOTS),
            8, new ArmorSet(ModItems.OFFICER_HELMET, ModItems.OFFICER_CHESTPLATE, ModItems.OFFICER_LEGGINGS, ModItems.OFFICER_BOOTS)
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
