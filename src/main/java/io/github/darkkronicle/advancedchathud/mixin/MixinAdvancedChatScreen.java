package io.github.darkkronicle.advancedchathud.mixin;

import io.github.darkkronicle.advancedchatcore.chat.AdvancedChatScreen;
import io.github.darkkronicle.advancedchathud.config.HudConfigStorage;
import io.github.darkkronicle.advancedchathud.gui.WindowManager;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(AdvancedChatScreen.class)
public class MixinAdvancedChatScreen {

    @Inject(at = @At("HEAD"), method = "getTextStyleAt", cancellable = true)
    public void getTextHead(double x, double y, CallbackInfoReturnable<Style> cir) {
        if (!HudConfigStorage.General.VANILLA_HUD.config.getBooleanValue()) {
            cir.setReturnValue(WindowManager.getInstance().getText(x, y));
        }
    }

    @Inject(at = @At("RETURN"), method = "getTextStyleAt", cancellable = true)
    public void getTextReturn(double x, double y, CallbackInfoReturnable<Style> cir) {
        if (cir.getReturnValue() == null) {
            cir.setReturnValue(WindowManager.getInstance().getText(x, y));
        }
    }
}
