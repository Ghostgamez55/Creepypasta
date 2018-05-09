
package com.PintsizedSix40;

import java.util.Date;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;

public class ohm {

	private static HashMap<EntityPlayer, Date> hmap = new HashMap<EntityPlayer, Date>();
	
	public static void put(EntityPlayer p, Date d) {
		hmap.put(p, d);
	}
	public static boolean isSet(EntityPlayer p) {
		return hmap.containsKey(p);
	}
	
	public static Date grab(EntityPlayer p) {
		return hmap.get(p);
	}
	
}
