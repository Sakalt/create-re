package com.simibubi.create_re.foundation.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.nbt.NbtAccounter;

@Mixin(NbtAccounter.class)
public interface NbtAccounterAccessor {
	@Accessor("usage")
	long create_re$getUsage();
}
