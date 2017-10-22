package com.ocelot.init;

import com.ocelot.Reference;
import com.ocelot.handlers.ItemEnumHandler;
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

public class ModItems {

	public static Item SMILE_DOG;
	public static Item PILL;
	public static Item NITRATE_POWDER;

	/**
	 * Initializes all the items.
	 */
	public static void init() {
		SMILE_DOG = new ItemSmileDog();
		PILL = new ItemPill();
		NITRATE_POWDER = new ItemNitratePowder();

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
		registerItem(PILL);
		registerItem(NITRATE_POWDER);

		ModTools.register();
	}

	/**
	 * Registers the renders for all the items.
	 */
	public static void registerRenders() {
		registerRender(SMILE_DOG);
		registerRender(NITRATE_POWDER);

		ModTools.registerRenders();
	}

	/**
	 * Registers the renders for all the items.
	 */
	public static void linkModels() {
		for (int i = 0; i < ItemEnumHandler.PillTypes.values().length; i++) {
			registerRender(PILL, i, "pill_" + ItemEnumHandler.PillTypes.values()[i].getName());
		}
	}

	/**
	 * @param item
	 *            The item to be registered.
	 */
	protected static void registerItem(Item item) {
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