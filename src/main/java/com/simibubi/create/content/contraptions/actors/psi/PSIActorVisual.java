package com.simibubi.create_re.content.contraptions.actors.psi;

import com.simibubi.create_re.content.contraptions.behaviour.MovementContext;
import com.simibubi.create_re.content.contraptions.render.ActorVisual;
import com.simibubi.create_re.foundation.utility.AnimationTickHolder;
import com.simibubi.create_re.foundation.utility.animation.LerpedFloat;
import com.simibubi.create_re.foundation.virtualWorld.VirtualRenderWorld;

import dev.engine_room.flywheel.api.visualization.VisualizationContext;

public class PSIActorVisual extends ActorVisual {

	private final PIInstance instance;

	public PSIActorVisual(VisualizationContext context, VirtualRenderWorld world, MovementContext movementContext) {
		super(context, world, movementContext);

		instance = new PIInstance(context.instancerProvider(), movementContext.state, movementContext.localPos);

		instance.init(false);
		instance.middle.light(localBlockLight(), 0);
		instance.top.light(localBlockLight(), 0);
	}

	@Override
	public void beginFrame() {
		LerpedFloat lf = PortableStorageInterfaceMovement.getAnimation(context);
		instance.tick(lf.settled());
		instance.beginFrame(lf.getValue(AnimationTickHolder.getPartialTicks()));
	}

	@Override
	protected void _delete() {
		instance.remove();
	}
}
