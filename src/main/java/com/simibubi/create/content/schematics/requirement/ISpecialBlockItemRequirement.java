package com.sakalti.create_re.content.schematics.requirement;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface ISpecialBlockItemRequirement {

	public ItemRequirement getRequiredItems(BlockState state, BlockEntity blockEntity);

}
