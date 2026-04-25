package dashketch.mods.gar_mod.network;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.server.events.ResetHandler;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.MOD)
public class ModNetworking {

    // --- 1. THE PACKETS (PAYLOADS) ---

    // Packet from Client -> Server (I chose a team!)
    public record SelectTeamPayload(String team) implements CustomPacketPayload {
        public static final Type<SelectTeamPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "select_team"));
        public static final StreamCodec<ByteBuf, SelectTeamPayload> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, SelectTeamPayload::team, SelectTeamPayload::new
        );
        @Override public @NotNull Type<? extends CustomPacketPayload> type() { return TYPE; }
    }

    // Packet from Server -> Client (Hey everyone, this player changed teams!)
    public record SyncTeamPayload(int entityId, String team) implements CustomPacketPayload {
        public static final Type<SyncTeamPayload> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "sync_team"));
        public static final StreamCodec<ByteBuf, SyncTeamPayload> STREAM_CODEC = StreamCodec.composite(
                ByteBufCodecs.INT, SyncTeamPayload::entityId,
                ByteBufCodecs.STRING_UTF8, SyncTeamPayload::team,
                SyncTeamPayload::new
        );
        @Override public @NotNull Type<? extends CustomPacketPayload> type() { return TYPE; }
    }


    // --- 2. REGISTERING THE PACKETS ---

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar(Gar_mod.MODID);

        registrar.playToServer(SelectTeamPayload.TYPE, SelectTeamPayload.STREAM_CODEC, ModNetworking::handleSelectTeam);
        registrar.playToClient(SyncTeamPayload.TYPE, SyncTeamPayload.STREAM_CODEC, ModNetworking::handleSyncTeam);
    }


    // --- 3. HANDLING THE LOGIC ---

    // Server receives team choice from GUI
    public static void handleSelectTeam(SelectTeamPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player player = context.player();
            if (player instanceof ServerPlayer serverPlayer) {
                // 1. Get the NEW team from the packet
                String newTeam = payload.team();

                // DEBUG: See what's happening in your console!
                System.out.println("Switching " + serverPlayer.getName().getString() + " to team: " + newTeam);

                // 2. Update the data attachment FIRST
                PlayerRankData oldData = player.getData(ModAttachments.PLAYER_RANK);
                player.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(oldData.rank, oldData.points, oldData.tickCounter, newTeam));

                // 3. Force the inventory clear/restore based on the NEW team name explicitly
                // We pass 'newTeam' directly to ensure it doesn't use the old data
                ResetHandler.restoreTeamInventory(serverPlayer, newTeam);

                // 4. Sync
                PacketDistributor.sendToPlayersTrackingEntityAndSelf(serverPlayer, new SyncTeamPayload(serverPlayer.getId(), newTeam));
            }
        });
    }

    // Client receives sync data from Server
    public static void handleSyncTeam(SyncTeamPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            Player clientPlayer = context.player();
            if (clientPlayer != null && clientPlayer.level() != null) {
                // Find the player in the world that this packet is talking about
                Entity entity = clientPlayer.level().getEntity(payload.entityId());
                if (entity instanceof Player targetPlayer) {
                    PlayerRankData oldData = targetPlayer.getData(ModAttachments.PLAYER_RANK);
                    // Update their data locally so the Mixins and Models can see it
                    targetPlayer.setData(ModAttachments.PLAYER_RANK, new PlayerRankData(oldData.rank, oldData.points, oldData.tickCounter, payload.team()));
                }
            }
        });
    }
}