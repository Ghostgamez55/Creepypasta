package com.ocelot.container;

import com.ocelot.crafting.workbench.CreepypastaWorkbenchManager;
import com.ocelot.init.ModBlocks;
import com.ocelot.inventory.InventoryCraftResult;
import com.ocelot.inventory.InventoryCrafting;
import com.ocelot.inventory.SlotCrafting;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ContainerCreepypastaWorkbench extends Container {

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 8, 8);
	public InventoryCraftResult craftResult = new InventoryCraftResult();
	private World world;
	private BlockPos pos;
	private EntityPlayer player;

	public ContainerCreepypastaWorkbench(InventoryPlayer playerInventory, World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
		this.player = playerInventory.player;

		this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 192, 71));

		for (int y = 0; y < 8; ++y) {
			for (int x = 0; x < 8; ++x) {
				this.addSlotToContainer(new Slot(this.craftMatrix, x + y * 8, 8 + x * 18, 8 + y * 18));
			}
		}

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 32 + x * 18, 166 + y * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(playerInventory, i, 32 + i * 18, 224));
		}

		onCraftMatrixChanged(craftMatrix);
	}

	@Override
	public void onCraftMatrixChanged(IInventory inventoryIn) {
		this.craftResult.setInventorySlotContents(0, CreepypastaWorkbenchManager.getInstance().findMatchingRecipe(this.craftMatrix, world));
	}

	/**
	 * Called when the container is closed.
	 */
	public void onContainerClosed(EntityPlayer playerIn) {
		super.onContainerClosed(playerIn);

		if (!this.world.isRemote) {
			this.clearContainer(playerIn, this.world, this.craftMatrix);
		}
	}

	/**
	 * Determines whether supplied player can use this container
	 */
	public boolean canInteractWith(EntityPlayer player) {
		if (world.getBlockState(pos).getBlock() != ModBlocks.CREEPYPASTA_WORKBENCH) {
			return false;
		} else {
			return player.getDistanceSq(pos) <= 64.0;
		}
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this moves the stack between the player inventory and the other inventory(s).
	 */
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);

		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index == 0) {
				itemstack1.getItem().onCreated(itemstack1, this.world, playerIn);

				if (!this.mergeItemStack(itemstack1, 65, 101, true)) {
					return ItemStack.EMPTY;
				}
				// add 55 to each

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 65 && index < 92) {
				if (!this.mergeItemStack(itemstack1, 92, 101, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 92 && index < 101) {
				if (!this.mergeItemStack(itemstack1, 65, 92, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 65, 101, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			ItemStack itemstack2 = slot.onTake(playerIn, itemstack1);

			if (index == 0) {
				playerIn.dropItem(itemstack2, false);
			}
		}

		return itemstack;
	}

	/**
	 * Called to determine if the current slot is valid for the stack merging (double-click) code. The stack passed in is null for the initial slot that was double-clicked.
	 */
	public boolean canMergeSlot(ItemStack stack, Slot slotIn) {
		return slotIn.inventory != this.craftResult && super.canMergeSlot(stack, slotIn);
	}
}