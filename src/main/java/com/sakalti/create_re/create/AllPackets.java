package com.sakalti.create_re;

import static net.minecraftforge.network.NetworkDirection.PLAY_TO_CLIENT;
import static net.minecraftforge.network.NetworkDirection.PLAY_TO_SERVER;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

import com.sakalti.create_re.compat.computercraft.AttachedComputerPacket;
import com.sakalti.create_re.compat.trainmap.TrainMapSyncPacket;
import com.sakalti.create_re.compat.trainmap.TrainMapSyncRequestPacket;
import com.sakalti.create_re.content.contraptions.ContraptionBlockChangedPacket;
import com.sakalti.create_re.content.contraptions.ContraptionColliderLockPacket;
import com.sakalti.create_re.content.contraptions.ContraptionColliderLockPacket.ContraptionColliderLockPacketRequest;
import com.sakalti.create_re.content.contraptions.ContraptionDisassemblyPacket;
import com.sakalti.create_re.content.contraptions.ContraptionRelocationPacket;
import com.sakalti.create_re.content.contraptions.ContraptionStallPacket;
import com.sakalti.create_re.content.contraptions.TrainCollisionPacket;
import com.sakalti.create_re.content.contraptions.actors.contraptionControls.ContraptionDisableActorPacket;
import com.sakalti.create_re.content.contraptions.actors.trainControls.ControlsInputPacket;
import com.sakalti.create_re.content.contraptions.actors.trainControls.ControlsStopControllingPacket;
import com.sakalti.create_re.content.contraptions.elevator.ElevatorContactEditPacket;
import com.sakalti.create_re.content.contraptions.elevator.ElevatorFloorListPacket;
import com.sakalti.create_re.content.contraptions.elevator.ElevatorTargetFloorPacket;
import com.sakalti.create_re.content.contraptions.gantry.GantryContraptionUpdatePacket;
import com.sakalti.create_re.content.contraptions.glue.GlueEffectPacket;
import com.sakalti.create_re.content.contraptions.glue.SuperGlueRemovalPacket;
import com.sakalti.create_re.content.contraptions.glue.SuperGlueSelectionPacket;
import com.sakalti.create_re.content.contraptions.minecart.CouplingCreationPacket;
import com.sakalti.create_re.content.contraptions.minecart.capability.MinecartControllerUpdatePacket;
import com.sakalti.create_re.content.contraptions.sync.ClientMotionPacket;
import com.sakalti.create_re.content.contraptions.sync.ContraptionFluidPacket;
import com.sakalti.create_re.content.contraptions.sync.ContraptionInteractionPacket;
import com.sakalti.create_re.content.contraptions.sync.ContraptionSeatMappingPacket;
import com.sakalti.create_re.content.contraptions.sync.LimbSwingUpdatePacket;
import com.sakalti.create_re.content.equipment.bell.SoulPulseEffectPacket;
import com.sakalti.create_re.content.equipment.blueprint.BlueprintAssignCompleteRecipePacket;
import com.sakalti.create_re.content.equipment.clipboard.ClipboardEditPacket;
import com.sakalti.create_re.content.equipment.extendoGrip.ExtendoGripInteractionPacket;
import com.sakalti.create_re.content.equipment.potatoCannon.PotatoCannonPacket;
import com.sakalti.create_re.content.equipment.potatoCannon.PotatoProjectileTypeManager;
import com.sakalti.create_re.content.equipment.symmetryWand.ConfigureSymmetryWandPacket;
import com.sakalti.create_re.content.equipment.symmetryWand.SymmetryEffectPacket;
import com.sakalti.create_re.content.equipment.toolbox.ToolboxDisposeAllPacket;
import com.sakalti.create_re.content.equipment.toolbox.ToolboxEquipPacket;
import com.sakalti.create_re.content.equipment.zapper.ZapperBeamPacket;
import com.sakalti.create_re.content.equipment.zapper.terrainzapper.ConfigureWorldshaperPacket;
import com.sakalti.create_re.content.fluids.transfer.FluidSplashPacket;
import com.sakalti.create_re.content.kinetics.gauge.GaugeObservedPacket;
import com.sakalti.create_re.content.kinetics.mechanicalArm.ArmPlacementPacket;
import com.sakalti.create_re.content.kinetics.transmission.sequencer.ConfigureSequencedGearshiftPacket;
import com.sakalti.create_re.content.logistics.depot.EjectorAwardPacket;
import com.sakalti.create_re.content.logistics.depot.EjectorElytraPacket;
import com.sakalti.create_re.content.logistics.depot.EjectorPlacementPacket;
import com.sakalti.create_re.content.logistics.depot.EjectorTriggerPacket;
import com.sakalti.create_re.content.logistics.filter.FilterScreenPacket;
import com.sakalti.create_re.content.logistics.funnel.FunnelFlapPacket;
import com.sakalti.create_re.content.logistics.tunnel.TunnelFlapPacket;
import com.sakalti.create_re.content.redstone.displayLink.DisplayLinkConfigurationPacket;
import com.sakalti.create_re.content.redstone.link.controller.LinkedControllerBindPacket;
import com.sakalti.create_re.content.redstone.link.controller.LinkedControllerInputPacket;
import com.sakalti.create_re.content.redstone.link.controller.LinkedControllerStopLecternPacket;
import com.sakalti.create_re.content.redstone.thresholdSwitch.ConfigureThresholdSwitchPacket;
import com.sakalti.create_re.content.schematics.cannon.ConfigureSchematicannonPacket;
import com.sakalti.create_re.content.schematics.packet.InstantSchematicPacket;
import com.sakalti.create_re.content.schematics.packet.SchematicPlacePacket;
import com.sakalti.create_re.content.schematics.packet.SchematicSyncPacket;
import com.sakalti.create_re.content.schematics.packet.SchematicUploadPacket;
import com.sakalti.create_re.content.trains.HonkPacket;
import com.sakalti.create_re.content.trains.TrainHUDUpdatePacket;
import com.sakalti.create_re.content.trains.entity.TrainPacket;
import com.sakalti.create_re.content.trains.entity.TrainPromptPacket;
import com.sakalti.create_re.content.trains.entity.TrainRelocationPacket;
import com.sakalti.create_re.content.trains.graph.TrackGraphRequestPacket;
import com.sakalti.create_re.content.trains.graph.TrackGraphRollCallPacket;
import com.sakalti.create_re.content.trains.graph.TrackGraphSyncPacket;
import com.sakalti.create_re.content.trains.schedule.ScheduleEditPacket;
import com.sakalti.create_re.content.trains.signal.SignalEdgeGroupPacket;
import com.sakalti.create_re.content.trains.station.StationEditPacket;
import com.sakalti.create_re.content.trains.station.TrainEditPacket;
import com.sakalti.create_re.content.trains.station.TrainEditPacket.TrainEditReturnPacket;
import com.sakalti.create_re.content.trains.track.CurvedTrackDestroyPacket;
import com.sakalti.create_re.content.trains.track.CurvedTrackSelectionPacket;
import com.sakalti.create_re.content.trains.track.PlaceExtendedCurvePacket;
import com.sakalti.create_re.foundation.blockEntity.RemoveBlockEntityPacket;
import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueSettingsPacket;
import com.sakalti.create_re.foundation.config.ui.CConfigureConfigPacket;
import com.sakalti.create_re.foundation.gui.menu.ClearMenuPacket;
import com.sakalti.create_re.foundation.gui.menu.GhostItemSubmitPacket;
import com.sakalti.create_re.foundation.networking.ISyncPersistentData;
import com.sakalti.create_re.foundation.networking.LeftClickPacket;
import com.sakalti.create_re.foundation.networking.SimplePacketBase;
import com.sakalti.create_re.foundation.utility.ServerSpeedProvider;
import com.sakalti.create_re.infrastructure.command.HighlightPacket;
import com.sakalti.create_re.infrastructure.command.SConfigureConfigPacket;
import com.sakalti.create_re.infrastructure.debugInfo.ServerDebugInfoPacket;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent.Context;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.PacketDistributor.TargetPoint;
import net.minecraftforge.network.simple.SimpleChannel;

public enum AllPackets {

	// Client to Server
	CONFIGURE_SCHEMATICANNON(ConfigureSchematicannonPacket.class, ConfigureSchematicannonPacket::new, PLAY_TO_SERVER),
	CONFIGURE_STOCKSWITCH(ConfigureThresholdSwitchPacket.class, ConfigureThresholdSwitchPacket::new, PLAY_TO_SERVER),
	CONFIGURE_SEQUENCER(ConfigureSequencedGearshiftPacket.class, ConfigureSequencedGearshiftPacket::new,
		PLAY_TO_SERVER),
	PLACE_SCHEMATIC(SchematicPlacePacket.class, SchematicPlacePacket::new, PLAY_TO_SERVER),
	UPLOAD_SCHEMATIC(SchematicUploadPacket.class, SchematicUploadPacket::new, PLAY_TO_SERVER),
	CLEAR_CONTAINER(ClearMenuPacket.class, ClearMenuPacket::new, PLAY_TO_SERVER),
	CONFIGURE_FILTER(FilterScreenPacket.class, FilterScreenPacket::new, PLAY_TO_SERVER),
	EXTENDO_INTERACT(ExtendoGripInteractionPacket.class, ExtendoGripInteractionPacket::new, PLAY_TO_SERVER),
	CONTRAPTION_INTERACT(ContraptionInteractionPacket.class, ContraptionInteractionPacket::new, PLAY_TO_SERVER),
	CLIENT_MOTION(ClientMotionPacket.class, ClientMotionPacket::new, PLAY_TO_SERVER),
	PLACE_ARM(ArmPlacementPacket.class, ArmPlacementPacket::new, PLAY_TO_SERVER),
	MINECART_COUPLING_CREATION(CouplingCreationPacket.class, CouplingCreationPacket::new, PLAY_TO_SERVER),
	INSTANT_SCHEMATIC(InstantSchematicPacket.class, InstantSchematicPacket::new, PLAY_TO_SERVER),
	SYNC_SCHEMATIC(SchematicSyncPacket.class, SchematicSyncPacket::new, PLAY_TO_SERVER),
	LEFT_CLICK(LeftClickPacket.class, LeftClickPacket::new, PLAY_TO_SERVER),
	PLACE_EJECTOR(EjectorPlacementPacket.class, EjectorPlacementPacket::new, PLAY_TO_SERVER),
	TRIGGER_EJECTOR(EjectorTriggerPacket.class, EjectorTriggerPacket::new, PLAY_TO_SERVER),
	EJECTOR_ELYTRA(EjectorElytraPacket.class, EjectorElytraPacket::new, PLAY_TO_SERVER),
	LINKED_CONTROLLER_INPUT(LinkedControllerInputPacket.class, LinkedControllerInputPacket::new, PLAY_TO_SERVER),
	LINKED_CONTROLLER_BIND(LinkedControllerBindPacket.class, LinkedControllerBindPacket::new, PLAY_TO_SERVER),
	LINKED_CONTROLLER_USE_LECTERN(LinkedControllerStopLecternPacket.class, LinkedControllerStopLecternPacket::new,
		PLAY_TO_SERVER),
	C_CONFIGURE_CONFIG(CConfigureConfigPacket.class, CConfigureConfigPacket::new, PLAY_TO_SERVER),
	SUBMIT_GHOST_ITEM(GhostItemSubmitPacket.class, GhostItemSubmitPacket::new, PLAY_TO_SERVER),
	BLUEPRINT_COMPLETE_RECIPE(BlueprintAssignCompleteRecipePacket.class, BlueprintAssignCompleteRecipePacket::new,
		PLAY_TO_SERVER),
	CONFIGURE_SYMMETRY_WAND(ConfigureSymmetryWandPacket.class, ConfigureSymmetryWandPacket::new, PLAY_TO_SERVER),
	CONFIGURE_WORLDSHAPER(ConfigureWorldshaperPacket.class, ConfigureWorldshaperPacket::new, PLAY_TO_SERVER),
	TOOLBOX_EQUIP(ToolboxEquipPacket.class, ToolboxEquipPacket::new, PLAY_TO_SERVER),
	TOOLBOX_DISPOSE_ALL(ToolboxDisposeAllPacket.class, ToolboxDisposeAllPacket::new, PLAY_TO_SERVER),
	CONFIGURE_SCHEDULE(ScheduleEditPacket.class, ScheduleEditPacket::new, PLAY_TO_SERVER),
	CONFIGURE_STATION(StationEditPacket.class, StationEditPacket::new, PLAY_TO_SERVER),
	C_CONFIGURE_TRAIN(TrainEditPacket.class, TrainEditPacket::new, PLAY_TO_SERVER),
	RELOCATE_TRAIN(TrainRelocationPacket.class, TrainRelocationPacket::new, PLAY_TO_SERVER),
	CONTROLS_INPUT(ControlsInputPacket.class, ControlsInputPacket::new, PLAY_TO_SERVER),
	CONFIGURE_DATA_GATHERER(DisplayLinkConfigurationPacket.class, DisplayLinkConfigurationPacket::new, PLAY_TO_SERVER),
	DESTROY_CURVED_TRACK(CurvedTrackDestroyPacket.class, CurvedTrackDestroyPacket::new, PLAY_TO_SERVER),
	SELECT_CURVED_TRACK(CurvedTrackSelectionPacket.class, CurvedTrackSelectionPacket::new, PLAY_TO_SERVER),
	PLACE_CURVED_TRACK(PlaceExtendedCurvePacket.class, PlaceExtendedCurvePacket::new, PLAY_TO_SERVER),
	GLUE_IN_AREA(SuperGlueSelectionPacket.class, SuperGlueSelectionPacket::new, PLAY_TO_SERVER),
	GLUE_REMOVED(SuperGlueRemovalPacket.class, SuperGlueRemovalPacket::new, PLAY_TO_SERVER),
	TRAIN_COLLISION(TrainCollisionPacket.class, TrainCollisionPacket::new, PLAY_TO_SERVER),
	C_TRAIN_HUD(TrainHUDUpdatePacket.Serverbound.class, TrainHUDUpdatePacket.Serverbound::new, PLAY_TO_SERVER),
	C_TRAIN_HONK(HonkPacket.Serverbound.class, HonkPacket.Serverbound::new, PLAY_TO_SERVER),
	OBSERVER_STRESSOMETER(GaugeObservedPacket.class, GaugeObservedPacket::new, PLAY_TO_SERVER),
	EJECTOR_AWARD(EjectorAwardPacket.class, EjectorAwardPacket::new, PLAY_TO_SERVER),
	TRACK_GRAPH_REQUEST(TrackGraphRequestPacket.class, TrackGraphRequestPacket::new, PLAY_TO_SERVER),
	CONFIGURE_ELEVATOR_CONTACT(ElevatorContactEditPacket.class, ElevatorContactEditPacket::new, PLAY_TO_SERVER),
	REQUEST_FLOOR_LIST(ElevatorFloorListPacket.RequestFloorList.class, ElevatorFloorListPacket.RequestFloorList::new,
		PLAY_TO_SERVER),
	ELEVATOR_SET_FLOOR(ElevatorTargetFloorPacket.class, ElevatorTargetFloorPacket::new, PLAY_TO_SERVER),
	VALUE_SETTINGS(ValueSettingsPacket.class, ValueSettingsPacket::new, PLAY_TO_SERVER),
	CLIPBOARD_EDIT(ClipboardEditPacket.class, ClipboardEditPacket::new, PLAY_TO_SERVER),
	CONTRAPTION_COLLIDER_LOCK_REQUEST(ContraptionColliderLockPacketRequest.class,
		ContraptionColliderLockPacketRequest::new, PLAY_TO_SERVER),
	TRAIN_MAP_REQUEST(TrainMapSyncRequestPacket.class, TrainMapSyncRequestPacket::new, PLAY_TO_SERVER),

	// Server to Client
	SYMMETRY_EFFECT(SymmetryEffectPacket.class, SymmetryEffectPacket::new, PLAY_TO_CLIENT),
	SERVER_SPEED(ServerSpeedProvider.Packet.class, ServerSpeedProvider.Packet::new, PLAY_TO_CLIENT),
	BEAM_EFFECT(ZapperBeamPacket.class, ZapperBeamPacket::new, PLAY_TO_CLIENT),
	S_CONFIGURE_CONFIG(SConfigureConfigPacket.class, SConfigureConfigPacket::new, PLAY_TO_CLIENT),
	CONTRAPTION_STALL(ContraptionStallPacket.class, ContraptionStallPacket::new, PLAY_TO_CLIENT),
	CONTRAPTION_DISASSEMBLE(ContraptionDisassemblyPacket.class, ContraptionDisassemblyPacket::new, PLAY_TO_CLIENT),
	CONTRAPTION_BLOCK_CHANGED(ContraptionBlockChangedPacket.class, ContraptionBlockChangedPacket::new, PLAY_TO_CLIENT),
	GLUE_EFFECT(GlueEffectPacket.class, GlueEffectPacket::new, PLAY_TO_CLIENT),
	CONTRAPTION_SEAT_MAPPING(ContraptionSeatMappingPacket.class, ContraptionSeatMappingPacket::new, PLAY_TO_CLIENT),
	LIMBSWING_UPDATE(LimbSwingUpdatePacket.class, LimbSwingUpdatePacket::new, PLAY_TO_CLIENT),
	MINECART_CONTROLLER(MinecartControllerUpdatePacket.class, MinecartControllerUpdatePacket::new, PLAY_TO_CLIENT),
	FLUID_SPLASH(FluidSplashPacket.class, FluidSplashPacket::new, PLAY_TO_CLIENT),
	CONTRAPTION_FLUID(ContraptionFluidPacket.class, ContraptionFluidPacket::new, PLAY_TO_CLIENT),
	GANTRY_UPDATE(GantryContraptionUpdatePacket.class, GantryContraptionUpdatePacket::new, PLAY_TO_CLIENT),
	BLOCK_HIGHLIGHT(HighlightPacket.class, HighlightPacket::new, PLAY_TO_CLIENT),
	TUNNEL_FLAP(TunnelFlapPacket.class, TunnelFlapPacket::new, PLAY_TO_CLIENT),
	FUNNEL_FLAP(FunnelFlapPacket.class, FunnelFlapPacket::new, PLAY_TO_CLIENT),
	POTATO_CANNON(PotatoCannonPacket.class, PotatoCannonPacket::new, PLAY_TO_CLIENT),
	SOUL_PULSE(SoulPulseEffectPacket.class, SoulPulseEffectPacket::new, PLAY_TO_CLIENT),
	PERSISTENT_DATA(ISyncPersistentData.PersistentDataPacket.class, ISyncPersistentData.PersistentDataPacket::new,
		PLAY_TO_CLIENT),
	SYNC_POTATO_PROJECTILE_TYPES(PotatoProjectileTypeManager.SyncPacket.class,
		PotatoProjectileTypeManager.SyncPacket::new, PLAY_TO_CLIENT),
	SYNC_RAIL_GRAPH(TrackGraphSyncPacket.class, TrackGraphSyncPacket::new, PLAY_TO_CLIENT),
	SYNC_EDGE_GROUP(SignalEdgeGroupPacket.class, SignalEdgeGroupPacket::new, PLAY_TO_CLIENT),
	SYNC_TRAIN(TrainPacket.class, TrainPacket::new, PLAY_TO_CLIENT),
	REMOVE_TE(RemoveBlockEntityPacket.class, RemoveBlockEntityPacket::new, PLAY_TO_CLIENT),
	S_CONFIGURE_TRAIN(TrainEditReturnPacket.class, TrainEditReturnPacket::new, PLAY_TO_CLIENT),
	CONTROLS_ABORT(ControlsStopControllingPacket.class, ControlsStopControllingPacket::new, PLAY_TO_CLIENT),
	S_TRAIN_HUD(TrainHUDUpdatePacket.class, TrainHUDUpdatePacket::new, PLAY_TO_CLIENT),
	S_TRAIN_HONK(HonkPacket.class, HonkPacket::new, PLAY_TO_CLIENT),
	S_TRAIN_PROMPT(TrainPromptPacket.class, TrainPromptPacket::new, PLAY_TO_CLIENT),
	CONTRAPTION_RELOCATION(ContraptionRelocationPacket.class, ContraptionRelocationPacket::new, PLAY_TO_CLIENT),
	TRACK_GRAPH_ROLL_CALL(TrackGraphRollCallPacket.class, TrackGraphRollCallPacket::new, PLAY_TO_CLIENT),
	S_PLACE_EJECTOR(ArmPlacementPacket.ClientBoundRequest.class, ArmPlacementPacket.ClientBoundRequest::new,
		PLAY_TO_CLIENT),
	S_PLACE_ARM(EjectorPlacementPacket.ClientBoundRequest.class, EjectorPlacementPacket.ClientBoundRequest::new,
		PLAY_TO_CLIENT),
	UPDATE_ELEVATOR_FLOORS(ElevatorFloorListPacket.class, ElevatorFloorListPacket::new, PLAY_TO_CLIENT),
	CONTRAPTION_ACTOR_TOGGLE(ContraptionDisableActorPacket.class, ContraptionDisableActorPacket::new, PLAY_TO_CLIENT),
	CONTRAPTION_COLLIDER_LOCK(ContraptionColliderLockPacket.class, ContraptionColliderLockPacket::new, PLAY_TO_CLIENT),
	ATTACHED_COMPUTER(AttachedComputerPacket.class, AttachedComputerPacket::new, PLAY_TO_CLIENT),
	SERVER_DEBUG_INFO(ServerDebugInfoPacket.class, ServerDebugInfoPacket::new, PLAY_TO_CLIENT),
	TRAIN_MAP_SYNC(TrainMapSyncPacket.class, TrainMapSyncPacket::new, PLAY_TO_CLIENT)
	;

	public static final ResourceLocation CHANNEL_NAME = Create.asResource("main");
	public static final int NETWORK_VERSION = 3;
	public static final String NETWORK_VERSION_STR = String.valueOf(NETWORK_VERSION);
	private static SimpleChannel channel;

	private PacketType<?> packetType;

	<T extends SimplePacketBase> AllPackets(Class<T> type, Function<FriendlyByteBuf, T> factory,
		NetworkDirection direction) {
		packetType = new PacketType<>(type, factory, direction);
	}

	public static void registerPackets() {
		channel = NetworkRegistry.ChannelBuilder.named(CHANNEL_NAME)
			.serverAcceptedVersions(NETWORK_VERSION_STR::equals)
			.clientAcceptedVersions(NETWORK_VERSION_STR::equals)
			.networkProtocolVersion(() -> NETWORK_VERSION_STR)
			.simpleChannel();

		for (AllPackets packet : values())
			packet.packetType.register();
	}

	public static SimpleChannel getChannel() {
		return channel;
	}

	public static void sendToNear(Level world, BlockPos pos, int range, Object message) {
		getChannel().send(
			PacketDistributor.NEAR.with(TargetPoint.p(pos.getX(), pos.getY(), pos.getZ(), range, world.dimension())),
			message);
	}

	private static class PacketType<T extends SimplePacketBase> {
		private static int index = 0;

		private BiConsumer<T, FriendlyByteBuf> encoder;
		private Function<FriendlyByteBuf, T> decoder;
		private BiConsumer<T, Supplier<Context>> handler;
		private Class<T> type;
		private NetworkDirection direction;

		private PacketType(Class<T> type, Function<FriendlyByteBuf, T> factory, NetworkDirection direction) {
			encoder = T::write;
			decoder = factory;
			handler = (packet, contextSupplier) -> {
				Context context = contextSupplier.get();
				if (packet.handle(context)) {
					context.setPacketHandled(true);
				}
			};
			this.type = type;
			this.direction = direction;
		}

		private void register() {
			getChannel().messageBuilder(type, index++, direction)
				.encoder(encoder)
				.decoder(decoder)
				.consumerNetworkThread(handler)
				.add();
		}
	}

}
