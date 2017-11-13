package com.ocelot.items;

import com.ocelot.creativetab.CreepypastaCreativeTabs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;

public class ItemCigarette extends Item {

	int timer = 0;

	public ItemCigarette() {
		setRegistryName("cigarette");
		setUnlocalizedName("cigarette");
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
		setMaxStackSize(1);
		setMaxDamage(3);
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			if (player.getHeldItemMainhand().getItem() == this) {
				timer++;
				if (timer % 120 == 0) {
					world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, entity.posX, entity.posY + 1, entity.posZ, 0, 0.1, 0, new int[0]);
				}
			}
		}
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack heldItem = player.getHeldItem(hand);
		if (heldItem.getItem() instanceof ItemCigarette) {
			if (!world.isRemote) {
				player.heal(4);
				heldItem.damageItem(1, player);
			}
			// TODO add 5% of sanity back here
			world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, player.posX, player.posY + 1, player.posZ, 0, 0.1, 0, new int[0]);
			player.playSound(SoundEvents.ENTITY_PLAYER_BREATH, 1, 0.5f);
		}
		return super.onItemRightClick(world, player, hand);
	}
}