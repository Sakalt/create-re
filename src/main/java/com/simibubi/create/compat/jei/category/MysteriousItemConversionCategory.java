package com.sakalti.create_re.compat.jei.category;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import com.sakalti.create_re.AllBlocks;
import com.sakalti.create_re.AllItems;
import com.sakalti.create_re.compat.jei.ConversionRecipe;
import com.sakalti.create_re.foundation.gui.AllGuiTextures;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import net.minecraft.client.gui.GuiGraphics;

@ParametersAreNonnullByDefault
public class MysteriousItemConversionCategory extends CreateRecipeCategory<ConversionRecipe> {

	public static final List<ConversionRecipe> RECIPES = new ArrayList<>();

	static {
		RECIPES.add(ConversionRecipe.create_re(AllItems.EMPTY_BLAZE_BURNER.asStack(), AllBlocks.BLAZE_BURNER.asStack()));
		RECIPES.add(ConversionRecipe.create_re(AllBlocks.PECULIAR_BELL.asStack(), AllBlocks.HAUNTED_BELL.asStack()));
//		RECIPES.add(ConversionRecipe.create_re(AllItems.CHROMATIC_COMPOUND.asStack(), AllItems.SHADOW_STEEL.asStack()));
//		RECIPES.add(ConversionRecipe.create_re(AllItems.CHROMATIC_COMPOUND.asStack(), AllItems.REFINED_RADIANCE.asStack()));
	}

	public MysteriousItemConversionCategory(Info<ConversionRecipe> info) {
		super(info);
	}

	@Override
	public void setRecipe(IRecipeLayoutBuilder builder, ConversionRecipe recipe, IFocusGroup focuses) {
		builder
				.addSlot(RecipeIngredientRole.INPUT, 27, 17)
				.setBackground(getRenderedSlot(), -1, -1)
				.addIngredients(recipe.getIngredients().get(0));
		builder
				.addSlot(RecipeIngredientRole.OUTPUT, 132, 17)
				.setBackground(getRenderedSlot(), -1, -1)
				.addItemStack(recipe.getRollableResults().get(0).getStack());
	}

	@Override
	public void draw(ConversionRecipe recipe, IRecipeSlotsView iRecipeSlotsView, GuiGraphics graphics, double mouseX, double mouseY) {
		AllGuiTextures.JEI_LONG_ARROW.render(graphics, 52, 20);
		AllGuiTextures.JEI_QUESTION_MARK.render(graphics, 77, 5);
	}

}
