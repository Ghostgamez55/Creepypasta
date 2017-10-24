package com.ocelot;

import com.ocelot.events.ModEventHandler;
import com.ocelot.init.ModBlocks;
import com.ocelot.init.ModCrafting;
import com.ocelot.init.ModEntities;
import com.ocelot.init.ModItems;
import com.ocelot.proxy.CommonProxy;
import com.ocelot.sound.ModSounds;
import com.ocelot.world.OreGen;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

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
		ModSounds.register();
		ModBlocks.preInit();
		ModItems.preInit();
		ModItems.register();
		ModBlocks.register();
		ModEntities.preInit();

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModCrafting.init();
		ModEntities.init();

		GameRegistry.registerWorldGenerator(new OreGen(), 0);
		MinecraftForge.EVENT_BUS.register(new ModEventHandler());

		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}