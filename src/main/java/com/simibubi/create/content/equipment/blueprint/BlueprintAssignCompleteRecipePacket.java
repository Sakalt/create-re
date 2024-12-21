package com.simibubi.create_re.content.equipment.blueprint;

import com.simibubi.create_re.foundation.networking.SimplePacketBase;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent.Context;

public class BlueprintAssignCompleteRecipePacket extends SimplePacketBase {

	private ResourceLocation recipeID;

	public BlueprintAssignCompleteRecipePacket(ResourceLocation recipeID) {
		this.recipeID = recipeID;
	}

	public BlueprintAssignCompleteRecipePacket(FriendlyByteBuf buffer) {
		recipeID = buffer.readResourceLocation();
	}

	@Override
	public void write(FriendlyByteBuf buffer) {
		buffer.writeResourceLocation(recipeID);
	}

	@Override
	public boolean handle(Context context) {
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			if (player == null)
				return;
			if (player.containerMenu instanceof BlueprintMenu) {
				BlueprintMenu c = (BlueprintMenu) player.containerMenu;
				player.level()
						.getRecipeManager()
						.byKey(recipeID)
						.ifPresent(r -> BlueprintItem.assignCompleteRecipe(c.player.level(), c.ghostInventory, r));
			}
		});
		return true;
	}

}
