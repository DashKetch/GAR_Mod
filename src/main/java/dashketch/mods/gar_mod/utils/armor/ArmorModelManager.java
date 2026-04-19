package dashketch.mods.gar_mod.utils.armor;

import dashketch.mods.gar_mod.client.model.cadet;
import dashketch.mods.gar_mod.client.model.trooper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

public class ArmorModelManager {

    // Add all your future types here
    public enum ArmorType {
        CADET,
        TROOPER,
        LANCE,
        SERGEANT,
        WARRANT1,
        WARRANT2,
        WARRANT3,
        OFFICER
    }

    public static HumanoidModel<LivingEntity> getModelForType(ArmorType type, EquipmentSlot slot) {
        HumanoidModel<LivingEntity> model;

        // Use a switch statement to pick the right model
        switch (type) {
            case TROOPER -> model = new trooper<>(Minecraft.getInstance().getEntityModels().bakeLayer(trooper.LAYER_LOCATION));
            case CADET -> model = new cadet<>(Minecraft.getInstance().getEntityModels().bakeLayer(cadet.LAYER_LOCATION));
            // Add more cases here as you create the new model classes:
            // case LANCE -> model = new lance<>(...);
            default -> model = new cadet<>(Minecraft.getInstance().getEntityModels().bakeLayer(cadet.LAYER_LOCATION));
        }

        // Apply universal visibility logic to whatever model was chosen
        model.head.visible = (slot == EquipmentSlot.HEAD);
        model.hat.visible = (slot == EquipmentSlot.HEAD);
        model.body.visible = (slot == EquipmentSlot.CHEST);
        model.rightArm.visible = (slot == EquipmentSlot.CHEST);
        model.leftArm.visible = (slot == EquipmentSlot.CHEST);
        model.rightLeg.visible = (slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET);
        model.leftLeg.visible = (slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET);

        return model;
    }
}