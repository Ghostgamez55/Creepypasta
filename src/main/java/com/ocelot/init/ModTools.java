package com.ocelot.init;

import com.ocelot.Reference;
import com.ocelot.items.tool.ItemBat;
import com.ocelot.items.tool.ItemBat.EnumBatType;
import com.ocelot.items.tool.ItemChainsaw;
import com.ocelot.items.tool.ItemModAxe;
import com.ocelot.items.tool.ItemPocketKnife;
import com.ocelot.items.tool.ItemTiccyAxe;

import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

/**
 * @author Ocelot5836
 */
public class ModTools extends ModItems {

	public static final ToolMaterial CHAINSAW_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":chainsaw", 3, 230, 200.0F, 4.0F, 16);

	public static final ToolMaterial BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":bat", 0, 60, 2.0F, -1.0F, 15);
	public static final ToolMaterial SPIKED_BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":spiked_bat", 1, 130, 4.0F, 1.0F, 5);
	public static final ToolMaterial BARBED_WIRE_BAT_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":barbed_wire_bat", 2, 200, 6.0F, 3.0F, 14);
	public static final ToolMaterial POCKET_KNIFE_MATERIAL = EnumHelper.addToolMaterial(Reference.MOD_ID + ":pocket_knife", 1, 131, 4.0F, -0.5F, 5);

	public static final ItemModAxe TICCYS_AXE;
	public static final ItemModAxe CHAINSAW;

	public static final ItemSword BAT;
	public static final ItemSword SPIKED_BAT;
	public static final ItemSword BARBED_WIRE_BAT;
	public static final ItemSword POCKET_KNIFE;

	static {
		TICCYS_AXE = new ItemTiccyAxe(ToolMaterial.IRON);
		CHAINSAW = new ItemChainsaw(CHAINSAW_MATERIAL);

		BAT = new ItemBat(EnumBatType.WOODEN);
		SPIKED_BAT = new ItemBat(EnumBatType.SPIKED);
		BARBED_WIRE_BAT = new ItemBat(EnumBatType.BARBED_WIRE);

		POCKET_KNIFE = new ItemPocketKnife(POCKET_KNIFE_MATERIAL);
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