package dashketch.mods.gar_mod.network.packets;

import dashketch.mods.gar_mod.Gar_mod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record ToggleSafetyPayload() implements CustomPacketPayload {

    public static void handleSafetyToggle(ToggleSafetyPayload payload, net.neoforged.neoforge.network.handling.IPayloadContext context) {
        context.enqueueWork(() -> {
            net.minecraft.world.entity.player.Player player = context.player();
            if (player == null) return;

            // 1. Get the current item in the main hand
            net.minecraft.world.item.ItemStack currentStack = player.getMainHandItem();
            net.minecraft.world.item.Item currentItem = currentStack.getItem();

            // 2. Determine the replacement item
            net.minecraft.world.item.Item newItem;
            if (currentItem == dashketch.mods.gar_mod.global.items.ModItems.BLASTER_RIFLE.get()) {
                newItem = dashketch.mods.gar_mod.global.items.ModItems.BLASTER_RIFLE_SAFETY.get();
            } else if (currentItem == dashketch.mods.gar_mod.global.items.ModItems.BLASTER_RIFLE_SAFETY.get()) {
                newItem = dashketch.mods.gar_mod.global.items.ModItems.BLASTER_RIFLE.get();
            } else {
                return; // Player is not holding either version, return
            }

            // 3. Create the new item stack
            net.minecraft.world.item.ItemStack newStack = new net.minecraft.world.item.ItemStack(newItem);

            // 4. Copy any existing NBT/Data Components
            if (currentStack.getComponents() != null) {
                newStack.applyComponents(currentStack.getComponentsPatch());
            }

            // 5. Update the player's hand on the server
            player.setItemInHand(net.minecraft.world.InteractionHand.MAIN_HAND, newStack);

            // 6. FORCE packet sync to the client
            if (player instanceof net.minecraft.server.level.ServerPlayer serverPlayer) {
                // Tell the server container tracking system to look for updates
                serverPlayer.containerMenu.broadcastChanges();

                // Get the absolute slot ID of the player's main hand selection
                int slotId = serverPlayer.getInventory().selected + net.minecraft.world.entity.player.Inventory.getSelectionSize();

                // Send a direct vanilla packet forcing the client UI to update that specific slot
                serverPlayer.connection.send(new net.minecraft.network.protocol.game.ClientboundContainerSetSlotPacket(
                        serverPlayer.containerMenu.containerId,
                        serverPlayer.containerMenu.incrementStateId(),
                        slotId,
                        newStack
                ));
            }
        });
    }

    public static final CustomPacketPayload.Type<ToggleSafetyPayload> TYPE =
            new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "toggle_safety"));

    public static final StreamCodec<FriendlyByteBuf, ToggleSafetyPayload> CODEC =
            StreamCodec.unit(new ToggleSafetyPayload());

    @Override
    public CustomPacketPayload.@NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
