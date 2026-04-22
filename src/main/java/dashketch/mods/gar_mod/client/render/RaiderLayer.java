package dashketch.mods.gar_mod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.client.model.raider;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RaiderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private final raider<AbstractClientPlayer> droidModel;
    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "textures/entity/raider.png");

    public RaiderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer,
                       EntityModelSet modelSet) {
        super(renderer);
        this.droidModel = new raider<>(modelSet.bakeLayer(raider.LAYER_LOCATION));
    }

    @Override
    public void render(PoseStack poseStack,
                       MultiBufferSource buffer,
                       int packedLight,
                       AbstractClientPlayer player,
                       float limbSwing,
                       float limbSwingAmount,
                       float partialTick,
                       float ageInTicks,
                       float netHeadYaw,
                       float headPitch) {

        PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);

        if ("raider".equals(data.team)) {
            this.getParentModel().setAllVisible(false);

            poseStack.pushPose();

            // 1. Reset any weird player rotations
            poseStack.mulPose(Axis.XP.rotationDegrees(0));

            // 2. Scale the droid (B1s are tall!)
            float scale = 1.2F;
            poseStack.scale(scale, scale, scale);

            // 3. IMPORTANT: If he's still in the ground, adjust this Y value.
            // Positive Y moves DOWN, Negative Y moves UP.
            poseStack.translate(0.0, -1.5, 0.0);

            this.droidModel.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

            VertexConsumer vc = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
            this.droidModel.renderToBuffer(poseStack, vc, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

            poseStack.popPose();
        } else {
            this.getParentModel().setAllVisible(true);
        }
    }
}
