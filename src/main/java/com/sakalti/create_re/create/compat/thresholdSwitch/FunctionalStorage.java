package com.sakalti.create_re.compat.thresholdSwitch;

import com.sakalti.create_re.compat.Mods;
import com.sakalti.create_re.foundation.utility.RegisteredObjects;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;

public class FunctionalStorage implements ThresholdSwitchCompat {

	@Override
	public boolean isFromThisMod(BlockEntity blockEntity) {
		return blockEntity != null && Mods.FUNCTIONALSTORAGE.id()
			.equals(RegisteredObjects.getKeyOrThrow(blockEntity.getType())
				.getNamespace());
	}

	@Override
	public long getSpaceInSlot(IItemHandler inv, int slot) {
		return inv.getSlotLimit(slot);
	}
}
