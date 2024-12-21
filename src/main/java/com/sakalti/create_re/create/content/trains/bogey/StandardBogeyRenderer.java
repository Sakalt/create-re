package com.sakalti.create_re.content.trains.bogey;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.sakalti.create_re.AllBlocks;
import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.content.kinetics.simpleRelays.ShaftBlock;
import com.sakalti.create_re.foundation.render.CachedBufferer;
import com.sakalti.create_re.foundation.render.SuperByteBuffer;
import com.sakalti.create_re.foundation.utility.AngleHelper;
import com.sakalti.create_re.foundation.utility.Iterate;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.Blocks;

public class StandardBogeyRenderer implements BogeyRenderer {
	@Override
	public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
		VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());

		SuperByteBuffer shaft = CachedBufferer.block(AllBlocks.SHAFT.getDefaultState()
				.setValue(ShaftBlock.AXIS, Direction.Axis.Z));
		for (int i : Iterate.zeroAndOne) {
			shaft.translate(-.5f, .25f, i * -1)
					.center()
					.rotateZDegrees(wheelAngle)
					.uncenter()
					.light(light)
					.overlay(overlay)
					.renderInto(poseStack, buffer);
		}
	}

	public static class Small extends StandardBogeyRenderer {
		@Override
		public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
			super.render(bogeyData, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

			VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());

			CachedBufferer.partial(AllPartialModels.BOGEY_FRAME, Blocks.AIR.defaultBlockState())
					.scale(1 - 1 / 512f)
					.light(light)
					.overlay(overlay)
					.renderInto(poseStack, buffer);

			SuperByteBuffer wheels = CachedBufferer.partial(AllPartialModels.SMALL_BOGEY_WHEELS, Blocks.AIR.defaultBlockState());
			for (int side : Iterate.positiveAndNegative) {
				wheels.translate(0, 12 / 16f, side)
					.rotateXDegrees(wheelAngle)
					.light(light)
					.overlay(overlay)
					.renderInto(poseStack, buffer);
			}
		}
	}

	public static class Large extends StandardBogeyRenderer {
		@Override
		public void render(CompoundTag bogeyData, float wheelAngle, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int light, int overlay, boolean inContraption) {
			super.render(bogeyData, wheelAngle, partialTick, poseStack, bufferSource, light, overlay, inContraption);

			VertexConsumer buffer = bufferSource.getBuffer(RenderType.cutoutMipped());

			SuperByteBuffer secondaryShaft = CachedBufferer.block(AllBlocks.SHAFT.getDefaultState()
					.setValue(ShaftBlock.AXIS, Direction.Axis.X));
			for (int i : Iterate.zeroAndOne) {
				secondaryShaft.translate(-.5f, .25f, .5f + i * -2)
						.center()
						.rotateXDegrees(wheelAngle)
						.uncenter()
						.light(light)
						.overlay(overlay)
						.renderInto(poseStack, buffer);
			}

			CachedBufferer.partial(AllPartialModels.BOGEY_DRIVE, Blocks.AIR.defaultBlockState())
					.scale(1 - 1 / 512f)
					.light(light)
					.overlay(overlay)
					.renderInto(poseStack, buffer);

			CachedBufferer.partial(AllPartialModels.BOGEY_PISTON, Blocks.AIR.defaultBlockState())
					.translate(0, 0, 1 / 4f * Math.sin(AngleHelper.rad(wheelAngle)))
					.light(light)
					.overlay(overlay)
					.renderInto(poseStack, buffer);

			CachedBufferer.partial(AllPartialModels.LARGE_BOGEY_WHEELS, Blocks.AIR.defaultBlockState())
					.translate(0, 1, 0)
					.rotateXDegrees(wheelAngle)
					.light(light)
					.overlay(overlay)
					.renderInto(poseStack, buffer);

			CachedBufferer.partial(AllPartialModels.BOGEY_PIN, Blocks.AIR.defaultBlockState())
					.translate(0, 1, 0)
					.rotateXDegrees(wheelAngle)
					.translate(0, 1 / 4f, 0)
					.rotateXDegrees(-wheelAngle)
					.light(light)
					.overlay(overlay)
					.renderInto(poseStack, buffer);
		}
	}
}
