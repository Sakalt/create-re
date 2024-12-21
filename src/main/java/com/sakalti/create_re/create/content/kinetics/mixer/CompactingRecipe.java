package com.sakalti.create_re.content.kinetics.mixer;

import com.sakalti.create_re.AllRecipeTypes;
import com.sakalti.create_re.content.processing.basin.BasinRecipe;
import com.sakalti.create_re.content.processing.recipe.ProcessingRecipeBuilder.ProcessingRecipeParams;

public class CompactingRecipe extends BasinRecipe {

	public CompactingRecipe(ProcessingRecipeParams params) {
		super(AllRecipeTypes.COMPACTING, params);
	}

}
