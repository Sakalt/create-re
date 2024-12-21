package com.sakalti.create_re.content.kinetics.belt;

import static net.minecraft.world.level.block.Block.box;

import java.util.HashMap;
import java.util.Map;

import com.sakalti.create_re.AllShapes;
import com.sakalti.create_re.foundation.utility.VoxelShaper;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BeltShapes {

	/*
	 * | hi. i made these comments mostly to help me with creating the shapes. but they should also be able to help you understand what i'm doing here if that's why you came here. cheers
	 * |
	 * |                 belt shape slope south descending
	 * |                    generated by makeSlopePart
	 * |         z
	 * |  y        15 14 13 12 11 10  9  8  7  6  5  4  3  2  1  0   |               |                                                    |                                                           |
	 * |            |                                            |   |               |                                                    |                                                           |
	 * | +5         |                                            #   |               |            belt shape flat south ending            |            belt shape flat south full                     |
	 * | +4         |                                         #  #   |               |             generated by makeFlatEnding            |             generated by makeFlatFull                     |
	 * | +3         |                                      #  #  #   | z             |z                                                   |z                                                          |
	 * | +2         |                                   #  #  #  #   |  15  14 ...   |  15 14 13 12 11 10  9  8  7  6  5  4  3  2  1  0   |  15 14 13 12 11 10  9  8  7  6  5  4  3  2  1  0          |
	 * | +1         |                                #  #  #  #  #   |   |           |   |                                            |   |   |                                            |          |
	 * | 15   ------+  -  -  -  -  -  -  -  -  -  #  #  #  #  #  #-- | --+---------- | --+  -  -  -  -  -  -  -  -  -  -  -  -  -  -  +-- | --+  -  -  -  -  -  -  -  -  -  -  -  -  -  -  +---       |
	 * | 14         |                          #  #  #  #  #  #  #   |   |           |   |                                            |   |   |                                            |          |
	 * | 13         |                       #  #  #  #  #  #  #  #   |   |           |   |                                            |   |   |                                            |          |
	 * | 12         |                    #  #  #  #  #  #  #  #  #   |   |           |   |  #  #  #  #  #  #  #  #  #  #  #  #  #  #  |   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 11         |                 #  #  #  #  #  #  #  #  #  #   |   |           |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 10         |              #  #  #  #  #  #  #  #  #  #  #   |   #           |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 9          |           #  #  #  #  #  #  #  #  #  #  #  |   |   #           |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 8          |        #  #  #  #  #  #  #  #  #  #  #     |   |   #           |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 7          |     #  #  #  #  #  #  #  #  #  #  #        |   |   #           |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 6          |  #  #  #  #  #  #  #  #  #  #  #           |   |   #           |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 5          #  #  #  #  #  #  #  #  #  #  #              |   |   #           |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 4          #  #  #  #  #  #  #  #  #  #                 |   |   #           |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 3          #  #  #  #  #  #  #  #  #                    |   |   #           |   |  #  #  #  #  #  #  #  #  #  #  #  #  #  #  |   |   #  #  #  #  #  #  #  #  #  #  #  #  #  #  #  #          |
	 * | 2          #  #  #  #  #  #  #  #                       |   |   #           |   |                                            |   |   |                                            |          |
	 * | 1          #  #  #  #  #  #  #                          |   |   #           |   |                                            |   |   |                                            |          |
	 * | 0   -------#  #  #  #  #  #  -  -  -  -  -  -  -  -  -  +-- | --#---------- | --+  -  -  -  -  -  -  -  -  -  -  -  -  -  -  +-- | --+  -  -  -  -  -  -  -  -  -  -  -  -  -  -  +---       |
	 * |-1          #  #  #  #  #                                |   |   |           |   |                                            |   |   |                                            |          |
	 * |-2          #  #  #  #                                   |   |   |           |   |                                            |   |   |                                            |          |
	 * |-3          #  #  #                                          |  slice used   |                                                    |                                                           |
	 * |-4          #  #                                             |  to create_re    |                                                    |                                                           |
	 * |-5          #                                                |  the stairs   |                                                    |                                                           |
	 * |                                  x 1 to 14                  |               |                                                    |                                                           |
	 */

	/*
	 * |Belt shapes always consist of 2 halves depending on state. This class generated all shapes for belts facing SOUTH and then uses VoxelShapers to fill the remaining 3 Directions
	 * |Middle shapes use the same building part in both halved and don't need to be composed
	 * |some of these shapes could be skipped and easily achieved by rotating other shapes but i left them in for clarity's sake
	 * | Flat Belts:                                    Sloped Belts: (DESC)                           (ASC)
	 * |                south half    north half                         south half    north half         south half    north half
	 * |
	 * |      Middle     flat full     flat full               Middle    slope desc    slope desc          slope asc     slope asc
	 * |         End      flat end     flat full                  End     flat end     slope desc           flat end     slope asc
	 * |       Start     flat full      flat end                Start    slope desc     flat end           slope asc      flat end
	 */

	//Building parts for the shapes
	private static final VoxelShape
			SLOPE_DESC_PART = makeSlopePart(false),
			SLOPE_ASC_PART = makeSlopePart(true),
			SIDEWAYS_FULL_PART = makeSidewaysFull(),
			SIDEWAYS_END_PART = makeSidewaysEnding(),
			FLAT_FULL_PART = makeFlatFull(),
			FLAT_END_PART = makeFlatEnding();

	private static final VoxelShape SOUTH_MASK = box(0,-5,8,16,16+5,16);
	private static final VoxelShape NORTH_MASK = box(0,-5,0,16,16+5,8);

	//Vertical Shapes
	private static final VoxelShaper
			VERTICAL_FULL = VerticalBeltShaper.make(FLAT_FULL_PART),
			VERTICAL_END = VerticalBeltShaper.make(compose(FLAT_END_PART, FLAT_FULL_PART)),
			VERTICAL_START = VerticalBeltShaper.make(compose(FLAT_FULL_PART, FLAT_END_PART));

	//Flat Shapes
	private static final VoxelShaper
			FLAT_FULL = VoxelShaper.forHorizontalAxis(FLAT_FULL_PART, Axis.Z),
			FLAT_END = VoxelShaper.forHorizontal(compose(FLAT_END_PART, FLAT_FULL_PART), Direction.SOUTH),
			FLAT_START = VoxelShaper.forHorizontal(compose(FLAT_FULL_PART, FLAT_END_PART), Direction.SOUTH);

	//Sideways Shapes
	private static final VoxelShaper
			SIDE_FULL = VoxelShaper.forHorizontalAxis(SIDEWAYS_FULL_PART, Axis.Z),
			SIDE_END = VoxelShaper.forHorizontal(compose(SIDEWAYS_END_PART, SIDEWAYS_FULL_PART), Direction.SOUTH),
			SIDE_START = VoxelShaper.forHorizontal(compose(SIDEWAYS_FULL_PART, SIDEWAYS_END_PART), Direction.SOUTH);

	//Sloped Shapes
	private static final VoxelShaper
			SLOPE_DESC = VoxelShaper.forHorizontal(SLOPE_DESC_PART, Direction.SOUTH),
			SLOPE_ASC = VoxelShaper.forHorizontal(SLOPE_ASC_PART, Direction.SOUTH),
			SLOPE_DESC_END = VoxelShaper.forHorizontal(compose(FLAT_END_PART, SLOPE_DESC_PART), Direction.SOUTH),
			SLOPE_DESC_START = VoxelShaper.forHorizontal(compose(SLOPE_DESC_PART, FLAT_END_PART), Direction.SOUTH),
			SLOPE_ASC_END = VoxelShaper.forHorizontal(compose(FLAT_END_PART, SLOPE_ASC_PART), Direction.SOUTH),
			SLOPE_ASC_START = VoxelShaper.forHorizontal(compose(SLOPE_ASC_PART, FLAT_END_PART), Direction.SOUTH);

	private static final VoxelShaper
			PARTIAL_CASING = VoxelShaper.forHorizontal(box(0, 0, 5, 16, 11, 16), Direction.SOUTH);

	static Map<BlockState, VoxelShape> cache = new HashMap<>();
	static Map<BlockState, VoxelShape> collisionCache = new HashMap<>();

	private static VoxelShape compose(VoxelShape southPart, VoxelShape northPart){
		return Shapes.or(
				Shapes.joinUnoptimized(SOUTH_MASK, southPart, BooleanOp.AND),
				Shapes.joinUnoptimized(NORTH_MASK, northPart, BooleanOp.AND)
		);
	}

	private static VoxelShape makeSlopePart(boolean ascendingInstead) {
		VoxelShape slice = box(1, 0, 15, 15, 11, 16);
		VoxelShape result = Shapes.empty();

		for (int i = 0; i < 16; i++) {

			int yOffset = ascendingInstead ? 10 - i : i - 5;

			result = Shapes.or(
					result,//move slice i voxels "right" and i-5 voxels "down"
					slice.move(0, yOffset / 16f, -i / 16f));
		}

		return result;
	}

	private static VoxelShape makeFlatEnding(){
		return Shapes.or(
			box(1,4,0,15,12,16),
			box(1,3,1,15,13,15)
		);
	}

	private static VoxelShape makeFlatFull(){
		return box(1,3,0,15,13,16);
	}

	private static VoxelShape makeSidewaysEnding(){
		return Shapes.or(
			box(4,1,0,12,15,16),
			box(3,1,1,13,15,15)
		);
	}

	private static VoxelShape makeSidewaysFull(){
		return box(3,1,0,13,15,16);
	}

	public static VoxelShape getShape(BlockState state) {
		if (cache.containsKey(state))
			return cache.get(state);
		VoxelShape create_redShape = Shapes.or(getBeltShape(state), getCasingShape(state));
		cache.put(state, create_redShape);
		return create_redShape;
	}

	public static VoxelShape getCollisionShape(BlockState state) {
		if (collisionCache.containsKey(state))
			return collisionCache.get(state);
		VoxelShape create_redShape = Shapes.joinUnoptimized(AllShapes.BELT_COLLISION_MASK, getShape(state), BooleanOp.AND);
		collisionCache.put(state, create_redShape);
		return create_redShape;
	}

	private static VoxelShape getBeltShape(BlockState state) {
		Direction facing = state.getValue(BeltBlock.HORIZONTAL_FACING);
		Axis axis = facing.getAxis();
		BeltPart part = state.getValue(BeltBlock.PART);
		BeltSlope slope = state.getValue(BeltBlock.SLOPE);

		//vertical
		if (slope == BeltSlope.VERTICAL) {
			if (part == BeltPart.MIDDLE || part == BeltPart.PULLEY)
				return VERTICAL_FULL.get(axis);
			//vertical ending
			return (part == BeltPart.START ? VERTICAL_START : VERTICAL_END).get(facing);
		}

		//flat part
		if (slope == BeltSlope.HORIZONTAL) {
			if (part == BeltPart.MIDDLE || part == BeltPart.PULLEY)
				return FLAT_FULL.get(axis);
			//flat ending
			return (part == BeltPart.START ? FLAT_START : FLAT_END).get(facing);
		}

		//sideways part
		if (slope == BeltSlope.SIDEWAYS) {
			if (part == BeltPart.MIDDLE || part == BeltPart.PULLEY)
				return SIDE_FULL.get(axis);
			//flat ending
			return (part == BeltPart.START ? SIDE_START : SIDE_END).get(facing);
		}

		//slope
		if (part == BeltPart.MIDDLE || part == BeltPart.PULLEY)
			return (slope == BeltSlope.DOWNWARD ? SLOPE_DESC : SLOPE_ASC).get(facing);
		//sloped ending
		if (part == BeltPart.START)
			return (slope == BeltSlope.DOWNWARD ? SLOPE_DESC_START : SLOPE_ASC_START).get(facing);
		if (part == BeltPart.END)
			return (slope == BeltSlope.DOWNWARD ? SLOPE_DESC_END : SLOPE_ASC_END).get(facing);

		//bad state
		return Shapes.empty();
	}

	private static VoxelShape getCasingShape(BlockState state) {
		if (!state.getValue(BeltBlock.CASING))
			return Shapes.empty();

		Direction facing = state.getValue(BeltBlock.HORIZONTAL_FACING);
		BeltPart part = state.getValue(BeltBlock.PART);
		BeltSlope slope = state.getValue(BeltBlock.SLOPE);

		if (slope == BeltSlope.VERTICAL)
			return Shapes.empty();
		if (slope == BeltSlope.SIDEWAYS)
			return Shapes.empty();

		if (slope == BeltSlope.HORIZONTAL) {
			return AllShapes.CASING_11PX.get(Direction.UP);
		}

		if (part == BeltPart.MIDDLE || part == BeltPart.PULLEY)
			return PARTIAL_CASING.get(slope == BeltSlope.UPWARD ? facing : facing.getOpposite());

		if (part == BeltPart.START)
			return slope == BeltSlope.UPWARD ? AllShapes.CASING_11PX.get(Direction.UP) : PARTIAL_CASING.get(facing.getOpposite());
		if (part == BeltPart.END)
			return slope == BeltSlope.DOWNWARD ? AllShapes.CASING_11PX.get(Direction.UP) : PARTIAL_CASING.get(facing);

		//something went wrong
		return Shapes.block();
	}

	private static class VerticalBeltShaper extends VoxelShaper {

		public static VoxelShaper make(VoxelShape southBeltShape){
			return forDirectionsWithRotation(
					rotatedCopy(southBeltShape, new Vec3(-90,0,0)),
					Direction.SOUTH,
					Direction.Plane.HORIZONTAL,//idk, this can probably be improved :S
					direction -> new Vec3(
							direction.getAxisDirection() == Direction.AxisDirection.POSITIVE ? 0 : 180,
							-direction.toYRot(),
							0));
		}
	}

}