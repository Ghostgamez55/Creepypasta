package com.ocelot.init;

import java.util.ArrayList;
import java.util.List;

import com.ocelot.Reference;
import com.ocelot.items.tool.ItemBat;
import com.ocelot.items.tool.ItemBat.EnumBatType;
import com.ocelot.items.tool.ItemModAxe;
import com.ocelot.items.tool.ItemModSword;
import com.ocelot.items.tool.ItemTiccyAxe;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author Ocelot5836
 */
public class ModTools extends ModItems {

	public static final List<ItemTool> TOOLS = new ArrayList<ItemTool>();
	
	public static final ToolMaterial BLOODY_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":bloody", 2, 600, 5.0F, 2.0F, 12);
	
	public static final ToolMaterial BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":bat", 0, 60, 2.0F, -1.0F, 15);
	public static final ToolMaterial SPIKED_BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":spiked_bat", 1, 130, 4.0F, 1.0F, 5);
	public static final ToolMaterial BARBED_WIRE_BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":barbed_wire_bat", 2, 200, 6.0F, 3.0F, 14);

	public static ItemModAxe TICCYS_AXE;
	
	public static ItemModSword BAT;
	public static ItemModSword SPIKED_BAT;
	public static ItemModSword BARBED_WIRE_BAT;

	public static void init() {
		TICCYS_AXE = new ItemTiccyAxe(BLOODY_MATERIAL);
		
		BAT = new ItemBat(EnumBatType.WOODEN);
		SPIKED_BAT = new ItemBat(EnumBatType.SPIKED);
		BARBED_WIRE_BAT = new ItemBat(EnumBatType.BARBED_WIRE);
	}

	public static void register() {
		registerItem(TICCYS_AXE);
		
		registerItem(BAT);
		registerItem(SPIKED_BAT);
		registerItem(BARBED_WIRE_BAT);
	}
}