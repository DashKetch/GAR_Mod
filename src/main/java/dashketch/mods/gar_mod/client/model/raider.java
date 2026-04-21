package dashketch.mods.gar_mod.client.model;

import net.minecraft.client.animation.AnimationChannel;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.Keyframe;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

// 1. Extend HierarchicalModel instead of EntityModel
@SuppressWarnings("unused")
public class raider<T extends Entity> extends HierarchicalModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath("gar_mod", "raider"), "main");

	// The Walk Animation Definition
	public static final AnimationDefinition WALK = AnimationDefinition.Builder.withLength(1.0F)
			.addAnimation("Head", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			// ... (Position & Scale removed for brevity if they are 0/1, but left here as you provided)
			.addAnimation("Head", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Head", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Body", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			// NOTE: Changed "Left Arm" to "Left_Arm" to match your model parts below!
			.addAnimation("Left_Arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Left_Arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Left_Arm", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Right_Arm", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(35.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Right_Arm", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Right_Arm", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Right_Leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Right_Leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Right_Leg", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Left_Leg", new AnimationChannel(AnimationChannel.Targets.ROTATION,
					new Keyframe(0.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.3333F, KeyframeAnimations.degreeVec(12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(0.6667F, KeyframeAnimations.degreeVec(-12.5F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR),
					new Keyframe(1.0F, KeyframeAnimations.degreeVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Left_Leg", new AnimationChannel(AnimationChannel.Targets.POSITION,
					new Keyframe(0.0F, KeyframeAnimations.posVec(0.0F, 0.0F, 0.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.addAnimation("Left_Leg", new AnimationChannel(AnimationChannel.Targets.SCALE,
					new Keyframe(0.0F, KeyframeAnimations.scaleVec(1.0F, 1.0F, 1.0F), AnimationChannel.Interpolations.LINEAR)
			))
			.build();

	private final ModelPart root;
	private final ModelPart waist;
	private final ModelPart head;
	private final ModelPart body;
	private final ModelPart rightArm;
	private final ModelPart leftArm;
	private final ModelPart rightLeg;
	private final ModelPart leftLeg;

	public raider(ModelPart root) {
		this.root = root; // Store the root part for HierarchicalModel
		this.waist = root.getChild("Waist");
		this.head = this.waist.getChild("Head");
		this.body = this.waist.getChild("Body");
		this.rightArm = this.waist.getChild("Right_Arm");
		this.leftArm = this.waist.getChild("Left_Arm");
		this.rightLeg = root.getChild("Right_Leg");
		this.leftLeg = root.getChild("Left_Leg");
	}

	// 2. We must provide the root part to the HierarchicalModel
	@Override
	public @NotNull ModelPart root() {
		return this.root;
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		// (Your existing layout code stays exactly the same)
		PartDefinition waist = partdefinition.addOrReplaceChild("Waist", CubeListBuilder.create(), PartPose.offset(0.0F, 12.0F, 0.0F));
		PartDefinition head = waist.addOrReplaceChild("Head", CubeListBuilder.create(), PartPose.offset(0.0F, -12.0F, 0.0F));
		head.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(20, 8).addBox(-1.0F, -3.05F, 0.0F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.25F, 0.5F, -1.3963F, 0.0F, 0.0F));
		head.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(22, 16).addBox(0.0F, -4.0F, 0.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.5F, 10.0F, 0.25F, -0.7418F, 0.0F, 0.0F));
		PartDefinition body = waist.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(16, 21).addBox(1.25F, 5.0F, 2.25F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));
		body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(17, 21).addBox(0.25F, -2.5F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)).texOffs(17, 21).addBox(-0.25F, -2.5F, -0.5F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 7.5F, 2.75F, 0.0F, 3.1416F, 0.0F));
		body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(17, 21).addBox(-1.0F, -6.0F, 0.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.75F, 11.0F, 1.25F, 0.0F, 1.5708F, 0.0F));
		body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -6.0F, 0.0F, 0.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.25F, 11.0F, 1.75F, 0.0F, 1.5708F, 0.0F));
		body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 16).addBox(-1.0F, -4.0F, -2.0F, 2.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 2.0F, 0.0F, 1.5708F, 0.0F));
		body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(16, 0).addBox(0.0F, -4.0F, -2.0F, 1.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).texOffs(16, 16).addBox(0.0F, 0.0F, -1.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 14.0F, 1.0F, 0.0F, 1.5708F, 0.0F));
		PartDefinition rightArm = waist.addOrReplaceChild("Right_Arm", CubeListBuilder.create(), PartPose.offset(4.0F, -10.0F, 0.0F));
		rightArm.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(4, 23).addBox(-0.5F, -0.23F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.25F, 8.5F, 1.5F, 0.0F, 1.5708F, -0.7854F));
		rightArm.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 8).addBox(0.0F, -5.05F, 0.9F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.25F, 13.75F, 2.0F, 0.0F, 1.5708F, 0.0F));
		rightArm.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(0, 23).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 8.5F, 1.5F, 0.0F, 1.5708F, 0.0F));
		PartDefinition leftArm = waist.addOrReplaceChild("Left_Arm", CubeListBuilder.create(), PartPose.offsetAndRotation(-5.0F, -10.0F, 1.0F, 0.0F, 3.1416F, 0.0F));
		leftArm.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(12, 24).addBox(-0.5F, -7.3011F, -7.5711F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.75F, 8.5F, -0.5F, 0.0F, 1.5708F, -0.7854F));
		leftArm.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(12, 16).addBox(0.0F, -5.05F, -9.1F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, 13.75F, 0.0F, 0.0F, 1.5708F, 0.0F));
		leftArm.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(8, 23).addBox(0.0F, -4.0F, -9.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, 12.0F, 0.0F, 0.0F, 1.5708F, 0.0F));
		partdefinition.addOrReplaceChild("Right_Leg", CubeListBuilder.create().texOffs(20, 21).addBox(-0.1F, 11.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 12.0F, 0.0F));
		partdefinition.addOrReplaceChild("Left_Leg", CubeListBuilder.create().texOffs(20, 13).addBox(-0.9F, 11.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.9F, 12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(@NotNull T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		// IMPORTANT: Reset all poses first, so the animation doesn't stack on itself and twist up!
		this.root().getAllParts().forEach(ModelPart::resetPose);

		// This allows the model's head to look up, down, left, and right with your mouse
		this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
		this.head.xRot = headPitch * ((float)Math.PI / 180F);

		// 3. Play the Walk Animation!
		// The last two numbers (2.0f, 2.5f) control animation speed and intensity. Tweak as needed!
		this.animateWalk(WALK, limbSwing, limbSwingAmount, 2.0f, 2.5f);
	}
}