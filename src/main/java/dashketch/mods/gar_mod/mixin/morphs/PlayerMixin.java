package dashketch.mods.gar_mod.mixin.morphs;

import dashketch.mods.gar_mod.global.GlobalSkins;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.resources.PlayerSkin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.neoforged.neoforgespi.ILaunchContext.LOGGER;

@Mixin(AbstractClientPlayer.class)
public class PlayerMixin {

    @Inject(method = "getSkin", at = @At("HEAD"), cancellable = true)
    private void gar_mod$forceSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        // 1. Cast "this" to a player to get their data
        AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;

        // 2. Grab the data attachment
        PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);

        // 3. If they are an Immigrant, do NOTHING (let standard skin load)
        if (data.team.equals("immigrant")) {
            return;
        }

        // 4. If they are Republic, force the skin
        if (data.team.equals("republic")) {
            cir.setReturnValue(GlobalSkins.FLIGHT_SUIT);
        }

        if (data.team.equals("raider")) {
            LOGGER.info("Player {} is on the raider team - loading their morph.", player.getName().getString());
        }
    }
}