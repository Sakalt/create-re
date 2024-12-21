package com.simibubi.create_re.content.trains.graph;

import java.util.List;

import com.simibubi.create_re.content.trains.station.GlobalStation;
import com.simibubi.create_re.foundation.utility.Couple;

public class DiscoveredPath {
	public List<Couple<TrackNode>> path;
	public GlobalStation destination;
	public double distance;
	public double cost;

	public DiscoveredPath(double distance, double cost, List<Couple<TrackNode>> path, GlobalStation destination) {
		this.distance = distance;
		this.cost = cost;
		this.path = path;
		this.destination = destination;
	}
}
