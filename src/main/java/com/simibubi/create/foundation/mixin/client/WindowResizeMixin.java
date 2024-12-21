package com.sakalti.create_re.foundation.mixin.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.mojang.blaze3d.platform.Window;
import com.sakalti.create_re.foundation.gui.UIRenderHelper;

import net.minecraft.client.Minecraft;

@Mixin(Minecraft.class)
public class WindowResizeMixin {
	@Shadow
	@Final
	private Window window;

	@Inject(method = "resizeDisplay()V", at = @At("TAIL"))
	private void create_re$updateWindowSize(CallbackInfo ci) {
		UIRenderHelper.updateWindowSize(window);
	}
}
