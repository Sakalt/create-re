package com.sakalti.create_re.foundation.utility;

import net.minecraft.core.BlockPos;

@FunctionalInterface
public interface ICoordinate {
	float get(BlockPos from);
}
