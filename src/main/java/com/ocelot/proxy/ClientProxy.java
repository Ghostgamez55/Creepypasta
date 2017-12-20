package com.ocelot.proxy;

import javax.annotation.Nullable;

import com.ocelot.Creepypasta;
import com.ocelot.client.gui.GuiHandler;
import com.ocelot.init.ModBlocks;
import com.ocelot.init.ModEntities;
import com.ocelot.init.ModItems;
import com.ocelot.init.OBJHandler;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

/**
 * <em><b>Copyright (c) 2017 The Creepypasta Minecraft Mod Team.</b></em>
 * 
 * @author Ocelot5836
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
		ModBlocks.createStateMappers();
		ModEntities.registerEntityRenders();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		OBJHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(Creepypasta.instance, new GuiHandler());
	}
}