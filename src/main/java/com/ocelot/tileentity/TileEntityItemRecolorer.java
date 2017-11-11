package com.ocelot.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityItemRecolorer extends BasicTileEntity implements ICapabilityProvider {

	private ItemStackHandler handler;

	public TileEntityItemRecolorer() {
		handler = new ItemStackHandler(1);
	}

	@Override
	protected void onServerUpdate() {
		
	}
	
	@Override
	public void writeNBTTag(NBTTagCompound nbt) {
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
	}

	@Override
	public void readNBTTag(NBTTagCompound nbt) {
		this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
	}
	
	@Override
	public void writeNBTTagToClient(NBTTagCompound nbt) {
		readNBTTag(nbt);
	}
	
	@Override
	public void readNBTTagFromClient(NBTTagCompound nbt) {
		writeNBTTag(nbt);
	}
	
	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) this.handler;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		return super.hasCapability(capability, facing);
	}
}