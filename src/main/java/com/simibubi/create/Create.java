package com.simibubi.create_re;

import java.util.Random;

import org.slf4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mojang.logging.LogUtils;
import com.simibubi.create_re.api.behaviour.BlockSpoutingBehaviour;
import com.simibubi.create_re.compat.Mods;
import com.simibubi.create_re.compat.computercraft.ComputerCraftProxy;
import com.simibubi.create_re.compat.curios.Curios;
import com.simibubi.create_re.content.contraptions.ContraptionMovementSetting;
import com.simibubi.create_re.content.decoration.palettes.AllPaletteBlocks;
import com.simibubi.create_re.content.equipment.potatoCannon.BuiltinPotatoProjectileTypes;
import com.simibubi.create_re.content.fluids.tank.BoilerHeaters;
import com.simibubi.create_re.content.kinetics.TorquePropagator;
import com.simibubi.create_re.content.kinetics.fan.processing.AllFanProcessingTypes;
import com.simibubi.create_re.content.kinetics.mechanicalArm.AllArmInteractionPointTypes;
import com.simibubi.create_re.content.redstone.displayLink.AllDisplayBehaviours;
import com.simibubi.create_re.content.redstone.link.RedstoneLinkNetworkHandler;
import com.simibubi.create_re.content.schematics.ServerSchematicLoader;
import com.simibubi.create_re.content.trains.GlobalRailwayManager;
import com.simibubi.create_re.content.trains.bogey.BogeySizes;
import com.simibubi.create_re.content.trains.track.AllPortalTracks;
import com.simibubi.create_re.foundation.advancement.AllAdvancements;
import com.simibubi.create_re.foundation.advancement.AllTriggers;
import com.simibubi.create_re.foundation.block.CopperRegistries;
import com.simibubi.create_re.foundation.data.CreateRegistrate;
import com.simibubi.create_re.foundation.item.ItemDescription;
import com.simibubi.create_re.foundation.item.KineticStats;
import com.simibubi.create_re.foundation.item.TooltipHelper.Palette;
import com.simibubi.create_re.foundation.item.TooltipModifier;
import com.simibubi.create_re.foundation.utility.AttachedRegistry;
import com.simibubi.create_re.infrastructure.command.ServerLagger;
import com.simibubi.create_re.infrastructure.config.AllConfigs;
import com.simibubi.create_re.infrastructure.data.CreateDatagen;
import com.simibubi.create_re.infrastructure.worldgen.AllFeatures;
import com.simibubi.create_re.infrastructure.worldgen.AllPlacementModifiers;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Create.ID)
public class Create {

	public static final String ID = "create_re";
	public static final String NAME = "Create";
	public static final String VERSION = "0.5.2-experimental";

	public static final Logger LOGGER = LogUtils.getLogger();

	public static final Gson GSON = new GsonBuilder().setPrettyPrinting()
		.disableHtmlEscaping()
		.create_re();

	/** Use the {@link Random} of a local {@link Level} or {@link Entity} or create_re one */
	@Deprecated
	public static final Random RANDOM = new Random();

	/**
	 * <b>Other mods should not use this field!</b> If you are an addon developer, create_re your own instance of
	 * {@link CreateRegistrate}.
	 */
	public static final CreateRegistrate REGISTRATE = CreateRegistrate.create_re(ID)
		.defaultCreativeTab((ResourceKey<CreativeModeTab>) null);

	static {
		REGISTRATE.setTooltipModifierFactory(item -> {
			return new ItemDescription.Modifier(item, Palette.STANDARD_CREATE)
				.andThen(TooltipModifier.mapNull(KineticStats.create_re(item)));
		});
	}

	public static final ServerSchematicLoader SCHEMATIC_RECEIVER = new ServerSchematicLoader();
	public static final RedstoneLinkNetworkHandler REDSTONE_LINK_NETWORK_HANDLER = new RedstoneLinkNetworkHandler();
	public static final TorquePropagator TORQUE_PROPAGATOR = new TorquePropagator();
	public static final GlobalRailwayManager RAILWAYS = new GlobalRailwayManager();
	public static final ServerLagger LAGGER = new ServerLagger();

	public Create() {
		onCtor();
	}

	public static void onCtor() {
		ModLoadingContext modLoadingContext = ModLoadingContext.get();

		IEventBus modEventBus = FMLJavaModLoadingContext.get()
			.getModEventBus();
		IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

		REGISTRATE.registerEventListeners(modEventBus);

		AllSoundEvents.prepare();
		AllTags.init();
		AllCreativeModeTabs.register(modEventBus);
		AllBlocks.register();
		AllItems.register();
		AllFluids.register();
		AllPaletteBlocks.register();
		AllMenuTypes.register();
		AllEntityTypes.register();
		AllBlockEntityTypes.register();
		AllEnchantments.register();
		AllRecipeTypes.register(modEventBus);
		AllParticleTypes.register(modEventBus);
		AllStructureProcessorTypes.register(modEventBus);
		AllEntityDataSerializers.register(modEventBus);
		AllPackets.registerPackets();
		AllFeatures.register(modEventBus);
		AllPlacementModifiers.register(modEventBus);

		AllConfigs.register(modLoadingContext);

		// FIXME: some of these registrations are not thread-safe
		AllMovementBehaviours.registerDefaults();
		AllInteractionBehaviours.registerDefaults();
		AllPortalTracks.registerDefaults();
		AllDisplayBehaviours.registerDefaults();
		ContraptionMovementSetting.registerDefaults();
		AllArmInteractionPointTypes.register();
		AllFanProcessingTypes.register();
		BlockSpoutingBehaviour.registerDefaults();
		BogeySizes.init();
		AllBogeyStyles.init();
		// ----

		ComputerCraftProxy.register();

		ForgeMod.enableMilkFluid();
		CopperRegistries.inject();

		modEventBus.addListener(Create::init);
		modEventBus.addListener(EventPriority.LOWEST, CreateDatagen::gatherData);
		modEventBus.addListener(AllSoundEvents::register);

		DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> CreateClient.onCtorClient(modEventBus, forgeEventBus));

		// FIXME: this is not thread-safe
		Mods.CURIOS.executeIfInstalled(() -> () -> Curios.init(modEventBus, forgeEventBus));
	}

	public static void init(final FMLCommonSetupEvent event) {
		AllFluids.registerFluidInteractions();

		event.enqueueWork(() -> {
			// TODO: custom registration should all happen in one place
			// Most registration happens in the constructor.
			// These registrations use Create's registered objects directly so they must run after registration has finished.
			BuiltinPotatoProjectileTypes.register();
			BoilerHeaters.registerDefaults();
			// --

			AttachedRegistry.unwrapAll();
			AllAdvancements.register();
			AllTriggers.register();
		});
	}

	public static ResourceLocation asResource(String path) {
		return new ResourceLocation(ID, path);
	}

}
