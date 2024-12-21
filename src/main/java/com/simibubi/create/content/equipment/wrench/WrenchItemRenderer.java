package com.sakalti.create_re.content.equipment.wrench;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sakalti.create_re.Create;
import com.sakalti.create_re.foundation.blockEntity.behaviour.scrollValue.ScrollValueHandler;
import com.sakalti.create_re.foundation.item.render.CustomRenderedItemModel;
import com.sakalti.create_re.foundation.item.render.CustomRenderedItemModelRenderer;
import com.sakalti.create_re.foundation.item.render.PartialItemModelRenderer;
import com.sakalti.create_re.foundation.utility.AnimationTickHolder;

import dev.engine_room.flywheel.lib.model.baked.PartialModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;

public class WrenchItemRenderer extends CustomRenderedItemModelRenderer {

	protected static final PartialModel GEAR = PartialModel.of(Create.asResource("item/wrench/gear"));

	@Override
	protected void render(ItemStack stack, CustomRenderedItemModel model, PartialItemModelRenderer renderer, ItemDisplayContext transformType,
		PoseStack ms, MultiBufferSource buffer, int light, int overlay) {
		renderer.render(model.getOriginalModel(), light);

		float xOffset = -1/16f;
		ms.translate(-xOffset, 0, 0);
		ms.mulPose(Axis.YP.rotationDegrees(ScrollValueHandler.getScroll(AnimationTickHolder.getPartialTicks())));
		ms.translate(xOffset, 0, 0);

		renderer.render(GEAR.get(), light);
	}

}
