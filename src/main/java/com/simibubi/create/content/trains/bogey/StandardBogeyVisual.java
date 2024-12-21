package com.sakalti.create_re.content.trains.bogey;

import java.util.function.Consumer;

import org.jetbrains.annotations.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.create_re.AllBlocks;
import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.content.kinetics.simpleRelays.ShaftBlock;
import com.sakalti.create_re.foundation.render.VirtualRenderHelper;
import com.sakalti.create_re.foundation.utility.AngleHelper;

import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;

public class StandardBogeyVisual implements BogeyVisual {
	private final TransformedInstance shaft1;
	private final TransformedInstance shaft2;

	public StandardBogeyVisual(VisualizationContext ctx, float partialTick, boolean inContraption) {
		var shaftInstancer = ctx.instancerProvider()
				.instancer(InstanceTypes.TRANSFORMED, VirtualRenderHelper.blockModel(AllBlocks.SHAFT.getDefaultState()
						.setValue(ShaftBlock.AXIS, Direction.Axis.Z)));
		shaft1 = shaftInstancer.create_reInstance();
		shaft2 = shaftInstancer.create_reInstance();
	}

	@Override
	public void update(CompoundTag bogeyData, float wheelAngle, PoseStack poseStack) {
		shaft1.setTransform(poseStack)
			.translate(-.5f, .25f, 0)
			.center()
			.rotateZDegrees(wheelAngle)
			.uncenter()
			.setChanged();
		shaft2.setTransform(poseStack)
			.translate(-.5f, .25f, -1)
			.center()
			.rotateZDegrees(wheelAngle)
			.uncenter()
			.setChanged();
	}

	@Override
	public void hide() {
		shaft1.setZeroTransform().setChanged();
		shaft2.setZeroTransform().setChanged();
	}

	@Override
	public void updateLight(int packedLight) {
		shaft1.light(packedLight).setChanged();
		shaft2.light(packedLight).setChanged();
	}

	@Override
	public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
		consumer.accept(shaft1);
		consumer.accept(shaft2);
	}

	@Override
	public void delete() {
		shaft1.delete();
		shaft2.delete();
	}

	public static class Small extends StandardBogeyVisual {
		private final TransformedInstance frame;
		private final TransformedInstance wheel1;
		private final TransformedInstance wheel2;

		public Small(VisualizationContext ctx, float partialTick, boolean inContraption) {
			super(ctx, partialTick, inContraption);
			var wheelInstancer = ctx.instancerProvider()
				.instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.SMALL_BOGEY_WHEELS));
			frame = ctx.instancerProvider()
					.instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.BOGEY_FRAME))
					.create_reInstance();
			wheel1 = wheelInstancer.create_reInstance();
			wheel2 = wheelInstancer.create_reInstance();
		}

		@Override
		public void update(CompoundTag bogeyData, float wheelAngle, PoseStack poseStack) {
			super.update(bogeyData, wheelAngle, poseStack);
			wheel1.setTransform(poseStack)
				.translate(0, 12 / 16f, -1)
				.rotateXDegrees(wheelAngle)
				.setChanged();
			wheel2.setTransform(poseStack)
				.translate(0, 12 / 16f, 1)
				.rotateXDegrees(wheelAngle)
				.setChanged();
			frame.setTransform(poseStack)
				.scale(1 - 1 / 512f)
				.setChanged();
		}

		@Override
		public void hide() {
			super.hide();
			frame.setZeroTransform().setChanged();
			wheel1.setZeroTransform().setChanged();
			wheel2.setZeroTransform().setChanged();
		}

		@Override
		public void updateLight(int packedLight) {
			super.updateLight(packedLight);
			frame.light(packedLight).setChanged();
			wheel1.light(packedLight).setChanged();
			wheel2.light(packedLight).setChanged();
		}

		@Override
		public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
			super.collectCrumblingInstances(consumer);
			consumer.accept(frame);
			consumer.accept(wheel1);
			consumer.accept(wheel2);
		}

		@Override
		public void delete() {
			super.delete();
			frame.delete();
			wheel1.delete();
			wheel2.delete();
		}
	}

	public static class Large extends StandardBogeyVisual {
		private final TransformedInstance secondaryShaft1;
		private final TransformedInstance secondaryShaft2;
		private final TransformedInstance drive;
		private final TransformedInstance piston;
		private final TransformedInstance wheels;
		private final TransformedInstance pin;

		public Large(VisualizationContext ctx, float partialTick, boolean inContraption) {
			super(ctx, partialTick, inContraption);
			var secondaryShaftInstancer = ctx.instancerProvider()
					.instancer(InstanceTypes.TRANSFORMED, VirtualRenderHelper.blockModel(AllBlocks.SHAFT.getDefaultState()
							.setValue(ShaftBlock.AXIS, Direction.Axis.X)));
			secondaryShaft1 = secondaryShaftInstancer.create_reInstance();
			secondaryShaft2 = secondaryShaftInstancer.create_reInstance();
			drive = ctx.instancerProvider()
					.instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.BOGEY_DRIVE))
					.create_reInstance();
			piston = ctx.instancerProvider()
					.instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.BOGEY_PISTON))
					.create_reInstance();
			wheels = ctx.instancerProvider()
					.instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.LARGE_BOGEY_WHEELS))
					.create_reInstance();
			pin = ctx.instancerProvider()
					.instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.BOGEY_PIN))
					.create_reInstance();
		}

		@Override
		public void update(CompoundTag bogeyData, float wheelAngle, PoseStack poseStack) {
			super.update(bogeyData, wheelAngle, poseStack);
			secondaryShaft1.setTransform(poseStack)
				.translate(-.5f, .25f, .5f)
				.center()
				.rotateXDegrees(wheelAngle)
				.uncenter()
				.setChanged();
			secondaryShaft2.setTransform(poseStack)
				.translate(-.5f, .25f, -1.5f)
				.center()
				.rotateXDegrees(wheelAngle)
				.uncenter()
				.setChanged();
			drive.setTransform(poseStack)
				.scale(1 - 1/512f)
				.setChanged();
			piston.setTransform(poseStack)
				.translate(0, 0, 1 / 4f * Math.sin(AngleHelper.rad(wheelAngle)))
				.setChanged();
			wheels.setTransform(poseStack)
				.translate(0, 1, 0)
				.rotateXDegrees(wheelAngle)
				.setChanged();
			pin.setTransform(poseStack)
				.translate(0, 1, 0)
				.rotateXDegrees(wheelAngle)
				.translate(0, 1 / 4f, 0)
				.rotateXDegrees(-wheelAngle)
				.setChanged();
		}

		@Override
		public void hide() {
			super.hide();
			secondaryShaft1.setZeroTransform().setChanged();
			secondaryShaft2.setZeroTransform().setChanged();
			wheels.setZeroTransform().setChanged();
			drive.setZeroTransform().setChanged();
			piston.setZeroTransform().setChanged();
			pin.setZeroTransform().setChanged();
		}

		@Override
		public void updateLight(int packedLight) {
			super.updateLight(packedLight);
			secondaryShaft1.light(packedLight).setChanged();
			secondaryShaft2.light(packedLight).setChanged();
			wheels.light(packedLight).setChanged();
			drive.light(packedLight).setChanged();
			piston.light(packedLight).setChanged();
			pin.light(packedLight).setChanged();
		}

		@Override
		public void collectCrumblingInstances(Consumer<@Nullable Instance> consumer) {
			super.collectCrumblingInstances(consumer);
			consumer.accept(secondaryShaft1);
			consumer.accept(secondaryShaft2);
			consumer.accept(wheels);
			consumer.accept(drive);
			consumer.accept(piston);
			consumer.accept(pin);
		}

		@Override
		public void delete() {
			super.delete();
			secondaryShaft1.delete();
			secondaryShaft2.delete();
			wheels.delete();
			drive.delete();
			piston.delete();
			pin.delete();
		}
	}
}
