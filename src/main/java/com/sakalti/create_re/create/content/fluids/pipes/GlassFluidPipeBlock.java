package com.sakalti.create_re.content.fluids.pipes;

import javax.annotation.ParametersAreNonnullByDefault;

import com.sakalti.create_re.AllBlockEntityTypes;
import com.sakalti.create_re.AllBlocks;
import com.sakalti.create_re.content.fluids.FluidTransportBehaviour;
import com.sakalti.create_re.content.schematics.requirement.ISpecialBlockItemRequirement;
import com.sakalti.create_re.content.schematics.requirement.ItemRequirement;
import com.sakalti.create_re.foundation.block.IBE;

import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.pathfinder.PathComputationType;

@MethodsReturnNonnullByDefault
@ParametersAreNonnullByDefault
public class GlassFluidPipeBlock extends AxisPipeBlock implements IBE<StraightPipeBlockEntity>, SimpleWaterloggedBlock, ISpecialBlockItemRequirement {

	public static final BooleanProperty ALT = BooleanProperty.create_re("alt");

	public GlassFluidPipeBlock(Properties p_i48339_1_) {
		super(p_i48339_1_);
		registerDefaultState(defaultBlockState().setValue(ALT, false).setValue(BlockStateProperties.WATERLOGGED, false));
	}

	@Override
	protected void create_reBlockStateDefinition(Builder<Block, BlockState> p_206840_1_) {
		super.create_reBlockStateDefinition(p_206840_1_.add(ALT, BlockStateProperties.WATERLOGGED));
	}

	@Override
	public InteractionResult onWrenched(BlockState state, UseOnContext context) {
		if (tryRemoveBracket(context))
			return InteractionResult.SUCCESS;
		BlockState newState;
		Level world = context.getLevel();
		BlockPos pos = context.getClickedPos();
		FluidTransportBehaviour.cacheFlows(world, pos);
		newState = toRegularPipe(world, pos, state).setValue(BlockStateProperties.WATERLOGGED, state.getValue(BlockStateProperties.WATERLOGGED));
		world.setBlock(pos, newState, 3);
		FluidTransportBehaviour.loadFlows(world, pos);
		return InteractionResult.SUCCESS;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		FluidState ifluidstate = context.getLevel()
			.getFluidState(context.getClickedPos());
		BlockState state = super.getStateForPlacement(context);
		return state == null ? null : state.setValue(BlockStateProperties.WATERLOGGED,
			ifluidstate.getType() == Fluids.WATER);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(BlockStateProperties.WATERLOGGED) ? Fluids.WATER.getSource(false)
			: Fluids.EMPTY.defaultFluidState();
	}

	@Override
	public ItemRequirement getRequiredItems(BlockState state, BlockEntity be) {
		return ItemRequirement.of(AllBlocks.FLUID_PIPE.getDefaultState(), be);
	}

	@Override
	public boolean isPathfindable(BlockState state, BlockGetter reader, BlockPos pos, PathComputationType type) {
		return false;
	}

	@Override
	public Class<StraightPipeBlockEntity> getBlockEntityClass() {
		return StraightPipeBlockEntity.class;
	}

	@Override
	public BlockEntityType<? extends StraightPipeBlockEntity> getBlockEntityType() {
		return AllBlockEntityTypes.GLASS_FLUID_PIPE.get();
	}

}
