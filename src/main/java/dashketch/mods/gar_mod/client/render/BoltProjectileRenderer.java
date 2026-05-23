package dashketch.mods.gar_mod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dashketch.mods.gar_mod.client.model.projectile.blaster_bolt;
import dashketch.mods.gar_mod.entity.BoltProjectile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType; // Needed for transparency
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class BoltProjectileRenderer extends EntityRenderer<BoltProjectile> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath("gar_mod", "textures/entity/blaster_bolt.png");

    private final blaster_bolt<BoltProjectile> model;

    public BoltProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new blaster_bolt<>(context.bakeLayer(blaster_bolt.LAYER_LOCATION));
    }

    @Override
    public void render(@NotNull BoltProjectile entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        poseStack.pushPose();

        ResourceLocation textureLocation = this.getTextureLocation(entity);
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityTranslucent(this.getTextureLocation(entity), false));

        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);

        poseStack.popPose();
        super.render(entity, entityYaw, partialTick, poseStack, buffer, packedLight);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BoltProjectile entity) {
        return TEXTURE;
    }
}