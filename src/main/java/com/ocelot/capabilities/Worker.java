package com.ocelot.capabilities;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.INBTSerializable;

public class Worker implements IWork, INBTSerializable<NBTTagCompound> {

	private int cooldown;
	private int maxCooldown;

	private Runnable doWork, workDone;

	public Worker(int maxCooldown, Runnable doWork, Runnable workDone) {
		this.cooldown = 0;
		this.maxCooldown = maxCooldown;
		this.doWork = doWork;
		this.workDone = workDone;
	}

	@Override
	public int getWorkDone() {
		return cooldown;
	}
	
	@Override
	public int getMaxWork() {
		return maxCooldown;
	}

	@Override
	public void doWork() {
		this.cooldown++;
		this.cooldown %= maxCooldown;
		this.doWork.run();
		if (this.cooldown == 0)
			workDone();
	}

	@Override
	public void workDone() {
		this.workDone.run();
	}

	@Override
	public NBTTagCompound serializeNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setInteger("Cooldown", this.cooldown);
		nbt.setInteger("MaxCooldown", this.maxCooldown);
		return nbt;
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		this.cooldown = nbt.getInteger("Cooldown");
		this.maxCooldown = nbt.getInteger("MaxCooldown");
	}

	public void setMaxCooldown(int maxCooldown) {
		this.maxCooldown = maxCooldown;
	}
}