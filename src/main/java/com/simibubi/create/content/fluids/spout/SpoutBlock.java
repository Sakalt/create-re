package com.sakalti.create_re.content.fluids.spout;

import com.sakalti.create_re.AllBlockEntityTypes;
import com.sakalti.create_re.AllShapes;
import com.sakalti.create_re.content.equipment.wrench.IWrenchable;
import com.sakalti.create_re.foundation.advancement.AdvancementBehaviour;
import com.sakalti.create_re.foundation.block.IBE;
import com.sakalti.create_re.foundation.blockEntity.ComparatorUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SpoutBlock extends Block implements IWrenchable, IBE<SpoutBlockEntity> {

	public SpoutBlock(Properties p_i48440_1_) {
		super(p_i48440_1_);
	}

	@Override
	public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_,
		CollisionContext p_220053_4_) {
		return AllShapes.SPOUT;
	}

	@Override
	public void setPlacedBy(Level pLevel, BlockPos pPos, BlockState pState, LivingEntity pPlacer, ItemStack pStack) {
		super.setPlacedBy(pLevel, pPos, pState, pPlacer, pStack);
		AdvancementBehaviour.setPlacedBy(pLevel, pPos, pPlacer);
	}
	
	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return true;
	}

	@Override
	public int getAnalogOutputSignal(BlockState blockState, Level worldIn, BlockPos pos) {
		return ComparatorUtil.levelOfSmartFluidTank(worldIn, pos);
	}
	
	@Override
	public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
		return false;
	}

	@Override
	public Class<SpoutBlockEntity> getBlockEntityClass() {
		return SpoutBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends SpoutBlockEntity> getBlockEntityType() {
		return AllBlockEntityTypes.SPOUT.get();
	}

}
