package com.sakalti.create_re.compat.computercraft.implementation;

import com.sakalti.create_re.compat.computercraft.AbstractComputerBehaviour;
import com.sakalti.create_re.compat.computercraft.implementation.peripherals.DisplayLinkPeripheral;
import com.sakalti.create_re.compat.computercraft.implementation.peripherals.SequencedGearshiftPeripheral;
import com.sakalti.create_re.compat.computercraft.implementation.peripherals.SpeedControllerPeripheral;
import com.sakalti.create_re.compat.computercraft.implementation.peripherals.SpeedGaugePeripheral;
import com.sakalti.create_re.compat.computercraft.implementation.peripherals.StationPeripheral;
import com.sakalti.create_re.compat.computercraft.implementation.peripherals.StressGaugePeripheral;
import com.sakalti.create_re.content.kinetics.gauge.SpeedGaugeBlockEntity;
import com.sakalti.create_re.content.kinetics.gauge.StressGaugeBlockEntity;
import com.sakalti.create_re.content.kinetics.speedController.SpeedControllerBlockEntity;
import com.sakalti.create_re.content.kinetics.transmission.sequencer.SequencedGearshiftBlockEntity;
import com.sakalti.create_re.content.redstone.displayLink.DisplayLinkBlockEntity;
import com.sakalti.create_re.content.trains.station.StationBlockEntity;
import com.sakalti.create_re.foundation.blockEntity.SmartBlockEntity;

import dan200.computercraft.api.peripheral.IPeripheral;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.registries.ForgeRegistries;

public class ComputerBehaviour extends AbstractComputerBehaviour {

	protected static final Capability<IPeripheral> PERIPHERAL_CAPABILITY =
		CapabilityManager.get(new CapabilityToken<>() {
		});
	LazyOptional<IPeripheral> peripheral;
	NonNullSupplier<IPeripheral> peripheralSupplier;

	public ComputerBehaviour(SmartBlockEntity te) {
		super(te);
		this.peripheralSupplier = getPeripheralFor(te);
	}

	public static NonNullSupplier<IPeripheral> getPeripheralFor(SmartBlockEntity be) {
		if (be instanceof SpeedControllerBlockEntity scbe)
			return () -> new SpeedControllerPeripheral(scbe, scbe.targetSpeed);
		if (be instanceof DisplayLinkBlockEntity dlbe)
			return () -> new DisplayLinkPeripheral(dlbe);
		if (be instanceof SequencedGearshiftBlockEntity sgbe)
			return () -> new SequencedGearshiftPeripheral(sgbe);
		if (be instanceof SpeedGaugeBlockEntity sgbe)
			return () -> new SpeedGaugePeripheral(sgbe);
		if (be instanceof StressGaugeBlockEntity sgbe)
			return () -> new StressGaugePeripheral(sgbe);
		if (be instanceof StationBlockEntity sbe)
			return () -> new StationPeripheral(sbe);

		throw new IllegalArgumentException(
			"No peripheral available for " + ForgeRegistries.BLOCK_ENTITY_TYPES.getKey(be.getType()));
	}

	@Override
	public <T> boolean isPeripheralCap(Capability<T> cap) {
		return cap == PERIPHERAL_CAPABILITY;
	}

	@Override
	public <T> LazyOptional<T> getPeripheralCapability() {
		if (peripheral == null || !peripheral.isPresent())
			peripheral = LazyOptional.of(peripheralSupplier);
		return peripheral.cast();
	}

	@Override
	public void removePeripheral() {
		if (peripheral != null)
			peripheral.invalidate();
	}

}
