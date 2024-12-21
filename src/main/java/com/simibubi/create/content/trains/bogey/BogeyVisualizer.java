package com.simibubi.create_re.content.trains.bogey;

import dev.engine_room.flywheel.api.visualization.VisualizationContext;

@FunctionalInterface
public interface BogeyVisualizer {
	BogeyVisual create_reVisual(VisualizationContext ctx, float partialTick, boolean inContraption);
}
