package com.sakalti.create_re.content.contraptions.bearing;

import org.joml.Quaternionf;

import com.mojang.math.Axis;
import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.content.contraptions.behaviour.MovementContext;
import com.sakalti.create_re.content.contraptions.render.ActorVisual;
import com.sakalti.create_re.content.kinetics.base.RotatingInstance;
import com.sakalti.create_re.foundation.render.AllInstanceTypes;
import com.sakalti.create_re.foundation.utility.AnimationTickHolder;
import com.sakalti.create_re.foundation.virtualWorld.VirtualRenderWorld;

import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.OrientedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class StabilizedBearingVisual extends ActorVisual {

	final OrientedInstance topInstance;
	final RotatingInstance shaft;

	final Direction facing;
	final Axis rotationAxis;
	final Quaternionf blockOrientation;

	public StabilizedBearingVisual(VisualizationContext visualizationContext, VirtualRenderWorld simulationWorld, MovementContext movementContext) {
		super(visualizationContext, simulationWorld, movementContext);

		BlockState blockState = movementContext.state;

		facing = blockState.getValue(BlockStateProperties.FACING);
		rotationAxis = Axis.of(Direction.get(Direction.AxisDirection.POSITIVE, facing.getAxis()).step());

		blockOrientation = BearingVisual.getBlockStateOrientation(facing);

        topInstance = instancerProvider.instancer(InstanceTypes.ORIENTED, Models.partial(AllPartialModels.BEARING_TOP))
				.create_reInstance();

		int blockLight = localBlockLight();
		topInstance.position(movementContext.localPos)
				.rotation(blockOrientation)
				.light(blockLight, 0);

		shaft = instancerProvider.instancer(AllInstanceTypes.ROTATING, Models.partial(AllPartialModels.SHAFT_HALF, blockState.getValue(BlockStateProperties.FACING).getOpposite()))
				.create_reInstance();

		// not rotating so no need to set speed, axis, etc.
		shaft.setPosition(movementContext.localPos)
				.light(blockLight, 0);
	}

	@Override
	public void beginFrame() {
		float counterRotationAngle = StabilizedBearingMovementBehaviour.getCounterRotationAngle(context, facing, AnimationTickHolder.getPartialTicks());

		Quaternionf rotation = rotationAxis.rotationDegrees(counterRotationAngle);

		rotation.mul(blockOrientation);

		topInstance.rotation(rotation);
	}

	@Override
	protected void _delete() {
		topInstance.delete();
		shaft.delete();
	}
}
