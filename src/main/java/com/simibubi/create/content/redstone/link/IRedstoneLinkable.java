package com.sakalti.create_re.content.redstone.link;

import com.sakalti.create_re.content.redstone.link.RedstoneLinkNetworkHandler.Frequency;
import com.sakalti.create_re.foundation.utility.Couple;

import net.minecraft.core.BlockPos;

public interface IRedstoneLinkable {

	public int getTransmittedStrength();
	
	public void setReceivedStrength(int power);
	
	public boolean isListening();
	
	public boolean isAlive();
	
	public Couple<Frequency> getNetworkKey();
	
	public BlockPos getLocation();
	
}
