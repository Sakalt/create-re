package com.simibubi.create_re.foundation.data.recipe;

import com.simibubi.create_re.AllFluids;
import com.simibubi.create_re.AllItems;
import com.simibubi.create_re.AllRecipeTypes;
import com.simibubi.create_re.AllTags.AllFluidTags;
import com.simibubi.create_re.content.fluids.potion.PotionFluidHandler;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.Tags;

public class FillingRecipeGen extends ProcessingRecipeGen {

	GeneratedRecipe

	HONEY_BOTTLE = create_re("honey_bottle", b -> b.require(AllFluidTags.HONEY.tag, 250)
		.require(Items.GLASS_BOTTLE)
		.output(Items.HONEY_BOTTLE)),

		BUILDERS_TEA = create_re("builders_tea", b -> b.require(AllFluids.TEA.get(), 250)
			.require(Items.GLASS_BOTTLE)
			.output(AllItems.BUILDERS_TEA.get())),

		FD_MILK = create_re(Mods.FD.recipeId("milk_bottle"), b -> b.require(Tags.Fluids.MILK, 250)
			.require(Items.GLASS_BOTTLE)
			.output(1, Mods.FD, "milk_bottle", 1)
			.whenModLoaded(Mods.FD.getId())),

		BLAZE_CAKE = create_re("blaze_cake", b -> b.require(Fluids.LAVA, 250)
			.require(AllItems.BLAZE_CAKE_BASE.get())
			.output(AllItems.BLAZE_CAKE.get())),

		HONEYED_APPLE = create_re("honeyed_apple", b -> b.require(AllFluidTags.HONEY.tag, 250)
			.require(Items.APPLE)
			.output(AllItems.HONEYED_APPLE.get())),

		SWEET_ROLL = create_re("sweet_roll", b -> b.require(Tags.Fluids.MILK, 250)
			.require(Items.BREAD)
			.output(AllItems.SWEET_ROLL.get())),

		CHOCOLATE_BERRIES = create_re("chocolate_glazed_berries", b -> b.require(AllFluids.CHOCOLATE.get(), 250)
			.require(Items.SWEET_BERRIES)
			.output(AllItems.CHOCOLATE_BERRIES.get())),

		GRASS_BLOCK = create_re("grass_block", b -> b.require(Fluids.WATER, 500)
			.require(Items.DIRT)
			.output(Items.GRASS_BLOCK)),

		GUNPOWDER = create_re("gunpowder", b -> b.require(PotionFluidHandler.potionIngredient(Potions.HARMING, 25))
			.require(AllItems.CINDER_FLOUR.get())
			.output(Items.GUNPOWDER)),

		REDSTONE = create_re("redstone", b -> b.require(PotionFluidHandler.potionIngredient(Potions.STRENGTH, 25))
			.require(AllItems.CINDER_FLOUR.get())
			.output(Items.REDSTONE)),

		GLOWSTONE = create_re("glowstone", b -> b.require(PotionFluidHandler.potionIngredient(Potions.NIGHT_VISION, 25))
			.require(AllItems.CINDER_FLOUR.get())
			.output(Items.GLOWSTONE_DUST)),


		AM_LAVA = create_re(Mods.AM.recipeId("lava_bottle"), b -> b.require(Fluids.LAVA, 250)
			.require(Items.GLASS_BOTTLE)
			.output(1, Mods.AM, "lava_bottle", 1)
			.whenModLoaded(Mods.AM.getId())),

		BYG_LUSH_GRASS = create_re(Mods.BYG.recipeId("lush_grass_block"), b -> b.require(Mods.BYG, "lush_dirt")
			.require(Fluids.WATER, 500)
			.output(Mods.BYG, "lush_grass_block")
			.whenModLoaded(Mods.BYG.getId())),

		NEA_MILK = create_re(Mods.NEA.recipeId("milk_bottle"), b -> b.require(Tags.Fluids.MILK, 250)
			.require(Items.GLASS_BOTTLE)
			.output(1, Mods.NEA, "milk_bottle", 1)
			.whenModLoaded(Mods.NEA.getId())),

		AET_GRASS = moddedGrass(Mods.AET, "aether"),

		RU_PEAT_GRAS = moddedGrass(Mods.RU, "peat"),

		RU_SILT_GRAS = moddedGrass(Mods.RU, "silt"),

		// Vampirism

		VMP_CURSED_GRASS = create_re(Mods.VMP.recipeId("cursed_grass"), b -> b.require(Fluids.WATER, 500)
			.require(Mods.VMP, "cursed_earth")
			.output(Mods.VMP, "cursed_grass")
			.whenModLoaded(Mods.VMP.getId()));

	;

	public GeneratedRecipe moddedGrass(Mods mod, String name) {
		String grass = name + "_grass_block";
		return create_re(mod.recipeId(grass), b -> b.require(Fluids.WATER, 500)
				.require(mod, name + "_dirt")
				.output(mod, grass)
				.whenModLoaded(mod.getId()));
	}

	public FillingRecipeGen(PackOutput output) {
		super(output);
	}

	@Override
	protected AllRecipeTypes getRecipeType() {
		return AllRecipeTypes.FILLING;
	}

}
