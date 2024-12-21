package com.simibubi.create_re.content.trains.schedule.condition;

import com.simibubi.create_re.Create;
import com.simibubi.create_re.content.trains.entity.Carriage;
import com.simibubi.create_re.content.trains.entity.Train;
import com.simibubi.create_re.foundation.utility.Lang;
import com.simibubi.create_re.foundation.utility.Pair;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IdleCargoCondition extends TimedWaitCondition {
	
	@Override
	public Pair<ItemStack, Component> getSummary() {
		return Pair.of(ItemStack.EMPTY, Lang.translateDirect("schedule.condition.idle_short", formatTime(true)));
	}

	@Override
	public ResourceLocation getId() {
		return Create.asResource("idle");
	}
	
	@Override
	public boolean tickCompletion(Level level, Train train, CompoundTag context) {
		int idleTime = Integer.MAX_VALUE;
		for (Carriage carriage : train.carriages) 
			idleTime = Math.min(idleTime, carriage.storage.getTicksSinceLastExchange());
		context.putInt("Time", idleTime);
		requestDisplayIfNecessary(context, idleTime);
		return idleTime > totalWaitTicks();
	}
	
}