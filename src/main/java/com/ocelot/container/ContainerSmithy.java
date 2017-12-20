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

public class ContainerSmithy extends Container {

	public InventoryCrafting craftMatrix = new InventoryCrafting(this, 6, 6);
	public InventoryCraftResult craftResult = new InventoryCraftResult();
	private World world;
	private BlockPos pos;
	private EntityPlayer player;

	public ContainerSmithy(InventoryPlayer playerInventory, World world, BlockPos pos) {
		this.world = world;
		this.pos = pos;
		this.player = playerInventory.player;

		this.addSlotToContainer(new SlotCrafting(playerInventory.player, this.craftMatrix, this.craftResult, 0, 160, 70));

		for (int y = 0; y < 6; ++y) {
			for (int x = 0; x < 6; ++x) {
				this.addSlotToContainer(new Slot(this.craftMatrix, x + y * 8, 12 + x * 18, 17 + y * 18));
			}
		}

		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInventory, x + y * 9 + 9, 16 + x * 18, 139 + y * 18));
			}
		}

		for (int i = 0; i < 9; ++i) {
			this.addSlotToContainer(new Slot(playerInventory, i, 16 + i * 18, 197));
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
		if (world.getBlockState(pos).getBlock() != ModBlocks.SMITHY) {
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

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index >= 37 && index < 64) {
				if (!this.mergeItemStack(itemstack1, 64, 73, false)) {
					return ItemStack.EMPTY;
				}
			} else if (index >= 64 && index < 73) {
				if (!this.mergeItemStack(itemstack1, 37, 64, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 37, 73, false)) {
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