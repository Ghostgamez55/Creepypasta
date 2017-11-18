package com.ocelot.network.message;

import java.lang.reflect.Field;

import com.ocelot.lib.Utils;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class MessageReturnWorker implements IMessage, IMessageHandler<MessageReturnWorker, IMessage> {

	private boolean messageValid;

	private int cooldown;
	private int maxCooldown;

	private String className;
	private String cooldownFieldName;
	private String maxCooldownFieldName;

	public MessageReturnWorker() {
		this.messageValid = false;
	}

	public MessageReturnWorker(int cooldown, int maxCooldown, String className, String cooldownFieldName, String maxCooldownFieldName) {
		this.cooldown = cooldown;
		this.maxCooldown = maxCooldown;
		this.className = className;
		this.cooldownFieldName = cooldownFieldName;
		this.maxCooldownFieldName = maxCooldownFieldName;
		this.messageValid = true;
	}

	@Override
	public void toBytes(ByteBuf buf) {
		if (!this.messageValid)
			return;

		buf.writeInt(this.cooldown);
		buf.writeInt(this.maxCooldown);
		ByteBufUtils.writeUTF8String(buf, className);
		ByteBufUtils.writeUTF8String(buf, cooldownFieldName);
		ByteBufUtils.writeUTF8String(buf, maxCooldownFieldName);
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		try {
			this.cooldown = buf.readInt();
			this.maxCooldown = buf.readInt();
			this.className = ByteBufUtils.readUTF8String(buf);
			this.cooldownFieldName = ByteBufUtils.readUTF8String(buf);
			this.maxCooldownFieldName = ByteBufUtils.readUTF8String(buf);
		} catch (IndexOutOfBoundsException e) {
			Utils.getLogger().catching(e);
			return;
		}
		this.messageValid = true;
	}

	@Override
	public IMessage onMessage(MessageReturnWorker message, MessageContext ctx) {
		if (!message.messageValid && ctx.side != Side.CLIENT)
			return null;

		Minecraft.getMinecraft().addScheduledTask(() -> processMessage(message));

		return null;
	}

	void processMessage(MessageReturnWorker message) {
		try {
			Class clazz = Class.forName(message.className);
			Field cooldownField = clazz.getDeclaredField(message.cooldownFieldName);
			Field maxCooldownField = clazz.getDeclaredField(message.maxCooldownFieldName);
			cooldownField.setInt(clazz, message.cooldown);
			maxCooldownField.setInt(clazz, message.maxCooldown);
		} catch (Exception e) {
			Utils.getLogger().catching(e);
			return;
		}
	}
}