package com.sakalti.create_re.compat.jei;

import java.util.function.Supplier;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.create_re.foundation.gui.element.GuiGameElement;

import mezz.jei.api.gui.drawable.IDrawable;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;

public class ItemIcon implements IDrawable {

	private Supplier<ItemStack> supplier;
	private ItemStack stack;

	public ItemIcon(Supplier<ItemStack> stack) {
		this.supplier = stack;
	}

	@Override
	public int getWidth() {
		return 18;
	}

	@Override
	public int getHeight() {
		return 18;
	}

	@Override
	public void draw(GuiGraphics graphics, int xOffset, int yOffset) {
		PoseStack matrixStack = graphics.pose();
		if (stack == null) {
			stack = supplier.get();
		}

		RenderSystem.enableDepthTest();
		matrixStack.pushPose();
		matrixStack.translate(xOffset + 1, yOffset + 1, 0);

		GuiGameElement.of(stack)
			.render(graphics);

		matrixStack.popPose();
	}


}
