package com.sakalti.create_re.content.contraptions.pulley;


import com.sakalti.create_re.AllBlocks;
import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.foundation.render.VirtualRenderHelper;

import dev.engine_room.flywheel.api.instance.Instancer;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import dev.engine_room.flywheel.lib.instance.InstanceTypes;
import dev.engine_room.flywheel.lib.instance.OrientedInstance;
import dev.engine_room.flywheel.lib.instance.TransformedInstance;
import dev.engine_room.flywheel.lib.model.Models;

public class RopePulleyVisual extends AbstractPulleyVisual<PulleyBlockEntity> {
	public RopePulleyVisual(VisualizationContext context, PulleyBlockEntity blockEntity, float partialTick) {
		super(context, blockEntity, partialTick);
	}

	@Override
	protected Instancer<TransformedInstance> getRopeModel() {
		return instancerProvider().instancer(InstanceTypes.TRANSFORMED, VirtualRenderHelper.blockModel(AllBlocks.ROPE.getDefaultState()));
	}

	@Override
	protected Instancer<TransformedInstance> getMagnetModel() {
		return instancerProvider().instancer(InstanceTypes.TRANSFORMED, VirtualRenderHelper.blockModel(AllBlocks.PULLEY_MAGNET.getDefaultState()));
	}

	@Override
	protected Instancer<TransformedInstance> getHalfMagnetModel() {
		return instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.ROPE_HALF_MAGNET));
	}

	@Override
	protected Instancer<OrientedInstance> getCoilModel() {
		return instancerProvider().instancer(InstanceTypes.ORIENTED, Models.partial(AllPartialModels.ROPE_COIL, rotatingAbout));
	}

	@Override
	protected Instancer<TransformedInstance> getHalfRopeModel() {
		return instancerProvider().instancer(InstanceTypes.TRANSFORMED, Models.partial(AllPartialModels.ROPE_HALF));
	}

	@Override
	protected float getOffset(float pt) {
		return PulleyRenderer.getBlockEntityOffset(pt, blockEntity);
	}

	@Override
	protected boolean isRunning() {
		return PulleyRenderer.isPulleyRunning(blockEntity);
	}
}
