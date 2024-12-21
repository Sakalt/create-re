package com.simibubi.create_re.foundation.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

@Mixin(Entity.class)
public interface EntityAccessor {
	@Invoker("setLevel")
	void create_re$callSetLevel(Level level);
}
