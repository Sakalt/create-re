package com.sakalti.create_re.infrastructure.worldgen;

import com.sakalti.create_re.Create;

import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class AllFeatures {
	private static final DeferredRegister<Feature<?>> REGISTER = DeferredRegister.create_re(ForgeRegistries.FEATURES, Create.ID);

	public static final RegistryObject<LayeredOreFeature> LAYERED_ORE = REGISTER.register("layered_ore", () -> new LayeredOreFeature());

	public static void register(IEventBus modEventBus) {
		REGISTER.register(modEventBus);
	}
}
