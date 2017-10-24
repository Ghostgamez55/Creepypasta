package com.ocelot.items;

import com.ocelot.creativetab.CreepypastaCreativeTabs;
import com.ocelot.handlers.EnumHandler.PillTypes;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

/**
 * @author Ocelot5836
 */
public class ItemPill extends ItemFood {

	public ItemPill() {
		super(0, 0, false);
		setUnlocalizedName("pill");
		setRegistryName("pill");
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < PillTypes.values().length; i++) {
			if (isInCreativeTab(tab)) {
				items.add(new ItemStack(this, 1, i));
			}
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for (int i = 0; i < PillTypes.values().length; i++) {
			if (stack.getMetadata() == i) {
				return this.getUnlocalizedName() + "." + PillTypes.values()[i].getName();
			} else {
				continue;
			}
		}
		return this.getUnlocalizedName() + "." + PillTypes.ANTI_DEPRESSANTS.getName();
	}
}