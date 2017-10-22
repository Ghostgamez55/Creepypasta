package com.ocelot.init;

import com.ocelot.Reference;
import com.ocelot.blocks.BlockNitratePowderOre;
import com.ocelot.proxy.ClientProxy;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModBlocks {

	public static Block NITRATE_POWDER_ORE;

	/**
	 * Initializes the blocks.
	 */
	public static void init() {
		NITRATE_POWDER_ORE = new BlockNitratePowderOre();
	}

	/**
	 * Register's the blocks.
	 */
	public static void register() {
		registerBlock(NITRATE_POWDER_ORE);
	}

	/**
	 * Register's the renders for the normal blocks.
	 */
	public static void registerRenders() {
		registerRender(NITRATE_POWDER_ORE);
	}

	/**
	 * Link's the meta block's item models to the block's in the {@link ClientProxy}'s preInitialization.
	 */
	public static void linkModels() {

	}

	/**
	 * Creates the stateMappers for ignoring properties
	 */
	@SideOnly(Side.CLIENT)
	public static void createStateMappers() {

	}

	/**
	 * 
	 * @param block
	 *            The block to be registered.
	 */
	private static void registerBlock(Block block) {
		ForgeRegistries.BLOCKS.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(item);
	}

	/**
	 * Only register's the block without an item.
	 * 
	 * @param block
	 *            The block to be registered.
	 */
	private static void registerBlockOnly(Block block) {
		ForgeRegistries.BLOCKS.register(block);
	}

	/**
	 * 
	 * @param block
	 *            The block to be registered.
	 * @param item
	 *            The itemBlock for the block be registered.
	 */
	private static void registerBlock(Block block, ItemBlock item) {
		ForgeRegistries.BLOCKS.register(block);
		item.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(item);
	}

	/**
	 * 
	 * @param block
	 *            The block to have the render registered for.
	 */
	private static void registerRender(Block block) {
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
	}

	/**
	 * 
	 * @param block
	 *            The block to have the render registered for.
	 * @param meta
	 *            The block to have the render registered for's meta data.
	 * @param fileName
	 *            The block's name in the files.
	 */
	private static void registerRender(Block block, int meta, String fileName) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(Reference.MOD_ID + ":" + fileName, "inventory"));
	}
}