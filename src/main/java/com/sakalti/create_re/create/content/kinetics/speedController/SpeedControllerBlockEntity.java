package com.sakalti.create_re.content.kinetics.speedController;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.sakalti.create_re.compat.computercraft.AbstractComputerBehaviour;
import com.sakalti.create_re.compat.computercraft.ComputerCraftProxy;
import com.sakalti.create_re.content.kinetics.RotationPropagator;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntity;
import com.sakalti.create_re.content.kinetics.motor.KineticScrollValueBehaviour;
import com.sakalti.create_re.content.kinetics.simpleRelays.CogWheelBlock;
import com.sakalti.create_re.content.kinetics.simpleRelays.ICogWheel;
import com.sakalti.create_re.foundation.advancement.AllAdvancements;
import com.sakalti.create_re.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.sakalti.create_re.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.sakalti.create_re.foundation.utility.Lang;
import com.sakalti.create_re.foundation.utility.VecHelper;
import com.sakalti.create_re.infrastructure.config.AllConfigs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

public class SpeedControllerBlockEntity extends KineticBlockEntity {

	public static final int DEFAULT_SPEED = 16;
	public ScrollValueBehaviour targetSpeed;
	public AbstractComputerBehaviour computerBehaviour;

	boolean hasBracket;

	public SpeedControllerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		hasBracket = false;
	}

	@Override
	public void lazyTick() {
		super.lazyTick();
		updateBracket();
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		super.addBehaviours(behaviours);
		Integer max = AllConfigs.server().kinetics.maxRotationSpeed.get();

		targetSpeed = new KineticScrollValueBehaviour(Lang.translateDirect("kinetics.speed_controller.rotation_speed"),
			this, new ControllerValueBoxTransform());
		targetSpeed.between(-max, max);
		targetSpeed.value = DEFAULT_SPEED;
		targetSpeed.withCallback(i -> this.updateTargetRotation());
		behaviours.add(targetSpeed);
		behaviours.add(computerBehaviour = ComputerCraftProxy.behaviour(this));

		registerAwardables(behaviours, AllAdvancements.SPEED_CONTROLLER);
	}

	private void updateTargetRotation() {
		if (hasNetwork())
			getOrCreateNetwork().remove(this);
		RotationPropagator.handleRemoved(level, worldPosition, this);
		removeSource();
		attachKinetics();

		if (isCogwheelPresent() && getSpeed() != 0)
			award(AllAdvancements.SPEED_CONTROLLER);
	}

	public static float getConveyedSpeed(KineticBlockEntity cogWheel, KineticBlockEntity speedControllerIn,
		boolean targetingController) {
		if (!(speedControllerIn instanceof SpeedControllerBlockEntity))
			return 0;

		float speed = speedControllerIn.getTheoreticalSpeed();
		float wheelSpeed = cogWheel.getTheoreticalSpeed();
		float desiredOutputSpeed = getDesiredOutputSpeed(cogWheel, speedControllerIn, targetingController);

		float compareSpeed = targetingController ? speed : wheelSpeed;
		if (desiredOutputSpeed >= 0 && compareSpeed >= 0)
			return Math.max(desiredOutputSpeed, compareSpeed);
		if (desiredOutputSpeed < 0 && compareSpeed < 0)
			return Math.min(desiredOutputSpeed, compareSpeed);

		return desiredOutputSpeed;
	}

	public static float getDesiredOutputSpeed(KineticBlockEntity cogWheel, KineticBlockEntity speedControllerIn,
		boolean targetingController) {
		SpeedControllerBlockEntity speedController = (SpeedControllerBlockEntity) speedControllerIn;
		float targetSpeed = speedController.targetSpeed.getValue();
		float speed = speedControllerIn.getTheoreticalSpeed();
		float wheelSpeed = cogWheel.getTheoreticalSpeed();

		if (targetSpeed == 0)
			return 0;
		if (targetingController && wheelSpeed == 0)
			return 0;
		if (!speedController.hasSource()) {
			if (targetingController)
				return targetSpeed;
			return 0;
		}

		boolean wheelPowersController = speedController.source.equals(cogWheel.getBlockPos());

		if (wheelPowersController) {
			if (targetingController)
				return targetSpeed;
			return wheelSpeed;
		}

		if (targetingController)
			return speed;
		return targetSpeed;
	}

	public void updateBracket() {
		if (level != null && level.isClientSide)
			hasBracket = isCogwheelPresent();
	}

	private boolean isCogwheelPresent() {
		BlockState stateAbove = level.getBlockState(worldPosition.above());
		return ICogWheel.isDedicatedCogWheel(stateAbove.getBlock()) && ICogWheel.isLargeCog(stateAbove)
			&& stateAbove.getValue(CogWheelBlock.AXIS)
				.isHorizontal();
	}

	@NotNull
	@Override
	public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
		if (computerBehaviour.isPeripheralCap(cap))
			return computerBehaviour.getPeripheralCapability();
		return super.getCapability(cap, side);
	}

	@Override
	public void invalidateCaps() {
		super.invalidateCaps();
		computerBehaviour.removePeripheral();
	}

	private class ControllerValueBoxTransform extends ValueBoxTransform.Sided {

		@Override
		protected Vec3 getSouthLocation() {
			return VecHelper.voxelSpace(8, 11f, 15.5f);
		}

		@Override
		protected boolean isSideActive(BlockState state, Direction direction) {
			if (direction.getAxis()
				.isVertical())
				return false;
			return state.getValue(SpeedControllerBlock.HORIZONTAL_AXIS) != direction.getAxis();
		}

		@Override
		public float getScale() {
			return 0.5f;
		}

	}

}
