package com.simibubi.create_re.content.kinetics.crusher;

import com.simibubi.create_re.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create_re.content.processing.recipe.ProcessingRecipeBuilder.ProcessingRecipeParams;
import com.simibubi.create_re.foundation.recipe.IRecipeTypeInfo;

import net.minecraftforge.items.wrapper.RecipeWrapper;

public abstract class AbstractCrushingRecipe extends ProcessingRecipe<RecipeWrapper> {

	public AbstractCrushingRecipe(IRecipeTypeInfo recipeType, ProcessingRecipeParams params) {
		super(recipeType, params);
	}

	@Override
	protected int getMaxInputCount() {
		return 1;
	}

	@Override
	protected boolean canSpecifyDuration() {
		return true;
	}
}
