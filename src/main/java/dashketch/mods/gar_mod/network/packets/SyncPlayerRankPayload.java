package dashketch.mods.gar_mod.network.packets;

import dashketch.mods.gar_mod.Gar_mod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record SyncPlayerRankPayload(int points, int rank, int tickCounter, String team, int entityId) implements CustomPacketPayload {

    public static final Type<SyncPlayerRankPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "sync_player_rank")
    );

    public static final StreamCodec<FriendlyByteBuf, SyncPlayerRankPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_INT, SyncPlayerRankPayload::points,
            ByteBufCodecs.VAR_INT, SyncPlayerRankPayload::rank,
            ByteBufCodecs.VAR_INT, SyncPlayerRankPayload::tickCounter,
            ByteBufCodecs.STRING_UTF8, SyncPlayerRankPayload::team,
            ByteBufCodecs.INT, SyncPlayerRankPayload::entityId,
            SyncPlayerRankPayload::new
    );

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}