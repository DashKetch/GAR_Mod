package dashketch.mods.gar_mod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dashketch.mods.gar_mod.Gar_mod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;

//TODO: PLAY THE GAME, SEE THE PROBLEM, AND FIX THE RAIDER MODEL

public class raider<T extends LivingEntity> extends EntityModel<T> {

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "raider"), "main");

	public final ModelPart Waist;
	public final ModelPart Head;
	public final ModelPart LeftArm;
	public final ModelPart RightArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;

	public raider(ModelPart root) {
		this.Waist = root.getChild("Waist");
		this.Head = this.Waist.getChild("Head");
		this.LeftArm = this.Waist.getChild("LeftArm");
		this.RightArm = this.Waist.getChild("RightArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Waist = partdefinition.addOrReplaceChild(
				"Waist",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, 12.0F, 0.0F)
		);

		PartDefinition Head = Waist.addOrReplaceChild(
				"Head",
				CubeListBuilder.create(),
				PartPose.offset(0.0F, -2.0F, 1.0F)
		);

		Head.addOrReplaceChild(
				"cube_r1",
				CubeListBuilder.create()
						.texOffs(20, 8)
						.addBox(-1.0F, -3.05F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, -2.75F, -0.5F, -1.3963F, 0.0F, 0.0F)
		);

		Head.addOrReplaceChild(
				"cube_r2",
				CubeListBuilder.create()
						.texOffs(22, 16)
						.addBox(0.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.5F, 0.0F, -0.75F, -0.7418F, 0.0F, 0.0F)
		);

		PartDefinition Body = Waist.addOrReplaceChild(
				"Body",
				CubeListBuilder.create()
						.texOffs(16, 21)
						.addBox(1.25F, 5.0F, 2.25F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, -12.0F, 0.0F)
		);

		Body.addOrReplaceChild(
				"cube_r3",
				CubeListBuilder.create()
						.texOffs(17, 21)
						.addBox(0.25F, -2.5F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
						.texOffs(17, 21)
						.addBox(-0.25F, -2.5F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.5F, 7.5F, 2.75F, 0.0F, 3.1416F, 0.0F)
		);

		Body.addOrReplaceChild(
				"cube_r4",
				CubeListBuilder.create()
						.texOffs(17, 21)
						.addBox(-1.0F, -6.0F, 0.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.75F, 11.0F, 1.25F, 0.0F, 1.5708F, 0.0F)
		);

		Body.addOrReplaceChild(
				"cube_r5",
				CubeListBuilder.create()
						.texOffs(16, 21)
						.addBox(-1.0F, -6.0F, 0.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(1.25F, 11.0F, 1.75F, 0.0F, 1.5708F, 0.0F)
		);

		Body.addOrReplaceChild(
				"cube_r6",
				CubeListBuilder.create()
						.texOffs(0, 16)
						.addBox(-1.0F, -4.0F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 0.0F, 1.5708F, 0.0F)
		);

		Body.addOrReplaceChild(
				"cube_r7",
				CubeListBuilder.create()
						.texOffs(16, 0)
						.addBox(0.0F, -4.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
						.texOffs(16, 16)
						.addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(0.0F, 14.0F, 1.0F, 0.0F, 1.5708F, 0.0F)
		);

		PartDefinition LeftArm = Waist.addOrReplaceChild(
				"LeftArm",
				CubeListBuilder.create(),
				PartPose.offset(2.8636F, -1.203F, 1.5F)
		);

		LeftArm.addOrReplaceChild(
				"cube_r8",
				CubeListBuilder.create()
						.texOffs(4, 23)
						.addBox(-0.5F, -0.23F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.1136F, -0.297F, 0.0F, 0.0F, 1.5708F, -0.7854F)
		);

		LeftArm.addOrReplaceChild(
				"cube_r9",
				CubeListBuilder.create()
						.texOffs(16, 8)
						.addBox(0.0F, -5.05F, 0.9F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-1.1136F, 4.953F, 0.5F, 0.0F, 1.5708F, 0.0F)
		);

		LeftArm.addOrReplaceChild(
				"cube_r10",
				CubeListBuilder.create()
						.texOffs(0, 23)
						.addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-0.3636F, -0.297F, 0.0F, 0.0F, 1.5708F, 0.0F)
		);

		PartDefinition RightArm = Waist.addOrReplaceChild(
				"RightArm",
				CubeListBuilder.create(),
				PartPose.offsetAndRotation(-2.8636F, -1.203F, 1.5F, 0.0F, 3.1416F, 0.0F)
		);

		RightArm.addOrReplaceChild(
				"cube_r11",
				CubeListBuilder.create()
						.texOffs(12, 24)
						.addBox(-0.5F, -7.3011F, -7.5711F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(9.8864F, -0.297F, 0.0F, 0.0F, 1.5708F, -0.7854F)
		);

		RightArm.addOrReplaceChild(
				"cube_r12",
				CubeListBuilder.create()
						.texOffs(12, 16)
						.addBox(0.0F, -5.05F, -9.1F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.8864F, 4.953F, 0.5F, 0.0F, 1.5708F, 0.0F)
		);

		RightArm.addOrReplaceChild(
				"cube_r13",
				CubeListBuilder.create()
						.texOffs(8, 23)
						.addBox(0.0F, -4.0F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(8.1364F, 3.203F, 0.5F, 0.0F, 1.5708F, 0.0F)
		);

		partdefinition.addOrReplaceChild(
				"RightLeg",
				CubeListBuilder.create()
						.texOffs(20, 21)
						.addBox(-0.5F, 0.0F, -1.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(-1.5F, 12.0F, 0.25F)
		);

		partdefinition.addOrReplaceChild(
				"LeftLeg",
				CubeListBuilder.create()
						.texOffs(20, 13)
						.addBox(-0.5F, 0.0F, -1.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
				PartPose.offset(1.5F, 12.0F, 0.25F)
		);

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(@NotNull T entity,
	                      float limbSwing,
	                      float limbSwingAmount,
	                      float ageInTicks,
	                      float netHeadYaw,
	                      float headPitch) {

		this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.Head.xRot = headPitch * ((float) Math.PI / 180F);

		this.RightLeg.xRot = Mth.cos(limbSwing * 0.6662F) * 1.0F * limbSwingAmount;
		this.LeftLeg.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 1.0F * limbSwingAmount;
		this.RightArm.xRot = Mth.cos(limbSwing * 0.6662F + (float) Math.PI) * 0.8F * limbSwingAmount;
		this.LeftArm.xRot = Mth.cos(limbSwing * 0.6662F) * 0.8F * limbSwingAmount;
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack,
	                           @NotNull VertexConsumer vertexConsumer,
	                           int packedLight,
	                           int packedOverlay,
	                           int color) {
		this.Waist.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		this.RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
		this.LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}
}
