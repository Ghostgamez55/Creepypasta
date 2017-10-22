package com.ocelot;

import com.ocelot.init.ModBlocks;
import com.ocelot.init.ModEntities;
import com.ocelot.init.ModItems;
import com.ocelot.proxy.CommonProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * This class is the basis for the entire creepypasta mod.<br>
 * </br>
 * <em><b>Copyright (c) 2017 The Creepypasta Minecraft Mod Team.</b></em>
 * 
 * @author Ocelot5836
 */
@Mod(modid = Reference.MOD_ID, name = Reference.NAME, acceptedMinecraftVersions = "[1.12][1.12.1][1.12.2]", useMetadata = true, version = "1.6")
public class Creepypasta {

	@Instance(Reference.MOD_ID)
	public static Creepypasta instance;

	@SidedProxy(clientSide = Reference.CLIENT_SIDE_PROXY, serverSide = Reference.SERVER_SIDE_PROXY)
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ModBlocks.init();
		ModItems.init();
		ModItems.register();
		ModBlocks.register();
		ModEntities.preInit();

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModEntities.init();

		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}