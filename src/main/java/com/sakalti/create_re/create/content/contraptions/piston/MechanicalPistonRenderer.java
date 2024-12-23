package com.sakalti.create_re.content.contraptions.piston;

import com.sakalti.create_re.content.kinetics.base.KineticBlockEntityRenderer;

import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.state.BlockState;

public class MechanicalPistonRenderer extends KineticBlockEntityRenderer<MechanicalPistonBlockEntity> {

	public MechanicalPistonRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected BlockState getRenderedBlockState(MechanicalPistonBlockEntity be) {
		return shaft(getRotationAxisOf(be));
	}

}
