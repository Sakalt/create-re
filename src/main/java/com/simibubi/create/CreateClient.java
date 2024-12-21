package com.sakalti.create_re;

import com.sakalti.create_re.content.contraptions.glue.SuperGlueSelectionHandler;
import com.sakalti.create_re.content.contraptions.render.ContraptionRenderInfo;
import com.sakalti.create_re.content.contraptions.render.ContraptionRenderInfoManager;
import com.sakalti.create_re.content.decoration.encasing.CasingConnectivity;
import com.sakalti.create_re.content.equipment.bell.SoulPulseEffectHandler;
import com.sakalti.create_re.content.equipment.potatoCannon.PotatoCannonRenderHandler;
import com.sakalti.create_re.content.equipment.zapper.ZapperRenderHandler;
import com.sakalti.create_re.content.kinetics.base.KineticBlockEntityRenderer;
import com.sakalti.create_re.content.kinetics.waterwheel.WaterWheelRenderer;
import com.sakalti.create_re.content.schematics.client.ClientSchematicLoader;
import com.sakalti.create_re.content.schematics.client.SchematicAndQuillHandler;
import com.sakalti.create_re.content.schematics.client.SchematicHandler;
import com.sakalti.create_re.content.trains.GlobalRailwayManager;
import com.sakalti.create_re.foundation.ClientResourceReloadListener;
import com.sakalti.create_re.foundation.blockEntity.behaviour.ValueSettingsClient;
import com.sakalti.create_re.foundation.gui.UIRenderHelper;
import com.sakalti.create_re.foundation.outliner.Outliner;
import com.sakalti.create_re.foundation.ponder.element.WorldSectionElement;
import com.sakalti.create_re.foundation.render.AllInstanceTypes;
import com.sakalti.create_re.foundation.render.CachedBufferer;
import com.sakalti.create_re.foundation.render.StitchedSprite;
import com.sakalti.create_re.foundation.render.SuperByteBufferCache;
import com.sakalti.create_re.foundation.utility.Components;
import com.sakalti.create_re.foundation.utility.ModelSwapper;
import com.sakalti.create_re.foundation.utility.ghost.GhostBlocks;
import com.sakalti.create_re.infrastructure.config.AllConfigs;
import com.sakalti.create_re.infrastructure.ponder.AllPonderTags;
import com.sakalti.create_re.infrastructure.ponder.PonderIndex;

import net.minecraft.ChatFormatting;
import net.minecraft.client.GraphicsStatus;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.ClickEvent;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.HoverEvent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class CreateClient {

	public static final SuperByteBufferCache BUFFER_CACHE = new SuperByteBufferCache();
	public static final Outliner OUTLINER = new Outliner();
	public static final GhostBlocks GHOST_BLOCKS = new GhostBlocks();
	public static final ModelSwapper MODEL_SWAPPER = new ModelSwapper();
	public static final CasingConnectivity CASING_CONNECTIVITY = new CasingConnectivity();

	public static final ClientSchematicLoader SCHEMATIC_SENDER = new ClientSchematicLoader();
	public static final SchematicHandler SCHEMATIC_HANDLER = new SchematicHandler();
	public static final SchematicAndQuillHandler SCHEMATIC_AND_QUILL_HANDLER = new SchematicAndQuillHandler();
	public static final SuperGlueSelectionHandler GLUE_HANDLER = new SuperGlueSelectionHandler();

	public static final ZapperRenderHandler ZAPPER_RENDER_HANDLER = new ZapperRenderHandler();
	public static final PotatoCannonRenderHandler POTATO_CANNON_RENDER_HANDLER = new PotatoCannonRenderHandler();
	public static final SoulPulseEffectHandler SOUL_PULSE_EFFECT_HANDLER = new SoulPulseEffectHandler();
	public static final GlobalRailwayManager RAILWAYS = new GlobalRailwayManager();
	public static final ValueSettingsClient VALUE_SETTINGS_HANDLER = new ValueSettingsClient();

	public static final ClientResourceReloadListener RESOURCE_RELOAD_LISTENER = new ClientResourceReloadListener();

	public static void onCtorClient(IEventBus modEventBus, IEventBus forgeEventBus) {
		modEventBus.addListener(CreateClient::clientInit);
		modEventBus.addListener(AllParticleTypes::registerFactories);

		modEventBus.addListener(StitchedSprite::onTextureStitchPost);

		AllInstanceTypes.init();

		MODEL_SWAPPER.registerListeners(modEventBus);

		ZAPPER_RENDER_HANDLER.registerListeners(forgeEventBus);
		POTATO_CANNON_RENDER_HANDLER.registerListeners(forgeEventBus);
	}

	public static void clientInit(final FMLClientSetupEvent event) {
		BUFFER_CACHE.registerCompartment(CachedBufferer.GENERIC_BLOCK);
		BUFFER_CACHE.registerCompartment(CachedBufferer.PARTIAL);
		BUFFER_CACHE.registerCompartment(CachedBufferer.DIRECTIONAL_PARTIAL);
		BUFFER_CACHE.registerCompartment(KineticBlockEntityRenderer.KINETIC_BLOCK);
		BUFFER_CACHE.registerCompartment(WaterWheelRenderer.WATER_WHEEL);
		BUFFER_CACHE.registerCompartment(ContraptionRenderInfo.CONTRAPTION, 20);
		BUFFER_CACHE.registerCompartment(WorldSectionElement.DOC_WORLD_SECTION, 20);

		AllPartialModels.init();

		AllPonderTags.register();
		PonderIndex.register();

		UIRenderHelper.init();
	}

	public static void invalidateRenderers() {
		BUFFER_CACHE.invalidate();

		SCHEMATIC_HANDLER.updateRenderers();
		ContraptionRenderInfoManager.resetAll();
	}

	public static void checkGraphicsFanciness() {
		Minecraft mc = Minecraft.getInstance();
		if (mc.player == null)
			return;

		if (mc.options.graphicsMode().get() != GraphicsStatus.FABULOUS)
			return;

		if (AllConfigs.client().ignoreFabulousWarning.get())
			return;

		MutableComponent text = ComponentUtils.wrapInSquareBrackets(Components.literal("WARN"))
			.withStyle(ChatFormatting.GOLD)
			.append(Components.literal(
				" Some of Create's visual features will not be available while Fabulous graphics are enabled!"))
			.withStyle(style -> style
				.withClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/create_re dismissFabulousWarning"))
				.withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
					Components.literal("Click here to disable this warning"))));

		mc.player.displayClientMessage(text, false);
	}

}
