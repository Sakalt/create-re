package com.sakalti.create_re.foundation.ponder.instruction;

import com.sakalti.create_re.foundation.ponder.PonderScene;
import com.sakalti.create_re.foundation.ponder.PonderScene.SceneTransform;
import com.sakalti.create_re.foundation.utility.animation.LerpedFloat.Chaser;

public class RotateSceneInstruction extends PonderInstruction {

	private float xRot;
	private float yRot;
	private boolean relative;

	public RotateSceneInstruction(float xRot, float yRot, boolean relative) {
		this.xRot = xRot;
		this.yRot = yRot;
		this.relative = relative;
	}

	@Override
	public boolean isComplete() {
		return true;
	}

	@Override
	public void tick(PonderScene scene) {
		SceneTransform transform = scene.getTransform();
		float targetX = relative ? transform.xRotation.getChaseTarget() + xRot : xRot;
		float targetY = relative ? transform.yRotation.getChaseTarget() + yRot : yRot;
		transform.xRotation.chase(targetX, .1f, Chaser.EXP);
		transform.yRotation.chase(targetY, .1f, Chaser.EXP);
	}

}
