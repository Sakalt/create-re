package com.sakalti.create_re.content.redstone.displayLink.source;

import java.util.UUID;

import com.sakalti.create_re.Create;
import com.sakalti.create_re.content.redstone.displayLink.DisplayLinkContext;
import com.sakalti.create_re.content.redstone.displayLink.target.DisplayTargetStats;
import com.sakalti.create_re.content.trains.entity.Train;
import com.sakalti.create_re.content.trains.observer.TrackObserver;
import com.sakalti.create_re.content.trains.observer.TrackObserverBlockEntity;

import net.minecraft.network.chat.MutableComponent;

public class ObservedTrainNameSource extends SingleLineDisplaySource {

	@Override
	protected MutableComponent provideLine(DisplayLinkContext context, DisplayTargetStats stats) {
		if (!(context.getSourceBlockEntity() instanceof TrackObserverBlockEntity observerBE))
			return EMPTY_LINE;
		TrackObserver observer = observerBE.getObserver();
		if (observer == null)
			return EMPTY_LINE;
		UUID currentTrain = observer.getCurrentTrain();
		if (currentTrain == null)
			return EMPTY_LINE;
		Train train = Create.RAILWAYS.trains.get(currentTrain);
		if (train == null)
			return EMPTY_LINE;
		return train.name.copy();
	}
	
	@Override
	public int getPassiveRefreshTicks() {
		return 400;
	}
	
	@Override
	protected String getTranslationKey() {
		return "observed_train_name";
	}

	@Override
	protected boolean allowsLabeling(DisplayLinkContext context) {
		return true;
	}

}
