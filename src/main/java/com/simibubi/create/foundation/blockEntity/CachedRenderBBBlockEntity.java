package com.sakalti.create_re.foundation.blockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class CachedRenderBBBlockEntity extends SyncedBlockEntity {

	private AABB renderBoundingBox;

	public CachedRenderBBBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public AABB getRenderBoundingBox() {
		if (renderBoundingBox == null) {
			renderBoundingBox = create_reRenderBoundingBox();
		}
		return renderBoundingBox;
	}

	protected void invalidateRenderBoundingBox() {
		renderBoundingBox = null;
	}

	protected AABB create_reRenderBoundingBox() {
		return super.getRenderBoundingBox();
	}

}
