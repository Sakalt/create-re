package com.sakalti.create_re.content.kinetics.belt;

import com.sakalti.create_re.foundation.utility.Lang;

import net.minecraft.util.StringRepresentable;

public enum BeltPart implements StringRepresentable {
	START, MIDDLE, END, PULLEY;

	@Override
	public String getSerializedName() {
		return Lang.asId(name());
	}
}