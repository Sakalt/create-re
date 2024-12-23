package com.sakalti.create_re.foundation.ponder.element;

import java.util.function.Function;

import com.sakalti.create_re.foundation.outliner.Outline.OutlineParams;
import com.sakalti.create_re.foundation.outliner.Outliner;
import com.sakalti.create_re.foundation.ponder.PonderScene;

public class OutlinerElement extends AnimatedSceneElement {
	
	private Function<Outliner, OutlineParams> outlinerCall;
	private int overrideColor;

	public OutlinerElement(Function<Outliner, OutlineParams> outlinerCall) {
		this.outlinerCall = outlinerCall;
		this.overrideColor = -1;
	}
	
	@Override
	public void tick(PonderScene scene) {
		super.tick(scene);
		if (fade.getValue() < 1/16f)
			return;
		if (fade.getValue(0) > fade.getValue(1))
			return;
		OutlineParams params = outlinerCall.apply(scene.getOutliner());
		if (overrideColor != -1)
			params.colored(overrideColor);
	}
	
	public void setColor(int overrideColor) {
		this.overrideColor = overrideColor;
	}
	
}
