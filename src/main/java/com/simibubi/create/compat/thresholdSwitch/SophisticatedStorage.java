package com.simibubi.create_re.compat.thresholdSwitch;

import com.simibubi.create_re.compat.Mods;
import com.simibubi.create_re.foundation.utility.RegisteredObjects;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;

public class SophisticatedStorage implements ThresholdSwitchCompat {

	@Override
	public boolean isFromThisMod(BlockEntity be) {
		if (be == null)
			return false;

		String namespace = RegisteredObjects.getKeyOrThrow(be.getType())
			.getNamespace();

		return
			Mods.SOPHISTICATEDSTORAGE.id().equals(namespace)
			|| Mods.SOPHISTICATEDBACKPACKS.id().equals(namespace);
	}

	@Override
	public long getSpaceInSlot(IItemHandler inv, int slot) {
		return ((long) inv.getSlotLimit(slot) * inv.getStackInSlot(slot).getMaxStackSize()) / 64;
	}

}
