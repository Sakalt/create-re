package com.sakalti.create_re.foundation.ponder.instruction;

import com.sakalti.create_re.foundation.ponder.PonderScene;

public class MarkAsFinishedInstruction extends PonderInstruction {

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public void tick(PonderScene scene) {
		scene.setFinished(true);
	}
	
	@Override
	public void onScheduled(PonderScene scene) {
		scene.stopCounting();
	}

}
