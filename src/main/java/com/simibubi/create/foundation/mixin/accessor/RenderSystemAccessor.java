package com.sakalti.create_re.foundation.mixin.accessor;

import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import com.mojang.blaze3d.systems.RenderSystem;

@Mixin(RenderSystem.class)
public interface RenderSystemAccessor {
	@Accessor("shaderLightDirections")
	static Vector3f[] create_re$getShaderLightDirections() {
		throw new AssertionError();
	}
}
