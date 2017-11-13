package com.ocelot.init;

import com.google.gson.JsonObject;
import com.ocelot.Reference;
import com.ocelot.enums.EnumModCraftingRecipes;

import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.IRecipeFactory;
import net.minecraftforge.common.crafting.JsonContext;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * 
 * @author Ocelot5836
 *
 *         The class that houses all of the mod's crafting/ore dictionary.
 *
 */
public class ModCrafting {

	/**
	 * Initializes the crafting recipes as well as ore dictionary.
	 */
	public static void init() {
		registerOreDictionary();
		registerCraftingRecipes();
		registerFurnaceRecipes();
	}

	/**
	 * Registers the crafting recipes.
	 */
	private static void registerCraftingRecipes() {
		for (int i = 0; i < EnumModCraftingRecipes.values().length; i++) {
			addRecipe(EnumModCraftingRecipes.values()[i].getName(), EnumModCraftingRecipes.values()[i].getPath());
		}

		for (int i = 0; i < EnumDyeColor.values().length; i++) {

		}
	}

	/**
	 * Registers the furnace recipes.
	 */
	private static void registerFurnaceRecipes() {
		GameRegistry.addSmelting(ModBlocks.NITRATE_POWDER_ORE, new ItemStack(ModItems.NITRATE_POWDER, 1, 0), 0.2f);
	}

	/**
	 * Adds a recipe to minecraft's crafting helper.
	 * 
	 * @param fileName
	 *            the name of the json in the files.
	 */
	private static void addRecipe(String fileName) {
		addRecipe(fileName, null);
	}

	/**
	 * Adds a recipe to minecraft's crafting helper.
	 * 
	 * @param fileName
	 *            the name of the json in the files.
	 * @param subPath
	 *            the sub path of the json in the files.
	 */
	private static void addRecipe(String fileName, String subPath) {
		String location = subPath == null ? Reference.MOD_ID + ":" + fileName + ".json" : Reference.MOD_ID + ":" + subPath + "/" + fileName + ".json";

		CraftingHelper.register(new ResourceLocation(location), new IRecipeFactory() {
			@Override
			public IRecipe parse(JsonContext context, JsonObject json) {
				return CraftingHelper.getRecipe(json, context);
			}
		});
	}

	/**
	 * Adds the an IRecipe to the crafting table.
	 * 
	 * @param recipe
	 *            The recipe to add
	 */
	private static void addRecipe(IRecipe recipe) {
		ForgeRegistries.RECIPES.register(recipe);
	}

	/**
	 * Register's the ore dictionary.
	 */
	private static void registerOreDictionary() {

	}
}