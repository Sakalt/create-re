package com.sakalti.create_re.content.trains;

import com.sakalti.create_re.foundation.utility.AnimationTickHolder;
import com.sakalti.create_re.foundation.utility.animation.LerpedFloat;
import com.sakalti.create_re.infrastructure.config.AllConfigs;

public class CameraDistanceModifier {

	private static final LerpedFloat multiplier = LerpedFloat.linear().startWithValue(1);

	public static float getMultiplier() {
		return getMultiplier(AnimationTickHolder.getPartialTicks());
	}

	public static float getMultiplier(float partialTicks) {
		return multiplier.getValue(partialTicks);
	}

	public static void tick() {
		multiplier.tickChaser();
	}

	public static void reset() {
		multiplier.chase(1, 0.1, LerpedFloat.Chaser.EXP);
	}

	public static void zoomOut() {
		zoomOut(AllConfigs.client().mountedZoomMultiplier.getF());
	}

	public static void zoomOut(float targetMultiplier) {
		multiplier.chase(targetMultiplier, 0.075, LerpedFloat.Chaser.EXP);
	}

}
