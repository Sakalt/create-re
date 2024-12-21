package com.sakalti.create_re.foundation.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.Position;
import net.minecraft.core.dispenser.AbstractProjectileDispenseBehavior;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

@Mixin(AbstractProjectileDispenseBehavior.class)
public interface AbstractProjectileDispenseBehaviorAccessor {
	@Invoker("getProjectile")
	Projectile create_re$callGetProjectile(Level level, Position position, ItemStack stack);

	@Invoker("getUncertainty")
	float create_re$callGetUncertainty();

	@Invoker("getPower")
	float create_re$callGetPower();
}
