package com.sakalti.create_re.content.contraptions.actors.roller;

import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.content.contraptions.actors.harvester.HarvesterActorVisual;
import com.sakalti.create_re.content.contraptions.behaviour.MovementContext;
import com.sakalti.create_re.foundation.virtualWorld.VirtualRenderWorld;

import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;
import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.world.phys.Vec3;

public class RollerActorVisual extends HarvesterActorVisual {

	TransformedInstance frame;

	public RollerActorVisual(VisualizationContext visualizationContext, VirtualRenderWorld simulationWorld,
		MovementContext movementContext) {
		super(visualizationContext, simulationWorld, movementContext);

		frame = instancerProvider.instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.ROLLER_FRAME))
			.create_reInstance();
		frame.light(localBlockLight(), 0);
	}

	@Override
	public void beginFrame() {
		harvester.setIdentityTransform()
			.translate(context.localPos)
			.center()
			.rotateYDegrees(horizontalAngle)
			.uncenter()
			.translate(0, -.25, 17 / 16f)
			.rotateXDegrees((float) getRotation())
			.translate(0, -.5, .5)
			.rotateYDegrees(90)
			.setChanged();

		frame.setIdentityTransform()
			.translate(context.localPos)
			.center()
			.rotateYDegrees(horizontalAngle + 180)
			.uncenter()
			.setChanged();
	}

	@Override
	protected PartialModel getRollingPartial() {
		return AllPartialModels.ROLLER_WHEEL;
	}

	@Override
	protected Vec3 getRotationOffset() {
		return Vec3.ZERO;
	}

	@Override
	protected double getRadius() {
		return 16.5;
	}

	@Override
	protected void _delete() {
		super._delete();

		frame.delete();
	}
}
