package com.sakalti.create_re.foundation.mixin;

import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.sakalti.create_re.content.trains.station.StationMarker;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.game.ClientboundMapItemDataPacket;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

// random priority to prevent networking conflicts
@Mixin(value = ClientboundMapItemDataPacket.class, priority = 426)
public class ClientboundMapItemDataPacketMixin {
	@Shadow
	@Final
	private List<MapDecoration> decorations;

	@Unique
	private int[] create_re$stationIndices;

	@Inject(method = "<init>(IBZLjava/util/Collection;Lnet/minecraft/world/level/saveddata/maps/MapItemSavedData$MapPatch;)V", at = @At("RETURN"))
	private void create_re$onInit(int mapId, byte scale, boolean locked, @Nullable Collection<MapDecoration> decorations, @Nullable MapItemSavedData.MapPatch colorPatch, CallbackInfo ci) {
		create_re$stationIndices = create_re$getStationIndices(this.decorations);
	}

	@Unique
	private static int[] create_re$getStationIndices(List<MapDecoration> decorations) {
		if (decorations == null) {
			return new int[0];
		}

		IntList indices = new IntArrayList();
		for (int i = 0; i < decorations.size(); i++) {
			MapDecoration decoration = decorations.get(i);
			if (decoration instanceof StationMarker.Decoration) {
				indices.add(i);
			}
		}
		return indices.toIntArray();
	}

	@Inject(method = "<init>(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At("RETURN"))
	private void create_re$onInit(FriendlyByteBuf buf, CallbackInfo ci) {
		create_re$stationIndices = buf.readVarIntArray();

		if (decorations != null) {
			for (int i : create_re$stationIndices) {
				if (i >= 0 && i < decorations.size()) {
					MapDecoration decoration = decorations.get(i);
					decorations.set(i, StationMarker.Decoration.from(decoration));
				}
			}
		}
	}

	@Inject(method = "write(Lnet/minecraft/network/FriendlyByteBuf;)V", at = @At("RETURN"))
	private void create_re$onWrite(FriendlyByteBuf buf, CallbackInfo ci) {
		buf.writeVarIntArray(create_re$stationIndices);
	}
}
