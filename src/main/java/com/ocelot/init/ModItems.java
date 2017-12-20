package com.ocelot.init;

import java.util.ArrayList;
import java.util.List;

import com.ocelot.Reference;
import com.ocelot.handlers.EnumHandler;
import com.ocelot.items.ItemBattery;
import com.ocelot.items.ItemCigarette;
import com.ocelot.items.ItemFlashlight;
import com.ocelot.items.ItemKey;
import com.ocelot.items.ItemNitratePowder;
import com.ocelot.items.ItemPill;
import com.ocelot.items.ItemSmileDog;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

/**
 * @author Ocelot5836
 */
public class ModItems {

	public static final List<Item> ITEMS = new ArrayList<Item>();

	public static Item SMILE_DOG;
	public static Item NITRATE_POWDER;
	public static Item KEY;
	public static Item CIGARETTE;
	public static Item FLASHLIGHT;

	public static Item PILL;
	public static Item BATTERY;

	public static void preInit() {
		instantiate();
		register();
	}

	private static void instantiate() {
		SMILE_DOG = new ItemSmileDog();
		NITRATE_POWDER = new ItemNitratePowder();
		KEY = new ItemKey();
		CIGARETTE = new ItemCigarette();
		FLASHLIGHT = new ItemFlashlight();

		PILL = new ItemPill();
		BATTERY = new ItemBattery();
	}

	private static void register() {
		registerItem(SMILE_DOG);
		registerItem(NITRATE_POWDER);
		registerItem(KEY);
		registerItem(CIGARETTE);
		registerItem(FLASHLIGHT);

		registerMetaItem(PILL);
		registerMetaItem(BATTERY);

		ModTools.register();
	}

	/**
	 * Registers the renders for all the items.
	 */
	public static void registerRenders() {
		for (Item item : ITEMS) {
			registerRender(item);
		}

		for (int i = 0; i < EnumHandler.PillTypes.values().length; i++) {
			registerRender(PILL, i, "pill_" + EnumHandler.PillTypes.values()[i].getName());
		}
		for (int i = 0; i < EnumHandler.BatteryTypes.values().length; i++) {
			registerRender(BATTERY, i, "battery_item_" + EnumHandler.BatteryTypes.values()[i].getName());
		}
	}

	/**
	 * @param item
	 *            The item to be registered.
	 */
	protected static void registerItem(Item item) {
		ForgeRegistries.ITEMS.register(item);
		ITEMS.add(item);
	}

	/**
	 * @param item
	 *            The item to be registered.
	 */
	protected static void registerMetaItem(Item item) {
		ForgeRegistries.ITEMS.register(item);
	}

	/**
	 * @param item
	 *            The item to have the render registered for.
	 */
	protected static void registerRender(Item item) {
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	/**
	 * @param item
	 *            The item to have the render registered for.
	 * @param meta
	 *            The item to have the render registered for's meta data.
	 * @param fileName
	 *            The item's name in the files.
	 */
	protected static void registerRender(Item item, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, fileName), "inventory"));
	}
}