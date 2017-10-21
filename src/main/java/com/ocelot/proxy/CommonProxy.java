package com.ocelot.proxy;

import javax.annotation.Nullable;

import net.minecraft.client.model.ModelBiped;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/**
 * This class is a template for a basic proxy class. <br>
 * </br>
 * <em><b>Copyright (c) 2017 The Creepypasta Minecraft Mod Team.</b></em>
 * 
 * 
 * @author Ocelot5836
 */
public abstract class CommonProxy {

	/**
	 * This method is called on the pre initialization of the mod.
	 */
	public void preInit(FMLPreInitializationEvent event) {
	}

	/**
	 * This method is called on the initialization of the mod.
	 */
	public void init(FMLInitializationEvent event) {
	}

	/**
	 * This method is called on the post initialization of the mod.
	 */
	public void postInit(FMLPostInitializationEvent event) {
	}

	/**
	 * This method returns the model for the armor using the id.
	 * 
	 * @param id
	 *            The id of the model
	 * @return The biped model for the armor. Null if there is none
	 */
	@Nullable
	public ModelBiped getArmorModel(int id) {
		return null;
	}
}