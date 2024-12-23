package com.sakalti.create_re.foundation.recipe;

import java.util.ArrayList;
import java.util.List;

import com.sakalti.create_re.content.kinetics.deployer.ManualApplicationRecipe;
import com.sakalti.create_re.content.processing.recipe.ProcessingOutput;
import com.sakalti.create_re.content.processing.recipe.ProcessingRecipe;
import com.sakalti.create_re.foundation.item.ItemHelper;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;

public class RecipeApplier {
	public static void applyRecipeOn(ItemEntity entity, Recipe<?> recipe) {
		List<ItemStack> stacks = applyRecipeOn(entity.level(), entity.getItem(), recipe);
		if (stacks == null)
			return;
		if (stacks.isEmpty()) {
			entity.discard();
			return;
		}
		entity.setItem(stacks.remove(0));
		for (ItemStack additional : stacks) {
			ItemEntity entityIn = new ItemEntity(entity.level(), entity.getX(), entity.getY(), entity.getZ(), additional);
			entityIn.setDeltaMovement(entity.getDeltaMovement());
			entity.level().addFreshEntity(entityIn);
		}
	}

	public static List<ItemStack> applyRecipeOn(Level level, ItemStack stackIn, Recipe<?> recipe) {
		List<ItemStack> stacks;

		if (recipe instanceof ProcessingRecipe<?> pr) {
			stacks = new ArrayList<>();
			for (int i = 0; i < stackIn.getCount(); i++) {
				List<ProcessingOutput> outputs =
					pr instanceof ManualApplicationRecipe mar ? mar.getRollableResults() : pr.getRollableResults();
				for (ItemStack stack : pr.rollResults(outputs)) {
					for (ItemStack previouslyRolled : stacks) {
						if (stack.isEmpty())
							continue;
						if (!ItemHandlerHelper.canItemStacksStack(stack, previouslyRolled))
							continue;
						int amount = Math.min(previouslyRolled.getMaxStackSize() - previouslyRolled.getCount(),
							stack.getCount());
						previouslyRolled.grow(amount);
						stack.shrink(amount);
					}

					if (stack.isEmpty())
						continue;

					stacks.add(stack);
				}
			}
		} else {
			ItemStack out = recipe.getResultItem(level.registryAccess())
				.copy();
			stacks = ItemHelper.multipliedOutput(stackIn, out);
		}

		return stacks;
	}
}
