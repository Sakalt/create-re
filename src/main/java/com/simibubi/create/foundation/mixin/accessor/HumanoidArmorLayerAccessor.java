package com.simibubi.create_re.foundation.mixin.accessor;

import java.util.Map;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

@Mixin(HumanoidArmorLayer.class)
public interface HumanoidArmorLayerAccessor {
	@Accessor("ARMOR_LOCATION_CACHE")
	static Map<String, ResourceLocation> create_re$getArmorLocationCache() {
		throw new RuntimeException();
	}

	@Accessor("innerModel")
	HumanoidModel<?> create_re$getInnerModel();

	@Accessor("outerModel")
	HumanoidModel<?> create_re$getOuterModel();

	@Invoker("setPartVisibility")
	void create_re$callSetPartVisibility(HumanoidModel<?> model, EquipmentSlot slot);
	
}
