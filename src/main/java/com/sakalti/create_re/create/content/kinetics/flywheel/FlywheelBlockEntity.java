package com.sakalti.create_re.content.kinetics.flywheel;

import com.sakalti.create_re.content.kinetics.base.KineticBlockEntity;
import com.sakalti.create_re.foundation.utility.animation.LerpedFloat;
import com.sakalti.create_re.foundation.utility.animation.LerpedFloat.Chaser;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

public class FlywheelBlockEntity extends KineticBlockEntity {

	LerpedFloat visualSpeed = LerpedFloat.linear();
	float angle;

	public FlywheelBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	protected AABB create_reRenderBoundingBox() {
		return super.create_reRenderBoundingBox().inflate(2);
	}

	@Override
	public void write(CompoundTag compound, boolean clientPacket) {
		super.write(compound, clientPacket);
	}

	@Override
	protected void read(CompoundTag compound, boolean clientPacket) {
		super.read(compound, clientPacket);
		if (clientPacket)
			visualSpeed.chase(getGeneratedSpeed(), 1 / 64f, Chaser.EXP);
	}

	@Override
	public void tick() {
		super.tick();

		if (!level.isClientSide)
			return;

		float targetSpeed = getSpeed();
		visualSpeed.updateChaseTarget(targetSpeed);
		visualSpeed.tickChaser();
		angle += visualSpeed.getValue() * 3 / 10f;
		angle %= 360;
	}
}
