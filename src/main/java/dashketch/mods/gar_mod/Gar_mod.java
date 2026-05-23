package dashketch.mods.gar_mod;

import com.mojang.logging.LogUtils;
import dashketch.mods.gar_mod.client.model.armor.*;
import dashketch.mods.gar_mod.network.ResetPayload;
import dashketch.mods.gar_mod.server.events.ResetHandler;
import dashketch.mods.gar_mod.utils.armor.ModArmorMaterials;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import static dashketch.mods.gar_mod.global.items.ModItems.*;
import static dashketch.mods.gar_mod.global.items.ModArmor.*;
import static dashketch.mods.gar_mod.global.items.ModBlocks.*;
import static dashketch.mods.gar_mod.global.registries.ModEntities.ENTITY_TYPES;

@Mod(Gar_mod.MODID)
public class Gar_mod {
    public static final String MODID = "gar_mod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GAR_ARMOR_TAB = CREATIVE_MODE_TABS.register("gar_armor_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gar_mod_armor"))
            .icon(() -> CADET_CHESTPLATE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(CADET_HELMET.get());
                output.accept(CADET_CHESTPLATE.get());
                output.accept(CADET_LEGGINGS.get());
                output.accept(CADET_BOOTS.get());
                output.accept(TROOPER_HELMET.get());
                output.accept(TROOPER_CHESTPLATE.get());
                output.accept(TROOPER_LEGGINGS.get());
                output.accept(TROOPER_BOOTS.get());
                output.accept(LANCE_BOOTS.get());
                output.accept(LANCE_LEGGINGS.get());
                output.accept(LANCE_CHESTPLATE.get());
                output.accept(LANCE_HELMET.get());
                output.accept(SERGEANT_BOOTS.get());
                output.accept(SERGEANT_LEGGINGS.get());
                output.accept(SERGEANT_CHESTPLATE.get());
                output.accept(SERGEANT_HELMET.get());
                output.accept(WO_BOOTS.get());
                output.accept(WO_LEGGINGS.get());
                output.accept(WO_CHESTPLATE.get());
                output.accept(WO_HELMET.get());
                output.accept(UWO_BOOTS.get());
                output.accept(UWO_LEGGINGS.get());
                output.accept(UWO_CHESTPLATE.get());
                output.accept(UWO_HELMET.get());
                output.accept(CWO_BOOTS.get());
                output.accept(CWO_LEGGINGS.get());
                output.accept(CWO_CHESTPLATE.get());
                output.accept(CWO_HELMET.get());
                output.accept(OFFICER_BOOTS.get());
                output.accept(OFFICER_LEGGINGS.get());
                output.accept(OFFICER_CHESTPLATE.get());
                output.accept(OFFICER_HELMET.get());
            }).build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GAR_ITEM_TAB = CREATIVE_MODE_TABS.register("gar_item_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gar_mod_items"))
            .icon(() -> BLASTER_RIFLE.get().getDefaultInstance())
            .displayItems((parameters, output) -> output.accept(BLASTER_RIFLE.get())).build());

    public Gar_mod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerPackets);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
        ENTITY_TYPES.register(modEventBus);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);
        ModAttachments.ATTACHMENT_TYPES.register(modEventBus);

        // This registers 'onServerStarting' to the FORGE bus
        NeoForge.EVENT_BUS.register(this);
    }

    private void registerPackets(final RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(MODID);
        registrar.playToServer(
                ResetPayload.TYPE,
                ResetPayload.STREAM_CODEC,
                ResetHandler::handle
        );
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        LOGGER.info("HELLO FROM COMMON SETUP");
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");
    }

    // Automatically handles MOD bus events for the Physical Client
    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");
        }

        @SubscribeEvent
        public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
            // Registering the model layers here
            event.registerLayerDefinition(cadet.LAYER_LOCATION, cadet::createBodyLayer);
            event.registerLayerDefinition(trooper.LAYER_LOCATION, trooper::createBodyLayer);
            event.registerLayerDefinition(sergeant.LAYER_LOCATION, sergeant::createBodyLayer);
            event.registerLayerDefinition(officer.LAYER_LOCATION, officer::createBodyLayer);
            event.registerLayerDefinition(lance.LAYER_LOCATION, lance::createBodyLayer);
            event.registerLayerDefinition(warrant_officer.LAYER_LOCATION, warrant_officer::createBodyLayer);
            event.registerLayerDefinition(upper_warrant_officer.LAYER_LOCATION, upper_warrant_officer::createBodyLayer);
            event.registerLayerDefinition(chief_warrant_officer.LAYER_LOCATION, chief_warrant_officer::createBodyLayer);
            LOGGER.info("GAR_MOD: Registered armor layers");
        }
    }
}