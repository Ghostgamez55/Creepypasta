package com.ocelot.init;

import com.ocelot.Reference;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.obj.OBJLoader;

public class OBJHandler {

	public static void preInit() {
		OBJLoader.INSTANCE.addDomain(Reference.MOD_ID);
	}

	public static void init() {

	}

	private static void add(Object o) {
		Item item;
		if (o instanceof Item) {
			item = (Item) o;
		} else if(o instanceof Block) {
			item = Item.getItemFromBlock((Block) o);
		} else {
			throw new IllegalArgumentException(String.format("Object %s is NOT an instanceof Item or Block! Because of this it cannot be added to OBJ loading!", o));
		}
	}
}