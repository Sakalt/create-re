package com.sakalti.create_re;

import com.sakalti.create_re.content.contraptions.AbstractContraptionEntity;
import com.sakalti.create_re.content.contraptions.ControlledContraptionEntity;
import com.sakalti.create_re.content.contraptions.OrientedContraptionEntity;
import com.sakalti.create_re.content.contraptions.actors.seat.SeatEntity;
import com.sakalti.create_re.content.contraptions.gantry.GantryContraptionEntity;
import com.sakalti.create_re.content.contraptions.glue.SuperGlueEntity;
import com.sakalti.create_re.content.contraptions.glue.SuperGlueRenderer;
import com.sakalti.create_re.content.contraptions.render.ContraptionEntityRenderer;
import com.sakalti.create_re.content.contraptions.render.ContraptionVisual;
import com.sakalti.create_re.content.contraptions.render.OrientedContraptionEntityRenderer;
import com.sakalti.create_re.content.equipment.blueprint.BlueprintEntity;
import com.sakalti.create_re.content.equipment.blueprint.BlueprintRenderer;
import com.sakalti.create_re.content.equipment.potatoCannon.PotatoProjectileEntity;
import com.sakalti.create_re.content.equipment.potatoCannon.PotatoProjectileRenderer;
import com.sakalti.create_re.content.trains.entity.CarriageContraptionEntity;
import com.sakalti.create_re.content.trains.entity.CarriageContraptionEntityRenderer;
import com.sakalti.create_re.content.trains.entity.CarriageContraptionVisual;
import com.sakalti.create_re.foundation.data.CreateEntityBuilder;
import com.sakalti.create_re.foundation.utility.Lang;
import com.tterrag.registrate.util.entry.EntityEntry;
import com.tterrag.registrate.util.nullness.NonNullConsumer;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.MobCategory;

public class AllEntityTypes {

	public static final EntityEntry<OrientedContraptionEntity> ORIENTED_CONTRAPTION = contraption("contraption",
		OrientedContraptionEntity::new, () -> OrientedContraptionEntityRenderer::new, 5, 3, true)
			.visual(() -> ContraptionVisual::new)
			.register();
	public static final EntityEntry<ControlledContraptionEntity> CONTROLLED_CONTRAPTION =
		contraption("stationary_contraption", ControlledContraptionEntity::new, () -> ContraptionEntityRenderer::new,
			20, 40, false)
				.visual(() -> ContraptionVisual::new)
				.register();
	public static final EntityEntry<GantryContraptionEntity> GANTRY_CONTRAPTION = contraption("gantry_contraption",
		GantryContraptionEntity::new, () -> ContraptionEntityRenderer::new, 10, 40, false)
			.visual(() -> ContraptionVisual::new)
			.register();
	public static final EntityEntry<CarriageContraptionEntity> CARRIAGE_CONTRAPTION =
		contraption("carriage_contraption", CarriageContraptionEntity::new,
			() -> CarriageContraptionEntityRenderer::new, 15, 3, true)
				.visual(() -> CarriageContraptionVisual::new)
				.register();

	public static final EntityEntry<SuperGlueEntity> SUPER_GLUE =
		register("super_glue", SuperGlueEntity::new, () -> SuperGlueRenderer::new, MobCategory.MISC, 10,
			Integer.MAX_VALUE, false, true, SuperGlueEntity::build).register();

	public static final EntityEntry<BlueprintEntity> CRAFTING_BLUEPRINT =
		register("crafting_blueprint", BlueprintEntity::new, () -> BlueprintRenderer::new, MobCategory.MISC, 10,
			Integer.MAX_VALUE, false, true, BlueprintEntity::build).register();

	public static final EntityEntry<PotatoProjectileEntity> POTATO_PROJECTILE =
		register("potato_projectile", PotatoProjectileEntity::new, () -> PotatoProjectileRenderer::new,
			MobCategory.MISC, 4, 20, true, false, PotatoProjectileEntity::build).register();

	public static final EntityEntry<SeatEntity> SEAT = register("seat", SeatEntity::new, () -> SeatEntity.Render::new,
		MobCategory.MISC, 5, Integer.MAX_VALUE, false, true, SeatEntity::build).register();

	//

	private static <T extends Entity> CreateEntityBuilder<T, ?> contraption(String name, EntityFactory<T> factory,
		NonNullSupplier<NonNullFunction<EntityRendererProvider.Context, EntityRenderer<? super T>>> renderer, int range,
		int updateFrequency, boolean sendVelocity) {
		return register(name, factory, renderer, MobCategory.MISC, range, updateFrequency, sendVelocity, true,
			AbstractContraptionEntity::build);
	}

	private static <T extends Entity> CreateEntityBuilder<T, ?> register(String name, EntityFactory<T> factory,
		NonNullSupplier<NonNullFunction<EntityRendererProvider.Context, EntityRenderer<? super T>>> renderer,
		MobCategory group, int range, int updateFrequency, boolean sendVelocity, boolean immuneToFire,
		NonNullConsumer<EntityType.Builder<T>> propertyBuilder) {
		String id = Lang.asId(name);
		return (CreateEntityBuilder<T, ?>) Create.REGISTRATE
			.entity(id, factory, group)
			.properties(b -> b.setTrackingRange(range)
				.setUpdateInterval(updateFrequency)
				.setShouldReceiveVelocityUpdates(sendVelocity))
			.properties(propertyBuilder)
			.properties(b -> {
				if (immuneToFire)
					b.fireImmune();
			})
			.renderer(renderer);
	}

	public static void register() {}
}
