package dashketch.mods.gar_mod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.client.render.raiderAnimation;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.LivingEntity;
import org.jetbrains.annotations.NotNull;


public class raider<T extends LivingEntity> extends HierarchicalModel<T> {

	public final AnimationState walkingState = new AnimationState();
	public final AnimationState hitState = new AnimationState();

	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "raider"), "main");

	public final ModelPart Waist;
	public final ModelPart Head;
	public final ModelPart LeftArm;
	public final ModelPart RightArm;
	public final ModelPart RightLeg;
	public final ModelPart LeftLeg;
	private final ModelPart root;

	public raider(ModelPart root) {
		this.root = root;
		this.Waist = root.getChild("Waist");
		this.Head = this.Waist.getChild("Head");
		this.LeftArm = this.Waist.getChild("LeftArm");
		this.RightArm = this.Waist.getChild("RightArm");
		this.RightLeg = root.getChild("RightLeg");
		this.LeftLeg = root.getChild("LeftLeg");
	}

	public @NotNull ModelPart root() {
		return this.root;
	}

	@SuppressWarnings("unused")
    public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Waist = partdefinition.addOrReplaceChild("Waist", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));

		PartDefinition Head = Waist.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, 1.0F));

		PartDefinition cube_r1 = Head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 8).addBox(-1.0F, -3.05F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.75F, -0.5F, -1.3963F, 0.0F, 0.0F));

		PartDefinition cube_r2 = Head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 16).addBox(0.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.75F, -0.7418F, 0.0F, 0.0F));

		PartDefinition Body = Waist.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 21).addBox(1.25F, 5.0F, 2.25F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		PartDefinition cube_r3 = Body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(17, 21).addBox(0.25F, -2.5F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(17, 21).addBox(-0.25F, -2.5F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 7.5f, 2.75F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r4 = Body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(17, 21).addBox(-1.0F, -6.0F, 0.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 11.0F, 1.25F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r5 = Body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -6.0F, 0.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, 11.0F, 1.75F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r6 = Body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -4.0F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r7 = Body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, -4.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(16, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 1.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition LeftArm = Waist.addOrReplaceChild("LeftArm", CubeListBuilder.create(), PartPose.offset(2.8636F, -1.203F, 1.5F));

		PartDefinition cube_r8 = LeftArm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(4, 23).addBox(-0.5F, -0.23F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1136F, -0.297F, 0.0F, 0.0F, 1.5708F, -0.7854F));

		PartDefinition cube_r9 = LeftArm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 8).addBox(0.0F, -5.05F, 0.9F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.1136F, 4.953F, 0.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r10 = LeftArm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.3636F, -0.297F, 0.0F, 0.0F, 1.5708F, 0.0F));

		PartDefinition RightArm = Waist.addOrReplaceChild("RightArm", CubeListBuilder.create(), PartPose.offsetAndRotation(-2.8636F, -1.203F, 1.5F, 0.0F, 3.1416F, 0.0F));

		PartDefinition cube_r11 = RightArm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(12, 24).addBox(-0.5F, -7.3011F, -7.5711F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.8864F, -0.297F, 0.0F, 0.0F, 1.5708F, -0.7854F));

		PartDefinition cube_r12 = RightArm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(12, 16).addBox(0.0F, -5.05F, -9.1F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.8864F, 4.953F, 0.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition cube_r13 = RightArm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(8, 23).addBox(0.0F, -4.0F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.1364F, 3.203F, 0.5F, 0.0F, 1.5708F, 0.0F));

		PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(20, 21).addBox(-0.5F, 6.5F, -1.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 16.5F, 0.25F));

		PartDefinition cube_r14 = RightLeg.addOrReplaceChild("cube_r14", CubeListBuilder.create().texOffs(0, 8).addBox(0.0F, -1.0F, -6.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 5.5F, -0.25F, -1.5708F, 0.0F, 0.0F));

		PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(20, 13).addBox(-0.5F, 6.5F, -1.25F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 16.5F, 0.25F));

		PartDefinition cube_r15 = LeftLeg.addOrReplaceChild("cube_r15", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -6.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 5.5F, -0.25F, -1.5708F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(@NotNull T entity,
	                      float limbSwing,
	                      float limbSwingAmount,
	                      float ageInTicks,
	                      float netHeadYaw,
	                      float headPitch) {

		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.Head.yRot = netHeadYaw * ((float) Math.PI / 180F);
		this.Head.xRot = headPitch * ((float) Math.PI / 180F);
		float animationSpeed = 1.0f; // Default speed

		if (limbSwingAmount > 0.7f) { // Sprinting threshold
			this.walkingState.startIfStopped(entity.tickCount);
			animationSpeed = 2.0f; // Play 2x faster (I know sprinting is only 1.3x faster, but this looks better.)
		} else if (limbSwingAmount > 0.05f) { // Walking
			this.walkingState.startIfStopped(entity.tickCount);
			animationSpeed = 1.0f;
		} else {
			this.walkingState.stop();
		}

		if (entity.swinging) {
			this.hitState.startIfStopped(entity.tickCount);
		} else {
			this.hitState.stop();
		}

		this.animate(this.walkingState, raiderAnimation.walking, ageInTicks, animationSpeed);
		this.animate(this.hitState, raiderAnimation.hit, ageInTicks, animationSpeed);
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
