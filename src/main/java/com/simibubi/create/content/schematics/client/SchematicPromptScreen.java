package com.sakalti.create_re.content.schematics.client;

import org.lwjgl.glfw.GLFW;

import com.sakalti.create_re.AllItems;
import com.sakalti.create_re.CreateClient;
import com.sakalti.create_re.foundation.gui.AbstractSimiScreen;
import com.sakalti.create_re.foundation.gui.AllGuiTextures;
import com.sakalti.create_re.foundation.gui.AllIcons;
import com.sakalti.create_re.foundation.gui.element.GuiGameElement;
import com.sakalti.create_re.foundation.gui.widget.IconButton;
import com.sakalti.create_re.foundation.utility.Components;
import com.sakalti.create_re.foundation.utility.Lang;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.network.chat.Component;

public class SchematicPromptScreen extends AbstractSimiScreen {

	private AllGuiTextures background;

	private final Component convertLabel = Lang.translateDirect("schematicAndQuill.convert");
	private final Component abortLabel = Lang.translateDirect("action.discard");
	private final Component confirmLabel = Lang.translateDirect("action.saveToFile");

	private EditBox nameField;
	private IconButton confirm;
	private IconButton abort;
	private IconButton convert;

	public SchematicPromptScreen() {
		super(Lang.translateDirect("schematicAndQuill.title"));
		background = AllGuiTextures.SCHEMATIC_PROMPT;
	}

	@Override
	public void init() {
		setWindowSize(background.width, background.height);
		super.init();

		int x = guiLeft;
		int y = guiTop;

		nameField = new EditBox(font, x + 49, y + 26, 131, 10, Components.immutableEmpty());
		nameField.setTextColor(-1);
		nameField.setTextColorUneditable(-1);
		nameField.setBordered(false);
		nameField.setMaxLength(35);
		nameField.setFocused(true);
		setFocused(nameField);
		addRenderableWidget(nameField);

		abort = new IconButton(x + 7, y + 53, AllIcons.I_TRASH);
		abort.withCallback(() -> {
			CreateClient.SCHEMATIC_AND_QUILL_HANDLER.discard();
			onClose();
		});
		abort.setToolTip(abortLabel);
		addRenderableWidget(abort);

		confirm = new IconButton(x + 158, y + 53, AllIcons.I_CONFIRM);
		confirm.withCallback(() -> {
			confirm(false);
		});
		confirm.setToolTip(confirmLabel);
		addRenderableWidget(confirm);

		convert = new IconButton(x + 180, y + 53, AllIcons.I_SCHEMATIC);
		convert.withCallback(() -> {
			confirm(true);
		});
		convert.setToolTip(convertLabel);
		addRenderableWidget(convert);
	}

	@Override
	protected void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		int x = guiLeft;
		int y = guiTop;

		background.render(graphics, x, y);
		graphics.drawCenteredString(font, title, x + (background.width - 8) / 2, y + 3, 0xFFFFFF);

		GuiGameElement.of(AllItems.SCHEMATIC.asStack())
				.at(x + 22, y + 23, 0)
				.render(graphics);

		GuiGameElement.of(AllItems.SCHEMATIC_AND_QUILL.asStack())
				.scale(3)
				.at(x + background.width + 6, y + background.height - 40, -200)
				.render(graphics);
	}

	@Override
	public boolean keyPressed(int keyCode, int p_keyPressed_2_, int p_keyPressed_3_) {
		if (keyCode == GLFW.GLFW_KEY_ENTER) {
			confirm(false);
			return true;
		}
		if (keyCode == 256 && this.shouldCloseOnEsc()) {
			this.onClose();
			return true;
		}
		return nameField.keyPressed(keyCode, p_keyPressed_2_, p_keyPressed_3_);
	}

	private void confirm(boolean convertImmediately) {
		CreateClient.SCHEMATIC_AND_QUILL_HANDLER.saveSchematic(nameField.getValue(), convertImmediately);
		onClose();
	}
}
