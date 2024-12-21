package com.simibubi.create_re.content.redstone.displayLink.source;

import java.util.stream.Stream;

import com.simibubi.create_re.content.redstone.displayLink.DisplayLinkContext;
import com.simibubi.create_re.content.redstone.smartObserver.SmartObserverBlockEntity;
import com.simibubi.create_re.foundation.blockEntity.behaviour.filtering.FilteringBehaviour;
import com.simibubi.create_re.foundation.blockEntity.behaviour.inventory.InvManipulationBehaviour;
import com.simibubi.create_re.foundation.item.CountedItemStackList;
import com.simibubi.create_re.foundation.utility.IntAttached;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.items.IItemHandler;

public class ItemListDisplaySource extends ValueListDisplaySource {

	@Override
	protected Stream<IntAttached<MutableComponent>> provideEntries(DisplayLinkContext context, int maxRows) {
		BlockEntity sourceBE = context.getSourceBlockEntity();
		if (!(sourceBE instanceof SmartObserverBlockEntity cobe))
			return Stream.empty();

		InvManipulationBehaviour invManipulationBehaviour = cobe.getBehaviour(InvManipulationBehaviour.TYPE);
		FilteringBehaviour filteringBehaviour = cobe.getBehaviour(FilteringBehaviour.TYPE);
		IItemHandler handler = invManipulationBehaviour.getInventory();

		if (handler == null)
			return Stream.empty();

		return new CountedItemStackList(handler, filteringBehaviour).getTopNames(maxRows);
	}

	@Override
	protected String getTranslationKey() {
		return "list_items";
	}

	@Override
	protected boolean valueFirst() {
		return true;
	}

}
