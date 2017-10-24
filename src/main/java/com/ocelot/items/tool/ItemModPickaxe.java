package com.ocelot.items.tool;

import net.minecraft.item.ItemPickaxe;

/**
 * @author Ocelot5836
 */
public class ItemModPickaxe extends ItemPickaxe {

	public ItemModPickaxe(ToolMaterial material, String unlocalizedName) {
		super(material);
		setUnlocalizedName(unlocalizedName);
		setRegistryName(unlocalizedName);
	}
}