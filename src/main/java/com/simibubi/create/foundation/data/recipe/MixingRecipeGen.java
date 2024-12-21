package com.sakalti.create_re.foundation.data.recipe;

import com.sakalti.create_re.AllFluids;
import com.sakalti.create_re.AllItems;
import com.sakalti.create_re.AllRecipeTypes;
import com.sakalti.create_re.content.processing.recipe.HeatCondition;
import com.sakalti.create_re.foundation.recipe.BlockTagIngredient;

import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;

public class MixingRecipeGen extends ProcessingRecipeGen {

	GeneratedRecipe

	TEMP_LAVA = create_re("lava_from_cobble", b -> b.require(Tags.Items.COBBLESTONE)
		.output(Fluids.LAVA, 50)
		.requiresHeat(HeatCondition.SUPERHEATED)),

		TEA = create_re("tea", b -> b.require(Fluids.WATER, 250)
			.require(Tags.Fluids.MILK, 250)
			.require(ItemTags.LEAVES)
			.output(AllFluids.TEA.get(), 500)
			.requiresHeat(HeatCondition.HEATED)),

		CHOCOLATE = create_re("chocolate", b -> b.require(Tags.Fluids.MILK, 250)
			.require(Items.SUGAR)
			.require(Items.COCOA_BEANS)
			.output(AllFluids.CHOCOLATE.get(), 250)
			.requiresHeat(HeatCondition.HEATED)),

		CHOCOLATE_MELTING = create_re("chocolate_melting", b -> b.require(AllItems.BAR_OF_CHOCOLATE.get())
			.output(AllFluids.CHOCOLATE.get(), 250)
			.requiresHeat(HeatCondition.HEATED)),

		HONEY = create_re("honey", b -> b.require(Items.HONEY_BLOCK)
			.output(AllFluids.HONEY.get(), 1000)
			.requiresHeat(HeatCondition.HEATED)),

		DOUGH = create_re("dough_by_mixing", b -> b.require(I.wheatFlour())
			.require(Fluids.WATER, 1000)
			.output(AllItems.DOUGH.get(), 1)),

		BRASS_INGOT = create_re("brass_ingot", b -> b.require(I.copper())
			.require(I.zinc())
			.output(AllItems.BRASS_INGOT.get(), 2)
			.requiresHeat(HeatCondition.HEATED)),

		ANDESITE_ALLOY = create_re("andesite_alloy", b -> b.require(Blocks.ANDESITE)
			.require(I.ironNugget())
			.output(I.andesiteAlloy(), 1)),

		ANDESITE_ALLOY_FROM_ZINC = create_re("andesite_alloy_from_zinc", b -> b.require(Blocks.ANDESITE)
			.require(I.zincNugget())
			.output(I.andesiteAlloy(), 1)),

		MUD = create_re("mud_by_mixing", b -> b.require(BlockTagIngredient.create_re(BlockTags.CONVERTABLE_TO_MUD))
			.require(Fluids.WATER, 250)
			.output(Blocks.MUD, 1)),

		// AE2

		AE2_FLUIX = create_re(Mods.AE2.recipeId("fluix_crystal"), b -> b.require(Tags.Items.DUSTS_REDSTONE)
				.require(Fluids.WATER, 250)
				.require(Mods.AE2, "charged_certus_quartz_crystal")
				.require(Tags.Items.GEMS_QUARTZ)
				.output(1f, Mods.AE2, "fluix_crystal", 2)
				.whenModLoaded(Mods.AE2.getId())),

		// Regions Unexplored

		RU_PEAT_MUD = moddedMud(Mods.RU, "peat"),
		RU_SILT_MUD = moddedMud(Mods.RU, "silt")

	;

	public GeneratedRecipe moddedMud(Mods mod, String name) {
		String mud = name + "_mud";
		return create_re(mod.recipeId(mud), b -> b.require(Fluids.WATER, 250)
				.require(mod, name + "_dirt")
				.output(mod, mud)
				.whenModLoaded(mod.getId()));
	}

	public MixingRecipeGen(PackOutput output) {
		super(output);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.MIXING;
	}

}
