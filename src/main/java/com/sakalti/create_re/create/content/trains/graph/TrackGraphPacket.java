package com.sakalti.create_re.content.trains.graph;

import java.util.UUID;

import com.sakalti.create_re.CreateClient;
import com.sakalti.create_re.content.trains.GlobalRailwayManager;
import com.sakalti.create_re.foundation.networking.SimplePacketBase;

import net.minecraftforge.network.NetworkEvent.Context;

public abstract class TrackGraphPacket extends SimplePacketBase {

	public UUID graphId;
	public int netId;
	public boolean packetDeletesGraph;

	@Override
	public boolean handle(Context context) {
		context.enqueueWork(() -> handle(CreateClient.RAILWAYS, CreateClient.RAILWAYS.getOrCreateGraph(graphId, netId)));
		return true;
	}

	protected abstract void handle(GlobalRailwayManager manager, TrackGraph graph);

}
