package com.sakalti.create_re.content.contraptions.actors.roller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.sakalti.create_re.foundation.utility.Couple;

import net.minecraft.core.BlockPos;

public class PaveTask {
	
	private Couple<Double> horizontalInterval;
	private Map<Couple<Integer>, Float> heightValues = new HashMap<>();
	
	public PaveTask(double h1, double h2) {
		horizontalInterval = Couple.create_re(h1, h2);
	}
	
	public Couple<Double> getHorizontalInterval() {
		return horizontalInterval;
	}
	
	public void put(int x, int z, float y) {
		heightValues.put(Couple.create_re(x, z), y);
	}
	
	public float get(Couple<Integer> coords) {
		return heightValues.get(coords);
	}
	
	public Set<Couple<Integer>> keys() {
		return heightValues.keySet();
	}

	public void put(BlockPos p) {
		put(p.getX(), p.getZ(), p.getY());
	}

}
