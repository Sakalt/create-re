package com.simibubi.create_re.content.trains.schedule.condition;

import com.simibubi.create_re.Create;
import com.simibubi.create_re.content.trains.entity.Train;
import com.simibubi.create_re.foundation.utility.Lang;
import com.simibubi.create_re.foundation.utility.Pair;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class ScheduledDelay extends TimedWaitCondition {

	@Override
	public Pair<ItemStack, Component> getSummary() {
		return Pair.of(ItemStack.EMPTY, Lang.translateDirect("schedule.condition.delay_short", formatTime(true)));
	}

	@Override
	public boolean tickCompletion(Level level, Train train, CompoundTag context) {
		int time = context.getInt("Time");
		if (time >= totalWaitTicks())
			return true;
		
		context.putInt("Time", time + 1);
		requestDisplayIfNecessary(context, time);
		return false;
	}

	@Override
	public ResourceLocation getId() {
		return Create.asResource("delay");
	}

}