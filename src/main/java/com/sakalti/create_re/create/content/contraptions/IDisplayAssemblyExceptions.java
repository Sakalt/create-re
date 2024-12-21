package com.sakalti.create_re.content.contraptions;

import java.util.Arrays;
import java.util.List;

import com.sakalti.create_re.foundation.item.TooltipHelper;
import com.sakalti.create_re.foundation.item.TooltipHelper.Palette;
import com.sakalti.create_re.foundation.utility.Components;
import com.sakalti.create_re.foundation.utility.Lang;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public interface IDisplayAssemblyExceptions {

	default boolean addExceptionToTooltip(List<Component> tooltip) {
		AssemblyException e = getLastAssemblyException();
		if (e == null)
			return false;

		if (!tooltip.isEmpty())
			tooltip.add(Components.immutableEmpty());

		Lang.translate("gui.assembly.exception").style(ChatFormatting.GOLD)
			.forGoggles(tooltip);

		String text = e.component.getString();
		Arrays.stream(text.split("\n"))
			.forEach(l -> TooltipHelper.cutStringTextComponent(l, Palette.GRAY_AND_WHITE)
				.forEach(c -> Lang.builder().add(c).forGoggles(tooltip)));

		return true;
	}

	AssemblyException getLastAssemblyException();

}