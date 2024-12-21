package com.simibubi.create_re.infrastructure.command;

import com.simibubi.create_re.AllPackets;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class ToggleDebugCommand extends ConfigureConfigCommand {

	public ToggleDebugCommand() {
		super("rainbowDebug");
	}

	@Override
	protected void sendPacket(ServerPlayer player, String option) {
		AllPackets.getChannel().send(
				PacketDistributor.PLAYER.with(() -> player),
				new SConfigureConfigPacket(SConfigureConfigPacket.Actions.rainbowDebug.name(), option)
		);
	}
}
