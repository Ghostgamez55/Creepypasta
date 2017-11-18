package com.ocelot.items;

import java.util.List;

import cjminecraft.core.energy.EnergyUtils;
import cjminecraft.core.energy.compat.forge.CustomForgeEnergyStorage;
import cjminecraft.core.energy.compat.forge.ForgeEnergyCapabilityProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * This class creates the energy capability so items can have energy without having to add all the capability methods.
 * 
 * @author Ocelot5836
 */
public class ItemEnergy extends Item {

	private int capacity;
	private int maxReceive;
	private int maxExtract;

	public ItemEnergy(int capacity, int maxReceive, int maxExtract) {
		this.capacity = capacity;
		this.maxReceive = maxReceive;
		this.maxExtract = maxExtract;
	}

	/**
	 * Adds an energy item to the creative tab.
	 * 
	 * @param items
	 *            The items array
	 * @param stack
	 *            The stack to add
	 * @param capacity
	 *            The capacity of the energy item
	 * @param maxReceive
	 *            The maximum amount of energy the item can receive
	 * @param maxExtract
	 *            The maximum amount of energy that can extract
	 * @param energy
	 *            The amount of energy inside the item
	 */
	public void addEnergyItemToTab(NonNullList<ItemStack> items, ItemStack stack, int capacity, int maxReceive, int maxExtract, int energy) {
		NBTTagCompound nbt = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
		CustomForgeEnergyStorage storage = new CustomForgeEnergyStorage(capacity, maxReceive, maxExtract, energy);
		storage.writeToNBT(nbt);
		stack.setTagCompound(nbt);
		items.add(stack);
	}

	/**
	 * Adds an energy item to the creative tab.
	 * 
	 * @param items
	 *            The items array
	 * @param stack
	 *            The stack to add
	 */
	public void addEnergyItemToTab(NonNullList<ItemStack> items, ItemStack stack) {
		addEnergyItemToTab(items, stack, capacity, maxReceive, maxExtract, 0);
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		if (EnergyUtils.hasSupport(stack, null)) {
			EnergyUtils.addEnergyInformation(stack, tooltip);
		}
	}

	@Override
	public int getRGBDurabilityForDisplay(ItemStack stack) {
		return EnergyUtils.getEnergyRGBDurabilityForDisplay(stack);
	}

	@Override
	public double getDurabilityForDisplay(ItemStack stack) {
		return EnergyUtils.getEnergyDurabilityForDisplay(stack);
	}

	@Override
	public boolean showDurabilityBar(ItemStack stack) {
		return EnergyUtils.hasSupport(stack, null);
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, NBTTagCompound nbt) {
		if (nbt != null && nbt.hasKey("Energy") && nbt.hasKey("Capacity") && nbt.hasKey("MaxReceive") && nbt.hasKey("MaxExtract")) {
			stack.setTagCompound(nbt);
			return new ForgeEnergyCapabilityProvider(stack, nbt);
		}
		return new ForgeEnergyCapabilityProvider(stack, 0, 0, 0, 0);
	}

	@Override
	public ItemStack getDefaultInstance() {
		NBTTagCompound nbt = new ItemStack(this).serializeNBT();
		CustomForgeEnergyStorage storage = new CustomForgeEnergyStorage(capacity, maxReceive, maxExtract, 0);
		storage.writeToNBT(nbt);
		ItemStack stack = new ItemStack(nbt);
		stack.setTagCompound(nbt);
		return stack;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getMaxRecieve() {
		return maxReceive;
	}

	public int getMaxExtract() {
		return maxExtract;
	}
}