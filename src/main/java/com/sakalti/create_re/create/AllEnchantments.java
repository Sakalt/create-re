package com.sakalti.create_re;

import static com.sakalti.create_re.Create.REGISTRATE;

import com.sakalti.create_re.content.equipment.armor.CapacityEnchantment;
import com.sakalti.create_re.content.equipment.potatoCannon.PotatoRecoveryEnchantment;
import com.tterrag.registrate.util.entry.RegistryEntry;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraft.world.item.enchantment.EnchantmentCategory;

public class AllEnchantments {

	public static final RegistryEntry<PotatoRecoveryEnchantment> POTATO_RECOVERY = REGISTRATE.object("potato_recovery")
		.enchantment(EnchantmentCategory.BOW, PotatoRecoveryEnchantment::new)
		.addSlots(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)
		.lang("Potato Recovery")
		.rarity(Rarity.UNCOMMON)
		.register();
	
	public static final RegistryEntry<CapacityEnchantment> CAPACITY = REGISTRATE.object("capacity")
		.enchantment(EnchantmentCategory.ARMOR_CHEST, CapacityEnchantment::new)
		.addSlots(EquipmentSlot.CHEST)
		.lang("Capacity")
		.rarity(Rarity.COMMON)
		.register();

	public static void register() {}

}
