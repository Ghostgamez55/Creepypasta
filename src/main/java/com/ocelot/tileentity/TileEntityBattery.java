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
	public int energyDifference = 0;
	private int transfer;

	public TileEntityBattery() {
		this.handler = new ItemStackHandler(2);
		this.storage = new CustomForgeEnergyStorage(1000000, 0);
	}

	@Override
	public void onServerUpdate() {
		if (this.storage.getEnergyStored() > 1000) {
			this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergyAllFaces(this.world, this.pos, 1000, EnergyUnits.FORGE_ENERGY, false), false);
		} else {
			this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergyAllFaces(this.world, this.pos, this.storage.getEnergyStored(), EnergyUnits.FORGE_ENERGY, false), false);
		}
		if (this.storage.getEnergyStored() < this.storage.getMaxEnergyStored() - 999) {
			this.storage.receiveEnergyInternal((int) EnergyUtils.takeEnergyAllFaces(this.world, this.pos, 1000, EnergyUnits.FORGE_ENERGY, false), false);
		} else {
			this.storage.receiveEnergyInternal((int) EnergyUtils.takeEnergyAllFaces(this.world, this.pos, this.storage.getMaxEnergyStored() - this.storage.getEnergyStored(), EnergyUnits.FORGE_ENERGY, false), false);
		}

		if (this.storage.getEnergyStored() > 1000) {
			this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergy(this.handler.getStackInSlot(1), 1000, EnergyUnits.FORGE_ENERGY, false, null), false);
		} else {
			this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergy(this.handler.getStackInSlot(1), this.storage.getEnergyStored(), EnergyUnits.FORGE_ENERGY, false, null), false);
		}
		if (this.storage.getEnergyStored() < this.storage.getMaxEnergyStored() - 999) {
			this.storage.receiveEnergyInternal((int) EnergyUtils.takeEnergy(this.handler.getStackInSlot(0), 1000, EnergyUnits.FORGE_ENERGY, false, null), false);
		} else {
			this.storage.receiveEnergyInternal((int) EnergyUtils.takeEnergy(this.handler.getStackInSlot(0), this.storage.getMaxEnergyStored() - this.storage.getEnergyStored(), EnergyUnits.FORGE_ENERGY, false, null), false);
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
		this.storage.setMaxTransfer(0);
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