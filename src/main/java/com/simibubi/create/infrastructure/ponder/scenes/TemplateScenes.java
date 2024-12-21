package com.sakalti.create_re.infrastructure.ponder.scenes;

import com.sakalti.create_re.foundation.ponder.SceneBuilder;
import com.sakalti.create_re.foundation.ponder.SceneBuildingUtil;

import net.minecraft.core.Direction;

public class TemplateScenes {
	
	public static void templateMethod(SceneBuilder scene, SceneBuildingUtil util) {
		scene.title("", "");
		scene.configureBasePlate(0, 0, 5);
		scene.world.showSection(util.select.layer(0), Direction.UP);
		scene.idle(5);
		scene.world.showSection(util.select.layersFrom(1), Direction.DOWN);
	}
	
}
