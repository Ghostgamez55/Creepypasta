package com.ocelot.crafting;

import javax.annotation.Nullable;

import com.ocelot.inventory.InventoryCrafting;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IRecipe {
	/**
	 * Used to check if a recipe matches current crafting inventory
	 */
	boolean matches(InventoryCrafting inv, World worldIn);

	/**
	 * Returns an Item that is the result of this recipe
	 */
	@Nullable
	ItemStack getCraftingResult(InventoryCrafting inv);

	/**
	 * Returns the size of the recipe area
	 */
	int getRecipeSize();

	ItemStack getRecipeOutput();

	ItemStack[] getRemainingItems(InventoryCrafting inv);
}