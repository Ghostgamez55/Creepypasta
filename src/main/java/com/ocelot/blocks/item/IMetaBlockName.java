package com.ocelot.blocks.item;

import net.minecraft.item.ItemStack;

/**
 * This allows the ability to have meta blocks. This is required for the items if you want to create multiple blocks from one id.
 * 
 * @author Ocelot5836
 */
public interface IMetaBlockName {

	/**
	 * Returns the name for the specified stack.
	 * 
	 * @param stack
	 *            The stack the block is
	 * @return The name of the itemblock
	 */
	String getSpecialName(ItemStack stack);

}