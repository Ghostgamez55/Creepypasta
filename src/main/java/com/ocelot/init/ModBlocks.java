package com.ocelot.init;

import java.util.ArrayList;
import java.util.List;

import com.PintsizedSix40.block.Blockouiji;
import com.ocelot.Reference;
import com.ocelot.blocks.BlockBattery;
import com.ocelot.blocks.BlockCreepypastaWorkbench;
import com.ocelot.blocks.BlockCross;
import com.ocelot.blocks.BlockItemRecolorer;
import com.ocelot.blocks.BlockMailbox;
import com.ocelot.blocks.BlockNitratePowderOre;
import com.ocelot.blocks.BlockReserveGenerator;
import com.ocelot.blocks.BlockSafe;
import com.ocelot.blocks.BlockTv;
import com.ocelot.blocks.item.ItemBlockMeta;
import com.ocelot.handlers.EnumHandler.CrossTypes;
import com.ocelot.proxy.ClientProxy;

import cjminecraft.core.energy.compat.ItemBlockEnergy;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMap;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * @author Ocelot5836
 */
public class ModBlocks {

	public static final List<Block> BLOCKS = new ArrayList<Block>();

	public static Block NITRATE_POWDER_ORE;
	public static Block CROSS;
	public static Block SAFE;
	public static Block CREEPYPASTA_WORKBENCH;
	public static Block SMITHY;
	public static Block ITEM_RECOLORER;
	public static Block BATTERY;
	public static Block RESERVE_GENERATOR;
	public static Block MAILBOX;
	public static Block TV;
	public static Block OUIJI;

	public static void preInit() {
		instantiate();
		register();
	}
	
	private static void instantiate() {
		NITRATE_POWDER_ORE = new BlockNitratePowderOre();
		CROSS = new BlockCross();
		SAFE = new BlockSafe();
		CREEPYPASTA_WORKBENCH = new BlockCreepypastaWorkbench();
		ITEM_RECOLORER = new BlockItemRecolorer();
		BATTERY = new BlockBattery();
		RESERVE_GENERATOR = new BlockReserveGenerator();
		MAILBOX = new BlockMailbox();
		TV = new BlockTv();
		OUIJI = new Blockouiji();
	}

	private static void register() {
		registerBlock(NITRATE_POWDER_ORE);
		registerBlock(CROSS, new ItemBlockMeta(CROSS));
		registerBlock(SAFE);
		registerBlock(CREEPYPASTA_WORKBENCH);
		registerBlock(ITEM_RECOLORER);
		registerBlock(BATTERY);
		registerBlock(RESERVE_GENERATOR);
		registerBlock(MAILBOX);
		registerBlock(TV);
		registerBlock(OUIJI);
	}

	/**
	 * Register's the renders for the normal blocks.
	 */
	public static void registerRenders() {
		for (Block block : BLOCKS) {
			registerRender(block);
		}
		
		for (int i = 0; i < CrossTypes.values().length; i++) {
			registerRender(CROSS, i, "cross_" + CrossTypes.values()[i].getName());
		}
	}

	/**
	 * Creates the stateMappers for ignoring properties
	 */
	@SideOnly(Side.CLIENT)
	public static void createStateMappers() {
		ModelLoader.setCustomStateMapper(SAFE, getDefaultStateMapper(SAFE));
		ModelLoader.setCustomStateMapper(CREEPYPASTA_WORKBENCH, (new StateMap.Builder().ignore(BlockCreepypastaWorkbench.PART).build()));
	}

	private static IStateMapper getDefaultStateMapper(final Block block) {
		return new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation(block.getRegistryName(), "normal");
			}
		};
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
		BLOCKS.add(block);
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
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), 0, new ModelResourceLocation(block.getRegistryName(), "inventory"));
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
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), meta, new ModelResourceLocation(new ResourceLocation(Reference.MOD_ID, fileName), "inventory"));
	}
}