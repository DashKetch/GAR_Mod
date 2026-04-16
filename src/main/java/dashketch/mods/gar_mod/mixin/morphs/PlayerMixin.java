package dashketch.mods.gar_mod.mixin.morphs;

import dashketch.mods.gar_mod.global.GlobalSkins;
import net.minecraft.client.resources.PlayerSkin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minecraft.client.player.AbstractClientPlayer.class)
public class PlayerMixin {
    @Inject(method = "getSkin", at = @At("HEAD"), cancellable = true)
    private void gar_mod$forceSkin(CallbackInfoReturnable<PlayerSkin> cir) {
        cir.setReturnValue(GlobalSkins.FLIGHT_SUIT);
    }
}