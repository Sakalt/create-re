package com.sakalti.create_re.content.contraptions.actors.roller;

import static net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.content.contraptions.actors.harvester.HarvesterRenderer;
import com.sakalti.create_re.content.contraptions.behaviour.MovementContext;
import com.sakalti.create_re.content.contraptions.render.ContraptionMatrices;
import com.sakalti.create_re.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.sakalti.create_re.foundation.render.CachedBufferer;
import com.sakalti.create_re.foundation.render.SuperByteBuffer;
import com.sakalti.create_re.foundation.utility.AngleHelper;
import com.sakalti.create_re.foundation.utility.VecHelper;
import com.sakalti.create_re.foundation.virtualWorld.VirtualRenderWorld;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class RollerRenderer extends SmartBlockEntityRenderer<RollerBlockEntity> {

	public RollerRenderer(Context context) {
		super(context);
	}

	@Override
	protected void renderSafe(RollerBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
		int light, int overlay) {
		super.renderSafe(be, partialTicks, ms, buffer, light, overlay);

		BlockState blockState = be.getBlockState();
		VertexConsumer vc = buffer.getBuffer(RenderType.cutoutMipped());

		ms.pushPose();
		ms.translate(0, -0.25, 0);
		SuperByteBuffer superBuffer = CachedBufferer.partial(AllPartialModels.ROLLER_WHEEL, blockState);
		Direction facing = blockState.getValue(RollerBlock.FACING);
		superBuffer.translate(Vec3.atLowerCornerOf(facing.getNormal())
			.scale(17 / 16f));
		HarvesterRenderer.transform(be.getLevel(), facing, superBuffer, be.getAnimatedSpeed(), Vec3.ZERO);
		superBuffer.translate(0, -.5, .5)
			.rotateYDegrees(90)
			.light(light)
			.renderInto(ms, vc);
		ms.popPose();

		CachedBufferer.partial(AllPartialModels.ROLLER_FRAME, blockState)
			.rotateCentered(AngleHelper.rad(AngleHelper.horizontalAngle(facing) + 180), Direction.UP)
			.light(light)
			.renderInto(ms, vc);
	}

	public static void renderInContraption(MovementContext context, VirtualRenderWorld renderWorld,
		ContraptionMatrices matrices, MultiBufferSource buffers) {
		BlockState blockState = context.state;
		Direction facing = blockState.getValue(HORIZONTAL_FACING);
		VertexConsumer vc = buffers.getBuffer(RenderType.cutoutMipped());
		SuperByteBuffer superBuffer = CachedBufferer.partial(AllPartialModels.ROLLER_WHEEL, blockState);
		float speed = (float) (!VecHelper.isVecPointingTowards(context.relativeMotion, facing.getOpposite())
			? context.getAnimationSpeed()
			: -context.getAnimationSpeed());
		if (context.contraption.stalled)
			speed = 0;

		superBuffer.transform(matrices.getModel())
			.translate(Vec3.atLowerCornerOf(facing.getNormal())
				.scale(17 / 16f));
		HarvesterRenderer.transform(context.world, facing, superBuffer, speed, Vec3.ZERO);

		PoseStack viewProjection = matrices.getViewProjection();
		viewProjection.pushPose();
		viewProjection.translate(0, -.25, 0);
		int contraptionWorldLight = LevelRenderer.getLightColor(renderWorld, context.localPos);
		superBuffer.translate(0, -.5, .5)
			.rotateYDegrees(90)
			.light(contraptionWorldLight)
			.useLevelLight(context.world, matrices.getWorld())
			.renderInto(viewProjection, vc);
		viewProjection.popPose();

		CachedBufferer.partial(AllPartialModels.ROLLER_FRAME, blockState)
			.transform(matrices.getModel())
			.rotateCentered(AngleHelper.rad(AngleHelper.horizontalAngle(facing) + 180), Direction.UP)
			.light(contraptionWorldLight)
			.useLevelLight(context.world, matrices.getWorld())
			.renderInto(viewProjection, vc);
	}

}
