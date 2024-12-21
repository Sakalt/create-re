package com.sakalti.create_re.foundation.mixin.accessor;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.DispenserBlock;

@Mixin(DispenserBlock.class)
public interface DispenserBlockAccessor {
	@Invoker("getDispenseMethod")
	DispenseItemBehavior create_re$callGetDispenseMethod(ItemStack stack);
}
