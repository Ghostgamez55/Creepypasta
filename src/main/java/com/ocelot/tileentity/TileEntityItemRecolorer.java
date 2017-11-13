package com.ocelot.tileentity;

import cjminecraft.core.energy.compat.forge.CustomForgeEnergyStorage;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityItemRecolorer extends BasicTileEntity implements ICapabilityProvider {

	private ItemStackHandler handler;
	private CustomForgeEnergyStorage storage;

	public TileEntityItemRecolorer() {
		this.handler = new ItemStackHandler(1);
		this.storage = new CustomForgeEnergyStorage(100000, 1000, 0);
	}

	@Override
	public void onServerUpdate() {
		this.storage.extractEnergyInternal(1, false);
	}

	@Override
	public void writeNBTTag(NBTTagCompound nbt) {
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
		this.storage.writeToNBT(nbt);
	}

	@Override
	public void readNBTTag(NBTTagCompound nbt) {
		this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
		this.storage.readFromNBT(nbt);
	}
	
	@Override
	public void writeNBTTagToClient(NBTTagCompound nbt) {
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
	}
	
	@Override
	public void readNBTTagFromClient(NBTTagCompound nbt) {
		this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		if (capability == CapabilityEnergy.ENERGY)
			return (T) this.storage;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		if (capability == CapabilityEnergy.ENERGY)
			return true;
		return super.hasCapability(capability, facing);
	}

	public ItemStack getStack() {
		return handler.getStackInSlot(0);
	}
}