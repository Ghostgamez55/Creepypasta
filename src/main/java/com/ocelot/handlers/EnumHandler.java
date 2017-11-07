package com.ocelot.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * @author Ocelot5836
 */
public class EnumHandler {

	public enum PillTypes implements IStringSerializable {
		ANTI_DEPRESSANTS("anti_depressants", 0);

		private String name;
		private byte id;

		private PillTypes(String name, int id) {
			this.name = name;
			this.id = (byte) id;
		}
		
		@Override
		public String getName() {
			return name;
		}
		
		public int getId() {
			return id;
		}
	}
}