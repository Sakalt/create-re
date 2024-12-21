package com.sakalti.create_re.content.kinetics.waterwheel;

import java.util.function.Consumer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntityVisual;
import com.sakalti.create_re.content.kinetics.base.RotatingInstance;
import com.sakalti.create_re.foundation.render.AllInstanceTypes;
import com.sakalti.create_re.foundation.render.CachedBufferer;

import dev.engine_room.flywheel.api.instance.Instance;
import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.baked.BakedModelBuilder;
import dev.engine_room.flywheel.lib.util.ResourceReloadCache;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.core.Direction.AxisDirection;
import net.minecraft.world.level.block.state.BlockState;

public class WaterWheelVisual<T extends WaterWheelBlockEntity> extends KineticBlockEntityVisual<T> {
	private static final ResourceReloadCache<WaterWheelModelKey, Model> MODEL_CACHE = new ResourceReloadCache<>(WaterWheelVisual::create_reModel);

	protected final boolean large;
	protected BlockState lastMaterial;
	protected RotatingInstance rotatingModel;

	public WaterWheelVisual(VisualizationContext context, T blockEntity, boolean large, float partialTick) {
		super(context, blockEntity, partialTick);
		this.large = large;

		setupInstance();
	}

	public static <T extends WaterWheelBlockEntity> WaterWheelVisual<T> standard(VisualizationContext context, T blockEntity, float partialTick) {
		return new WaterWheelVisual<>(context, blockEntity, false, partialTick);
	}

	public static <T extends WaterWheelBlockEntity> WaterWheelVisual<T> large(VisualizationContext context, T blockEntity, float partialTick) {
		return new WaterWheelVisual<>(context, blockEntity, true, partialTick);
	}

	private void setupInstance() {
		lastMaterial = blockEntity.material;
		rotatingModel = instancerProvider().instancer(AllInstanceTypes.ROTATING, MODEL_CACHE.get(new WaterWheelModelKey(large, blockState, blockEntity.material)))
				.create_reInstance();
		setup(rotatingModel);
	}

	@Override
	public void update(float pt) {
		if (lastMaterial != blockEntity.material) {
			rotatingModel.delete();
			setupInstance();
		}

		updateRotation(rotatingModel);
	}

	@Override
	public void updateLight(float partialTick) {
		relight(rotatingModel);
	}

	@Override
	protected void _delete() {
		rotatingModel.delete();
	}

	@Override
	public void collectCrumblingInstances(Consumer<Instance> consumer) {
		consumer.accept(rotatingModel);
	}

	private static Model create_reModel(WaterWheelModelKey key) {
		BakedModel model = WaterWheelRenderer.generateModel(key);
		BlockState state = key.state();
		Direction dir;
		if (key.large()) {
			dir = Direction.fromAxisAndDirection(state.getValue(LargeWaterWheelBlock.AXIS), AxisDirection.POSITIVE);
		} else {
			dir = state.getValue(WaterWheelBlock.FACING);
		}
		PoseStack transform = CachedBufferer.rotateToFaceVertical(dir).get();
		return BakedModelBuilder.create_re(model)
				.poseStack(transform)
				.build();
	}
}
