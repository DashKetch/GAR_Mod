package dashketch.mods.gar_mod;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import dashketch.mods.gar_mod.client.model.cadet;
import dashketch.mods.gar_mod.client.model.lance;
import dashketch.mods.gar_mod.client.model.officer;
import dashketch.mods.gar_mod.client.model.trooper;
import dashketch.mods.gar_mod.global.GlobalMorphs;
import dashketch.mods.gar_mod.network.ResetPayload;
import dashketch.mods.gar_mod.server.events.ResetHandler;
import dashketch.mods.gar_mod.utils.armor.GarArmorItem;
import dashketch.mods.gar_mod.utils.armor.ModArmorMaterials;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(Gar_mod.MODID)
public class Gar_mod {
    public static final String MODID = "gar_mod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredItem<Item> CADET_HELMET = ITEMS.registerItem("cadet_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.CADET));

    public static final DeferredItem<Item> CADET_CHESTPLATE = ITEMS.registerItem("cadet_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.CADET));

    public static final DeferredItem<Item> CADET_LEGGINGS = ITEMS.registerItem("cadet_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.CADET));

    public static final DeferredItem<Item> CADET_BOOTS = ITEMS.registerItem("cadet_boots",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.CADET));

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);

    public static final Supplier<AttachmentType<Integer>> SKIN_TYPE = ATTACHMENT_TYPES.register("skin_type", () ->
            AttachmentType.builder(() -> 0).serialize(Codec.INT).copyOnDeath().build());

    public static final DeferredItem<Item> TROOPER_HELMET = ITEMS.registerItem("trooper_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.TROOPER, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.TROOPER));

    public static final DeferredItem<Item> TROOPER_CHESTPLATE = ITEMS.registerItem("trooper_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.TROOPER, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.TROOPER));

    public static final DeferredItem<Item> TROOPER_LEGGINGS = ITEMS.registerItem("trooper_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.TROOPER, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.TROOPER));

    public static final DeferredItem<Item> TROOPER_BOOTS = ITEMS.registerItem("trooper_boots",
            properties -> new GarArmorItem(ModArmorMaterials.TROOPER, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.TROOPER));

    public static final DeferredItem<Item> LANCE_HELMET = ITEMS.registerItem("lance_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.LANCE, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.LANCE));

    public static final DeferredItem<Item> LANCE_CHESTPLATE = ITEMS.registerItem("lance_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.LANCE, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.LANCE));

    public static final DeferredItem<Item> LANCE_LEGGINGS = ITEMS.registerItem("lance_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.LANCE, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.LANCE));

    public static final DeferredItem<Item> LANCE_BOOTS = ITEMS.registerItem("lance_boots",
            properties -> new GarArmorItem(ModArmorMaterials.LANCE, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.LANCE));

    public static final DeferredItem<Item> OFFICER_HELMET = ITEMS.registerItem("officer_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.OFFICER, ArmorItem.Type.HELMET, properties.stacksTo(1), GlobalMorphs.ArmorType.OFFICER));

    public static final DeferredItem<Item> OFFICER_CHESTPLATE = ITEMS.registerItem("officer_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.OFFICER, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1), GlobalMorphs.ArmorType.OFFICER));

    public static final DeferredItem<Item> OFFICER_LEGGINGS = ITEMS.registerItem("officer_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.OFFICER, ArmorItem.Type.LEGGINGS, properties.stacksTo(1), GlobalMorphs.ArmorType.OFFICER));

    public static final DeferredItem<Item> OFFICER_BOOTS = ITEMS.registerItem("officer_boots",
            properties -> new GarArmorItem(ModArmorMaterials.OFFICER, ArmorItem.Type.BOOTS, properties.stacksTo(1), GlobalMorphs.ArmorType.OFFICER));


    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GAR_TAB = CREATIVE_MODE_TABS.register("gar_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gar_mod"))
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
                output.accept( OFFICER_BOOTS.get());
                output.accept( OFFICER_LEGGINGS.get());
                output.accept( OFFICER_CHESTPLATE.get());
                output.accept( OFFICER_HELMET.get());
            }).build());

    public Gar_mod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerPackets);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
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
            event.registerLayerDefinition(lance.LAYER_LOCATION, lance::createBodyLayer);
            event.registerLayerDefinition(officer.LAYER_LOCATION, officer::createBodyLayer);
            LOGGER.info("GAR_MOD: Registered armor layers");
        }
    }
}