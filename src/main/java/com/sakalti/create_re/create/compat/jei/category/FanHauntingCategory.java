package com.sakalti.create_re.compat.jei.category;

import com.sakalti.create_re.compat.jei.category.animations.AnimatedKinetics;
import com.sakalti.create_re.content.kinetics.fan.processing.HauntingRecipe;
import com.sakalti.create_re.foundation.gui.AllGuiTextures;
import com.sakalti.create_re.foundation.gui.element.GuiGameElement;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.block.Blocks;

public class FanHauntingCategory extends ProcessingViaFanCategory.MultiOutput<HauntingRecipe> {

	public FanHauntingCategory(Info<HauntingRecipe> info) {
		super(info);
	}

	@Override
	protected AllGuiTextures getBlockShadow() {
		return AllGuiTextures.JEI_LIGHT;
	}

	@Override
	protected void renderAttachedBlock(GuiGraphics graphics) {
		GuiGameElement.of(Blocks.SOUL_FIRE.defaultBlockState())
			.scale(SCALE)
			.atLocal(0, 0, 2)
			.lighting(AnimatedKinetics.DEFAULT_LIGHTING)
			.render(graphics);
	}

}
