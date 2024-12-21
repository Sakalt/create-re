package com.simibubi.create_re.content.logistics.tunnel;

import com.simibubi.create_re.foundation.blockEntity.behaviour.ValueBoxTransform;
import com.simibubi.create_re.foundation.utility.VecHelper;

import net.minecraft.world.phys.Vec3;

public class BrassTunnelFilterSlot extends ValueBoxTransform.Sided {

	@Override
	protected Vec3 getSouthLocation() {
		return VecHelper.voxelSpace(8, 13, 15.5f);
	}

}
