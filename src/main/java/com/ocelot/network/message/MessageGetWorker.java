package com.ocelot.network.message;

import com.ocelot.capabilities.IWork;
import com.ocelot.init.ModCapabilities;
import com.ocelot.lib.Utils;
import com.ocelot.network.NetworkHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageGetWorker implements IMessage, IMessageHandler<MessageGetWorker, IMessage> {

	private boolean messageValid;

	private BlockPos pos;
	private EnumFacing side;

	private String className;
	private String cooldownFieldName;
	private String maxCooldownFieldName;

	public MessageGetWorker() {
		this.messageValid = false;
	}

	public MessageGetWorker(BlockPos pos, EnumFacing side, String className, String cooldownFieldName, String maxCooldownFieldName) {
		this.pos = pos;
		this.side = side;
		this.className = className;
		this.cooldownFieldName = cooldownFieldName;
		this.maxCooldownFieldName = maxCooldownFieldName;
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;

		buf.writeInt(pos.getX());
		buf.writeInt(pos.getY());
		buf.writeInt(pos.getZ());
		ByteBufUtils.writeUTF8String(buf, this.side.getName2());
		ByteBufUtils.writeUTF8String(buf, className);
		ByteBufUtils.writeUTF8String(buf, cooldownFieldName);
		ByteBufUtils.writeUTF8String(buf, maxCooldownFieldName);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.pos = new BlockPos(buf.readInt(), buf.readInt(), buf.readInt());
			this.side = EnumFacing.byName(ByteBufUtils.readUTF8String(buf));
			this.className = ByteBufUtils.readUTF8String(buf);
			this.cooldownFieldName = ByteBufUtils.readUTF8String(buf);
			this.maxCooldownFieldName = ByteBufUtils.readUTF8String(buf);
		} catch (IndexOutOfBoundsException e) {
			Utils.getLogger().catching(e);
		}
		this.messageValid = true;
	}

	@Override
	public IMessage onMessage(MessageGetWorker message, MessageContext ctx) {
		if (!message.messageValid && ctx.side != Side.SERVER)
			return null;

		FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> processMessage(message, ctx));

		return null;
	}

	void processMessage(MessageGetWorker message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		World world = player.world;

		TileEntity te = world.getTileEntity(message.pos);
		if (te == null)
			return;
		if (!te.hasCapability(ModCapabilities.CAPABILITY_WORKER, message.side))
			return;

		IWork worker = te.getCapability(ModCapabilities.CAPABILITY_WORKER, message.side);
		NetworkHandler.sendToClient(new MessageReturnWorker(worker.getWorkDone(), worker.getMaxWork(), message.className, message.cooldownFieldName, message.maxCooldownFieldName), player);
	}
}