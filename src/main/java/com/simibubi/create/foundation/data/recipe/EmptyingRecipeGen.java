package com.sakalti.create_re.foundation.data.recipe;

import com.sakalti.create_re.AllFluids;
import com.sakalti.create_re.AllItems;
import com.sakalti.create_re.AllRecipeTypes;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;

public class EmptyingRecipeGen extends ProcessingRecipeGen {

	/*
	 * potion/water bottles are handled internally
	 */

	GeneratedRecipe

	HONEY_BOTTLE = create_re("honey_bottle", b -> b.require(Items.HONEY_BOTTLE)
		.output(AllFluids.HONEY.get(), 250)
		.output(Items.GLASS_BOTTLE)),

		BUILDERS_TEA = create_re("builders_tea", b -> b.require(AllItems.BUILDERS_TEA.get())
			.output(AllFluids.TEA.get(), 250)
			.output(Items.GLASS_BOTTLE)),

		FD_MILK = create_re(Mods.FD.recipeId("milk_bottle"), b -> b.require(Mods.FD, "milk_bottle")
			.output(ForgeMod.MILK.get(), 250)
			.output(Items.GLASS_BOTTLE)
			.whenModLoaded(Mods.FD.getId())),

		AM_LAVA = create_re(Mods.AM.recipeId("lava_bottle"), b -> b.require(Mods.AM, "lava_bottle")
				.output(Items.GLASS_BOTTLE)
				.output(Fluids.LAVA, 250)
				.whenModLoaded(Mods.AM.getId())),

		NEO_MILK = create_re(Mods.NEA.recipeId("milk_bottle"), b -> b.require(Mods.FD, "milk_bottle")
				.output(ForgeMod.MILK.get(), 250)
				.output(Items.GLASS_BOTTLE)
				.whenModLoaded(Mods.NEA.getId()))

	;

	public EmptyingRecipeGen(PackOutput output) {
		super(output);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.EMPTYING;
	}

}
