package com.simibubi.create_re.compat.jei.category;

import com.simibubi.create_re.compat.jei.category.animations.AnimatedKinetics;
import com.simibubi.create_re.content.kinetics.fan.processing.SplashingRecipe;
import com.simibubi.create_re.foundation.gui.element.GuiGameElement;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.level.material.Fluids;

public class FanWashingCategory extends ProcessingViaFanCategory.MultiOutput<SplashingRecipe> {

	public FanWashingCategory(Info<SplashingRecipe> info) {
		super(info);
	}

	@Override
	protected void renderAttachedBlock(GuiGraphics graphics) {
		GuiGameElement.of(Fluids.WATER)
			.scale(SCALE)
			.atLocal(0, 0, 2)
			.lighting(AnimatedKinetics.DEFAULT_LIGHTING)
			.render(graphics);
	}

}
