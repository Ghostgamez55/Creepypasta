package com.ocelot.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;

public abstract class BasicTileEntity extends TileEntity implements ITickable {

	@Override
	public final void update() {
		if (this.world != null) {
			if (!this.world.isRemote) {
				this.onServerUpdate();
			} else {
				this.onClientUpdate();
			}
		}
	}

	public void onClientUpdate() {
	}

	public void onServerUpdate() {
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		this.writeNBTTag(nbt);
		return super.writeToNBT(nbt);
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		this.readNBTTag(nbt);
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
		NBTTagCompound nbt = pkt.getNbtCompound();
		this.readNBTTagFromClient(nbt);
	}

	@Override
	public SPacketUpdateTileEntity getUpdatePacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		this.writeNBTTagToClient(nbt);
		return new SPacketUpdateTileEntity(this.pos, getBlockMetadata(), nbt);
	}

	@Override
	public NBTTagCompound getUpdateTag() {
		NBTTagCompound nbt = super.getUpdateTag();
		this.writeNBTTagToClient(nbt);
		return nbt;
	}

	public void writeNBTTagToClient(NBTTagCompound nbt) {
	}

	public void readNBTTagFromClient(NBTTagCompound nbt) {
	}

	public void writeNBTTag(NBTTagCompound nbt) {
	}

	public void readNBTTag(NBTTagCompound nbt) {
	}

	protected void updateTileEntity() {
		markDirty();
		IBlockState state = this.world.getBlockState(this.pos);
		this.world.notifyBlockUpdate(this.pos, state, state, 3);
	}
}