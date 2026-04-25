package dashketch.mods.gar_mod.server.events;

import dashketch.mods.gar_mod.global.GlobalBools;
import dashketch.mods.gar_mod.network.ResetPayload;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.util.ArrayList;
import java.util.List;

public class ResetHandler {

    public static void handle(ResetPayload payload, IPayloadContext context) {
        context.enqueueWork(() -> {
            ServerPlayer player = (ServerPlayer) context.player();

            // Snapshot inventory
            Inventory inv = player.getInventory();
            List<ItemStack> savedItems   = copyList(inv.items);
            List<ItemStack> savedArmor   = copyList(inv.armor);
            List<ItemStack> savedOffhand = copyList(inv.offhand);

            // Temporarily enable keepInventory
            GameRules rules = player.level().getGameRules();
            boolean previous = rules.getRule(GameRules.RULE_KEEPINVENTORY).get();
            rules.getRule(GameRules.RULE_KEEPINVENTORY).set(true, player.server);

            player.kill();

            // Restore gamerule
            rules.getRule(GameRules.RULE_KEEPINVENTORY).set(previous, player.server);

            // Clear and restore
            player.getInventory().clearContent();
            restoreList(player.getInventory().items,   savedItems);
            restoreList(player.getInventory().offhand, savedOffhand);

            if (GlobalBools.isImmigrant(player) || GlobalBools.isRaider(player)) {
                restoreList(player.getInventory().armor, savedArmor);
            }
        });
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

    //todo: check if the keybind works as intended
}
