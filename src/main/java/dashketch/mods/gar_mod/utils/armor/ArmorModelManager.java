package dashketch.mods.gar_mod.utils.armor;

import dashketch.mods.gar_mod.client.model.cadet;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class ArmorModelManager {
    cadet<LivingEntity> m = new cadet<>(Minecraft.getInstance().getEntityModels().bakeLayer(cadet.LAYER_LOCATION));

    // Inside your ArmorModelManager or Client Extension
    public static cadet<LivingEntity> getModel(EquipmentSlot slot) {
        cadet<LivingEntity> m = new cadet<>(Minecraft.getInstance().getEntityModels().bakeLayer(cadet.LAYER_LOCATION));

        // Hide parts based on what item we are wearing
        m.head.visible = (slot == EquipmentSlot.HEAD);
        m.body.visible = (slot == EquipmentSlot.CHEST);
        m.rightArm.visible = (slot == EquipmentSlot.CHEST);
        m.leftArm.visible = (slot == EquipmentSlot.CHEST);
        m.leftLeg.visible = (slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET);
        m.rightLeg.visible = (slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET);

        return m;
    }
}