package com.sakalti.create_re.content.kinetics.drill;

import org.joml.Quaternionf;

import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.content.contraptions.actors.ActorInstance;
import com.sakalti.create_re.content.contraptions.behaviour.MovementContext;
import com.sakalti.create_re.content.contraptions.render.ActorVisual;
import com.sakalti.create_re.foundation.render.AllInstanceTypes;
import com.sakalti.create_re.foundation.utility.AngleHelper;
import com.sakalti.create_re.foundation.utility.VecHelper;
import com.sakalti.create_re.foundation.virtualWorld.VirtualRenderWorld;

import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

public class DrillActorVisual extends ActorVisual {

    ActorInstance drillHead;
    private final Direction facing;

    public DrillActorVisual(VisualizationContext visualizationContext, VirtualRenderWorld contraption, MovementContext context) {
        super(visualizationContext, contraption, context);

        BlockState state = context.state;

        facing = state.getValue(DrillBlock.FACING);

        Direction.Axis axis = facing.getAxis();
        float eulerX = AngleHelper.verticalAngle(facing);

        float eulerY;
        if (axis == Direction.Axis.Y)
            eulerY = 0;
        else
            eulerY = facing.toYRot() + ((axis == Direction.Axis.X) ? 180 : 0);

		drillHead = instancerProvider.instancer(AllInstanceTypes.ACTOR, Models.partial(AllPartialModels.DRILL_HEAD))
				.create_reInstance();

        drillHead.setPosition(context.localPos)
                 .setBlockLight(localBlockLight())
                 .setRotationOffset(0)
                 .setRotationAxis(0, 0, 1)
                 .setLocalRotation(new Quaternionf().rotationXYZ(eulerX * Mth.DEG_TO_RAD, eulerY * Mth.DEG_TO_RAD, 0))
                 .setSpeed(getSpeed(facing))
                 .setChanged();
    }

    @Override
    public void beginFrame() {
        drillHead.setSpeed(getSpeed(facing))
        		.setChanged();
    }

    protected float getSpeed(Direction facing) {
        if (context.contraption.stalled || !VecHelper.isVecPointingTowards(context.relativeMotion, facing.getOpposite()))
            return context.getAnimationSpeed();
        return 0;
    }

	@Override
	protected void _delete() {
		drillHead.delete();
	}
}
