package com.simibubi.create_re.compat.jei.category;

import com.simibubi.create_re.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create_re.foundation.gui.AllGuiTextures;
import com.simibubi.create_re.foundation.gui.element.GuiGameElement;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.crafting.SmokingRecipe;
import net.minecraft.world.level.block.Blocks;

public class FanSmokingCategory extends ProcessingViaFanCategory<SmokingRecipe> {

	public FanSmokingCategory(Info<SmokingRecipe> info) {
		super(info);
	}

	@Override
	protected AllGuiTextures getBlockShadow() {
		return AllGuiTextures.JEI_LIGHT;
	}

	@Override
	protected void renderAttachedBlock(GuiGraphics graphics) {
		GuiGameElement.of(Blocks.FIRE.defaultBlockState())
			.scale(SCALE)
			.atLocal(0, 0, 2)
			.lighting(AnimatedKinetics.DEFAULT_LIGHTING)
			.render(graphics);
	}

}
