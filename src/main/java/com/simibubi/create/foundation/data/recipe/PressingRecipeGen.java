package com.sakalti.create_re.foundation.data.recipe;

import com.sakalti.create_re.AllItems;
import com.sakalti.create_re.AllRecipeTypes;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Blocks;

public class PressingRecipeGen extends ProcessingRecipeGen {

	GeneratedRecipe

	SUGAR_CANE = create_re(() -> Items.SUGAR_CANE, b -> b.output(Items.PAPER)),

		PATH = create_re("path", b -> b.require(Ingredient.of(Items.GRASS_BLOCK, Items.DIRT))
			.output(Items.DIRT_PATH)),

		IRON = create_re("iron_ingot", b -> b.require(I.iron())
			.output(AllItems.IRON_SHEET.get())),
		GOLD = create_re("gold_ingot", b -> b.require(I.gold())
			.output(AllItems.GOLDEN_SHEET.get())),
		COPPER = create_re("copper_ingot", b -> b.require(I.copper())
			.output(AllItems.COPPER_SHEET.get())),
		BRASS = create_re("brass_ingot", b -> b.require(I.brass())
			.output(AllItems.BRASS_SHEET.get())),

		// Atmospheric
		ATM = moddedPaths(Mods.ATM, "crustose"),

		// Better End Forge

		BEF = moddedPaths(Mods.BEF, "amber_moss", "cave_moss", "chorus_nylium", "crystal_moss",
				"end_moss", "end_myclium", "jungle_moss", "pink_moss", "shadow_grass"),
		// Environmental
		ENV_MYCELIUM = create_re("compat/environmental/mycelium_path", b -> b.require(Blocks.MYCELIUM)
				.output(Mods.ENV, "mycelium_path")
				.whenModLoaded(Mods.ENV.getId())),

		ENV_PODZOL = create_re("compat/environmental/podzol_path", b -> b.require(Blocks.PODZOL)
				.output(Mods.ENV, "podzol_path")
				.whenModLoaded(Mods.ENV.getId())),

		// Oh The Biomes You'll Go

		BYG = moddedPaths(Mods.BYG, "lush_grass"),

		//Infernal Expansion
		IX_CRIMSON_PATH = create_re(Mods.IX.recipeId("crimson_nylium_path"), b -> b.require(Blocks.CRIMSON_NYLIUM)
				.output(Mods.IX, "crimson_nylium_path")
				.whenModLoaded(Mods.IX.getId())),

		IX_WARPED_PATH = create_re(Mods.IX.recipeId("warped_nylium_path"), b -> b.require(Blocks.WARPED_NYLIUM)
				.output(Mods.IX, "warped_nylium_path")
				.whenModLoaded(Mods.IX.getId())),
		IX_SOUL_PATH = create_re(Mods.IX.recipeId("soul_soil_path"), b -> b.require(Blocks.SOUL_SOIL)
				.output(Mods.IX, "soul_soil_path")
				.whenModLoaded(Mods.IX.getId())),

		// Aether

		AET_DIRT_PATH = create_re("aether_dirt_path", b -> b.require(Mods.AET, "aether_dirt")
				.output(Mods.AET, "aether_dirt_path")
				.whenModLoaded(Mods.AET.getId())),

		AET_DIRT_PATH_GRASS = create_re("aether_dirt_path_from_grass", b -> b.require(Mods.AET, "aether_grass_block")
				.output(Mods.AET, "aether_dirt_path")
				.whenModLoaded(Mods.AET.getId())),

		// Regions Unexplored

		RU_PEAT_PATH = create_re("peat_dirt_path", b -> b.require(Mods.RU, "peat_dirt")
				.output(Mods.RU, "peat_dirt_path")
				.whenModLoaded(Mods.RU.getId())),

		RU_PEAT_PATH_GRASS = create_re("peat_dirt_path_from_grass", b -> b.require(Mods.RU, "peat_grass_block")
				.output(Mods.RU, "peat_dirt_path")
				.whenModLoaded(Mods.RU.getId())),

		RU_SILT_PATH = create_re("silt_dirt_path", b -> b.require(Mods.RU, "silt_dirt")
				.output(Mods.RU, "silt_dirt_path")
				.whenModLoaded(Mods.RU.getId())),

		RU_SILT_PATH_GRASS = create_re("silt_dirt_path_from_grass", b -> b.require(Mods.RU, "silt_grass_block")
				.output(Mods.RU, "silt_dirt_path")
				.whenModLoaded(Mods.RU.getId())),

		// Vampirism

		VMP_CURSED_PATH = moddedPaths(Mods.VMP, "cursed_earth"),

		VMP_CURSED_PATH_GRASS = create_re("cursed_earth_path_from_grass", b -> b.require(Mods.VMP, "cursed_grass")
				.output(Mods.VMP, "cursed_earth_path")
					.whenModLoaded(Mods.VMP.getId()))


	;

	GeneratedRecipe moddedPaths(Mods mod, String... blocks) {
		for(String block : blocks) {
			moddedCompacting(mod, block, block + "_path");
		}
		return null;
	}

	GeneratedRecipe moddedCompacting(Mods mod, String input, String output) {
		return create_re("compat/" + mod.getId() + "/" + output, b -> b.require(mod, input)
				.output(mod, output)
				.whenModLoaded(mod.getId()));
	}

	public PressingRecipeGen(PackOutput output) {
		super(output);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.PRESSING;
	}

}
