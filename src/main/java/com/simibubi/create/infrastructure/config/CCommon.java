package com.sakalti.create_re.infrastructure.config;

import com.sakalti.create_re.foundation.config.ConfigBase;

public class CCommon extends ConfigBase {

	public final CWorldGen worldGen = nested(0, CWorldGen::new, Comments.worldGen);

	@Override
	public String getName() {
		return "common";
	}

	private static class Comments {
		static String worldGen = "Modify Create's impact on your terrain";
	}

}
