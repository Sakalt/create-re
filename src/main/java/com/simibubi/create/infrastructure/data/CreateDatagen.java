package com.sakalti.create_re.infrastructure.data;

import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.sakalti.create_re.AllSoundEvents;
import com.sakalti.create_re.Create;
import com.sakalti.create_re.foundation.advancement.AllAdvancements;
import com.sakalti.create_re.foundation.data.DamageTypeTagGen;
import com.sakalti.create_re.foundation.data.recipe.MechanicalCraftingRecipeGen;
import com.sakalti.create_re.foundation.data.recipe.ProcessingRecipeGen;
import com.sakalti.create_re.foundation.data.recipe.SequencedAssemblyRecipeGen;
import com.sakalti.create_re.foundation.data.recipe.StandardRecipeGen;
import com.sakalti.create_re.foundation.ponder.PonderLocalization;
import com.sakalti.create_re.foundation.utility.FilesHelper;
import com.sakalti.create_re.infrastructure.ponder.AllPonderTags;
import com.sakalti.create_re.infrastructure.ponder.GeneralText;
import com.sakalti.create_re.infrastructure.ponder.PonderIndex;
import com.sakalti.create_re.infrastructure.ponder.SharedText;
import com.tterrag.registrate.providers.ProviderType;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;

public class CreateDatagen {
	public static void gatherData(GatherDataEvent event) {
		addExtraRegistrateData();

		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeClient()) {
			generator.addProvider(true, AllSoundEvents.provider(generator));
		}

		if (event.includeServer()) {
			GeneratedEntriesProvider generatedEntriesProvider = new GeneratedEntriesProvider(output, lookupProvider);
			lookupProvider = generatedEntriesProvider.getRegistryProvider();
			generator.addProvider(true, generatedEntriesProvider);

			generator.addProvider(true, new CreateRecipeSerializerTagsProvider(output, lookupProvider, existingFileHelper));
			generator.addProvider(true, new DamageTypeTagGen(output, lookupProvider, existingFileHelper));
			generator.addProvider(true, new AllAdvancements(output));
			generator.addProvider(true, new StandardRecipeGen(output));
			generator.addProvider(true, new MechanicalCraftingRecipeGen(output));
			generator.addProvider(true, new SequencedAssemblyRecipeGen(output));
			generator.addProvider(true, new VanillaHatOffsetGenerator(output));
			ProcessingRecipeGen.registerAll(generator, output);
		}
	}

	private static void addExtraRegistrateData() {
		CreateRegistrateTags.addGenerators();

		Create.REGISTRATE.addDataGenerator(ProviderType.LANG, provider -> {
			BiConsumer<String, String> langConsumer = provider::add;

			provideDefaultLang("interface", langConsumer);
			provideDefaultLang("tooltips", langConsumer);
			AllAdvancements.provideLang(langConsumer);
			AllSoundEvents.provideLang(langConsumer);
			providePonderLang(langConsumer);
		});
	}

	private static void provideDefaultLang(String fileName, BiConsumer<String, String> consumer) {
		String path = "assets/create_re/lang/default/" + fileName + ".json";
		JsonElement jsonElement = FilesHelper.loadJsonResource(path);
		if (jsonElement == null) {
			throw new IllegalStateException(String.format("Could not find default lang file: %s", path));
		}
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		for (Entry<String, JsonElement> entry : jsonObject.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue().getAsString();
			consumer.accept(key, value);
		}
	}

	private static void providePonderLang(BiConsumer<String, String> consumer) {
		// Register these since FMLClientSetupEvent does not run during datagen
		AllPonderTags.register();
		PonderIndex.register();

		SharedText.gatherText();
		PonderLocalization.generateSceneLang();

		GeneralText.provideLang(consumer);
		PonderLocalization.provideLang(Create.ID, consumer);
	}
}
