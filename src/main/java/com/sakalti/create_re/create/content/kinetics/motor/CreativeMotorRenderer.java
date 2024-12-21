package com.sakalti.create_re.content.kinetics.motor;

import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntityRenderer;
import com.sakalti.create_re.foundation.render.CachedBufferer;
import com.sakalti.create_re.foundation.render.SuperByteBuffer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class CreativeMotorRenderer extends KineticBlockEntityRenderer<CreativeMotorBlockEntity> {

	public CreativeMotorRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected SuperByteBuffer getRotatedModel(CreativeMotorBlockEntity be, BlockState state) {
		return CachedBufferer.partialFacing(AllPartialModels.SHAFT_HALF, state);
	}

}