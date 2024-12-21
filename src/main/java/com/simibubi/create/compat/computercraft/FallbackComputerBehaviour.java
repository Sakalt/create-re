package com.simibubi.create_re.compat.computercraft;

import com.simibubi.create_re.foundation.blockEntity.SmartBlockEntity;

public class FallbackComputerBehaviour extends AbstractComputerBehaviour {

	public FallbackComputerBehaviour(SmartBlockEntity te) {
		super(te);
	}

	@Override
	public boolean hasAttachedComputer() {
		return false;
	}
	
}
