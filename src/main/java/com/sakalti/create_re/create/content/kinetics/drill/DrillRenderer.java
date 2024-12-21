package com.sakalti.create_re.content.kinetics.drill;

import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.content.contraptions.behaviour.MovementContext;
import com.sakalti.create_re.content.contraptions.render.ContraptionMatrices;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntityRenderer;
import com.sakalti.create_re.foundation.render.CachedBufferer;
import com.sakalti.create_re.foundation.render.SuperByteBuffer;
import com.sakalti.create_re.foundation.utility.AngleHelper;
import com.sakalti.create_re.foundation.utility.AnimationTickHolder;
import com.sakalti.create_re.foundation.utility.VecHelper;
import com.sakalti.create_re.foundation.virtualWorld.VirtualRenderWorld;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;

public class DrillRenderer extends KineticBlockEntityRenderer<DrillBlockEntity> {

	public DrillRenderer(BlockEntityRendererProvider.Context context) {
		super(context);
	}

	@Override
	protected SuperByteBuffer getRotatedModel(DrillBlockEntity be, BlockState state) {
		return CachedBufferer.partialFacing(AllPartialModels.DRILL_HEAD, state);
	}

	public static void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld,
		ContraptionMatrices matrices, MultiBufferSource buffer) {
		BlockState state = context.state;
		SuperByteBuffer superBuffer = CachedBufferer.partial(AllPartialModels.DRILL_HEAD, state);
		Direction facing = state.getValue(DrillBlock.FACING);

		float speed = (float) (context.contraption.stalled
				|| !VecHelper.isVecPointingTowards(context.relativeMotion, facing
				.getOpposite()) ? context.getAnimationSpeed() : 0);
		float time = AnimationTickHolder.getRenderTime() / 20;
		float angle = (float) (((time * speed) % 360));

		superBuffer
			.transform(matrices.getModel())
			.center()
			.rotateYDegrees(AngleHelper.horizontalAngle(facing))
			.rotateXDegrees(AngleHelper.verticalAngle(facing))
			.rotateZDegrees(angle)
			.uncenter()
			.light(LevelRenderer.getLightColor(renderWorld, context.localPos))
			.useLevelLight(context.world, matrices.getWorld())
			.renderInto(matrices.getViewProjection(), buffer.getBuffer(RenderType.solid()));
	}

}
