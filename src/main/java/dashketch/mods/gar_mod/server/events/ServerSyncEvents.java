package dashketch.mods.gar_mod.server.events;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.network.ModNetworking;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(modid = Gar_mod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ServerSyncEvents {

    // When the player logs in, send them their own data
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);
            PacketDistributor.sendToPlayer(player, new ModNetworking.SyncTeamPayload(player.getId(), data.team));
        }
    }

    // When a player loads into view of another player, sync their data to the viewer
    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        if (event.getTarget() instanceof Player targetPlayer && event.getEntity() instanceof ServerPlayer observingPlayer) {
            PlayerRankData data = targetPlayer.getData(ModAttachments.PLAYER_RANK);
            PacketDistributor.sendToPlayer(observingPlayer, new ModNetworking.SyncTeamPayload(targetPlayer.getId(), data.team));
        }
    }
}