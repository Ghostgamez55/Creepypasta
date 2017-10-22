package com.ocelot.creativetab;

import com.ocelot.init.ModItems;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class TabCreepypasta extends CreativeTabs {

	public TabCreepypasta() {
		super("creepyPasta");
	}

	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ModItems.SMILE_DOG, 1, 0);
	}
}