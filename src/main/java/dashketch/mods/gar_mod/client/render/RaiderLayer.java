package dashketch.mods.gar_mod.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
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
import org.jetbrains.annotations.NotNull;

public class RaiderLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {

    private final raider<AbstractClientPlayer> raider;
    private static final ResourceLocation RAIDER_TEXTURE = ResourceLocation.fromNamespaceAndPath("gar_mod", "textures/player/entity/raider_moprh_texture.png");

    public RaiderLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderer, EntityModelSet modelSet) {
        super(renderer);
        this.raider = new raider<>(modelSet.bakeLayer(dashketch.mods.gar_mod.client.model.raider.LAYER_LOCATION)) {};
    }

    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTick, float ageInTicks, float netHeadYaw, float headPitch) {
        // 1. Get the player's data
        PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);

        // 2. If they are NOT a Raider, do nothing and let the normal skin render
        if (!data.team.equals("raider")) {
            return;
        }

        // 3. If they ARE a Raider, hide the standard Minecraft player body
        this.getParentModel().setAllVisible(false);

        // 4. Sync animations (so the custom model walks and looks around properly)
        this.raider.setupAnim(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);

        // 5. Render the custom Raider model
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(RAIDER_TEXTURE));
        this.raider.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF);
    }
}