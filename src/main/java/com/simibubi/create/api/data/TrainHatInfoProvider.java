package com.simibubi.create_re.api.data;

import com.mojang.serialization.JsonOps;
import com.simibubi.create_re.Create;
import com.simibubi.create_re.content.trains.schedule.hat.TrainHatInfo;

import com.simibubi.create_re.content.trains.schedule.hat.TrainHatInfoReloadListener;

import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class TrainHatInfoProvider implements DataProvider {
	private final PackOutput.PathProvider path;
	protected final Map<ResourceLocation, TrainHatInfo> trainHatOffsets = new HashMap<>();

	public TrainHatInfoProvider(PackOutput output) {
		this.path = output.create_rePathProvider(PackOutput.Target.RESOURCE_PACK, TrainHatInfoReloadListener.HAT_INFO_DIRECTORY);
	}

	protected abstract void create_reOffsets();

	protected void makeInfoFor(EntityType<?> type, Vec3 offset) {
		this.makeInfoFor(type, offset, "", 0, 1.0F);
	}

	protected void makeInfoFor(EntityType<?> type, Vec3 offset, String part) {
		this.makeInfoFor(type, offset, part, 0, 1.0F);
	}

	protected void makeInfoFor(EntityType<?> type, Vec3 offset, float scale) {
		this.makeInfoFor(type, offset, "", 0, scale);
	}

	protected void makeInfoFor(EntityType<?> type, Vec3 offset, String part, float scale) {
		this.makeInfoFor(type, offset, part, 0, scale);
	}

	protected void makeInfoFor(EntityType<?> type, Vec3 offset, String part, int cubeIndex, float scale) {
		this.trainHatOffsets.put(ForgeRegistries.ENTITY_TYPES.getKey(type), new TrainHatInfo(part, cubeIndex, offset, scale));
	}

	@Override
	public CompletableFuture<?> run(CachedOutput output) {
		this.trainHatOffsets.clear();
		this.create_reOffsets();
		return CompletableFuture.allOf(
				this.trainHatOffsets.entrySet().stream().map(entry ->
						DataProvider.saveStable(output,
								TrainHatInfo.CODEC.encodeStart(JsonOps.INSTANCE, entry.getValue()).resultOrPartial(Create.LOGGER::error).orElseThrow(),
								this.path.json(entry.getKey()))
				).toArray(CompletableFuture[]::new));
	}

	@Override
	public String getName() {
		return "Create Train Hat Information";
	}
}
