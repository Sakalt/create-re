package com.simibubi.create_re.foundation;

import com.simibubi.create_re.CreateClient;
import com.simibubi.create_re.content.kinetics.belt.BeltHelper;
import com.simibubi.create_re.foundation.sound.SoundScapes;
import com.simibubi.create_re.foundation.utility.LangNumberFormat;

import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;

public class ClientResourceReloadListener implements ResourceManagerReloadListener {

	@Override
	public void onResourceManagerReload(ResourceManager resourceManager) {
		CreateClient.invalidateRenderers();
		SoundScapes.invalidateAll();
		LangNumberFormat.numberFormat.update();
		BeltHelper.uprightCache.clear();
	}

}
