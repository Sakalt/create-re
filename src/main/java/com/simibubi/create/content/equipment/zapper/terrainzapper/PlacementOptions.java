package com.simibubi.create_re.content.equipment.zapper.terrainzapper;

import com.simibubi.create_re.foundation.gui.AllIcons;
import com.simibubi.create_re.foundation.utility.Lang;

public enum PlacementOptions {

	Merged(AllIcons.I_CENTERED),
	Attached(AllIcons.I_ATTACHED),
	Inserted(AllIcons.I_INSERTED);

	public String translationKey;
	public AllIcons icon;

	private PlacementOptions(AllIcons icon) {
		this.translationKey = Lang.asId(name());
		this.icon = icon;
	}

}
