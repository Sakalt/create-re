package com.sakalti.create_re.content.logistics.depot;

import java.util.List;

import com.sakalti.create_re.foundation.blockEntity.SmartBlockEntity;
import com.sakalti.create_re.foundation.blockEntity.behaviour.BlockEntityBehaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;

public class DepotBlockEntity extends SmartBlockEntity {

	DepotBehaviour depotBehaviour;

	public DepotBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours.add(depotBehaviour = new DepotBehaviour(this));
		depotBehaviour.addSubBehaviours(behaviours);
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == ForgeCapabilities.ITEM_HANDLER)
			return depotBehaviour.getItemCapability(cap, side);
		return super.getCapability(cap, side);
	}

	public ItemStack getHeldItem() {
		return depotBehaviour.getHeldItemStack();
	}
}
