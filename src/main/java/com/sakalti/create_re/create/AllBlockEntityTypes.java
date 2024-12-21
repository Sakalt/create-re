package com.sakalti.create_re;

import static com.sakalti.create_re.Create.REGISTRATE;
import static com.sakalti.create_re.content.redstone.displayLink.AllDisplayBehaviours.assignDataBehaviourBE;

import com.sakalti.create_re.content.contraptions.actors.contraptionControls.ContraptionControlsBlockEntity;
import com.sakalti.create_re.content.contraptions.actors.contraptionControls.ContraptionControlsRenderer;
import com.sakalti.create_re.content.contraptions.actors.harvester.HarvesterBlockEntity;
import com.sakalti.create_re.content.contraptions.actors.harvester.HarvesterRenderer;
import com.sakalti.create_re.content.contraptions.actors.psi.PSIVisual;
import com.sakalti.create_re.content.contraptions.actors.psi.PortableFluidInterfaceBlockEntity;
import com.sakalti.create_re.content.contraptions.actors.psi.PortableItemInterfaceBlockEntity;
import com.sakalti.create_re.content.contraptions.actors.psi.PortableStorageInterfaceRenderer;
import com.sakalti.create_re.content.contraptions.actors.roller.RollerBlockEntity;
import com.sakalti.create_re.content.contraptions.actors.roller.RollerRenderer;
import com.sakalti.create_re.content.contraptions.bearing.BearingRenderer;
import com.sakalti.create_re.content.contraptions.bearing.BearingVisual;
import com.sakalti.create_re.content.contraptions.bearing.ClockworkBearingBlockEntity;
import com.sakalti.create_re.content.contraptions.bearing.MechanicalBearingBlockEntity;
import com.sakalti.create_re.content.contraptions.bearing.WindmillBearingBlockEntity;
import com.sakalti.create_re.content.contraptions.chassis.ChassisBlockEntity;
import com.sakalti.create_re.content.contraptions.chassis.StickerBlockEntity;
import com.sakalti.create_re.content.contraptions.chassis.StickerRenderer;
import com.sakalti.create_re.content.contraptions.chassis.StickerVisual;
import com.sakalti.create_re.content.contraptions.elevator.ElevatorContactBlockEntity;
import com.sakalti.create_re.content.contraptions.elevator.ElevatorPulleyBlockEntity;
import com.sakalti.create_re.content.contraptions.elevator.ElevatorPulleyRenderer;
import com.sakalti.create_re.content.contraptions.gantry.GantryCarriageBlockEntity;
import com.sakalti.create_re.content.contraptions.gantry.GantryCarriageRenderer;
import com.sakalti.create_re.content.contraptions.gantry.GantryCarriageVisual;
import com.sakalti.create_re.content.contraptions.mounted.CartAssemblerBlockEntity;
import com.sakalti.create_re.content.contraptions.piston.MechanicalPistonBlockEntity;
import com.sakalti.create_re.content.contraptions.piston.MechanicalPistonRenderer;
import com.sakalti.create_re.content.contraptions.pulley.HosePulleyVisual;
import com.sakalti.create_re.content.contraptions.pulley.PulleyBlockEntity;
import com.sakalti.create_re.content.contraptions.pulley.PulleyRenderer;
import com.sakalti.create_re.content.contraptions.pulley.RopePulleyVisual;
import com.sakalti.create_re.content.decoration.copycat.CopycatBlockEntity;
import com.sakalti.create_re.content.decoration.placard.PlacardBlockEntity;
import com.sakalti.create_re.content.decoration.placard.PlacardRenderer;
import com.sakalti.create_re.content.decoration.slidingDoor.SlidingDoorBlockEntity;
import com.sakalti.create_re.content.decoration.slidingDoor.SlidingDoorRenderer;
import com.sakalti.create_re.content.decoration.steamWhistle.WhistleBlockEntity;
import com.sakalti.create_re.content.decoration.steamWhistle.WhistleRenderer;
import com.sakalti.create_re.content.equipment.armor.BacktankBlockEntity;
import com.sakalti.create_re.content.equipment.armor.BacktankRenderer;
import com.sakalti.create_re.content.equipment.armor.BacktankVisual;
import com.sakalti.create_re.content.equipment.bell.BellRenderer;
import com.sakalti.create_re.content.equipment.bell.HauntedBellBlockEntity;
import com.sakalti.create_re.content.equipment.bell.PeculiarBellBlockEntity;
import com.sakalti.create_re.content.equipment.clipboard.ClipboardBlockEntity;
import com.sakalti.create_re.content.equipment.toolbox.ToolBoxVisual;
import com.sakalti.create_re.content.equipment.toolbox.ToolboxBlockEntity;
import com.sakalti.create_re.content.equipment.toolbox.ToolboxRenderer;
import com.sakalti.create_re.content.fluids.drain.ItemDrainBlockEntity;
import com.sakalti.create_re.content.fluids.drain.ItemDrainRenderer;
import com.sakalti.create_re.content.fluids.hosePulley.HosePulleyBlockEntity;
import com.sakalti.create_re.content.fluids.hosePulley.HosePulleyRenderer;
import com.sakalti.create_re.content.fluids.pipes.FluidPipeBlockEntity;
import com.sakalti.create_re.content.fluids.pipes.SmartFluidPipeBlockEntity;
import com.sakalti.create_re.content.fluids.pipes.StraightPipeBlockEntity;
import com.sakalti.create_re.content.fluids.pipes.TransparentStraightPipeRenderer;
import com.sakalti.create_re.content.fluids.pipes.valve.FluidValveBlockEntity;
import com.sakalti.create_re.content.fluids.pipes.valve.FluidValveRenderer;
import com.sakalti.create_re.content.fluids.pipes.valve.FluidValveVisual;
import com.sakalti.create_re.content.fluids.pump.PumpBlockEntity;
import com.sakalti.create_re.content.fluids.pump.PumpCogVisual;
import com.sakalti.create_re.content.fluids.pump.PumpRenderer;
import com.sakalti.create_re.content.fluids.spout.SpoutBlockEntity;
import com.sakalti.create_re.content.fluids.spout.SpoutRenderer;
import com.sakalti.create_re.content.fluids.tank.CreativeFluidTankBlockEntity;
import com.sakalti.create_re.content.fluids.tank.FluidTankBlockEntity;
import com.sakalti.create_re.content.fluids.tank.FluidTankRenderer;
import com.sakalti.create_re.content.kinetics.base.HalfShaftVisual;
import com.sakalti.create_re.content.kinetics.base.HorizontalHalfShaftVisual;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntity;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntityRenderer;
import com.sakalti.create_re.content.kinetics.base.ShaftRenderer;
import com.sakalti.create_re.content.kinetics.base.ShaftVisual;
import com.sakalti.create_re.content.kinetics.base.SingleRotatingVisual;
import com.sakalti.create_re.content.kinetics.belt.BeltBlockEntity;
import com.sakalti.create_re.content.kinetics.belt.BeltRenderer;
import com.sakalti.create_re.content.kinetics.belt.BeltVisual;
import com.sakalti.create_re.content.kinetics.chainDrive.ChainGearshiftBlockEntity;
import com.sakalti.create_re.content.kinetics.clock.CuckooClockBlockEntity;
import com.sakalti.create_re.content.kinetics.clock.CuckooClockRenderer;
import com.sakalti.create_re.content.kinetics.crafter.MechanicalCrafterBlockEntity;
import com.sakalti.create_re.content.kinetics.crafter.MechanicalCrafterRenderer;
import com.sakalti.create_re.content.kinetics.crafter.ShaftlessCogwheelVisual;
import com.sakalti.create_re.content.kinetics.crank.HandCrankBlockEntity;
import com.sakalti.create_re.content.kinetics.crank.HandCrankRenderer;
import com.sakalti.create_re.content.kinetics.crank.HandCrankVisual;
import com.sakalti.create_re.content.kinetics.crank.ValveHandleBlockEntity;
import com.sakalti.create_re.content.kinetics.crusher.CrushingWheelBlockEntity;
import com.sakalti.create_re.content.kinetics.crusher.CrushingWheelControllerBlockEntity;
import com.sakalti.create_re.content.kinetics.deployer.DeployerBlockEntity;
import com.sakalti.create_re.content.kinetics.deployer.DeployerRenderer;
import com.sakalti.create_re.content.kinetics.deployer.DeployerVisual;
import com.sakalti.create_re.content.kinetics.drill.DrillBlockEntity;
import com.sakalti.create_re.content.kinetics.drill.DrillRenderer;
import com.sakalti.create_re.content.kinetics.drill.DrillVisual;
import com.sakalti.create_re.content.kinetics.fan.EncasedFanBlockEntity;
import com.sakalti.create_re.content.kinetics.fan.EncasedFanRenderer;
import com.sakalti.create_re.content.kinetics.fan.FanVisual;
import com.sakalti.create_re.content.kinetics.fan.NozzleBlockEntity;
import com.sakalti.create_re.content.kinetics.flywheel.FlywheelBlockEntity;
import com.sakalti.create_re.content.kinetics.flywheel.FlywheelRenderer;
import com.sakalti.create_re.content.kinetics.flywheel.FlywheelVisual;
import com.sakalti.create_re.content.kinetics.gantry.GantryShaftBlockEntity;
import com.sakalti.create_re.content.kinetics.gauge.GaugeRenderer;
import com.sakalti.create_re.content.kinetics.gauge.GaugeVisual;
import com.sakalti.create_re.content.kinetics.gauge.SpeedGaugeBlockEntity;
import com.sakalti.create_re.content.kinetics.gauge.StressGaugeBlockEntity;
import com.sakalti.create_re.content.kinetics.gearbox.GearboxBlockEntity;
import com.sakalti.create_re.content.kinetics.gearbox.GearboxRenderer;
import com.sakalti.create_re.content.kinetics.gearbox.GearboxVisual;
import com.sakalti.create_re.content.kinetics.mechanicalArm.ArmBlockEntity;
import com.sakalti.create_re.content.kinetics.mechanicalArm.ArmRenderer;
import com.sakalti.create_re.content.kinetics.mechanicalArm.ArmVisual;
import com.sakalti.create_re.content.kinetics.millstone.MillstoneBlockEntity;
import com.sakalti.create_re.content.kinetics.millstone.MillstoneCogVisual;
import com.sakalti.create_re.content.kinetics.millstone.MillstoneRenderer;
import com.sakalti.create_re.content.kinetics.mixer.MechanicalMixerBlockEntity;
import com.sakalti.create_re.content.kinetics.mixer.MechanicalMixerRenderer;
import com.sakalti.create_re.content.kinetics.mixer.MixerVisual;
import com.sakalti.create_re.content.kinetics.motor.CreativeMotorBlockEntity;
import com.sakalti.create_re.content.kinetics.motor.CreativeMotorRenderer;
import com.sakalti.create_re.content.kinetics.press.MechanicalPressBlockEntity;
import com.sakalti.create_re.content.kinetics.press.MechanicalPressRenderer;
import com.sakalti.create_re.content.kinetics.press.PressVisual;
import com.sakalti.create_re.content.kinetics.saw.SawBlockEntity;
import com.sakalti.create_re.content.kinetics.saw.SawRenderer;
import com.sakalti.create_re.content.kinetics.saw.SawVisual;
import com.sakalti.create_re.content.kinetics.simpleRelays.BracketedKineticBlockEntity;
import com.sakalti.create_re.content.kinetics.simpleRelays.BracketedKineticBlockEntityRenderer;
import com.sakalti.create_re.content.kinetics.simpleRelays.BracketedKineticBlockEntityVisual;
import com.sakalti.create_re.content.kinetics.simpleRelays.SimpleKineticBlockEntity;
import com.sakalti.create_re.content.kinetics.simpleRelays.encased.EncasedCogRenderer;
import com.sakalti.create_re.content.kinetics.simpleRelays.encased.EncasedCogVisual;
import com.sakalti.create_re.content.kinetics.speedController.SpeedControllerBlockEntity;
import com.sakalti.create_re.content.kinetics.speedController.SpeedControllerRenderer;
import com.sakalti.create_re.content.kinetics.steamEngine.PoweredShaftBlockEntity;
import com.sakalti.create_re.content.kinetics.steamEngine.SteamEngineBlockEntity;
import com.sakalti.create_re.content.kinetics.steamEngine.SteamEngineRenderer;
import com.sakalti.create_re.content.kinetics.steamEngine.SteamEngineVisual;
import com.sakalti.create_re.content.kinetics.transmission.ClutchBlockEntity;
import com.sakalti.create_re.content.kinetics.transmission.GearshiftBlockEntity;
import com.sakalti.create_re.content.kinetics.transmission.SplitShaftRenderer;
import com.sakalti.create_re.content.kinetics.transmission.SplitShaftVisual;
import com.sakalti.create_re.content.kinetics.transmission.sequencer.SequencedGearshiftBlockEntity;
import com.sakalti.create_re.content.kinetics.turntable.TurntableBlockEntity;
import com.sakalti.create_re.content.kinetics.waterwheel.LargeWaterWheelBlockEntity;
import com.sakalti.create_re.content.kinetics.waterwheel.WaterWheelBlockEntity;
import com.sakalti.create_re.content.kinetics.waterwheel.WaterWheelRenderer;
import com.sakalti.create_re.content.kinetics.waterwheel.WaterWheelVisual;
import com.sakalti.create_re.content.logistics.chute.ChuteBlockEntity;
import com.sakalti.create_re.content.logistics.chute.ChuteRenderer;
import com.sakalti.create_re.content.logistics.chute.SmartChuteBlockEntity;
import com.sakalti.create_re.content.logistics.chute.SmartChuteRenderer;
import com.sakalti.create_re.content.logistics.crate.CreativeCrateBlockEntity;
import com.sakalti.create_re.content.logistics.depot.DepotBlockEntity;
import com.sakalti.create_re.content.logistics.depot.DepotRenderer;
import com.sakalti.create_re.content.logistics.depot.EjectorBlockEntity;
import com.sakalti.create_re.content.logistics.depot.EjectorRenderer;
import com.sakalti.create_re.content.logistics.depot.EjectorVisual;
import com.sakalti.create_re.content.logistics.funnel.FunnelBlockEntity;
import com.sakalti.create_re.content.logistics.funnel.FunnelRenderer;
import com.sakalti.create_re.content.logistics.funnel.FunnelVisual;
import com.sakalti.create_re.content.logistics.tunnel.BeltTunnelBlockEntity;
import com.sakalti.create_re.content.logistics.tunnel.BeltTunnelRenderer;
import com.sakalti.create_re.content.logistics.tunnel.BeltTunnelVisual;
import com.sakalti.create_re.content.logistics.tunnel.BrassTunnelBlockEntity;
import com.sakalti.create_re.content.logistics.vault.ItemVaultBlockEntity;
import com.sakalti.create_re.content.processing.basin.BasinBlockEntity;
import com.sakalti.create_re.content.processing.basin.BasinRenderer;
import com.sakalti.create_re.content.processing.burner.BlazeBurnerBlockEntity;
import com.sakalti.create_re.content.processing.burner.BlazeBurnerRenderer;
import com.sakalti.create_re.content.processing.burner.BlazeBurnerVisual;
import com.sakalti.create_re.content.redstone.analogLever.AnalogLeverBlockEntity;
import com.sakalti.create_re.content.redstone.analogLever.AnalogLeverRenderer;
import com.sakalti.create_re.content.redstone.analogLever.AnalogLeverVisual;
import com.sakalti.create_re.content.redstone.diodes.BrassDiodeRenderer;
import com.sakalti.create_re.content.redstone.diodes.BrassDiodeVisual;
import com.sakalti.create_re.content.redstone.diodes.PulseExtenderBlockEntity;
import com.sakalti.create_re.content.redstone.diodes.PulseRepeaterBlockEntity;
import com.sakalti.create_re.content.redstone.displayLink.DisplayLinkBlockEntity;
import com.sakalti.create_re.content.redstone.displayLink.DisplayLinkRenderer;
import com.sakalti.create_re.content.redstone.displayLink.source.NixieTubeDisplaySource;
import com.sakalti.create_re.content.redstone.displayLink.target.NixieTubeDisplayTarget;
import com.sakalti.create_re.content.redstone.link.RedstoneLinkBlockEntity;
import com.sakalti.create_re.content.redstone.link.controller.LecternControllerBlockEntity;
import com.sakalti.create_re.content.redstone.link.controller.LecternControllerRenderer;
import com.sakalti.create_re.content.redstone.nixieTube.NixieTubeBlockEntity;
import com.sakalti.create_re.content.redstone.nixieTube.NixieTubeRenderer;
import com.sakalti.create_re.content.redstone.smartObserver.SmartObserverBlockEntity;
import com.sakalti.create_re.content.redstone.thresholdSwitch.ThresholdSwitchBlockEntity;
import com.sakalti.create_re.content.schematics.cannon.SchematicannonBlockEntity;
import com.sakalti.create_re.content.schematics.cannon.SchematicannonRenderer;
import com.sakalti.create_re.content.schematics.cannon.SchematicannonVisual;
import com.sakalti.create_re.content.schematics.table.SchematicTableBlockEntity;
import com.sakalti.create_re.content.trains.bogey.BogeyBlockEntityRenderer;
import com.sakalti.create_re.content.trains.bogey.BogeyBlockEntityVisual;
import com.sakalti.create_re.content.trains.bogey.StandardBogeyBlockEntity;
import com.sakalti.create_re.content.trains.display.FlapDisplayBlockEntity;
import com.sakalti.create_re.content.trains.display.FlapDisplayRenderer;
import com.sakalti.create_re.content.trains.observer.TrackObserverBlockEntity;
import com.sakalti.create_re.content.trains.observer.TrackObserverRenderer;
import com.sakalti.create_re.content.trains.signal.SignalBlockEntity;
import com.sakalti.create_re.content.trains.signal.SignalRenderer;
import com.sakalti.create_re.content.trains.station.StationBlockEntity;
import com.sakalti.create_re.content.trains.station.StationRenderer;
import com.sakalti.create_re.content.trains.track.FakeTrackBlockEntity;
import com.sakalti.create_re.content.trains.track.TrackBlockEntity;
import com.sakalti.create_re.content.trains.track.TrackMaterial;
import com.sakalti.create_re.content.trains.track.TrackRenderer;
import com.sakalti.create_re.content.trains.track.TrackVisual;
import com.sakalti.create_re.foundation.blockEntity.renderer.SmartBlockEntityRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;

public class AllBlockEntityTypes {

	// Schematics
	public static final BlockEntityEntry<SchematicannonBlockEntity> SCHEMATICANNON = REGISTRATE
		.blockEntity("schematicannon", SchematicannonBlockEntity::new)
		.visual(() -> SchematicannonVisual::new)
		.validBlocks(AllBlocks.SCHEMATICANNON)
		.renderer(() -> SchematicannonRenderer::new)
		.register();

	public static final BlockEntityEntry<SchematicTableBlockEntity> SCHEMATIC_TABLE = REGISTRATE
		.blockEntity("schematic_table", SchematicTableBlockEntity::new)
		.validBlocks(AllBlocks.SCHEMATIC_TABLE)
		.register();

	// Kinetics
	public static final BlockEntityEntry<BracketedKineticBlockEntity> BRACKETED_KINETIC = REGISTRATE
		.blockEntity("simple_kinetic", BracketedKineticBlockEntity::new)
		.visual(() -> BracketedKineticBlockEntityVisual::new, false)
		.validBlocks(AllBlocks.SHAFT, AllBlocks.COGWHEEL, AllBlocks.LARGE_COGWHEEL)
		.renderer(() -> BracketedKineticBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<CreativeMotorBlockEntity> MOTOR = REGISTRATE
		.blockEntity("motor", CreativeMotorBlockEntity::new)
		.visual(() -> HalfShaftVisual::new, false)
		.validBlocks(AllBlocks.CREATIVE_MOTOR)
		.renderer(() -> CreativeMotorRenderer::new)
		.register();

	public static final BlockEntityEntry<GearboxBlockEntity> GEARBOX = REGISTRATE
		.blockEntity("gearbox", GearboxBlockEntity::new)
		.visual(() -> GearboxVisual::new, false)
		.validBlocks(AllBlocks.GEARBOX)
		.renderer(() -> GearboxRenderer::new)
		.register();

	public static final BlockEntityEntry<KineticBlockEntity> ENCASED_SHAFT = REGISTRATE
		.blockEntity("encased_shaft", KineticBlockEntity::new)
		.visual(() -> ShaftVisual::new, false)
		.validBlocks(AllBlocks.ANDESITE_ENCASED_SHAFT, AllBlocks.BRASS_ENCASED_SHAFT, AllBlocks.ENCASED_CHAIN_DRIVE,
			AllBlocks.METAL_GIRDER_ENCASED_SHAFT)
		.renderer(() -> ShaftRenderer::new)
		.register();

	public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_COGWHEEL = REGISTRATE
		.blockEntity("encased_cogwheel", SimpleKineticBlockEntity::new)
		.visual(() -> EncasedCogVisual::small, false)
		.validBlocks(AllBlocks.ANDESITE_ENCASED_COGWHEEL, AllBlocks.BRASS_ENCASED_COGWHEEL)
		.renderer(() -> EncasedCogRenderer::small)
		.register();

	public static final BlockEntityEntry<SimpleKineticBlockEntity> ENCASED_LARGE_COGWHEEL = REGISTRATE
		.blockEntity("encased_large_cogwheel", SimpleKineticBlockEntity::new)
		.visual(() -> EncasedCogVisual::large, false)
		.validBlocks(AllBlocks.ANDESITE_ENCASED_LARGE_COGWHEEL, AllBlocks.BRASS_ENCASED_LARGE_COGWHEEL)
		.renderer(() -> EncasedCogRenderer::large)
		.register();

	public static final BlockEntityEntry<ChainGearshiftBlockEntity> ADJUSTABLE_CHAIN_GEARSHIFT = REGISTRATE
		.blockEntity("adjustable_chain_gearshift", ChainGearshiftBlockEntity::new)
		.visual(() -> ShaftVisual::new, false)
		.validBlocks(AllBlocks.ADJUSTABLE_CHAIN_GEARSHIFT)
		.renderer(() -> ShaftRenderer::new)
		.register();

	public static final BlockEntityEntry<EncasedFanBlockEntity> ENCASED_FAN = REGISTRATE
		.blockEntity("encased_fan", EncasedFanBlockEntity::new)
		.visual(() -> FanVisual::new, false)
		.validBlocks(AllBlocks.ENCASED_FAN)
		.renderer(() -> EncasedFanRenderer::new)
		.register();

	public static final BlockEntityEntry<NozzleBlockEntity> NOZZLE = REGISTRATE
		.blockEntity("nozzle", NozzleBlockEntity::new)
		.validBlocks(AllBlocks.NOZZLE)
		// .renderer(() -> renderer)
		.register();

	public static final BlockEntityEntry<ClutchBlockEntity> CLUTCH = REGISTRATE
		.blockEntity("clutch", ClutchBlockEntity::new)
		.visual(() -> SplitShaftVisual::new, false)
		.validBlocks(AllBlocks.CLUTCH)
		.renderer(() -> SplitShaftRenderer::new)
		.register();

	public static final BlockEntityEntry<GearshiftBlockEntity> GEARSHIFT = REGISTRATE
		.blockEntity("gearshift", GearshiftBlockEntity::new)
		.visual(() -> SplitShaftVisual::new, false)
		.validBlocks(AllBlocks.GEARSHIFT)
		.renderer(() -> SplitShaftRenderer::new)
		.register();

	public static final BlockEntityEntry<TurntableBlockEntity> TURNTABLE = REGISTRATE
		.blockEntity("turntable", TurntableBlockEntity::new)
		.visual(() -> SingleRotatingVisual::new, false)
		.validBlocks(AllBlocks.TURNTABLE)
		.renderer(() -> KineticBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<HandCrankBlockEntity> HAND_CRANK = REGISTRATE
		.blockEntity("hand_crank", HandCrankBlockEntity::new)
		.visual(() -> HandCrankVisual::new)
		.validBlocks(AllBlocks.HAND_CRANK)
		.renderer(() -> HandCrankRenderer::new)
		.register();

	public static final BlockEntityEntry<ValveHandleBlockEntity> VALVE_HANDLE = REGISTRATE
		.blockEntity("valve_handle", ValveHandleBlockEntity::new)
		.visual(() -> HandCrankVisual::new)
		.validBlocks(AllBlocks.COPPER_VALVE_HANDLE)
		.validBlocks(AllBlocks.DYED_VALVE_HANDLES.toArray())
		.renderer(() -> HandCrankRenderer::new)
		.register();

	public static final BlockEntityEntry<CuckooClockBlockEntity> CUCKOO_CLOCK = REGISTRATE
		.blockEntity("cuckoo_clock", CuckooClockBlockEntity::new)
		.visual(() -> HorizontalHalfShaftVisual::new)
		.validBlocks(AllBlocks.CUCKOO_CLOCK, AllBlocks.MYSTERIOUS_CUCKOO_CLOCK)
		.renderer(() -> CuckooClockRenderer::new)
		.register();

	public static final BlockEntityEntry<GantryShaftBlockEntity> GANTRY_SHAFT = REGISTRATE
		.blockEntity("gantry_shaft", GantryShaftBlockEntity::new)
		.visual(() -> SingleRotatingVisual::new, false)
		.validBlocks(AllBlocks.GANTRY_SHAFT)
		.renderer(() -> KineticBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<GantryCarriageBlockEntity> GANTRY_PINION = REGISTRATE
		.blockEntity("gantry_pinion", GantryCarriageBlockEntity::new)
		.visual(() -> GantryCarriageVisual::new)
		.validBlocks(AllBlocks.GANTRY_CARRIAGE)
		.renderer(() -> GantryCarriageRenderer::new)
		.register();

	public static final BlockEntityEntry<PumpBlockEntity> MECHANICAL_PUMP = REGISTRATE
		.blockEntity("mechanical_pump", PumpBlockEntity::new)
		.visual(() -> PumpCogVisual::new)
		.validBlocks(AllBlocks.MECHANICAL_PUMP)
		.renderer(() -> PumpRenderer::new)
		.register();

	public static final BlockEntityEntry<SmartFluidPipeBlockEntity> SMART_FLUID_PIPE = REGISTRATE
		.blockEntity("smart_fluid_pipe", SmartFluidPipeBlockEntity::new)
		.validBlocks(AllBlocks.SMART_FLUID_PIPE)
		.renderer(() -> SmartBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<FluidPipeBlockEntity> FLUID_PIPE = REGISTRATE
		.blockEntity("fluid_pipe", FluidPipeBlockEntity::new)
		.validBlocks(AllBlocks.FLUID_PIPE)
		.register();

	public static final BlockEntityEntry<FluidPipeBlockEntity> ENCASED_FLUID_PIPE = REGISTRATE
		.blockEntity("encased_fluid_pipe", FluidPipeBlockEntity::new)
		.validBlocks(AllBlocks.ENCASED_FLUID_PIPE)
		.register();

	public static final BlockEntityEntry<StraightPipeBlockEntity> GLASS_FLUID_PIPE = REGISTRATE
		.blockEntity("glass_fluid_pipe", StraightPipeBlockEntity::new)
		.validBlocks(AllBlocks.GLASS_FLUID_PIPE)
		.renderer(() -> TransparentStraightPipeRenderer::new)
		.register();

	public static final BlockEntityEntry<FluidValveBlockEntity> FLUID_VALVE = REGISTRATE
		.blockEntity("fluid_valve", FluidValveBlockEntity::new)
		.visual(() -> FluidValveVisual::new)
		.validBlocks(AllBlocks.FLUID_VALVE)
		.renderer(() -> FluidValveRenderer::new)
		.register();

	public static final BlockEntityEntry<FluidTankBlockEntity> FLUID_TANK = REGISTRATE
		.blockEntity("fluid_tank", FluidTankBlockEntity::new)
		.validBlocks(AllBlocks.FLUID_TANK)
		.renderer(() -> FluidTankRenderer::new)
		.register();

	public static final BlockEntityEntry<CreativeFluidTankBlockEntity> CREATIVE_FLUID_TANK = REGISTRATE
		.blockEntity("creative_fluid_tank", CreativeFluidTankBlockEntity::new)
		.validBlocks(AllBlocks.CREATIVE_FLUID_TANK)
		.renderer(() -> FluidTankRenderer::new)
		.register();

	public static final BlockEntityEntry<HosePulleyBlockEntity> HOSE_PULLEY = REGISTRATE
		.blockEntity("hose_pulley", HosePulleyBlockEntity::new)
		.visual(() -> HosePulleyVisual::new)
		.validBlocks(AllBlocks.HOSE_PULLEY)
		.renderer(() -> HosePulleyRenderer::new)
		.register();

	public static final BlockEntityEntry<SpoutBlockEntity> SPOUT = REGISTRATE
		.blockEntity("spout", SpoutBlockEntity::new)
		.validBlocks(AllBlocks.SPOUT)
		.renderer(() -> SpoutRenderer::new)
		.register();

	public static final BlockEntityEntry<ItemDrainBlockEntity> ITEM_DRAIN = REGISTRATE
		.blockEntity("item_drain", ItemDrainBlockEntity::new)
		.validBlocks(AllBlocks.ITEM_DRAIN)
		.renderer(() -> ItemDrainRenderer::new)
		.register();

	public static final BlockEntityEntry<BeltBlockEntity> BELT = REGISTRATE
		.blockEntity("belt", BeltBlockEntity::new)
		.visual(() -> BeltVisual::new, BeltBlockEntity::shouldRenderNormally)
		.validBlocks(AllBlocks.BELT)
		.renderer(() -> BeltRenderer::new)
		.register();

	public static final BlockEntityEntry<ChuteBlockEntity> CHUTE = REGISTRATE
		.blockEntity("chute", ChuteBlockEntity::new)
		.validBlocks(AllBlocks.CHUTE)
		.renderer(() -> ChuteRenderer::new)
		.register();

	public static final BlockEntityEntry<SmartChuteBlockEntity> SMART_CHUTE = REGISTRATE
		.blockEntity("smart_chute", SmartChuteBlockEntity::new)
		.validBlocks(AllBlocks.SMART_CHUTE)
		.renderer(() -> SmartChuteRenderer::new)
		.register();

	public static final BlockEntityEntry<BeltTunnelBlockEntity> ANDESITE_TUNNEL = REGISTRATE
		.blockEntity("andesite_tunnel", BeltTunnelBlockEntity::new)
		.visual(() -> BeltTunnelVisual::new)
		.validBlocks(AllBlocks.ANDESITE_TUNNEL)
		.renderer(() -> BeltTunnelRenderer::new)
		.register();

	public static final BlockEntityEntry<BrassTunnelBlockEntity> BRASS_TUNNEL = REGISTRATE
		.blockEntity("brass_tunnel", BrassTunnelBlockEntity::new)
		.visual(() -> BeltTunnelVisual::new)
		.validBlocks(AllBlocks.BRASS_TUNNEL)
		.renderer(() -> BeltTunnelRenderer::new)
		.register();

	public static final BlockEntityEntry<ArmBlockEntity> MECHANICAL_ARM = REGISTRATE
		.blockEntity("mechanical_arm", ArmBlockEntity::new)
		.visual(() -> ArmVisual::new)
		.validBlocks(AllBlocks.MECHANICAL_ARM)
		.renderer(() -> ArmRenderer::new)
		.register();

	public static final BlockEntityEntry<ItemVaultBlockEntity> ITEM_VAULT = REGISTRATE
		.blockEntity("item_vault", ItemVaultBlockEntity::new)
		.validBlocks(AllBlocks.ITEM_VAULT)
		.register();

	public static final BlockEntityEntry<MechanicalPistonBlockEntity> MECHANICAL_PISTON = REGISTRATE
		.blockEntity("mechanical_piston", MechanicalPistonBlockEntity::new)
		.visual(() -> ShaftVisual::new, false)
		.validBlocks(AllBlocks.MECHANICAL_PISTON, AllBlocks.STICKY_MECHANICAL_PISTON)
		.renderer(() -> MechanicalPistonRenderer::new)
		.register();

	public static final BlockEntityEntry<WindmillBearingBlockEntity> WINDMILL_BEARING = REGISTRATE
		.blockEntity("windmill_bearing", WindmillBearingBlockEntity::new)
		.visual(() -> BearingVisual::new)
		.validBlocks(AllBlocks.WINDMILL_BEARING)
		.renderer(() -> BearingRenderer::new)
		.register();

	public static final BlockEntityEntry<MechanicalBearingBlockEntity> MECHANICAL_BEARING = REGISTRATE
		.blockEntity("mechanical_bearing", MechanicalBearingBlockEntity::new)
		.visual(() -> BearingVisual::new)
		.validBlocks(AllBlocks.MECHANICAL_BEARING)
		.renderer(() -> BearingRenderer::new)
		.register();

	public static final BlockEntityEntry<ClockworkBearingBlockEntity> CLOCKWORK_BEARING = REGISTRATE
		.blockEntity("clockwork_bearing", ClockworkBearingBlockEntity::new)
		.visual(() -> BearingVisual::new)
		.validBlocks(AllBlocks.CLOCKWORK_BEARING)
		.renderer(() -> BearingRenderer::new)
		.register();

	public static final BlockEntityEntry<PulleyBlockEntity> ROPE_PULLEY = REGISTRATE
		.blockEntity("rope_pulley", PulleyBlockEntity::new)
		.visual(() -> RopePulleyVisual::new, false)
		.validBlocks(AllBlocks.ROPE_PULLEY)
		.renderer(() -> PulleyRenderer::new)
		.register();

	public static final BlockEntityEntry<ElevatorPulleyBlockEntity> ELEVATOR_PULLEY =
		REGISTRATE.blockEntity("elevator_pulley", ElevatorPulleyBlockEntity::new)
//		.instance(() -> ElevatorPulleyInstance::new, false)
			.validBlocks(AllBlocks.ELEVATOR_PULLEY)
			.renderer(() -> ElevatorPulleyRenderer::new)
			.register();

	public static final BlockEntityEntry<ElevatorContactBlockEntity> ELEVATOR_CONTACT =
		REGISTRATE.blockEntity("elevator_contact", ElevatorContactBlockEntity::new)
			.validBlocks(AllBlocks.ELEVATOR_CONTACT)
			.register();

	public static final BlockEntityEntry<ChassisBlockEntity> CHASSIS = REGISTRATE
		.blockEntity("chassis", ChassisBlockEntity::new)
		.validBlocks(AllBlocks.RADIAL_CHASSIS, AllBlocks.LINEAR_CHASSIS, AllBlocks.SECONDARY_LINEAR_CHASSIS)
		// .renderer(() -> renderer)
		.register();

	public static final BlockEntityEntry<StickerBlockEntity> STICKER = REGISTRATE
		.blockEntity("sticker", StickerBlockEntity::new)
		.visual(() -> StickerVisual::new, false)
		.validBlocks(AllBlocks.STICKER)
		.renderer(() -> StickerRenderer::new)
		.register();

	public static final BlockEntityEntry<ContraptionControlsBlockEntity> CONTRAPTION_CONTROLS =
		REGISTRATE.blockEntity("contraption_controls", ContraptionControlsBlockEntity::new)
			.validBlocks(AllBlocks.CONTRAPTION_CONTROLS)
			.renderer(() -> ContraptionControlsRenderer::new)
			.register();

	public static final BlockEntityEntry<DrillBlockEntity> DRILL = REGISTRATE
		.blockEntity("drill", DrillBlockEntity::new)
		.visual(() -> DrillVisual::new, false)
		.validBlocks(AllBlocks.MECHANICAL_DRILL)
		.renderer(() -> DrillRenderer::new)
		.register();

	public static final BlockEntityEntry<SawBlockEntity> SAW = REGISTRATE
		.blockEntity("saw", SawBlockEntity::new)
		.visual(() -> SawVisual::new)
		.validBlocks(AllBlocks.MECHANICAL_SAW)
		.renderer(() -> SawRenderer::new)
		.register();

	public static final BlockEntityEntry<HarvesterBlockEntity> HARVESTER = REGISTRATE
		.blockEntity("harvester", HarvesterBlockEntity::new)
		.validBlocks(AllBlocks.MECHANICAL_HARVESTER)
		.renderer(() -> HarvesterRenderer::new)
		.register();

	public static final BlockEntityEntry<RollerBlockEntity> MECHANICAL_ROLLER =
		REGISTRATE.blockEntity("mechanical_roller", RollerBlockEntity::new)
			.validBlocks(AllBlocks.MECHANICAL_ROLLER)
			.renderer(() -> RollerRenderer::new)
			.register();

	public static final BlockEntityEntry<PortableItemInterfaceBlockEntity> PORTABLE_STORAGE_INTERFACE =
		REGISTRATE
			.blockEntity("portable_storage_interface", PortableItemInterfaceBlockEntity::new)
			.visual(() -> PSIVisual::new)
			.validBlocks(AllBlocks.PORTABLE_STORAGE_INTERFACE)
			.renderer(() -> PortableStorageInterfaceRenderer::new)
			.register();

	public static final BlockEntityEntry<PortableFluidInterfaceBlockEntity> PORTABLE_FLUID_INTERFACE =
		REGISTRATE
			.blockEntity("portable_fluid_interface", PortableFluidInterfaceBlockEntity::new)
			.visual(() -> PSIVisual::new)
			.validBlocks(AllBlocks.PORTABLE_FLUID_INTERFACE)
			.renderer(() -> PortableStorageInterfaceRenderer::new)
			.register();

	public static final BlockEntityEntry<SteamEngineBlockEntity> STEAM_ENGINE = REGISTRATE
		.blockEntity("steam_engine", SteamEngineBlockEntity::new)
		.visual(() -> SteamEngineVisual::new, false)
		.validBlocks(AllBlocks.STEAM_ENGINE)
		.renderer(() -> SteamEngineRenderer::new)
		.register();

	public static final BlockEntityEntry<WhistleBlockEntity> STEAM_WHISTLE = REGISTRATE
		.blockEntity("steam_whistle", WhistleBlockEntity::new)
		.validBlocks(AllBlocks.STEAM_WHISTLE)
		.renderer(() -> WhistleRenderer::new)
		.register();

	public static final BlockEntityEntry<PoweredShaftBlockEntity> POWERED_SHAFT = REGISTRATE
		.blockEntity("powered_shaft", PoweredShaftBlockEntity::new)
		.visual(() -> SingleRotatingVisual::new, false)
		.validBlocks(AllBlocks.POWERED_SHAFT)
		.renderer(() -> KineticBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<FlywheelBlockEntity> FLYWHEEL = REGISTRATE
		.blockEntity("flywheel", FlywheelBlockEntity::new)
		.visual(() -> FlywheelVisual::new, false)
		.validBlocks(AllBlocks.FLYWHEEL)
		.renderer(() -> FlywheelRenderer::new)
		.register();

	public static final BlockEntityEntry<MillstoneBlockEntity> MILLSTONE = REGISTRATE
		.blockEntity("millstone", MillstoneBlockEntity::new)
		.visual(() -> MillstoneCogVisual::new, false)
		.validBlocks(AllBlocks.MILLSTONE)
		.renderer(() -> MillstoneRenderer::new)
		.register();

	public static final BlockEntityEntry<CrushingWheelBlockEntity> CRUSHING_WHEEL = REGISTRATE
		.blockEntity("crushing_wheel", CrushingWheelBlockEntity::new)
		.visual(() -> SingleRotatingVisual::new, false)
		.validBlocks(AllBlocks.CRUSHING_WHEEL)
		.renderer(() -> KineticBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<CrushingWheelControllerBlockEntity> CRUSHING_WHEEL_CONTROLLER =
		REGISTRATE
			.blockEntity("crushing_wheel_controller", CrushingWheelControllerBlockEntity::new)
			.validBlocks(AllBlocks.CRUSHING_WHEEL_CONTROLLER)
			// .renderer(() -> renderer)
			.register();

	public static final BlockEntityEntry<WaterWheelBlockEntity> WATER_WHEEL = REGISTRATE
		.blockEntity("water_wheel", WaterWheelBlockEntity::new)
		.visual(() -> WaterWheelVisual::standard, false)
		.validBlocks(AllBlocks.WATER_WHEEL)
		.renderer(() -> WaterWheelRenderer::standard)
		.register();

	public static final BlockEntityEntry<LargeWaterWheelBlockEntity> LARGE_WATER_WHEEL = REGISTRATE
		.blockEntity("large_water_wheel", LargeWaterWheelBlockEntity::new)
		.visual(() -> WaterWheelVisual::large, false)
		.validBlocks(AllBlocks.LARGE_WATER_WHEEL)
		.renderer(() -> WaterWheelRenderer::large)
		.register();

	public static final BlockEntityEntry<MechanicalPressBlockEntity> MECHANICAL_PRESS = REGISTRATE
		.blockEntity("mechanical_press", MechanicalPressBlockEntity::new)
		.visual(() -> PressVisual::new)
		.validBlocks(AllBlocks.MECHANICAL_PRESS)
		.renderer(() -> MechanicalPressRenderer::new)
		.register();

	public static final BlockEntityEntry<MechanicalMixerBlockEntity> MECHANICAL_MIXER = REGISTRATE
		.blockEntity("mechanical_mixer", MechanicalMixerBlockEntity::new)
		.visual(() -> MixerVisual::new)
		.validBlocks(AllBlocks.MECHANICAL_MIXER)
		.renderer(() -> MechanicalMixerRenderer::new)
		.register();

	public static final BlockEntityEntry<DeployerBlockEntity> DEPLOYER = REGISTRATE
		.blockEntity("deployer", DeployerBlockEntity::new)
		.visual(() -> DeployerVisual::new)
		.validBlocks(AllBlocks.DEPLOYER)
		.renderer(() -> DeployerRenderer::new)
		.register();

	public static final BlockEntityEntry<BasinBlockEntity> BASIN = REGISTRATE
		.blockEntity("basin", BasinBlockEntity::new)
		.validBlocks(AllBlocks.BASIN)
		.renderer(() -> BasinRenderer::new)
		.register();

	public static final BlockEntityEntry<BlazeBurnerBlockEntity> HEATER = REGISTRATE
		.blockEntity("blaze_heater", BlazeBurnerBlockEntity::new)
		.visual(() -> BlazeBurnerVisual::new, false)
		.validBlocks(AllBlocks.BLAZE_BURNER)
		.renderer(() -> BlazeBurnerRenderer::new)
		.register();

	public static final BlockEntityEntry<MechanicalCrafterBlockEntity> MECHANICAL_CRAFTER = REGISTRATE
		.blockEntity("mechanical_crafter", MechanicalCrafterBlockEntity::new)
		.visual(() -> ShaftlessCogwheelVisual::new)
		.validBlocks(AllBlocks.MECHANICAL_CRAFTER)
		.renderer(() -> MechanicalCrafterRenderer::new)
		.register();

	public static final BlockEntityEntry<SequencedGearshiftBlockEntity> SEQUENCED_GEARSHIFT = REGISTRATE
		.blockEntity("sequenced_gearshift", SequencedGearshiftBlockEntity::new)
		.visual(() -> SplitShaftVisual::new, false)
		.validBlocks(AllBlocks.SEQUENCED_GEARSHIFT)
		.renderer(() -> SplitShaftRenderer::new)
		.register();

	public static final BlockEntityEntry<SpeedControllerBlockEntity> ROTATION_SPEED_CONTROLLER = REGISTRATE
		.blockEntity("rotation_speed_controller", SpeedControllerBlockEntity::new)
		.visual(() -> ShaftVisual::new)
		.validBlocks(AllBlocks.ROTATION_SPEED_CONTROLLER)
		.renderer(() -> SpeedControllerRenderer::new)
		.register();

	public static final BlockEntityEntry<SpeedGaugeBlockEntity> SPEEDOMETER = REGISTRATE
		.blockEntity("speedometer", SpeedGaugeBlockEntity::new)
		.visual(() -> GaugeVisual.Speed::new)
		.validBlocks(AllBlocks.SPEEDOMETER)
		.renderer(() -> GaugeRenderer::speed)
		.register();

	public static final BlockEntityEntry<StressGaugeBlockEntity> STRESSOMETER = REGISTRATE
		.blockEntity("stressometer", StressGaugeBlockEntity::new)
		.visual(() -> GaugeVisual.Stress::new)
		.validBlocks(AllBlocks.STRESSOMETER)
		.renderer(() -> GaugeRenderer::stress)
		.register();

	public static final BlockEntityEntry<AnalogLeverBlockEntity> ANALOG_LEVER = REGISTRATE
		.blockEntity("analog_lever", AnalogLeverBlockEntity::new)
		.visual(() -> AnalogLeverVisual::new, false)
		.validBlocks(AllBlocks.ANALOG_LEVER)
		.renderer(() -> AnalogLeverRenderer::new)
		.register();

	public static final BlockEntityEntry<PlacardBlockEntity> PLACARD = REGISTRATE
		.blockEntity("placard", PlacardBlockEntity::new)
		.validBlocks(AllBlocks.PLACARD)
		.renderer(() -> PlacardRenderer::new)
		.register();

	public static final BlockEntityEntry<CartAssemblerBlockEntity> CART_ASSEMBLER = REGISTRATE
		.blockEntity("cart_assembler", CartAssemblerBlockEntity::new)
		.validBlocks(AllBlocks.CART_ASSEMBLER)
		// .renderer(() -> renderer)
		.register();

	// Logistics
	public static final BlockEntityEntry<RedstoneLinkBlockEntity> REDSTONE_LINK = REGISTRATE
		.blockEntity("redstone_link", RedstoneLinkBlockEntity::new)
		.validBlocks(AllBlocks.REDSTONE_LINK)
		.renderer(() -> SmartBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<NixieTubeBlockEntity> NIXIE_TUBE = REGISTRATE
		.blockEntity("nixie_tube", NixieTubeBlockEntity::new)
		.validBlocks(AllBlocks.ORANGE_NIXIE_TUBE)
		.validBlocks(AllBlocks.NIXIE_TUBES.toArray())
		.renderer(() -> NixieTubeRenderer::new)
		.onRegister(assignDataBehaviourBE(new NixieTubeDisplayTarget()))
		.onRegister(assignDataBehaviourBE(new NixieTubeDisplaySource()))
		.register();

	public static final BlockEntityEntry<DisplayLinkBlockEntity> DISPLAY_LINK = REGISTRATE
		.blockEntity("display_link", DisplayLinkBlockEntity::new)
		.validBlocks(AllBlocks.DISPLAY_LINK)
		.renderer(() -> DisplayLinkRenderer::new)
		.register();

	public static final BlockEntityEntry<ThresholdSwitchBlockEntity> THRESHOLD_SWITCH = REGISTRATE
		.blockEntity("stockpile_switch", ThresholdSwitchBlockEntity::new)
		.validBlocks(AllBlocks.THRESHOLD_SWITCH)
		.renderer(() -> SmartBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<CreativeCrateBlockEntity> CREATIVE_CRATE = REGISTRATE
		.blockEntity("creative_crate", CreativeCrateBlockEntity::new)
		.validBlocks(AllBlocks.CREATIVE_CRATE)
		.renderer(() -> SmartBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<DepotBlockEntity> DEPOT = REGISTRATE
		.blockEntity("depot", DepotBlockEntity::new)
		.validBlocks(AllBlocks.DEPOT)
		.renderer(() -> DepotRenderer::new)
		.register();

	public static final BlockEntityEntry<EjectorBlockEntity> WEIGHTED_EJECTOR = REGISTRATE
		.blockEntity("weighted_ejector", EjectorBlockEntity::new)
		.visual(() -> EjectorVisual::new)
		.validBlocks(AllBlocks.WEIGHTED_EJECTOR)
		.renderer(() -> EjectorRenderer::new)
		.register();

	public static final BlockEntityEntry<FunnelBlockEntity> FUNNEL = REGISTRATE
		.blockEntity("funnel", FunnelBlockEntity::new)
		.visual(() -> FunnelVisual::new)
		.validBlocks(AllBlocks.BRASS_FUNNEL, AllBlocks.BRASS_BELT_FUNNEL, AllBlocks.ANDESITE_FUNNEL,
			AllBlocks.ANDESITE_BELT_FUNNEL)
		.renderer(() -> FunnelRenderer::new)
		.register();

	public static final BlockEntityEntry<SmartObserverBlockEntity> SMART_OBSERVER = REGISTRATE
		.blockEntity("content_observer", SmartObserverBlockEntity::new)
		.validBlocks(AllBlocks.SMART_OBSERVER)
		.renderer(() -> SmartBlockEntityRenderer::new)
		.register();

	public static final BlockEntityEntry<PulseExtenderBlockEntity> PULSE_EXTENDER = REGISTRATE
		.blockEntity("pulse_extender", PulseExtenderBlockEntity::new)
		.visual(() -> BrassDiodeVisual::new, false)
		.validBlocks(AllBlocks.PULSE_EXTENDER)
		.renderer(() -> BrassDiodeRenderer::new)
		.register();

	public static final BlockEntityEntry<PulseRepeaterBlockEntity> PULSE_REPEATER = REGISTRATE
		.blockEntity("pulse_repeater", PulseRepeaterBlockEntity::new)
		.visual(() -> BrassDiodeVisual::new, false)
		.validBlocks(AllBlocks.PULSE_REPEATER)
		.renderer(() -> BrassDiodeRenderer::new)
		.register();

	public static final BlockEntityEntry<LecternControllerBlockEntity> LECTERN_CONTROLLER = REGISTRATE
		.blockEntity("lectern_controller", LecternControllerBlockEntity::new)
		.validBlocks(AllBlocks.LECTERN_CONTROLLER)
		.renderer(() -> LecternControllerRenderer::new)
		.register();

	// Curiosities
	public static final BlockEntityEntry<BacktankBlockEntity> BACKTANK = REGISTRATE
		.blockEntity("backtank", BacktankBlockEntity::new)
		.visual(() -> BacktankVisual::new)
		.validBlocks(AllBlocks.COPPER_BACKTANK, AllBlocks.NETHERITE_BACKTANK)
		.renderer(() -> BacktankRenderer::new)
		.register();

	public static final BlockEntityEntry<PeculiarBellBlockEntity> PECULIAR_BELL = REGISTRATE
		.blockEntity("peculiar_bell", PeculiarBellBlockEntity::new)
		.validBlocks(AllBlocks.PECULIAR_BELL)
		.renderer(() -> BellRenderer::new)
		.register();

	public static final BlockEntityEntry<HauntedBellBlockEntity> HAUNTED_BELL = REGISTRATE
		.blockEntity("cursed_bell", HauntedBellBlockEntity::new)
		.validBlocks(AllBlocks.HAUNTED_BELL)
		.renderer(() -> BellRenderer::new)
		.register();

	public static final BlockEntityEntry<ToolboxBlockEntity> TOOLBOX = REGISTRATE
		.blockEntity("toolbox", ToolboxBlockEntity::new)
		.visual(() -> ToolBoxVisual::new, false)
		.validBlocks(AllBlocks.TOOLBOXES.toArray())
		.renderer(() -> ToolboxRenderer::new)
		.register();

	public static final BlockEntityEntry<TrackBlockEntity> TRACK = REGISTRATE
		.blockEntity("track", TrackBlockEntity::new)
		.visual(() -> TrackVisual::new)
		.validBlocksDeferred(TrackMaterial::allBlocks)
		.renderer(() -> TrackRenderer::new)
		.register();

	public static final BlockEntityEntry<FakeTrackBlockEntity> FAKE_TRACK = REGISTRATE
		.blockEntity("fake_track", FakeTrackBlockEntity::new)
		.validBlocks(AllBlocks.FAKE_TRACK)
		.register();

	public static final BlockEntityEntry<StandardBogeyBlockEntity> BOGEY = REGISTRATE
		.blockEntity("bogey", StandardBogeyBlockEntity::new)
		.visual(() -> BogeyBlockEntityVisual::new, false)
		.renderer(() -> BogeyBlockEntityRenderer::new)
		.validBlocks(AllBlocks.SMALL_BOGEY, AllBlocks.LARGE_BOGEY)
		.register();

	public static final BlockEntityEntry<StationBlockEntity> TRACK_STATION = REGISTRATE
		.blockEntity("track_station", StationBlockEntity::new)
		.renderer(() -> StationRenderer::new)
		.validBlocks(AllBlocks.TRACK_STATION)
		.register();

	public static final BlockEntityEntry<SlidingDoorBlockEntity> SLIDING_DOOR =
		REGISTRATE.blockEntity("sliding_door", SlidingDoorBlockEntity::new)
			.renderer(() -> SlidingDoorRenderer::new)
			.validBlocks(AllBlocks.TRAIN_DOOR, AllBlocks.FRAMED_GLASS_DOOR, AllBlocks.ANDESITE_DOOR,
				AllBlocks.BRASS_DOOR, AllBlocks.COPPER_DOOR)
			.register();

	public static final BlockEntityEntry<CopycatBlockEntity> COPYCAT =
		REGISTRATE.blockEntity("copycat", CopycatBlockEntity::new)
			.validBlocks(AllBlocks.COPYCAT_PANEL, AllBlocks.COPYCAT_STEP)
			.register();

	public static final BlockEntityEntry<FlapDisplayBlockEntity> FLAP_DISPLAY = REGISTRATE
		.blockEntity("flap_display", FlapDisplayBlockEntity::new)
		.visual(() -> ShaftlessCogwheelVisual::new)
		.renderer(() -> FlapDisplayRenderer::new)
		.validBlocks(AllBlocks.DISPLAY_BOARD)
		.register();

	public static final BlockEntityEntry<SignalBlockEntity> TRACK_SIGNAL = REGISTRATE
		.blockEntity("track_signal", SignalBlockEntity::new)
		.renderer(() -> SignalRenderer::new)
		.validBlocks(AllBlocks.TRACK_SIGNAL)
		.register();

	public static final BlockEntityEntry<TrackObserverBlockEntity> TRACK_OBSERVER = REGISTRATE
		.blockEntity("track_observer", TrackObserverBlockEntity::new)
		.renderer(() -> TrackObserverRenderer::new)
		.validBlocks(AllBlocks.TRACK_OBSERVER)
		.register();

	public static final BlockEntityEntry<ClipboardBlockEntity> CLIPBOARD = REGISTRATE
		.blockEntity("clipboard", ClipboardBlockEntity::new)
		.validBlocks(AllBlocks.CLIPBOARD)
		.register();

	public static void register() {}
}
