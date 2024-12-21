package com.simibubi.create_re.content.kinetics.millstone;

import com.simibubi.create_re.AllPartialModels;
import com.simibubi.create_re.content.kinetics.base.KineticBlockEntityRenderer;
import com.simibubi.create_re.foundation.render.CachedBufferer;
import com.simibubi.create_re.foundation.render.SuperByteBuffer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class MillstoneRenderer extends KineticBlockEntityRenderer<MillstoneBlockEntity> {

	public MillstoneRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected SuperByteBuffer getRotatedModel(MillstoneBlockEntity be, BlockState state) {
		return CachedBufferer.partial(AllPartialModels.MILLSTONE_COG, state);
	}

}
