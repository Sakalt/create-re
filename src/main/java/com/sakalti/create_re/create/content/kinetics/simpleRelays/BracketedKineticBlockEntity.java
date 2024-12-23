package com.sakalti.create_re.content.kinetics.simpleRelays;

import java.util.List;

import com.sakalti.create_re.content.contraptions.ITransformableBlockEntity;
import com.sakalti.create_re.content.contraptions.StructureTransform;
import com.sakalti.create_re.content.decoration.bracket.BracketedBlockEntityBehaviour;
import com.sakalti.create_re.foundation.blockEntity.behaviour.BlockEntityBehaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class BracketedKineticBlockEntity extends SimpleKineticBlockEntity implements ITransformableBlockEntity {

	public BracketedKineticBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours
			.add(new BracketedBlockEntityBehaviour(this, state -> state.getBlock() instanceof AbstractSimpleShaftBlock));
		super.addBehaviours(behaviours);
	}

	@Override
	public void transform(StructureTransform transform) {
		BracketedBlockEntityBehaviour bracketBehaviour = getBehaviour(BracketedBlockEntityBehaviour.TYPE);
		if (bracketBehaviour != null) {
			bracketBehaviour.transformBracket(transform);
		}
	}

}
