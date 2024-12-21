package com.sakalti.create_re.foundation.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.client.MouseHandler;

@Mixin(MouseHandler.class)
public interface MouseHandlerAccessor {

	@Accessor("xpos")
	void create_re$setXPos(double xPos);

	@Accessor("ypos")
	void create_re$setYPos(double yPos);
}
