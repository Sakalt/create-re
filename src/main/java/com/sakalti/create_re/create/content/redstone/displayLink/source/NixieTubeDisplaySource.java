package com.sakalti.create_re.content.redstone.displayLink.source;

import com.sakalti.create_re.content.redstone.displayLink.DisplayLinkContext;
import com.sakalti.create_re.content.redstone.displayLink.target.DisplayTargetStats;
import com.sakalti.create_re.content.redstone.displayLink.target.NixieTubeDisplayTarget;
import com.sakalti.create_re.content.redstone.nixieTube.NixieTubeBlockEntity;
import com.sakalti.create_re.content.trains.display.FlapDisplaySection;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.entity.BlockEntity;

public class NixieTubeDisplaySource extends SingleLineDisplaySource {

	@Override
	protected String getTranslationKey() {
		return "nixie_tube";
	}

	@Override
	protected MutableComponent provideLine(DisplayLinkContext context, DisplayTargetStats stats) {
		BlockEntity sourceBE = context.getSourceBlockEntity();
		if (!(sourceBE instanceof NixieTubeBlockEntity nbe))
			return EMPTY_LINE;
		
		MutableComponent text = nbe.getFullText();

		try {
			String line = text.getString();
			Integer.valueOf(line);
			context.flapDisplayContext = Boolean.TRUE;
		} catch (NumberFormatException e) {
		}

		return text;
	}

	@Override
	protected boolean allowsLabeling(DisplayLinkContext context) {
		return !(context.blockEntity().activeTarget instanceof NixieTubeDisplayTarget);
	}

	@Override
	protected String getFlapDisplayLayoutName(DisplayLinkContext context) {
		if (isNumeric(context))
			return "Number";
		return super.getFlapDisplayLayoutName(context);
	}

	@Override
	protected FlapDisplaySection create_reSectionForValue(DisplayLinkContext context, int size) {
		if (isNumeric(context))
			return new FlapDisplaySection(size * FlapDisplaySection.MONOSPACE, "numeric", false, false);
		return super.create_reSectionForValue(context, size);
	}

	protected boolean isNumeric(DisplayLinkContext context) {
		return context.flapDisplayContext == Boolean.TRUE;
	}

}