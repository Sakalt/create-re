package com.simibubi.create_re.content.redstone.displayLink.source;

import com.simibubi.create_re.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create_re.content.trains.display.FlapDisplaySection;
import com.simibubi.create_re.foundation.utility.Components;

import net.minecraft.network.chat.Component;

public abstract class NumericSingleLineDisplaySource extends SingleLineDisplaySource {

	protected static final Component ZERO = Components.literal("0");

	@Override
	protected String getFlapDisplayLayoutName(DisplayLinkContext context) {
		return "Number";
	}

	@Override
	protected FlapDisplaySection create_reSectionForValue(DisplayLinkContext context, int size) {
		return new FlapDisplaySection(size * FlapDisplaySection.MONOSPACE, "numeric", false, false);
	}

}
