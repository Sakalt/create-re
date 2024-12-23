package com.sakalti.create_re.content.redstone.displayLink;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.create_re.AllPartialModels;
import com.sakalti.create_re.foundation.blockEntity.renderer.SafeBlockEntityRenderer;
import com.sakalti.create_re.foundation.render.CachedBufferer;
import com.sakalti.create_re.foundation.render.RenderTypes;
import com.sakalti.create_re.foundation.utility.AngleHelper;

import dev.engine_room.flywheel.lib.transform.TransformStack;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.block.state.BlockState;

public class DisplayLinkRenderer extends SafeBlockEntityRenderer<DisplayLinkBlockEntity> {

	public DisplayLinkRenderer(BlockEntityRendererProvider.Context context) {}

	@Override
	protected void renderSafe(DisplayLinkBlockEntity be, float partialTicks, PoseStack ms, MultiBufferSource buffer,
		int light, int overlay) {
		float glow = be.glow.getValue(partialTicks);
		if (glow < .125f)
			return;

		glow = (float) (1 - (2 * Math.pow(glow - .75f, 2)));
		glow = Mth.clamp(glow, -1, 1);

		int color = (int) (200 * glow);

		BlockState blockState = be.getBlockState();
		var msr = TransformStack.of(ms);

		Direction face = blockState.getOptionalValue(DisplayLinkBlock.FACING)
			.orElse(Direction.UP);

		if (face.getAxis()
			.isHorizontal())
			face = face.getOpposite();

		ms.pushPose();

		msr.center()
			.rotateYDegrees(AngleHelper.horizontalAngle(face))
			.rotateXDegrees(-AngleHelper.verticalAngle(face) - 90)
			.uncenter();

		CachedBufferer.partial(AllPartialModels.DISPLAY_LINK_TUBE, blockState)
			.light(LightTexture.FULL_BRIGHT)
			.renderInto(ms, buffer.getBuffer(RenderType.translucent()));

		CachedBufferer.partial(AllPartialModels.DISPLAY_LINK_GLOW, blockState)
			.light(LightTexture.FULL_BRIGHT)
			.color(color, color, color, 255)
			.disableDiffuse()
			.renderInto(ms, buffer.getBuffer(RenderTypes.additive()));

		ms.popPose();
	}

}
