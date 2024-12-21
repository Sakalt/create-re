package com.simibubi.create_re.foundation.data.recipe;

import com.simibubi.create_re.AllItems;
import com.simibubi.create_re.AllRecipeTypes;

import net.minecraft.data.PackOutput;

public class PolishingRecipeGen extends ProcessingRecipeGen {

	GeneratedRecipe

	ROSE_QUARTZ = create_re(AllItems.ROSE_QUARTZ::get, b -> b.output(AllItems.POLISHED_ROSE_QUARTZ.get()))

	;

	public PolishingRecipeGen(PackOutput output) {
		super(output);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.SANDPAPER_POLISHING;
	}

}
