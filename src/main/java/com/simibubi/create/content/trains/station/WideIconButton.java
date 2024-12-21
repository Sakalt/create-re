package com.sakalti.create_re.content.trains.station;

import com.sakalti.create_re.foundation.gui.AllGuiTextures;
import com.sakalti.create_re.foundation.gui.element.ScreenElement;
import com.sakalti.create_re.foundation.gui.widget.IconButton;

import net.minecraft.client.gui.GuiGraphics;

public class WideIconButton extends IconButton {

	public WideIconButton(int x, int y, ScreenElement icon) {
		super(x, y, 26, 18, icon);
	}

	@Override
	protected void drawBg(GuiGraphics graphics, AllGuiTextures button) {
		super.drawBg(graphics, button);
		graphics.blit(button.location, getX() + 9, getY(), button.startX + 1, button.startY, button.width - 1, button.height);
	}

}
