package dashketch.mods.gar_mod.utils.armor;

import dashketch.mods.gar_mod.Gar_mod;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.EnumMap;
import java.util.List;

public class ModArmorMaterials {
    public static final DeferredRegister<ArmorMaterial> ARMOR_MATERIALS =
            DeferredRegister.create(Registries.ARMOR_MATERIAL, Gar_mod.MODID);

    public static final Holder<ArmorMaterial> CADET = ARMOR_MATERIALS.register("cadet", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            15, // Enchantability
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(Items.IRON_INGOT), // Repair item
            // This MUST match the folder name in textures: assets/gar_mod/textures/models/armor/cadet.png
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "cadet"))),
            0.0F, // Toughness
            0.0F  // Knockback resistance
    ));

    public static final Holder<ArmorMaterial> TROOPER = ARMOR_MATERIALS.register("trooper", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            15, // Enchantability
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(Items.IRON_INGOT), // Repair item
            // This MUST match the folder name in textures: assets/gar_mod/textures/models/armor/trooper.png
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "trooper"))),
            0.0F, // Toughness
            0.0F  // Knockback resistance
    ));

    public static final Holder<ArmorMaterial> LANCE = ARMOR_MATERIALS.register("lance", () -> new ArmorMaterial(
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 5);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
            }),
            15, // Enchantability
            SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(Items.IRON_INGOT), // Repair item
            // This MUST match the folder name in textures: assets/gar_mod/textures/models/armor/lance.png
            List.of(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "lance"))),
            0.0F, // Toughness
            0.0F  // Knockback resistance
    ));
}