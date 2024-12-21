package com.sakalti.create_re.foundation.mixin.client;

import java.util.Set;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import com.sakalti.create_re.foundation.block.render.BlockDestructionProgressExtension;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.BlockDestructionProgress;

@Mixin(BlockDestructionProgress.class)
public class BlockDestructionProgressMixin implements BlockDestructionProgressExtension {
	@Unique
	private Set<BlockPos> create_re$extraPositions;

	@Override
	public Set<BlockPos> getExtraPositions() {
		return create_re$extraPositions;
	}

	@Override
	public void setExtraPositions(Set<BlockPos> positions) {
		create_re$extraPositions = positions;
	}
}
