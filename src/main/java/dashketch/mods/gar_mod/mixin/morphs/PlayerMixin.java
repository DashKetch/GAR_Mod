package dashketch.mods.gar_mod.mixin.morphs;

import dashketch.mods.gar_mod.global.GlobalSkins;
import dashketch.mods.gar_mod.utils.data.ModAttachments;
import dashketch.mods.gar_mod.utils.data.PlayerRankData;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.resources.PlayerSkin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.neoforged.neoforgespi.ILaunchContext.LOGGER;

@Mixin(AbstractClientPlayer.class)
public class PlayerMixin {
    @Unique
    private static volatile boolean RAIDER_LOGGED = false;

    @Inject(method = "getSkin", at = @At("HEAD"), cancellable = true)
    private void gar_mod$forceSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        AbstractClientPlayer player = (AbstractClientPlayer) (Object) this;
        PlayerRankData data = player.getData(ModAttachments.PLAYER_RANK);

        switch (data.team) {
            case "immigrant" -> {
            }
            case "republic" -> cir.setReturnValue(GlobalSkins.FLIGHT_SUIT);
            case "raider" -> {
                if (!RAIDER_LOGGED) {
                    RAIDER_LOGGED = true;
                    LOGGER.info("Player {} is on the raider team - loading their morph.", player.getName().getString());
                }
            }
        }

    }
}
