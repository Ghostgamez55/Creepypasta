package com.ocelot.items;

import java.util.List;

import com.ocelot.creativetab.CreepypastaCreativeTabs;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemKey extends Item {

	public ItemKey() {
		setUnlocalizedName("key");
		setRegistryName("key");
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
		setMaxStackSize(1);
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
		ItemStack stack = player.getHeldItem(hand);
		if (!world.isRemote) {
			if (player.isSneaking()) {
				if (stack.getTagCompound() == null) {
					stack.setTagCompound(new NBTTagCompound());
				}

				NBTTagCompound nbt = stack.getTagCompound();
				if (!nbt.hasKey("nbt")) {
					nbt.setString("lockCode", player.getUniqueID().toString());
					stack.setTagCompound(nbt);
					return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
				}
			}
		}

		return super.onItemRightClick(world, player, hand);
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if (nbt.hasKey("lockCode")) {
				tooltip.add(TextFormatting.DARK_PURPLE + I18n.format("item.key.tooltip"));
				if (flag.isAdvanced()) {
					// tooltip.add(TextFormatting.DARK_GRAY + nbt.getString("lockCode"));
				}
			}
		}
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.hasTagCompound()) {
			NBTTagCompound nbt = stack.getTagCompound();
			if (nbt.hasKey("lockCode")) {
				return true;
			}
		}
		return super.hasEffect(stack);
	}
}