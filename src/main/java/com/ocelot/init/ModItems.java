package com.ocelot.init;

import java.util.ArrayList;
import java.util.List;

import com.ocelot.Reference;
import com.ocelot.handlers.EnumHandler;
import com.ocelot.items.ItemBattery;
import com.ocelot.items.ItemKey;
import com.ocelot.items.ItemNitratePowder;
import com.ocelot.items.ItemPill;
import com.ocelot.items.ItemSmileDog;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
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

	public static Item PILL;
	public static Item BATTERY;

	/**
	 * Initializes all the items.
	 */
	public static void preInit() {
		SMILE_DOG = new ItemSmileDog();
		NITRATE_POWDER = new ItemNitratePowder();
		KEY = new ItemKey();

		PILL = new ItemPill();
		BATTERY = new ItemBattery();

		ModTools.init();
	}

	/**
	 * Adds armor to the creative tab.
	 */
	protected static void addArmorToTab(ItemArmor helmet, ItemArmor chestplate, ItemArmor leggings, ItemArmor boots, CreativeTabs tab) {
		helmet.setCreativeTab(tab);
		chestplate.setCreativeTab(tab);
		leggings.setCreativeTab(tab);
		boots.setCreativeTab(tab);
	}

	/**
	 * Registers all the items.
	 */
	public static void register() {
		registerItem(SMILE_DOG);
		registerItem(NITRATE_POWDER);
		registerItem(KEY);

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
	}

	/**
	 * Registers the renders for all the items.
	 */
	public static void linkModels() {
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
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
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
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(Reference.MOD_ID + ":" + fileName, "inventory"));
	}
}