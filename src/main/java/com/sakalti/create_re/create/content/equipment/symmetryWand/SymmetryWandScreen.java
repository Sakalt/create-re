package com.sakalti.create_re.content.equipment.symmetryWand;

import org.joml.Vector3f;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.sakalti.create_re.AllPackets;
import com.sakalti.create_re.content.equipment.symmetryWand.mirror.CrossPlaneMirror;
import com.sakalti.create_re.content.equipment.symmetryWand.mirror.EmptyMirror;
import com.sakalti.create_re.content.equipment.symmetryWand.mirror.PlaneMirror;
import com.sakalti.create_re.content.equipment.symmetryWand.mirror.SymmetryMirror;
import com.sakalti.create_re.content.equipment.symmetryWand.mirror.TriplePlaneMirror;
import com.sakalti.create_re.foundation.gui.AbstractSimiScreen;
import com.sakalti.create_re.foundation.gui.AllGuiTextures;
import com.sakalti.create_re.foundation.gui.AllIcons;
import com.sakalti.create_re.foundation.gui.element.GuiGameElement;
import com.sakalti.create_re.foundation.gui.widget.IconButton;
import com.sakalti.create_re.foundation.gui.widget.Label;
import com.sakalti.create_re.foundation.gui.widget.ScrollInput;
import com.sakalti.create_re.foundation.gui.widget.SelectionScrollInput;
import com.sakalti.create_re.foundation.utility.Components;
import com.sakalti.create_re.foundation.utility.Lang;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public class SymmetryWandScreen extends AbstractSimiScreen {

	private AllGuiTextures background;

	private ScrollInput areaType;
	private Label labelType;
	private ScrollInput areaAlign;
	private Label labelAlign;
	private IconButton confirmButton;

	private final Component mirrorType = Lang.translateDirect("gui.symmetryWand.mirrorType");
	private final Component orientation = Lang.translateDirect("gui.symmetryWand.orientation");

	private SymmetryMirror currentElement;
	private ItemStack wand;
	private InteractionHand hand;

	public SymmetryWandScreen(ItemStack wand, InteractionHand hand) {
		background = AllGuiTextures.WAND_OF_SYMMETRY;

		currentElement = SymmetryWandItem.getMirror(wand);
		if (currentElement instanceof EmptyMirror) {
			currentElement = new PlaneMirror(Vec3.ZERO);
		}
		this.hand = hand;
		this.wand = wand;
	}

	@Override
	public void init() {
		setWindowSize(background.width, background.height);
		setWindowOffset(-20, 0);
		super.init();

		int x = guiLeft;
		int y = guiTop;

		labelType = new Label(x + 49, y + 28, Components.immutableEmpty()).colored(0xFFFFFFFF)
			.withShadow();
		labelAlign = new Label(x + 49, y + 50, Components.immutableEmpty()).colored(0xFFFFFFFF)
			.withShadow();

		int state =
			currentElement instanceof TriplePlaneMirror ? 2 : currentElement instanceof CrossPlaneMirror ? 1 : 0;
		areaType = new SelectionScrollInput(x + 45, y + 21, 109, 18).forOptions(SymmetryMirror.getMirrors())
			.titled(mirrorType.plainCopy())
			.writingTo(labelType)
			.setState(state);

		areaType.calling(position -> {
			switch (position) {
			case 0:
				currentElement = new PlaneMirror(currentElement.getPosition());
				break;
			case 1:
				currentElement = new CrossPlaneMirror(currentElement.getPosition());
				break;
			case 2:
				currentElement = new TriplePlaneMirror(currentElement.getPosition());
				break;
			default:
				break;
			}
			initAlign(currentElement, x, y);
		});

		initAlign(currentElement, x, y);

		addRenderableWidget(labelAlign);
		addRenderableWidget(areaType);
		addRenderableWidget(labelType);

		confirmButton = new IconButton(x + background.width - 33, y + background.height - 24, AllIcons.I_CONFIRM);
		confirmButton.withCallback(() -> {
			onClose();
		});
		addRenderableWidget(confirmButton);
	}

	private void initAlign(SymmetryMirror element, int x, int y) {
		if (areaAlign != null)
			removeWidget(areaAlign);

		areaAlign = new SelectionScrollInput(x + 45, y + 43, 109, 18).forOptions(element.getAlignToolTips())
			.titled(orientation.plainCopy())
			.writingTo(labelAlign)
			.setState(element.getOrientationIndex())
			.calling(element::setOrientation);

		addRenderableWidget(areaAlign);
	}

	@Override
	protected void renderWindow(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
		int x = guiLeft;
		int y = guiTop;

		background.render(graphics, x, y);
		graphics.drawString(font, wand.getHoverName(), x + 11, y + 4, 0x592424, false);

		renderBlock(graphics, x, y);
		GuiGameElement.of(wand)
				.scale(4)
				.rotate(-70, 20, 20)
				.at(x + 178, y + 448, -150)
				.render(graphics);
	}

	protected void renderBlock(GuiGraphics graphics, int x, int y) {
		PoseStack ms = graphics.pose();
		
		ms.pushPose();
		ms.translate(x + 26, y + 39, 20);
		ms.scale(16, 16, 16);
		ms.mulPose(Axis.of(new Vector3f(.3f, 1f, 0f)).rotationDegrees(-22.5f));
		currentElement.applyModelTransform(ms);
		// RenderSystem.multMatrix(ms.peek().getModel());
		GuiGameElement.of(currentElement.getModel())
			.render(graphics);

		ms.popPose();
	}

	@Override
	public void removed() {
		SymmetryWandItem.configureSettings(wand, currentElement);
		AllPackets.getChannel().sendToServer(new ConfigureSymmetryWandPacket(hand, currentElement));
	}

}