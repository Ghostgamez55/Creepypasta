package com.ocelot.init;

import com.ocelot.Reference;
import com.ocelot.items.ItemTiccyAxe;
import com.ocelot.items.tool.ItemModAxe;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ModTools extends ModItems {

	public static final ToolMaterial BLOODY_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":bloody", 2, 600, 5.0F, 2.0F, 12);

	public static ItemModAxe TICCYS_AXE;

	public static void init() {
		TICCYS_AXE = new ItemTiccyAxe(BLOODY_MATERIAL);
	}

	public static void register() {
		registerItem(TICCYS_AXE);
	}

	public static void registerRenders() {
		registerRender(TICCYS_AXE);
	}
}