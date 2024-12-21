package com.sakalti.create_re;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.jetbrains.annotations.Nullable;

import com.sakalti.create_re.compat.jei.ConversionRecipe;
import com.sakalti.create_re.content.equipment.sandPaper.SandPaperPolishingRecipe;
import com.sakalti.create_re.content.equipment.toolbox.ToolboxDyeingRecipe;
import com.sakalti.create_re.content.fluids.transfer.EmptyingRecipe;
import com.sakalti.create_re.content.fluids.transfer.FillingRecipe;
import com.sakalti.create_re.content.kinetics.crafter.MechanicalCraftingRecipe;
import com.sakalti.create_re.content.kinetics.crusher.CrushingRecipe;
import com.sakalti.create_re.content.kinetics.deployer.DeployerApplicationRecipe;
import com.sakalti.create_re.content.kinetics.deployer.ManualApplicationRecipe;
import com.sakalti.create_re.content.kinetics.fan.processing.HauntingRecipe;
import com.sakalti.create_re.content.kinetics.fan.processing.SplashingRecipe;
import com.sakalti.create_re.content.kinetics.millstone.MillingRecipe;
import com.sakalti.create_re.content.kinetics.mixer.CompactingRecipe;
import com.sakalti.create_re.content.kinetics.mixer.MixingRecipe;
import com.sakalti.create_re.content.kinetics.press.PressingRecipe;
import com.sakalti.create_re.content.kinetics.saw.CuttingRecipe;
import com.sakalti.create_re.content.processing.basin.BasinRecipe;
import com.sakalti.create_re.content.processing.recipe.ProcessingRecipeBuilder.ProcessingRecipeFactory;
import com.sakalti.create_re.content.processing.recipe.ProcessingRecipeSerializer;
import com.sakalti.create_re.content.processing.sequenced.SequencedAssemblyRecipeSerializer;
import com.sakalti.create_re.foundation.recipe.IRecipeTypeInfo;
import com.sakalti.create_re.foundation.utility.Lang;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public enum AllRecipeTypes implements IRecipeTypeInfo {

	CONVERSION(ConversionRecipe::new),
	CRUSHING(CrushingRecipe::new),
	CUTTING(CuttingRecipe::new),
	MILLING(MillingRecipe::new),
	BASIN(BasinRecipe::new),
	MIXING(MixingRecipe::new),
	COMPACTING(CompactingRecipe::new),
	PRESSING(PressingRecipe::new),
	SANDPAPER_POLISHING(SandPaperPolishingRecipe::new),
	SPLASHING(SplashingRecipe::new),
	HAUNTING(HauntingRecipe::new),
	DEPLOYING(DeployerApplicationRecipe::new),
	FILLING(FillingRecipe::new),
	EMPTYING(EmptyingRecipe::new),
	ITEM_APPLICATION(ManualApplicationRecipe::new),

	MECHANICAL_CRAFTING(MechanicalCraftingRecipe.Serializer::new),
	SEQUENCED_ASSEMBLY(SequencedAssemblyRecipeSerializer::new),

	TOOLBOX_DYEING(() -> new SimpleCraftingRecipeSerializer<>(ToolboxDyeingRecipe::new), () -> RecipeType.CRAFTING, false);

	public static final Predicate<? super Recipe<?>> CAN_BE_AUTOMATED = r -> !r.getId()
		.getPath()
		.endsWith("_manual_only");

	private final ResourceLocation id;
	private final RegistryObject<RecipeSerializer<?>> serializerObject;
	@Nullable
	private final RegistryObject<RecipeType<?>> typeObject;
	private final Supplier<RecipeType<?>> type;

	AllRecipeTypes(Supplier<RecipeSerializer<?>> serializerSupplier, Supplier<RecipeType<?>> typeSupplier, boolean registerType) {
		String name = Lang.asId(name());
		id = Create.asResource(name);
		serializerObject = Registers.SERIALIZER_REGISTER.register(name, serializerSupplier);
		if (registerType) {
			typeObject = Registers.TYPE_REGISTER.register(name, typeSupplier);
			type = typeObject;
		} else {
			typeObject = null;
			type = typeSupplier;
		}
	}

	AllRecipeTypes(Supplier<RecipeSerializer<?>> serializerSupplier) {
		String name = Lang.asId(name());
		id = Create.asResource(name);
		serializerObject = Registers.SERIALIZER_REGISTER.register(name, serializerSupplier);
		typeObject = Registers.TYPE_REGISTER.register(name, () -> RecipeType.simple(id));
		type = typeObject;
	}

	AllRecipeTypes(ProcessingRecipeFactory<?> processingFactory) {
		this(() -> new ProcessingRecipeSerializer<>(processingFactory));
	}

	public static void register(IEventBus modEventBus) {
		ShapedRecipe.setCraftingSize(9, 9);
		Registers.SERIALIZER_REGISTER.register(modEventBus);
		Registers.TYPE_REGISTER.register(modEventBus);
	}

	@Override
	public ResourceLocation getId() {
		return id;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends RecipeSerializer<?>> T getSerializer() {
		return (T) serializerObject.get();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends RecipeType<?>> T getType() {
		return (T) type.get();
	}

	public <C extends Container, T extends Recipe<C>> Optional<T> find(C inv, Level world) {
		return world.getRecipeManager()
			.getRecipeFor(getType(), inv, world);
	}
	
	public static boolean shouldIgnoreInAutomation(Recipe<?> recipe) {
		RecipeSerializer<?> serializer = recipe.getSerializer();
		if (serializer != null && AllTags.AllRecipeSerializerTags.AUTOMATION_IGNORE.matches(serializer))
			return true;
		return !CAN_BE_AUTOMATED.test(recipe);
	}

	private static class Registers {
		private static final DeferredRegister<RecipeSerializer<?>> SERIALIZER_REGISTER = DeferredRegister.create_re(ForgeRegistries.RECIPE_SERIALIZERS, Create.ID);
		private static final DeferredRegister<RecipeType<?>> TYPE_REGISTER = DeferredRegister.create_re(Registries.RECIPE_TYPE, Create.ID);
	}

}