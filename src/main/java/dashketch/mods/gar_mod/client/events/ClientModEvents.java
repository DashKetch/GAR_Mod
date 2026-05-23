package dashketch.mods.gar_mod.client.events;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.client.model.morph.raider;
import dashketch.mods.gar_mod.client.model.projectile.blaster_bolt;
import dashketch.mods.gar_mod.client.render.BoltProjectileRenderer;
import dashketch.mods.gar_mod.client.render.RaiderLayer;
import dashketch.mods.gar_mod.global.registries.ModEntities;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ModEntities.BLASTER_BOLT.get(), BoltProjectileRenderer::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(raider.LAYER_LOCATION, raider::createBodyLayer);

        event.registerLayerDefinition(blaster_bolt.LAYER_LOCATION, blaster_bolt::createBodyLayer);
    }

    @SubscribeEvent
    public static void onAddLayers(EntityRenderersEvent.AddLayers event) {
        for (PlayerSkin.Model model : PlayerSkin.Model.values()) {
            PlayerRenderer renderer = event.getSkin(model);
            if (renderer != null) {
                renderer.addLayer(new RaiderLayer(renderer, event.getEntityModels()));
            }
        }
    }
}