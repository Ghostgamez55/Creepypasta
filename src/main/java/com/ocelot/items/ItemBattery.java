package com.ocelot.items;

import java.util.List;

import com.ocelot.creativetab.CreepypastaCreativeTabs;
import com.ocelot.handlers.EnumHandler.BatteryTypes;

import cjminecraft.core.energy.EnergyUtils;
import cjminecraft.core.energy.compat.forge.CustomForgeEnergyStorage;
import cjminecraft.core.energy.compat.forge.ForgeEnergyCapabilityProvider;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

/**
 * @author Ocelot5836
 */
public class ItemBattery extends ItemEnergy {

	public ItemBattery() {
		super(100000, 1000, 1000);
		setRegistryName("battery_item");
		setUnlocalizedName("battery_item");
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		for (int i = 0; i < BatteryTypes.values().length; i++) {
			if (isInCreativeTab(tab)) {
				int level = (int) (i * 4.5 + 1);
				NBTTagCompound nbt = new NBTTagCompound();
				CustomForgeEnergyStorage storage = new CustomForgeEnergyStorage(getCapacity() * level, getMaxRecieve() * level, getMaxExtract() * level, getCapacity() * level);
				storage.writeToNBT(nbt);
				ItemStack stack = new ItemStack(this, 1, i, nbt);
				stack.setTagCompound(nbt);
				items.add(stack);
				nbt = new NBTTagCompound();
				storage = new CustomForgeEnergyStorage(getCapacity() * level, getMaxRecieve() * level, getMaxExtract() * level, 0);
				storage.writeToNBT(nbt);
				stack = new ItemStack(this, 1, i, nbt);
				stack.setTagCompound(nbt);
				items.add(stack);
			}
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		for (int i = 0; i < BatteryTypes.values().length; i++) {
			if (stack.getMetadata() == i) {
				return this.getUnlocalizedName() + "." + BatteryTypes.values()[i].getName();
			} else {
				continue;
			}
		}
		return this.getUnlocalizedName() + "." + BatteryTypes.BASIC.getName();
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
		CustomForgeEnergyStorage storage = new CustomForgeEnergyStorage(100000, 1000, 1000, 0);
		storage.writeToNBT(nbt);
		ItemStack stack = new ItemStack(nbt);
		stack.setTagCompound(nbt);
		return stack;
	}
}