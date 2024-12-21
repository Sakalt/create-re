package com.sakalti.create_re.content.contraptions.actors.seat;

import com.sakalti.create_re.content.contraptions.AbstractContraptionEntity;
import com.sakalti.create_re.content.contraptions.Contraption;
import com.sakalti.create_re.content.contraptions.behaviour.MovingInteractionBehaviour;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class SeatInteractionBehaviour extends MovingInteractionBehaviour {

	@Override
	public boolean handlePlayerInteraction(Player player, InteractionHand activeHand, BlockPos localPos,
		AbstractContraptionEntity contraptionEntity) {
		return false;
	}

	@Override
	public void handleEntityCollision(Entity entity, BlockPos localPos, AbstractContraptionEntity contraptionEntity) {
		Contraption contraption = contraptionEntity.getContraption();
		int index = contraption.getSeats()
			.indexOf(localPos);
		if (index == -1)
			return;
		if (!SeatBlock.canBePickedUp(entity))
			return;
		contraptionEntity.addSittingPassenger(entity, index);
	}

}