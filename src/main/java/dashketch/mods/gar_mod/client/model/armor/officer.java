package dashketch.mods.gar_mod.client.model.armor;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class officer<T extends LivingEntity> extends HumanoidModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("gar_mod", "officer"), "main");

	public officer(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		// Standard HumanoidModel anchors
		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.offset(-5.0F, 2.0F, 0.0F));
		PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.offset(5.0F, 2.0F, 0.0F));
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.offset(-1.9F, 12.0F, 0.0F));
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.offset(1.9F, 12.0F, 0.0F));

		// --- HEAD ---
		head.addOrReplaceChild("helmet_top", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 0.0F, 8.0F), PartPose.ZERO);
		head.addOrReplaceChild("bill", CubeListBuilder.create().texOffs(0, 52).addBox(-4.0F, -1.0F, -3.0F, 8.0F, 1.0F, 4.0F), PartPose.offsetAndRotation(0.0F, -5.5F, -4.5F, 0.3491F, 0.0F, 0.0F));
		head.addOrReplaceChild("head_side_r", CubeListBuilder.create().texOffs(32, 16).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F), PartPose.offsetAndRotation(-4.0F, -4.0F, 0.0F, 0.0F, 1.5708F, -1.5708F));
		head.addOrReplaceChild("head_side_l", CubeListBuilder.create().texOffs(32, 24).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 0.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 1.5708F, 1.5708F));
		head.addOrReplaceChild("head_front", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -4.0F, -4.0F, 1.5708F, 0.0F, 0.0F));
		head.addOrReplaceChild("head_back", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F), PartPose.offsetAndRotation(0.0F, -4.0F, 4.0F, -1.5708F, 0.0F, 0.0F));

		// --- BODY ---
		body.addOrReplaceChild("body_front", CubeListBuilder.create().texOffs(0, 8).addBox(-6.0F, 0.0F, -4.0F, 12.0F, 0.0F, 8.0F), PartPose.offsetAndRotation(0.0F, 6.0F, -2.0F, 1.5708F, 0.0F, 1.5708F));
		body.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -2.0F, -1.0F, 12.0F, 0.0F, 8.0F), PartPose.offsetAndRotation(3.0F, 9.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

		// --- RIGHT ARM ---
		rightArm.addOrReplaceChild("ra_top", CubeListBuilder.create().texOffs(24, 52).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.ZERO);
		rightArm.addOrReplaceChild("ra_side_out", CubeListBuilder.create().texOffs(40, 8).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(-3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
		rightArm.addOrReplaceChild("ra_front", CubeListBuilder.create().texOffs(40, 4).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(-1.0F, 2.0F, -2.0F, 1.5708F, 0.0F, 1.5708F));
		rightArm.addOrReplaceChild("ra_back", CubeListBuilder.create().texOffs(40, 0).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(-1.0F, 2.0F, 2.0F, -1.5708F, 0.0F, 1.5708F));
		rightArm.addOrReplaceChild("ra_bottom", CubeListBuilder.create().texOffs(24, 56).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offset(1.0F, 10.0F, 0.0F));

		// --- LEFT ARM (FIXED) ---
		// Top Face - Set at Y -2 (shoulder height)
		leftArm.addOrReplaceChild("la_top", CubeListBuilder.create().texOffs(40, 52).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.ZERO);
		// Back Face - Flipped rotation to face outward
		leftArm.addOrReplaceChild("la_back", CubeListBuilder.create().texOffs(0, 40).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(1.0F, 2.0F, 2.0F, -1.5708F, 0.0F, 1.5708F));
		// Front Face
		leftArm.addOrReplaceChild("la_front", CubeListBuilder.create().texOffs(32, 36).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(1.0F, 2.0F, -2.0F, 1.5708F, 0.0F, 1.5708F));
		// Outer Side Face
		leftArm.addOrReplaceChild("la_side_out", CubeListBuilder.create().texOffs(32, 32).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
		// Bottom Face
		leftArm.addOrReplaceChild("la_bottom", CubeListBuilder.create().texOffs(40, 56).addBox(-1.0F, 10.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.ZERO);

		// --- RIGHT LEG ---
		rightLeg.addOrReplaceChild("rl_bottom", CubeListBuilder.create().texOffs(56, 52).addBox(-2.0F, 12.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offset(0.1F, 0.0F, 0.0F));
		rightLeg.addOrReplaceChild("rl_side", CubeListBuilder.create().texOffs(0, 44).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(-2.1F, 4.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
		rightLeg.addOrReplaceChild("rl_front", CubeListBuilder.create().texOffs(32, 40).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(-0.1F, 4.0F, -2.0F, 1.5708F, 0.0F, 1.5708F));
		rightLeg.addOrReplaceChild("rl_back", CubeListBuilder.create().texOffs(40, 12).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(-0.1F, 4.0F, 2.0F, -1.5708F, 0.0F, 1.5708F));

		// --- LEFT LEG ---
		leftLeg.addOrReplaceChild("ll_side", CubeListBuilder.create().texOffs(32, 48).addBox(-9.0F, -1.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(1.1F, 9.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
		leftLeg.addOrReplaceChild("ll_front", CubeListBuilder.create().texOffs(0, 48).addBox(-9.0F, -2.0F, -1.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(1.1F, 9.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));
		leftLeg.addOrReplaceChild("ll_back", CubeListBuilder.create().texOffs(32, 44).addBox(-6.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F), PartPose.offsetAndRotation(0.1F, 6.0F, -2.0F, 1.5708F, 0.0F, 1.5708F));
		leftLeg.addOrReplaceChild("ll_bottom", CubeListBuilder.create().texOffs(56, 56).addBox(-2.0F, 12.0F, -2.0F, 4.0F, 0.0F, 4.0F), PartPose.offset(0.1F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}