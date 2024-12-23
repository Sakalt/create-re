package com.sakalti.create_re.foundation.mixin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import com.sakalti.create_re.content.contraptions.AbstractContraptionEntity;

import net.minecraft.world.entity.Entity;
import net.minecraftforge.common.capabilities.CapabilityProvider;
import net.minecraftforge.common.extensions.IForgeEntity;

@Mixin(Entity.class)
@Implements(@Interface(iface = IForgeEntity.class, prefix = "iForgeEntity$"))
public abstract class ContraptionDriverInteractMixin extends CapabilityProvider<Entity> {
	private ContraptionDriverInteractMixin(Class<Entity> baseClass) {
		super(baseClass);
	}

	@Shadow
	public abstract Entity getRootVehicle();

	@Nullable
	@Intrinsic
	public boolean iForgeEntity$canRiderInteract() {
		return getRootVehicle() instanceof AbstractContraptionEntity;
	}
}
