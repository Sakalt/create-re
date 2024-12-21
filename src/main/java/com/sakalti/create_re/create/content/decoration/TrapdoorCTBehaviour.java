package com.sakalti.create_re.content.decoration;

import org.jetbrains.annotations.Nullable;

import com.sakalti.create_re.AllSpriteShifts;
import com.sakalti.create_re.foundation.block.connected.CTSpriteShiftEntry;
import com.sakalti.create_re.foundation.block.connected.ConnectedTextureBehaviour;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.block.state.BlockState;

public class TrapdoorCTBehaviour extends ConnectedTextureBehaviour.Base {

	@Override
	public CTSpriteShiftEntry getShift(BlockState state, Direction direction, @Nullable TextureAtlasSprite sprite) {
		return AllSpriteShifts.FRAMED_GLASS;
	}

	@Override
	public boolean connectsTo(BlockState state, BlockState other, BlockAndTintGetter reader, BlockPos pos,
		BlockPos otherPos, Direction face, Direction primaryOffset, Direction secondaryOffset) {
		return state.getBlock() == other.getBlock()
			&& TrainTrapdoorBlock.isConnected(state, other, primaryOffset == null ? secondaryOffset : primaryOffset);
	}

}
