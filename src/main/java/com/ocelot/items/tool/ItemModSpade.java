package com.ocelot.items.tool;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSpade;

/**
 * @author Ocelot5836
 */
public class ItemModSpade extends ItemSpade {

	public ItemModSpade(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
	}
}