package com.sakalti.create_re.content.kinetics.turntable;

import com.sakalti.create_re.content.kinetics.base.KineticBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class TurntableBlockEntity extends KineticBlockEntity {

	public TurntableBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

}
