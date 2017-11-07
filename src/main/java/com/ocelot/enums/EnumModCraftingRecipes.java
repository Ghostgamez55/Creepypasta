package com.ocelot.enums;

public enum EnumModCraftingRecipes {

	ANTI_DEPRESSANT("anti_depressant"), SAFE("safe");

	private String name;
	private String path;

	private EnumModCraftingRecipes(String name) {
		this.name = name;
		this.path = null;
	}

	private EnumModCraftingRecipes(String name, String path) {
		this.name = name;
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}
}