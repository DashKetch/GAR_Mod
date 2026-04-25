package dashketch.mods.gar_mod.network;

import dashketch.mods.gar_mod.Gar_mod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public record ResetPayload() implements CustomPacketPayload {
    public static final Type<ResetPayload> TYPE = new Type<>(
            ResourceLocation.fromNamespaceAndPath(Gar_mod.MODID, "reset")
    );
    public static final StreamCodec<FriendlyByteBuf, ResetPayload> STREAM_CODEC =
            StreamCodec.unit(new ResetPayload());

    @Override
    public @NotNull Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}