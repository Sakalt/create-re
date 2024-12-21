package com.simibubi.create_re.foundation.blockEntity;

import com.simibubi.create_re.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.simibubi.create_re.foundation.blockEntity.behaviour.fluid.SmartFluidTankBehaviour;
import com.simibubi.create_re.foundation.fluid.SmartFluidTank;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;

public class ComparatorUtil {

	public static int fractionToRedstoneLevel(double frac) {
		return Mth.floor(Mth.clamp(frac * 14 + (frac > 0 ? 1 : 0), 0, 15));
	}

	public static int levelOfSmartFluidTank(BlockGetter world, BlockPos pos) {
		SmartFluidTankBehaviour fluidBehaviour = BlockEntityBehaviour.get(world, pos, SmartFluidTankBehaviour.TYPE);
		if (fluidBehaviour == null)
			return 0;
		SmartFluidTank primaryHandler = fluidBehaviour.getPrimaryHandler();
		double fillFraction = (double) primaryHandler.getFluid()
			.getAmount() / primaryHandler.getCapacity();
		return fractionToRedstoneLevel(fillFraction);
	}

}
