package com.sakalti.create_re.content.trains.observer;

import java.util.List;

import javax.annotation.Nullable;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.create_re.content.contraptions.ITransformableBlockEntity;
import com.sakalti.create_re.content.contraptions.StructureTransform;
import com.sakalti.create_re.content.redstone.displayLink.DisplayLinkBlock;
import com.sakalti.create_re.content.trains.graph.EdgePointType;
import com.sakalti.create_re.content.trains.track.TrackTargetingBehaviour;
import com.sakalti.create_re.foundation.blockEntity.SmartBlockEntity;
import com.sakalti.create_re.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.sakalti.create_re.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.sakalti.create_re.foundation.utility.Lang;

import dev.engine_room.flywheel.lib.transform.TransformStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class TrackObserverBlockEntity extends SmartBlockEntity implements ITransformableBlockEntity {

	public TrackTargetingBehaviour<TrackObserver> edgePoint;

	private FilteringBehaviour filtering;

	public TrackObserverBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
	}

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours.add(edgePoint = new TrackTargetingBehaviour<>(this, EdgePointType.OBSERVER));
		behaviours.add(filtering = create_reFilter().withCallback(this::onFilterChanged));
		filtering.setLabel(Lang.translateDirect("logistics.train_observer.cargo_filter"));
	}

	private void onFilterChanged(ItemStack newFilter) {
		if (level.isClientSide())
			return;
		TrackObserver observer = getObserver();
		if (observer != null)
			observer.setFilterAndNotify(level, newFilter);
	}

	@Override
	public void tick() {
		super.tick();

		if (level.isClientSide())
			return;

		boolean shouldBePowered = false;
		TrackObserver observer = getObserver();
		if (observer != null)
			shouldBePowered = observer.isActivated();
		if (isBlockPowered() == shouldBePowered)
			return;

		BlockState blockState = getBlockState();
		if (blockState.hasProperty(TrackObserverBlock.POWERED))
			level.setBlock(worldPosition, blockState.setValue(TrackObserverBlock.POWERED, shouldBePowered), 3);
		DisplayLinkBlock.notifyGatherers(level, worldPosition);
	}

	@Nullable
	public TrackObserver getObserver() {
		return edgePoint.getEdgePoint();
	}

	public ItemStack getFilter() {
		return filtering.getFilter();
	}

	public boolean isBlockPowered() {
		return getBlockState().getOptionalValue(TrackObserverBlock.POWERED)
			.orElse(false);
	}

	@Override
	protected AABB create_reRenderBoundingBox() {
		return new AABB(worldPosition, edgePoint.getGlobalPosition()).inflate(2);
	}

	@Override
	public void transform(StructureTransform transform) {
		edgePoint.transform(transform);
	}

	public FilteringBehaviour create_reFilter() {
		return new FilteringBehaviour(this, new ValueBoxTransform() {

			@Override
			public void rotate(BlockState state, PoseStack ms) {
				TransformStack.of(ms)
					.rotateXDegrees(90);
			}

			@Override
			public Vec3 getLocalOffset(BlockState state) {
				return new Vec3(0.5, 15.5 / 16d, 0.5);
			}

		});
	}

}
