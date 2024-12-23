package com.sakalti.create_re.foundation.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.sakalti.create_re.content.equipment.armor.NetheriteDivingHandler;

import net.minecraft.world.entity.Entity;

@Mixin(value = Entity.class, priority = 900)
public class EntityMixin {
	
	@Inject(method = "fireImmune()Z", at = @At("RETURN"), cancellable = true)
	public void create_re$onFireImmune(CallbackInfoReturnable<Boolean> cir) {
		if (!cir.getReturnValueZ()) {
			Entity self = (Entity) (Object) this;
			boolean immune = self.getPersistentData().getBoolean(NetheriteDivingHandler.FIRE_IMMUNE_KEY);
			if (immune)
				cir.setReturnValue(immune);
		}
	}
	
}
