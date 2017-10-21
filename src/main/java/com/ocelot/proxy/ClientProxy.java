package com.ocelot.proxy;

import net.minecraft.client.model.ModelBiped;

/**
 * <em><b>Copyright (c) 2017 The Creepypasta Minecraft Mod Team.</b></em>
 * 
 * @author Ocelot5836
 */
public class ClientProxy extends CommonProxy {

	@Override
	public ModelBiped getArmorModel(int id) {
		switch (id) {
		}
		return super.getArmorModel(id);
	}
}