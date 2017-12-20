package com.ocelot.tileentity;

import com.ocelot.capabilities.Worker;
import com.ocelot.init.ModCapabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class TileEntitySmithy extends BasicTileEntity implements ICapabilityProvider {

	private Worker worker;

	public TileEntitySmithy() {
		worker = new Worker(4 * 20, () -> {
			// do work
		}, () -> {
			// work done
		});
	}

	@Override
	public void onServerUpdate() {
		worker.doWork();
	}

	@Override
	public void writeNBTTag(NBTTagCompound nbt) {
		this.worker.deserializeNBT(nbt.getCompoundTag("Worker"));
	}

	@Override
	public void readNBTTag(NBTTagCompound nbt) {
		nbt.setTag("Worker", this.worker.serializeNBT());
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == ModCapabilities.CAPABILITY_WORKER)
			return (T) worker;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == ModCapabilities.CAPABILITY_WORKER)
			return true;
		return super.hasCapability(capability, facing);
	}
}