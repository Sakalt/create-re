package com.simibubi.create_re.compat.computercraft.implementation.peripherals;

import org.jetbrains.annotations.NotNull;

import com.simibubi.create_re.content.kinetics.gauge.SpeedGaugeBlockEntity;

import dan200.computercraft.api.lua.LuaFunction;

public class SpeedGaugePeripheral extends SyncedPeripheral<SpeedGaugeBlockEntity> {

	public SpeedGaugePeripheral(SpeedGaugeBlockEntity blockEntity) {
		super(blockEntity);
	}

	@LuaFunction
	public final float getSpeed() {
		return this.blockEntity.getSpeed();
	}

	@NotNull
	@Override
	public String getType() {
		return "Create_Speedometer";
	}

}
