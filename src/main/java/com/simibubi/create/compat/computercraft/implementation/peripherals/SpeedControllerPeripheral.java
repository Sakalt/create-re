package com.sakalti.create_re.compat.computercraft.implementation.peripherals;

import org.jetbrains.annotations.NotNull;

import com.sakalti.create_re.content.kinetics.speedController.SpeedControllerBlockEntity;
import com.sakalti.create_re.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;

import dan200.computercraft.api.lua.LuaFunction;

public class SpeedControllerPeripheral extends SyncedPeripheral<SpeedControllerBlockEntity> {

	private final ScrollValueBehaviour targetSpeed;

	public SpeedControllerPeripheral(SpeedControllerBlockEntity blockEntity, ScrollValueBehaviour targetSpeed) {
		super(blockEntity);
		this.targetSpeed = targetSpeed;
	}

	@LuaFunction(mainThread = true)
	public final void setTargetSpeed(int speed) {
		this.targetSpeed.setValue(speed);
	}

	@LuaFunction
	public final float getTargetSpeed() {
		return this.targetSpeed.getValue();
	}

	@NotNull
	@Override
	public String getType() {
		return "Create_RotationSpeedController";
	}

}
