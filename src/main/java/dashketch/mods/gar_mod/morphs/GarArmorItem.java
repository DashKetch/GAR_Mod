package dashketch.mods.gar_mod.morphs;

import dashketch.mods.gar_mod.utils.armor.ArmorModelManager;
import dashketch.mods.gar_mod.utils.armor.ArmorModelManager.ArmorType;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class GarArmorItem extends ArmorItem {
    private final ArmorType armorType;

    public GarArmorItem(Holder<ArmorMaterial> material, Type type, Properties properties, ArmorType armorType) {
        super(material, type, properties);
        this.armorType = armorType;
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(@NotNull LivingEntity livingEntity, @NotNull ItemStack itemStack, @NotNull EquipmentSlot equipmentSlot, @NotNull HumanoidModel<?> original) {
                return ArmorModelManager.getModelForType(armorType, equipmentSlot);
            }
        });
    }
}