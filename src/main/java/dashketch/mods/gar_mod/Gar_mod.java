package dashketch.mods.gar_mod;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import dashketch.mods.gar_mod.client.model.cadet;
import dashketch.mods.gar_mod.morphs.GarArmorItem;
import dashketch.mods.gar_mod.utils.armor.ModArmorMaterials;
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
import net.neoforged.neoforge.registries.*;
import org.slf4j.Logger;

import java.util.function.Supplier;

@Mod(Gar_mod.MODID)
public class Gar_mod {
    public static final String MODID = "gar_mod";
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredItem<Item> CADET_HELMET = ITEMS.registerItem("cadet_helmet",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.HELMET, properties.stacksTo(1)));

    public static final DeferredItem<Item> CADET_CHESTPLATE = ITEMS.registerItem("cadet_chestplate",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.CHESTPLATE, properties.stacksTo(1)));

    public static final DeferredItem<Item> CADET_LEGGINGS = ITEMS.registerItem("cadet_leggings",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.LEGGINGS, properties.stacksTo(1)));

    public static final DeferredItem<Item> CADET_BOOTS = ITEMS.registerItem("cadet_boots",
            properties -> new GarArmorItem(ModArmorMaterials.CADET, ArmorItem.Type.BOOTS, properties.stacksTo(1)));

    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, MODID);

    public static final Supplier<AttachmentType<Integer>> SKIN_TYPE = ATTACHMENT_TYPES.register("skin_type", () ->
            AttachmentType.builder(() -> 0).serialize(Codec.INT).copyOnDeath().build());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> GAR_TAB = CREATIVE_MODE_TABS.register("gar_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.gar_mod"))
            .icon(() -> CADET_CHESTPLATE.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(CADET_HELMET.get());
                output.accept(CADET_CHESTPLATE.get());
                output.accept(CADET_LEGGINGS.get());
                output.accept(CADET_BOOTS.get());
            }).build());

    public Gar_mod(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        BLOCKS.register(modEventBus);
        ITEMS.register(modEventBus);
        CREATIVE_MODE_TABS.register(modEventBus);
        ATTACHMENT_TYPES.register(modEventBus);
        ModArmorMaterials.ARMOR_MATERIALS.register(modEventBus);

        // This registers 'onServerStarting' to the FORGE bus
        NeoForge.EVENT_BUS.register(this);
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
            // Registering the cadet model layer here
            event.registerLayerDefinition(cadet.LAYER_LOCATION, cadet::createBodyLayer);
            LOGGER.info("GAR_MOD: Registered cadet armor layer");
        }
    }
}