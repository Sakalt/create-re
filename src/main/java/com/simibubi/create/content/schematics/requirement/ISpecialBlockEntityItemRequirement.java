package com.sakalti.create_re.content.schematics.requirement;

import net.minecraft.world.level.block.state.BlockState;

public interface ISpecialBlockEntityItemRequirement {

	public ItemRequirement getRequiredItems(BlockState state);

}
