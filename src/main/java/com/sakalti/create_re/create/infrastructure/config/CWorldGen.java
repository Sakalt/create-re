package com.sakalti.create_re.infrastructure.config;

import com.sakalti.create_re.foundation.config.ConfigBase;

public class CWorldGen extends ConfigBase {

	public final ConfigBool disable = b(false, "disableWorldGen", Comments.disable);

	@Override
	public String getName() {
		return "worldgen";
	}

	private static class Comments {
		static String disable = "Prevents all worldgen added by Create from taking effect";
	}

}
