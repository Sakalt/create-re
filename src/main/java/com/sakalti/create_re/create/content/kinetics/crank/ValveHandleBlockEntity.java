package com.sakalti.create_re.content.kinetics.crank;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.sakalti.create_re.AllBlocks;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntity;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntityRenderer;
import com.sakalti.create_re.content.kinetics.transmission.sequencer.SequencedGearshiftBlockEntity.SequenceContext;
import com.sakalti.create_re.content.kinetics.transmission.sequencer.SequencerInstructions;
import com.sakalti.create_re.foundation.blockEntity.SmartBlockEntity;
import com.sakalti.create_re.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueSettingsBoard;
import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueSettingsFormatter;
import com.sakalti.create_re.foundation.blockEntity.behaviour.scrollValue.ScrollValueBehaviour;
import com.sakalti.create_re.foundation.render.CachedBufferer;
import com.sakalti.create_re.foundation.render.SuperByteBuffer;
import com.sakalti.create_re.foundation.render.VirtualRenderHelper;
import com.sakalti.create_re.foundation.utility.Components;
import com.sakalti.create_re.foundation.utility.Lang;
import com.sakalti.create_re.foundation.utility.VecHelper;

import dev.engine_room.flywheel.api.model.Model;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ValveHandleBlockEntity extends HandCrankBlockEntity {

	public ScrollValueBehaviour angleInput;
	public int cooldown;

	protected int startAngle;
	protected int targetAngle;
	protected int totalUseTicks;

	public ValveHandleBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		super.addBehaviours(behaviours);
		behaviours.add(angleInput = new ValveHandleScrollValueBehaviour(this).between(-180, 180));
		angleInput.onlyActiveWhen(this::showValue);
		angleInput.setValue(45);
	}

	@Override
	protected boolean clockwise() {
		return angleInput.getValue() < 0 ^ backwards;
	}

	@Override
	public void write(CompoundTag compound, boolean clientPacket) {
		super.write(compound, clientPacket);
		compound.putInt("TotalUseTicks", totalUseTicks);
		compound.putInt("StartAngle", startAngle);
		compound.putInt("TargetAngle", targetAngle);
	}

	@Override
	protected void read(CompoundTag compound, boolean clientPacket) {
		super.read(compound, clientPacket);
		totalUseTicks = compound.getInt("TotalUseTicks");
		startAngle = compound.getInt("StartAngle");
		targetAngle = compound.getInt("TargetAngle");
	}

	@Override
	public void tick() {
		super.tick();
		if (inUse == 0 && cooldown > 0)
			cooldown--;
		independentAngle = level.isClientSide() ? getIndependentAngle(0) : 0;
	}

	@Override
	public float getIndependentAngle(float partialTicks) {
		if (inUse == 0 && source != null && getSpeed() != 0)
			return KineticBlockEntityRenderer.getAngleForBe(this, worldPosition,
				KineticBlockEntityRenderer.getRotationAxisOf(this));

		int step = getBlockState().getOptionalValue(ValveHandleBlock.FACING)
			.orElse(Direction.SOUTH)
			.getAxisDirection()
			.getStep();

		return (inUse > 0 && totalUseTicks > 0
			? Mth.lerp(Math.min(totalUseTicks, totalUseTicks - inUse + partialTicks) / (float) totalUseTicks,
				startAngle, targetAngle)
			: targetAngle) * Mth.DEG_TO_RAD * (backwards ? -1 : 1) * step;
	}

	public boolean showValue() {
		return inUse == 0;
	}

	public boolean activate(boolean sneak) {
		if (getTheoreticalSpeed() != 0)
			return false;
		if (inUse > 0 || cooldown > 0)
			return false;
		if (level.isClientSide)
			return true;

		// Always overshoot, target will stop early
		int value = angleInput.getValue();
		int target = Math.abs(value);
		int rotationSpeed = AllBlocks.COPPER_VALVE_HANDLE.get()
			.getRotationSpeed();
		double degreesPerTick = KineticBlockEntity.convertToAngular(rotationSpeed);
		inUse = (int) Math.ceil(target / degreesPerTick) + 2;

		startAngle = (int) ((independentAngle) % 90 + 360) % 90;
		targetAngle = Math.round((startAngle + (target > 135 ? 180 : 90) * Mth.sign(value)) / 90f) * 90;
		totalUseTicks = inUse;
		backwards = sneak;

		sequenceContext = SequenceContext.fromGearshift(SequencerInstructions.TURN_ANGLE, rotationSpeed, target);
		updateGeneratedRotation();
		cooldown = 4;

		return true;
	}

	@Override
	protected void copySequenceContextFrom(KineticBlockEntity sourceBE) {}

	@Override
	@OnlyIn(Dist.CLIENT)
	public SuperByteBuffer getRenderedHandle() {
		return CachedBufferer.block(getBlockState());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public Model getRenderedHandleInstance() {
		return VirtualRenderHelper.blockModel(getBlockState());
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public boolean shouldRenderShaft() {
		return false;
	}

	public static class ValveHandleScrollValueBehaviour extends ScrollValueBehaviour {

		public ValveHandleScrollValueBehaviour(SmartBlockEntity be) {
			super(Lang.translateDirect("kinetics.valve_handle.rotated_angle"), be, new ValveHandleValueBox());
			withFormatter(v -> String.valueOf(Math.abs(v)) + Lang.translateDirect("generic.unit.degrees")
				.getString());
		}

		@Override
		public ValueSettingsBoard create_reBoard(Player player, BlockHitResult hitResult) {
			ImmutableList<Component> rows = ImmutableList.of(Components.literal("\u27f3")
				.withStyle(ChatFormatting.BOLD),
				Components.literal("\u27f2")
					.withStyle(ChatFormatting.BOLD));
			return new ValueSettingsBoard(label, 180, 45, rows, new ValueSettingsFormatter(this::formatValue));
		}

		@Override
		public void setValueSettings(Player player, ValueSettings valueSetting, boolean ctrlHeld) {
			int value = Math.max(1, valueSetting.value());
			if (!valueSetting.equals(getValueSettings()))
				playFeedbackSound(this);
			setValue(valueSetting.row() == 0 ? -value : value);
		}

		@Override
		public ValueSettings getValueSettings() {
			return new ValueSettings(value < 0 ? 0 : 1, Math.abs(value));
		}

		public MutableComponent formatValue(ValueSettings settings) {
			return Lang.number(Math.max(1, Math.abs(settings.value())))
				.add(Lang.translateDirect("generic.unit.degrees"))
				.component();
		}

		@Override
		public void onShortInteract(Player player, InteractionHand hand, Direction side) {
			BlockState blockState = blockEntity.getBlockState();
			if (blockState.getBlock() instanceof ValveHandleBlock vhb)
				vhb.clicked(getWorld(), getPos(), blockState, player, hand);
		}

	}

	public static class ValveHandleValueBox extends ValueBoxTransform.Sided {

		@Override
		protected boolean isSideActive(BlockState state, Direction direction) {
			return direction == state.getValue(ValveHandleBlock.FACING);
		}

		@Override
		protected Vec3 getSouthLocation() {
			return VecHelper.voxelSpace(8, 8, 4.5);
		}

		@Override
		public boolean testHit(BlockState state, Vec3 localHit) {
			Vec3 offset = getLocalOffset(state);
			if (offset == null)
				return false;
			return localHit.distanceTo(offset) < scale / 1.5f;
		}

	}

}
