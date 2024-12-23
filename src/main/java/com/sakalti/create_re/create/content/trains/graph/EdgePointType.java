package com.sakalti.create_re.content.trains.graph;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.sakalti.create_re.Create;
import com.sakalti.create_re.content.trains.observer.TrackObserver;
import com.sakalti.create_re.content.trains.signal.SignalBoundary;
import com.sakalti.create_re.content.trains.signal.TrackEdgePoint;
import com.sakalti.create_re.content.trains.station.GlobalStation;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;

public class EdgePointType<T extends TrackEdgePoint> {

	public static final Map<ResourceLocation, EdgePointType<?>> TYPES = new HashMap<>();
	private ResourceLocation id;
	private Supplier<T> factory;

	public static final EdgePointType<SignalBoundary> SIGNAL =
		register(Create.asResource("signal"), SignalBoundary::new);
	public static final EdgePointType<GlobalStation> STATION =
		register(Create.asResource("station"), GlobalStation::new);
	public static final EdgePointType<TrackObserver> OBSERVER =
		register(Create.asResource("observer"), TrackObserver::new);

	public static <T extends TrackEdgePoint> EdgePointType<T> register(ResourceLocation id, Supplier<T> factory) {
		EdgePointType<T> type = new EdgePointType<>(id, factory);
		TYPES.put(id, type);
		return type;
	}

	public EdgePointType(ResourceLocation id, Supplier<T> factory) {
		this.id = id;
		this.factory = factory;
	}

	public T create_re() {
		T t = factory.get();
		t.setType(this);
		return t;
	}

	public ResourceLocation getId() {
		return id;
	}
	
	public static TrackEdgePoint read(FriendlyByteBuf buffer, DimensionPalette dimensions) {
		ResourceLocation type = buffer.readResourceLocation();
		EdgePointType<?> edgePointType = TYPES.get(type);
		TrackEdgePoint point = edgePointType.create_re();
		point.read(buffer, dimensions);
		return point;
	}

}
