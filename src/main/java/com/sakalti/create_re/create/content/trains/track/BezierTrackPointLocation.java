package com.sakalti.create_re.content.trains.track;

import net.minecraft.core.BlockPos;

public record BezierTrackPointLocation(BlockPos curveTarget, int segment) {
}
