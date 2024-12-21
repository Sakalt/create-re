package com.sakalti.create_re.foundation.gui.menu;

import com.sakalti.create_re.AllPackets;

public interface IClearableMenu {

	default void sendClearPacket() {
		AllPackets.getChannel().sendToServer(new ClearMenuPacket());
	}

	public void clearContents();

}
