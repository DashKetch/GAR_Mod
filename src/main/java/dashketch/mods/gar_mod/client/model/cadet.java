package dashketch.mods.gar_mod.client.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class cadet<T extends LivingEntity> extends HumanoidModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("gar_mod", "cadet"), "main");

	public cadet(ModelPart root) {
		super(root);
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
		partdefinition.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition rightArm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition leftArm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition rightLeg = partdefinition.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
		PartDefinition leftLeg = partdefinition.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

		head.addOrReplaceChild("helmet_top", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(32, 16).addBox(-4.0F, 4.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)).texOffs(32, 24).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 0.0F, 0.0F, 1.5708F, 1.5708F));
		head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, -4.0F, -1.5708F, 0.0F, 0.0F));
		head.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(0, 24).addBox(-4.0F, 0.0F, -4.0F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.0F, 4.0F, -1.5708F, 0.0F, 0.0F));

		body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(0, 8).addBox(-9.0F, -2.0F, -1.0F, 12.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 9.0F, -4.0F, -1.5708F, 0.0F, 1.5708F));
		body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-9.0F, -2.0F, -1.0F, 12.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 9.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));

		rightArm.addOrReplaceChild("right_arm_base", CubeListBuilder.create().texOffs(0, 52).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(32, 52).addBox(-3.0F, 10.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		rightArm.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(40, 8).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
		rightArm.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(40, 4).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 2.0F, -2.0F, 1.5708F, 0.0F, 1.5708F));

		leftArm.addOrReplaceChild("left_arm_base", CubeListBuilder.create().texOffs(16, 52).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(48, 52).addBox(-1.0F, 10.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		leftArm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(0, 40).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));
		leftArm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(32, 36).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -2.0F, 1.5708F, 0.0F, 1.5708F));
		leftArm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(32, 32).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 1.5708F));

		rightLeg.addOrReplaceChild("right_leg_base", CubeListBuilder.create().texOffs(0, 56).addBox(-2.1F, 0.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.ZERO);
		rightLeg.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(0, 44).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.1F, 4.0F, 0.0F, 3.1416F, 0.0F, 1.5708F));
		rightLeg.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(32, 40).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 4.0F, -2.0F, 1.5708F, 0.0F, 1.5708F));
		rightLeg.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(40, 12).addBox(-4.0F, 0.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 4.0F, 2.0F, 1.5708F, 0.0F, 1.5708F));

		leftLeg.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(32, 48).addBox(-9.0F, -1.0F, -2.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 9.0F, 0.0F, 0.0F, 0.0F, 1.5708F));
		leftLeg.addOrReplaceChild("cube_r16", CubeListBuilder.create().texOffs(0, 48).addBox(-9.0F, -2.0F, -1.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(32, 44).addBox(-9.0F, 2.0F, -1.0F, 12.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 9.0F, 0.0F, -1.5708F, 0.0F, 1.5708F));
		leftLeg.addOrReplaceChild("cube_r17", CubeListBuilder.create().texOffs(16, 56).addBox(-1.0F, 3.0F, -2.0F, 4.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.1F, 9.0F, 0.0F, -3.1416F, 0.0F, 3.1416F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}
}