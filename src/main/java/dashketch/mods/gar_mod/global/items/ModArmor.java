package dashketch.mods.gar_mod.global.items;

import com.mojang.serialization.Codec;
import dashketch.mods.gar_mod.global.GlobalMorphs;
import dashketch.mods.gar_mod.utils.armor.GarArmorItem;
import dashketch.mods.gar_mod.utils.armor.ModArmorMaterials;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

import static dashketch.mods.gar_mod.Gar_mod.MODID;
import static dashketch.mods.gar_mod.global.items.ModItems.ITEMS;

public class ModArmor {

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);

    public static final Supplier<AttachmentType<Integer>> SKIN_TYPE = ATTACHMENT_TYPES.register("skin_type", () ->
            AttachmentType.builder(() -> 0).serialize(Codec.INT).copyOnDeath().build());

    public static final DeferredItem<Item> CADET_HELMET = ITEMS.registerItem("cadet_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.CADET));

    public static final DeferredItem<Item> CADET_CHESTPLATE = ITEMS.registerItem("cadet_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.CADET));

    public static final DeferredItem<Item> CADET_LEGGINGS = ITEMS.registerItem("cadet_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.CADET));

    public static final DeferredItem<Item> CADET_BOOTS = ITEMS.registerItem("cadet_boots",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.CADET));

    public static final DeferredItem<Item> TROOPER_HELMET = ITEMS.registerItem("trooper_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.TROOPER, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.TROOPER));

    public static final DeferredItem<Item> TROOPER_CHESTPLATE = ITEMS.registerItem("trooper_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.TROOPER, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.TROOPER));

    public static final DeferredItem<Item> TROOPER_LEGGINGS = ITEMS.registerItem("trooper_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.TROOPER, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.TROOPER));

    public static final DeferredItem<Item> TROOPER_BOOTS = ITEMS.registerItem("trooper_boots",
            properties -> new GarArmorItem(ModArmorMaterials.TROOPER, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.TROOPER));

    public static final DeferredItem<Item> SERGEANT_HELMET = ITEMS.registerItem("sergeant_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.SERGEANT, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.SERGEANT));

    public static final DeferredItem<Item> SERGEANT_CHESTPLATE = ITEMS.registerItem("sergeant_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.SERGEANT, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.SERGEANT));

    public static final DeferredItem<Item> SERGEANT_LEGGINGS = ITEMS.registerItem("sergeant_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.SERGEANT, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.SERGEANT));

    public static final DeferredItem<Item> SERGEANT_BOOTS = ITEMS.registerItem("sergeant_boots",
            properties -> new GarArmorItem(ModArmorMaterials.SERGEANT, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.SERGEANT));

    public static final DeferredItem<Item> OFFICER_HELMET = ITEMS.registerItem("officer_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.OFFICER, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.OFFICER));

    public static final DeferredItem<Item> OFFICER_CHESTPLATE = ITEMS.registerItem("officer_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.OFFICER, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.OFFICER));

    public static final DeferredItem<Item> OFFICER_LEGGINGS = ITEMS.registerItem("officer_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.OFFICER, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.OFFICER));

    public static final DeferredItem<Item> OFFICER_BOOTS = ITEMS.registerItem("officer_boots",
            properties -> new GarArmorItem(ModArmorMaterials.OFFICER, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.OFFICER));

    public static final DeferredItem<Item> LANCE_HELMET = ITEMS.registerItem("lance_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.LANCE, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.LANCE));

    public static final DeferredItem<Item> LANCE_CHESTPLATE = ITEMS.registerItem("lance_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.LANCE, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.LANCE));

    public static final DeferredItem<Item> LANCE_LEGGINGS = ITEMS.registerItem("lance_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.LANCE, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.LANCE));

    public static final DeferredItem<Item> LANCE_BOOTS = ITEMS.registerItem("lance_boots",
            properties -> new GarArmorItem(ModArmorMaterials.LANCE, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.LANCE));

    public static final DeferredItem<Item> WO_HELMET = ITEMS.registerItem("wo_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.WO, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT1));

    public static final DeferredItem<Item> WO_CHESTPLATE = ITEMS.registerItem("wo_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.WO, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT1));

    public static final DeferredItem<Item> WO_LEGGINGS = ITEMS.registerItem("wo_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.WO, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT1));

    public static final DeferredItem<Item> WO_BOOTS = ITEMS.registerItem("wo_boots",
            properties -> new GarArmorItem(ModArmorMaterials.WO, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT1));

    public static final DeferredItem<Item> UWO_HELMET = ITEMS.registerItem("uwo_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.UWO, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT2));

    public static final DeferredItem<Item> UWO_CHESTPLATE = ITEMS.registerItem("uwo_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.UWO, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT2));

    public static final DeferredItem<Item> UWO_LEGGINGS = ITEMS.registerItem("uwo_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.UWO, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT2));

    public static final DeferredItem<Item> UWO_BOOTS = ITEMS.registerItem("uwo_boots",
            properties -> new GarArmorItem(ModArmorMaterials.UWO, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT2));

    public static final DeferredItem<Item> CWO_HELMET = ITEMS.registerItem("cwo_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.CWO, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT3));

    public static final DeferredItem<Item> CWO_CHESTPLATE = ITEMS.registerItem("cwo_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.CWO, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT3));

    public static final DeferredItem<Item> CWO_LEGGINGS = ITEMS.registerItem("cwo_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.CWO, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT3));

    public static final DeferredItem<Item> CWO_BOOTS = ITEMS.registerItem("cwo_boots",
            properties -> new GarArmorItem(ModArmorMaterials.CWO, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.WARRANT3));

}
