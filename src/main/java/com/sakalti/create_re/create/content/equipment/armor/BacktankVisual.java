package com.sakalti.create_re.content.equipment.armor;

import com.sakalti.create_re.content.kinetics.base.SingleRotatingVisual;

import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;

public class BacktankVisual extends SingleRotatingVisual<BacktankBlockEntity> {

	public BacktankVisual(VisualizationContext context, BacktankBlockEntity blockEntity, float partialTick) {
		super(context, blockEntity, partialTick);
	}

	@Override
	protected Model model() {
		return Models.partial(BacktankRenderer.getShaftModel(blockState));
	}
}