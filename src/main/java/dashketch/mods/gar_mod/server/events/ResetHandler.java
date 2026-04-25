package dashketch.mods.gar_mod.server.events;

import dashketch.mods.gar_mod.Gar_mod;
import dashketch.mods.gar_mod.network.ResetPayload;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = Gar_mod.MODID)
public class ResetHandler {

    // Maps Player UUID -> (Team Name -> Saved Inventory)
    // This allows a player to have a saved "raider" inventory, "immigrant" inventory, etc.
    private static final Map<UUID, Map<String, SavedInventory>> TEAM_INVENTORIES = new ConcurrentHashMap<>();

    @SubscribeEvent
    public static void onServerStart(ServerAboutToStartEvent event) {
        // Wipe the memory so items don't leak between different worlds/saves
        TEAM_INVENTORIES.clear();
        System.out.println("GAR Mod: Cleared team inventory cache for new world session.");
    }

    public static void handle(ResetPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();
            PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);
            String currentTeam = data.team;

            // 1. Save the inventory ONLY if they are on a valid team (not "none")
            if (currentTeam != null && !currentTeam.equals("none")) {
                Inventory inv = player.getInventory();
                SavedInventory saved = new SavedInventory(
                        copyList(inv.items),
                        copyList(inv.armor),
                        copyList(inv.offhand)
                );

                // 2. Store it under their specific team name
                TEAM_INVENTORIES.computeIfAbsent(player.getUUID(), k -> new ConcurrentHashMap<>())
                        .put(currentTeam, saved);
            }

            // 3. Clear inventory and kill
            player.getInventory().clearContent();
            player.kill();
        });
    }

    // This handles the case where a player respawns and KEEPS their team through death.
    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {
        if (event.isWasDeath() && event.getEntity() instanceof ServerPlayer newPlayer) {

            // Read the team from the DEAD player, bypassing the clone-timing issue
            ServerPlayer oldPlayer = (ServerPlayer) event.getOriginal();
            String team = oldPlayer.getData(ModAttachments.PLAYER_RANK).team;

            if (team != null && !team.equals("none")) {
                restoreTeamInventory(newPlayer, team);
            }
        }
    }

    /**
     * If the players spawn as "none" and have to manually join a team,
     * call this method inside the team-joining code to give them their saved items back!
     */
    public static void restoreTeamInventory(ServerPlayer player, String teamName) {
        Map<String, SavedInventory> playerSavedData = TEAM_INVENTORIES.get(player.getUUID());

        // Clear the inventory when switching teams to prevent leaking
        player.getInventory().clearContent();

        if (playerSavedData != null) {
            SavedInventory saved = playerSavedData.get(teamName);
            if (saved != null) {
                restoreList(player.getInventory().items, saved.items);
                restoreList(player.getInventory().armor, saved.armor);
                restoreList(player.getInventory().offhand, saved.offhand);
            }
        }
    }

    private static List<ItemStack> copyList(List<ItemStack> source) {
        List<ItemStack> copy = new ArrayList<>();
        for (ItemStack stack : source) copy.add(stack.copy());
        return copy;
    }

    private static void restoreList(List<ItemStack> target, List<ItemStack> saved) {
        for (int i = 0; i < saved.size() && i < target.size(); i++) {
            target.set(i, saved.get(i));
        }
    }

    private record SavedInventory(List<ItemStack> items, List<ItemStack> armor, List<ItemStack> offhand) {}
}