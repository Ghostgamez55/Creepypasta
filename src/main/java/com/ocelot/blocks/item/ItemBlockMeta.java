package com.ocelot.blocks.item;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

/**
 * This class is the root for all meta item blocks.
 * 
 * @author Ocelot5836
 */
public class ItemBlockMeta extends ItemBlock {

	public ItemBlockMeta(Block block) {
		super(block);
		if (!(block instanceof IMetaBlockName)) {
			throw new IllegalArgumentException(String.format("The given block %s is not an instance of IMetaBlockName!", block.getUnlocalizedName()));
		}
		this.setHasSubtypes(true);
		this.setMaxDamage(0);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + ((IMetaBlockName) this.block).getSpecialName(stack);
	}
	
	@Override
	public int getMetadata(int damage) {
		return damage;
	}
}