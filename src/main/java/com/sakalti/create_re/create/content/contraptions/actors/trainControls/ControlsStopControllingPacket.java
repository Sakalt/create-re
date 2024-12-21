package com.sakalti.create_re.content.contraptions.actors.trainControls;

import com.sakalti.create_re.foundation.networking.SimplePacketBase;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent.Context;

public class ControlsStopControllingPacket extends SimplePacketBase {

	public ControlsStopControllingPacket() {}

	public ControlsStopControllingPacket(FriendlyByteBuf buffer) {}

	@Override
	public void write(FriendlyByteBuf buffer) {}

	@Override
	public boolean handle(Context context) {
		context.enqueueWork(ControlsHandler::stopControlling);
		return true;
	}

}
