package com.sakalti.create_re.infrastructure.command;

import static net.minecraft.commands.Commands.literal;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.sakalti.create_re.AllPackets;
import com.sakalti.create_re.foundation.utility.Lang;
import com.sakalti.create_re.infrastructure.debugInfo.ServerDebugInfoPacket;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.PacketDistributor;

public class DebugInfoCommand {
	public static ArgumentBuilder<CommandSourceStack, ?> register() {
		return literal("debuginfo").executes(ctx -> {
			CommandSourceStack source = ctx.getSource();
			ServerPlayer player = source.getPlayerOrException();
			
			Lang.translate("command.debuginfo.sending")
				.sendChat(player);
			AllPackets.getChannel()
				.send(PacketDistributor.PLAYER.with(() -> player), new ServerDebugInfoPacket(player));
			
			return Command.SINGLE_SUCCESS;
		});
	}
}