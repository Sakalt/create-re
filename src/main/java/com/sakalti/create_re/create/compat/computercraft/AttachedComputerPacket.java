package com.sakalti.create_re.compat.computercraft;

import com.sakalti.create_re.foundation.blockEntity.SmartBlockEntity;
import com.sakalti.create_re.foundation.blockEntity.SyncedBlockEntity;
import com.sakalti.create_re.foundation.networking.BlockEntityDataPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;

public class AttachedComputerPacket extends BlockEntityDataPacket<SyncedBlockEntity> {

	private final boolean hasAttachedComputer;

	public AttachedComputerPacket(BlockPos blockEntityPos, boolean hasAttachedComputer) {
		super(blockEntityPos);
		this.hasAttachedComputer = hasAttachedComputer;
	}

	public AttachedComputerPacket(FriendlyByteBuf buffer) {
		super(buffer);
		this.hasAttachedComputer = buffer.readBoolean();
	}

	@Override
	protected void writeData(FriendlyByteBuf buffer) {
		buffer.writeBoolean(hasAttachedComputer);
	}

	@Override
	protected void handlePacket(SyncedBlockEntity blockEntity) {
		if (blockEntity instanceof SmartBlockEntity sbe) {
			sbe.getBehaviour(AbstractComputerBehaviour.TYPE)
				.setHasAttachedComputer(hasAttachedComputer);
		}
	}

}
