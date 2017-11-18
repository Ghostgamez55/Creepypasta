package com.ocelot.tileentity;

import com.ocelot.capabilities.Worker;
import com.ocelot.init.ModCapabilities;

import cjminecraft.core.energy.EnergyUnits;
import cjminecraft.core.energy.EnergyUtils;
import cjminecraft.core.energy.compat.forge.CustomForgeEnergyStorage;
import mezz.jei.plugins.vanilla.ingredients.FluidStackHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

public class TileEntityReserveGenerator extends BasicTileEntity implements ICapabilityProvider {

	private ItemStackHandler handler;
	private Worker worker;
	private CustomForgeEnergyStorage storage;

	public static final int RF_PER_TICK = 100;

	public TileEntityReserveGenerator() {
		this.handler = new ItemStackHandler(2);
		this.storage = new CustomForgeEnergyStorage(100000, 0, 1000);
		this.worker = new Worker(1, () -> {
			if (this.worker.getMaxWork() != 1 && this.storage.getMaxEnergyStored() - this.storage.getEnergyStored() >= 0) {
				if (this.storage.getMaxEnergyStored() - this.storage.getEnergyStored() >= 0) {
					this.storage.receiveEnergyInternal(RF_PER_TICK, false);
				}
			}
		}, () -> {
			this.worker.setMaxCooldown(1);
			if (TileEntityFurnace.isItemFuel(this.handler.getStackInSlot(0)) && this.handler.getStackInSlot(0).getItem() != Items.BUCKET) {
				ItemStack fuel = this.handler.extractItem(0, 1, false);
				if (!fuel.isEmpty()) {
					if (this.storage.getMaxEnergyStored() - this.storage.getEnergyStored() >= 0) {
						this.worker.setMaxCooldown(TileEntityFurnace.getItemBurnTime(fuel));
					}
				}

				if (fuel.getItem() == Items.LAVA_BUCKET) {
					this.handler.setStackInSlot(0, new ItemStack(Items.BUCKET));
				}
			}
		});
	}

	@Override
	public void onServerUpdate() {
		if (this.storage.getEnergyStored() > 1000) {
			this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergyAllFaces(this.world, this.pos, 1000, EnergyUnits.FORGE_ENERGY, false), false);
		} else {
			this.storage.extractEnergyInternal((int) EnergyUtils.giveEnergyAllFaces(this.world, this.pos, this.storage.getEnergyStored(), EnergyUnits.FORGE_ENERGY, false), false);
		}

		this.worker.doWork();
		updateTileEntity();
	}

	@Override
	public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
		return oldState.getBlock() != newState.getBlock();
	}

	@Override
	public void readNBTTag(NBTTagCompound nbt) {
		this.worker.deserializeNBT(nbt.getCompoundTag("Worker"));
		this.handler.deserializeNBT(nbt.getCompoundTag("ItemStackHandler"));
		this.storage.readFromNBT(nbt);
	}

	@Override
	public void writeNBTTag(NBTTagCompound nbt) {
		nbt.setTag("Worker", this.worker.serializeNBT());
		nbt.setTag("ItemStackHandler", this.handler.serializeNBT());
		this.storage.writeToNBT(nbt);
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return (T) handler;
		if (capability == ModCapabilities.CAPABILITY_WORKER)
			return (T) worker;
		if (capability == CapabilityEnergy.ENERGY)
			return (T) storage;
		return super.getCapability(capability, facing);
	}

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		if (capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY)
			return true;
		if (capability == ModCapabilities.CAPABILITY_WORKER)
			return true;
		if (capability == CapabilityEnergy.ENERGY)
			return true;
		return super.hasCapability(capability, facing);
	}
}