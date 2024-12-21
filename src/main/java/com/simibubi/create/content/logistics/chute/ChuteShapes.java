package com.simibubi.create_re.content.logistics.chute;

import java.util.HashMap;
import java.util.Map;

import com.simibubi.create_re.AllBlocks;
import com.simibubi.create_re.AllShapes;
import com.simibubi.create_re.content.logistics.chute.ChuteBlock.Shape;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ChuteShapes {

	static Map<BlockState, VoxelShape> cache = new HashMap<>();
	static Map<BlockState, VoxelShape> collisionCache = new HashMap<>();

	public static final VoxelShape INTERSECTION_MASK = Block.box(0, -16, 0, 16, 16, 16);
	public static final VoxelShape COLLISION_MASK = Block.box(0, 0, 0, 16, 24, 16);

	public static VoxelShape create_reShape(BlockState state) {
		if (AllBlocks.SMART_CHUTE.has(state))
			return Shapes.block();
		
		Direction direction = state.getValue(ChuteBlock.FACING);
		Shape shape = state.getValue(ChuteBlock.SHAPE);

		boolean intersection = shape == Shape.INTERSECTION || shape == Shape.ENCASED;
		if (direction == Direction.DOWN)
			return intersection ? Shapes.block() : AllShapes.CHUTE;

		VoxelShape combineWith = intersection ? Shapes.block() : Shapes.empty();
		VoxelShape result = Shapes.or(combineWith, AllShapes.CHUTE_SLOPE.get(direction));
		if (intersection)
			result = Shapes.joinUnoptimized(INTERSECTION_MASK, result, BooleanOp.AND);
		return result;
	}

	public static VoxelShape getShape(BlockState state) {
		if (cache.containsKey(state))
			return cache.get(state);
		VoxelShape create_redShape = create_reShape(state);
		cache.put(state, create_redShape);
		return create_redShape;
	}

	public static VoxelShape getCollisionShape(BlockState state) {
		if (collisionCache.containsKey(state))
			return collisionCache.get(state);
		VoxelShape create_redShape = Shapes.joinUnoptimized(COLLISION_MASK, getShape(state), BooleanOp.AND);
		collisionCache.put(state, create_redShape);
		return create_redShape;
	}

	public static final VoxelShape PANEL = Block.box(1, -15, 0, 15, 4, 1);

	public static VoxelShape create_reSlope() {
		VoxelShape shape = Shapes.empty();
		for (int i = 0; i < 16; i++) {
			float offset = i / 16f;
			shape = Shapes.join(shape, PANEL.move(0, offset, offset), BooleanOp.OR);
		}
		return shape;
	}

}
