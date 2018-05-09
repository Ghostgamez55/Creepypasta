package com.ocelot.items.tool;

import com.ocelot.creativetab.CreepypastaCreativeTabs;
import com.ocelot.init.ModTools;

import net.minecraft.item.ItemStack;

public class ItemBat extends ItemModSword {

	public ItemBat(EnumBatType type) {
		super(type.getMaterial(), type.getUnlocalizedName());
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}
	
	
	public int getItemBurnTime(ItemStack itemStack) {
		return 0;
	}

	public enum EnumBatType {
		WOODEN(ModTools.BAT_MATERIAL, "bat_wood"), SPIKED(ModTools.SPIKED_BAT_MATERIAL, "bat_spiked"), BARBED_WIRE(ModTools.BARBED_WIRE_BAT_MATERIAL, "bat_barbed_wire");

		private ToolMaterial material;
		private String unlocalizedName;

		private EnumBatType(ToolMaterial material, String unlocalizedName) {
			this.unlocalizedName = unlocalizedName;
			this.material = material;
		}

		public ToolMaterial getMaterial() {
			return material;
		}

		public String getUnlocalizedName() {
			return unlocalizedName;
		}
	}
}
