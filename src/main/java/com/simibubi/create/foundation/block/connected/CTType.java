package com.sakalti.create_re.foundation.block.connected;

import com.sakalti.create_re.foundation.block.connected.ConnectedTextureBehaviour.CTContext;
import com.sakalti.create_re.foundation.block.connected.ConnectedTextureBehaviour.ContextRequirement;

import net.minecraft.resources.ResourceLocation;

public interface CTType {
	ResourceLocation getId();

	int getSheetSize();

	ContextRequirement getContextRequirement();

	int getTextureIndex(CTContext context);
}
