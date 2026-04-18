package dashketch.mods.gar_mod.utils.data;

import dashketch.mods.gar_mod.Gar_mod;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES =
            DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, Gar_mod.MODID);

    // If a player joins for the very first time, they get Rank 1, 0 Points, 0 Ticks.
    public static final Supplier<AttachmentType<PlayerRankData>> PLAYER_RANK = ATTACHMENT_TYPES.register("player_rank",
            () -> AttachmentType.builder(() -> new PlayerRankData(1, 0, 0))
                    .serialize(PlayerRankData.CODEC) // Tells it to save to the player file
                    .copyOnDeath() // Keeps data when they respawn!
                    .build());
}