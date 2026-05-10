package dashketch.mods.gar_mod.utils.armor;

import dashketch.mods.gar_mod.client.model.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import dashketch.mods.gar_mod.global.GlobalMorphs.ArmorType;

public class ArmorModelManager {

    public static HumanoidModel<LivingEntity> getModelForType(ArmorType type, EquipmentSlot slot) {
        HumanoidModel<LivingEntity> model;

        // Use a switch statement to pick the right model
        switch (type) {
            case TROOPER -> model = new trooper<>(Minecraft.getInstance().getEntityModels().bakeLayer(trooper.LAYER_LOCATION));
            case CADET -> //noinspection DuplicateBranchesInSwitch
                    model = new cadet<>(Minecraft.getInstance().getEntityModels().bakeLayer(cadet.LAYER_LOCATION));
            // Add more cases here as I create the new model classes:
            // case SERGEANT -> model = new sergeant<>(...);
            case SERGEANT -> model = new sergeant<>(Minecraft.getInstance().getEntityModels().bakeLayer(sergeant.LAYER_LOCATION));
            case OFFICER -> model = new officer<>(Minecraft.getInstance().getEntityModels().bakeLayer(officer.LAYER_LOCATION));
            case LANCE -> model = new lance<>(Minecraft.getInstance().getEntityModels().bakeLayer(lance.LAYER_LOCATION));
            case WARRANT1 -> model = new warrant_officer<>(Minecraft.getInstance().getEntityModels().bakeLayer(warrant_officer.LAYER_LOCATION));
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