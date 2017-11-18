package com.ocelot.items;

import com.ocelot.creativetab.CreepypastaCreativeTabs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemFlashlight extends Item {

	public ItemFlashlight() {
		setRegistryName("flashlight");
		setUnlocalizedName("flashlight");
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof EntityPlayer && isSelected) {
			EntityPlayer player = (EntityPlayer) entity;
			player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:night_vision"), 0, 0, false, false));
		}
	}
}