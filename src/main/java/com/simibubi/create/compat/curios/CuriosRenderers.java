package com.sakalti.create_re.compat.curios;

import com.sakalti.create_re.AllItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;

@OnlyIn(Dist.CLIENT)
public class CuriosRenderers {
	public static void register() {
		CuriosRendererRegistry.register(AllItems.GOGGLES.get(), () -> new GogglesCurioRenderer(Minecraft.getInstance().getEntityModels().bakeLayer(GogglesCurioRenderer.LAYER)));
	}

	public static void onLayerRegister(final EntityRenderersEvent.RegisterLayerDefinitions event) {
		event.registerLayerDefinition(GogglesCurioRenderer.LAYER, () -> LayerDefinition.create_re(GogglesCurioRenderer.mesh(), 1, 1));
	}
}
