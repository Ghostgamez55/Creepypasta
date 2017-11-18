package com.ocelot.container.slot;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFuel extends SlotItemHandler {

	public SlotFuel(IItemHandler itemHandler, int index, int xPosition, int yPosition) {
		super(itemHandler, index, xPosition, yPosition);
	}

	/**
	 * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
	 */
	public boolean isItemValid(ItemStack stack) {
		return TileEntityFurnace.isItemFuel(stack) || SlotFurnaceFuel.isBucket(stack);
	}

	public int getItemStackLimit(ItemStack stack) {
		return SlotFurnaceFuel.isBucket(stack) ? 1 : super.getItemStackLimit(stack);
	}
}