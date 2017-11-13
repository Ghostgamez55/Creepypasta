package com.ocelot.init;

import java.util.ArrayList;
import java.util.List;

import com.ocelot.Reference;
import com.ocelot.items.tool.ItemBat;
import com.ocelot.items.tool.ItemBat.EnumBatType;
import com.ocelot.items.tool.ItemChainsaw;
import com.ocelot.items.tool.ItemModAxe;
import com.ocelot.items.tool.ItemPocketKnife;
import com.ocelot.items.tool.ItemTiccyAxe;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author Ocelot5836
 */
public class ModTools extends ModItems {

	public static final List<ItemTool> TOOLS = new ArrayList<ItemTool>();

	public static final ToolMaterial CHAINSAW_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":chainsaw", 3, 230, 200.0F, 4.0F, 16);

	public static final ToolMaterial BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":bat", 0, 60, 2.0F, -1.0F, 15);
	public static final ToolMaterial SPIKED_BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":spiked_bat", 1, 130, 4.0F, 1.0F, 5);
	public static final ToolMaterial BARBED_WIRE_BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":barbed_wire_bat", 2, 200, 6.0F, 3.0F, 14);

	public static ItemModAxe TICCYS_AXE;
	public static ItemModAxe CHAINSAW;

	public static ItemSword BAT;
	public static ItemSword SPIKED_BAT;
	public static ItemSword BARBED_WIRE_BAT;
	public static ItemSword POCKET_KNIFE;

	public static void init() {
		TICCYS_AXE = new ItemTiccyAxe(ToolMaterial.IRON);
		CHAINSAW = new ItemChainsaw(CHAINSAW_MATERIAL);

		BAT = new ItemBat(EnumBatType.WOODEN);
		SPIKED_BAT = new ItemBat(EnumBatType.SPIKED);
		BARBED_WIRE_BAT = new ItemBat(EnumBatType.BARBED_WIRE);

		POCKET_KNIFE = new ItemPocketKnife(ToolMaterial.STONE);
	}

	public static void register() {
		registerItem(TICCYS_AXE);
		registerItem(CHAINSAW);

		registerItem(BAT);
		registerItem(SPIKED_BAT);
		registerItem(BARBED_WIRE_BAT);
		registerItem(POCKET_KNIFE);
	}
}