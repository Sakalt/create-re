package com.sakalti.create_re.foundation.ponder.element;

import com.sakalti.create_re.foundation.ponder.PonderScene;
import com.sakalti.create_re.foundation.ponder.ui.PonderUI;

import net.minecraft.client.gui.GuiGraphics;

public abstract class PonderOverlayElement extends PonderElement {

	public void tick(PonderScene scene) {}

	public abstract void render(PonderScene scene, PonderUI screen, GuiGraphics graphics, float partialTicks);

}
