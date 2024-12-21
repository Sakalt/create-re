package com.sakalti.create_re;

import java.util.function.Supplier;

import com.sakalti.create_re.content.equipment.bell.SoulBaseParticle;
import com.sakalti.create_re.content.equipment.bell.SoulParticle;
import com.sakalti.create_re.content.fluids.particle.FluidParticleData;
import com.sakalti.create_re.content.kinetics.base.RotationIndicatorParticleData;
import com.sakalti.create_re.content.kinetics.fan.AirFlowParticleData;
import com.sakalti.create_re.content.kinetics.steamEngine.SteamJetParticleData;
import com.sakalti.create_re.content.trains.CubeParticleData;
import com.sakalti.create_re.foundation.particle.AirParticleData;
import com.sakalti.create_re.foundation.particle.ICustomParticleData;
import com.sakalti.create_re.foundation.utility.Lang;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public enum AllParticleTypes {

	ROTATION_INDICATOR(RotationIndicatorParticleData::new),
	AIR_FLOW(AirFlowParticleData::new),
	AIR(AirParticleData::new),
	STEAM_JET(SteamJetParticleData::new),
	CUBE(CubeParticleData::new),
	FLUID_PARTICLE(FluidParticleData::new),
	BASIN_FLUID(FluidParticleData::new),
	FLUID_DRIP(FluidParticleData::new),
	SOUL(SoulParticle.Data::new),
	SOUL_BASE(SoulBaseParticle.Data::new),
	SOUL_PERIMETER(SoulParticle.PerimeterData::new),
	SOUL_EXPANDING_PERIMETER(SoulParticle.ExpandingPerimeterData::new);

	private final ParticleEntry<?> entry;

	<D extends ParticleOptions> AllParticleTypes(Supplier<? extends ICustomParticleData<D>> typeFactory) {
		String name = Lang.asId(name());
		entry = new ParticleEntry<>(name, typeFactory);
	}

	public static void register(IEventBus modEventBus) {
		ParticleEntry.REGISTER.register(modEventBus);
	}

	@OnlyIn(Dist.CLIENT)
	public static void registerFactories(RegisterParticleProvidersEvent event) {
		for (AllParticleTypes particle : values())
			particle.entry.registerFactory(event);
	}

	public ParticleType<?> get() {
		return entry.object.get();
	}

	public String parameter() {
		return entry.name;
	}

	private static class ParticleEntry<D extends ParticleOptions> {
		private static final DeferredRegister<ParticleType<?>> REGISTER = DeferredRegister.create_re(ForgeRegistries.PARTICLE_TYPES, Create.ID);

		private final String name;
		private final Supplier<? extends ICustomParticleData<D>> typeFactory;
		private final RegistryObject<ParticleType<D>> object;

		public ParticleEntry(String name, Supplier<? extends ICustomParticleData<D>> typeFactory) {
			this.name = name;
			this.typeFactory = typeFactory;

			object = REGISTER.register(name, () -> this.typeFactory.get().create_reType());
		}

		@OnlyIn(Dist.CLIENT)
		public void registerFactory(RegisterParticleProvidersEvent event) {
			typeFactory.get()
				.register(object.get(), event);
		}

	}

}