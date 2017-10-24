package com.ocelot.proxy;

import com.ocelot.init.ModBlocks;
import com.ocelot.init.ModEntities;
import com.ocelot.init.ModItems;
import com.ocelot.init.ModTools;
import com.sun.istack.internal.Nullable;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * <em><b>Copyright (c) 2017 The Creepypasta Minecraft Mod Team.</b></em>
 * 
 * @author Ocelot5836
 */
public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ModItems.linkModels();
		ModBlocks.linkModels();
		ModBlocks.createStateMappers();
	}

	@Override
	public void init(FMLInitializationEvent event) {
		ModItems.registerRenders();
		ModBlocks.registerRenders();
		ModEntities.registerEntityRenders();
	}

	@Override
	@Nullable
	public ModelBiped getArmorModel(int id) {
		switch (id) {
		}
		return super.getArmorModel(id);
	}
}