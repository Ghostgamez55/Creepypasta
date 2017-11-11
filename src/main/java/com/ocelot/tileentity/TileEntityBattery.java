package com.ocelot.tileentity;

import cjminecraft.core.energy.EnergyUnits;
import cjminecraft.core.energy.EnergyUtils;
import cjminecraft.core.energy.compat.forge.CustomForgeEnergyStorage;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityBattery extends BasicTileEntity {

	private ItemStackHandler handler;
	private CustomForgeEnergyStorage storage;

	public TileEntityBattery() {
		this.handler = new ItemStackHandler(2);
		this.storage = new CustomForgeEnergyStorage(1000000, 0);
	}

	@Override
	protected void onServerUpdate() {
		if (this.storage.getEnergyStored() > this.storage.getMaxExtract()) {
			this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergyAllFaces(this.world, this.pos, 1000, EnergyUnits.REDSTONE_FLUX, false), false);
		} else {
			if (this.storage.getEnergyStored() > 0)
				this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergyAllFaces(this.world, this.pos, this.storage.getEnergyStored(), EnergyUnits.REDSTONE_FLUX, false), false);
		}

		if (this.storage.getMaxEnergyStored() > this.storage.getEnergyStored()) {
			this.storage.receiveEnergyInternal((int) EnergyUtils.takeEnergyAllFaces(this.world, this.pos, 1000, EnergyUnits.REDSTONE_FLUX, false), false);
		}

		if (this.storage.getEnergyStored() > this.storage.getMaxExtract()) {
			this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergy(this.handler.getStackInSlot(1), 1000, EnergyUnits.REDSTONE_FLUX, false, null), false);
		} else {
			if (this.storage.getEnergyStored() > 0)
				this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergy(this.handler.getStackInSlot(1), this.storage.getEnergyStored(), EnergyUnits.REDSTONE_FLUX, false, null), false);
		}

		if (this.storage.getMaxEnergyStored() > this.storage.getEnergyStored()) {
			this.storage.receiveEnergyInternal((int) EnergyUtils.takeEnergy(this.handler.getStackInSlot(0), 1000, EnergyUnits.REDSTONE_FLUX, false, null), false);
		}
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
}