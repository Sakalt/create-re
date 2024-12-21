package com.sakalti.create_re.content.logistics.crate;

import java.util.List;

import com.mojang.blaze3d.vertex.PoseStack;
import com.sakalti.create_re.foundation.blockEntity.behaviour.BlockEntityBehaviour;
import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.sakalti.create_re.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.sakalti.create_re.foundation.utility.Lang;

import dev.engine_room.flywheel.lib.transform.TransformStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;

public class CreativeCrateBlockEntity extends CrateBlockEntity {

	public CreativeCrateBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
		super(type, pos, state);
		inv = new BottomlessItemHandler(filtering::getFilter);
		itemHandler = LazyOptional.of(() -> inv);
	}

	FilteringBehaviour filtering;
	LazyOptional<IItemHandler> itemHandler;
	private BottomlessItemHandler inv;

	@Override
	public void addBehaviours(List<BlockEntityBehaviour> behaviours) {
		behaviours.add(filtering = create_reFilter());
		filtering.setLabel(Lang.translateDirect("logistics.creative_crate.supply"));
	}

	@Override
	public void invalidate() {
		super.invalidate();
		if (itemHandler != null)
			itemHandler.invalidate();
	}

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		if (cap == ForgeCapabilities.ITEM_HANDLER)
			return itemHandler.cast();
		return super.getCapability(cap, side);
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
				return new Vec3(0.5, 13.5 / 16d, 0.5);
			}

			public float getScale() {
				return super.getScale();
			};

		});
	}

}
