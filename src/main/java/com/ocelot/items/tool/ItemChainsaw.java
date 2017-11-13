package com.ocelot.items.tool;

import java.util.List;

import com.ocelot.creativetab.CreepypastaCreativeTabs;

import cjminecraft.core.energy.EnergyUnits;
import cjminecraft.core.energy.EnergyUtils;
import cjminecraft.core.energy.compat.forge.CustomForgeEnergyStorage;
import cjminecraft.core.energy.compat.forge.ForgeEnergyCapabilityProvider;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class ItemChainsaw extends ItemModAxe {

	private final float attackSpeedStored;

	public ItemChainsaw(ToolMaterial material) {
		super(material, "chainsaw");
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
		setMaxDamage(0);
		attackSpeedStored = attackSpeed;
	}

	@Override
	public void onUpdate(ItemStack stack, World world, Entity entity, int itemSlot, boolean isSelected) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;
			for (int i = 0; i < EnumHand.values().length; i++) {
				EnumHand hand = EnumHand.values()[i];
				if (player.getHeldItem(hand).getItem() == this) {
					player.addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("minecraft:slowness"), 1, 1, false, false));

					IEnergyStorage energy = player.getHeldItem(hand).getCapability(CapabilityEnergy.ENERGY, null);
					if (energy.getEnergyStored() == 0) {
						attackSpeed = -4;
					} else {
						attackSpeed = attackSpeedStored;
					}
				}
			}
		}
	}

	@Override
	public boolean onBlockStartBreak(ItemStack itemstack, BlockPos pos, EntityPlayer player) {
		if (!player.world.isRemote) {
			if (player.getHeldItemMainhand().getItem() == this) {
				IEnergyStorage energy = player.getHeldItemMainhand().getCapability(CapabilityEnergy.ENERGY, null);
				energy.extractEnergy((int) EnergyUtils.takeEnergy(player.getHeldItemMainhand(), 2500, EnergyUnits.REDSTONE_FLUX, false, null), false);
			}
		}
		return super.onBlockStartBreak(itemstack, pos, player);
	}

	@Override
	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		IEnergyStorage energy = stack.getCapability(CapabilityEnergy.ENERGY, null);
		if (energy.getEnergyStored() <= 1000) {
			return 0;
		} else {
			return super.getStrVsBlock(stack, state);
		}
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!target.world.isRemote) {
			IEnergyStorage energy = stack.getCapability(CapabilityEnergy.ENERGY, null);
			energy.extractEnergy((int) EnergyUtils.takeEnergy(stack, 2500, EnergyUnits.REDSTONE_FLUX, false, null), false);
		}
		return true;
	}

	@Override
	public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
		if (isInCreativeTab(tab)) {
			NBTTagCompound nbt = new NBTTagCompound();
			CustomForgeEnergyStorage storage = new CustomForgeEnergyStorage(1000000, 10000, 10000, 1000000);
			storage.writeToNBT(nbt);
			ItemStack stack = new ItemStack(this, 1, 0, nbt);
			stack.setTagCompound(nbt);
			items.add(stack);
			nbt = new NBTTagCompound();
			storage = new CustomForgeEnergyStorage(1000000, 10000, 10000, 0);
			storage.writeToNBT(nbt);
			stack = new ItemStack(this, 1, 0, nbt);
			stack.setTagCompound(nbt);
			items.add(stack);
		}
	}

	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
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
		CustomForgeEnergyStorage storage = new CustomForgeEnergyStorage(1000000, 10000, 10000, 0);
		nbt = storage.writeToNBT(nbt);
		ItemStack stack = new ItemStack(nbt);
		stack.setTagCompound(nbt);
		return stack;
	}
}