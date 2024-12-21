package com.simibubi.create_re.content.kinetics.drill;

import com.simibubi.create_re.AllPartialModels;
import com.simibubi.create_re.content.kinetics.base.SingleRotatingVisual;

import dev.engine_room.flywheel.api.model.Model;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.model.Models;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class DrillVisual extends SingleRotatingVisual<DrillBlockEntity> {

    public DrillVisual(VisualizationContext context, DrillBlockEntity blockEntity, float partialTick) {
        super(context, blockEntity, partialTick);
    }

	@Override
	protected Model model() {
        Direction facing = blockEntity.getBlockState()
                .getValue(BlockStateProperties.FACING);
		return Models.partial(AllPartialModels.DRILL_HEAD, facing);
	}
}
