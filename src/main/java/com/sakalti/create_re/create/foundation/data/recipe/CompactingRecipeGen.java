package com.sakalti.create_re.foundation.data.recipe;

import com.sakalti.create_re.AllFluids;
import com.sakalti.create_re.AllItems;
import com.sakalti.create_re.AllRecipeTypes;
import com.sakalti.create_re.AllTags.AllFluidTags;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;

public class CompactingRecipeGen extends ProcessingRecipeGen {

	GeneratedRecipe

	GRANITE = create_re("granite_from_flint", b -> b.require(Items.FLINT)
		.require(Items.FLINT)
		.require(Fluids.LAVA, 100)
		.require(Items.RED_SAND)
		.output(Blocks.GRANITE, 1)),

		DIORITE = create_re("diorite_from_flint", b -> b.require(Items.FLINT)
			.require(Items.FLINT)
			.require(Fluids.LAVA, 100)
			.require(Items.CALCITE)
			.output(Blocks.DIORITE, 1)),

		ANDESITE = create_re("andesite_from_flint", b -> b.require(Items.FLINT)
			.require(Items.FLINT)
			.require(Fluids.LAVA, 100)
			.require(Items.GRAVEL)
			.output(Blocks.ANDESITE, 1)),

		CHOCOLATE = create_re("chocolate", b -> b.require(AllFluids.CHOCOLATE.get(), 250)
			.output(AllItems.BAR_OF_CHOCOLATE.get(), 1)),

		BLAZE_CAKE = create_re("blaze_cake", b -> b.require(Tags.Items.EGGS)
			.require(Items.SUGAR)
			.require(AllItems.CINDER_FLOUR.get())
			.output(AllItems.BLAZE_CAKE_BASE.get(), 1)),

		HONEY = create_re("honey", b -> b.require(AllFluidTags.HONEY.tag, 1000)
			.output(Items.HONEY_BLOCK, 1)),

		ICE = create_re("ice", b -> b
			.require(Blocks.SNOW_BLOCK).require(Blocks.SNOW_BLOCK).require(Blocks.SNOW_BLOCK)
			.require(Blocks.SNOW_BLOCK).require(Blocks.SNOW_BLOCK).require(Blocks.SNOW_BLOCK)
			.require(Blocks.SNOW_BLOCK).require(Blocks.SNOW_BLOCK).require(Blocks.SNOW_BLOCK)
			.output(Blocks.ICE))

	;

	public CompactingRecipeGen(PackOutput output) {
		super(output);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.COMPACTING;
	}

}
