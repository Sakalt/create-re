package com.sakalti.create_re.foundation.item;

import org.jetbrains.annotations.Nullable;

import net.minecraft.client.model.HumanoidModel.ArmPose;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public interface CustomArmPoseItem {
	@Nullable
	ArmPose getArmPose(ItemStack stack, AbstractClientPlayer player, InteractionHand hand);
}
