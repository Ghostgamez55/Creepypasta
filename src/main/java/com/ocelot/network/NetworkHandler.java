package com.ocelot.network;

import com.ocelot.Reference;
import com.ocelot.network.message.MessageGetWorker;
import com.ocelot.network.message.MessageReturnWorker;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHandler {

	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);

	private static int nextId = 0;

	public static void preInit() {
		registerMessage(MessageGetWorker.class, MessageGetWorker.class, Side.SERVER);
		registerMessage(MessageReturnWorker.class, MessageReturnWorker.class, Side.CLIENT);
	}

	private static void registerMessage(IMessageHandler messageHandler, Class requestMessageType, Side side) {
		INSTANCE.registerMessage(messageHandler, requestMessageType, nextId++, side);
	}

	private static void registerMessage(Class messageHandler, Class requestMessageType, Side side) {
		INSTANCE.registerMessage(messageHandler, requestMessageType, nextId++, side);
	}

	public static void sendToServer(IMessage message) {
		INSTANCE.sendToServer(message);
	}

	public static void sendToClient(IMessage message, EntityPlayer player) {
		INSTANCE.sendTo(message, (EntityPlayerMP) player);
	}

	public static void sendToAllClients(IMessage message) {
		INSTANCE.sendToAll(message);
	}

	public static void getPacketFrom(IMessage message) {
		INSTANCE.getPacketFrom(message);
	}
}