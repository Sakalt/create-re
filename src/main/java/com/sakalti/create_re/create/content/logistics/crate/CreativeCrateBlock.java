package com.sakalti.create_re.content.logistics.crate;

import com.sakalti.create_re.AllBlockEntityTypes;
import com.sakalti.create_re.foundation.block.IBE;

import net.minecraft.world.level.block.entity.BlockEntityType;

public class CreativeCrateBlock extends CrateBlock implements IBE<CreativeCrateBlockEntity> {

	public CreativeCrateBlock(Properties p_i48415_1_) {
		super(p_i48415_1_);
	}

	@Override
	public Class<CreativeCrateBlockEntity> getBlockEntityClass() {
		return CreativeCrateBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends CreativeCrateBlockEntity> getBlockEntityType() {
		return AllBlockEntityTypes.CREATIVE_CRATE.get();
	}
}
