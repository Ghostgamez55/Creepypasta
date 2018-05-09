package com.PintsizedSix40;

import com.PintsizedSix40.block.Blockouiji;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.IStateMapper;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class main {
	
	private static IStateMapper getDefaultStateMapper(final String block) {
		return new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation(IBlockState state) {
				return new ModelResourceLocation("spooks:" + block + "/" + block, "normal");
			}
		};
	}

	private static void registerBlock(Block block) {
		ForgeRegistries.BLOCKS.register(block);
		ItemBlock item = new ItemBlock(block);
		item.setRegistryName(block.getRegistryName());
		ForgeRegistries.ITEMS.register(item);
		
	}
	
	public void init() {
		
	}
	
public static void preinit() {
	
	/*Block ou = new Blockouiji();
	registerBlock(ou);
	ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(ou), 0, new ModelResourceLocation("spooks:ouiji/ouiji", "inventory"));
	ModelLoader.setCustomStateMapper(ou, getDefaultStateMapper("ouiji"));
	*/}

public void postinit() {
	
}
}
