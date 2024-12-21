package com.sakalti.create_re.content.kinetics.mixer;

import com.sakalti.create_re.AllRecipeTypes;
import com.sakalti.create_re.content.processing.basin.BasinRecipe;
import com.sakalti.create_re.content.processing.recipe.ProcessingRecipeBuilder.ProcessingRecipeParams;

public class MixingRecipe extends BasinRecipe {

	public MixingRecipe(ProcessingRecipeParams params) {
		super(AllRecipeTypes.MIXING, params);
	}

}
