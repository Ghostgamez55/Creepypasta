package com.ocelot.crafting;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.ocelot.inventory.InventoryCrafting;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class CreepypastaWorkbenchManager {
	/** The static instance of this class */
	private static final CreepypastaWorkbenchManager INSTANCE = new CreepypastaWorkbenchManager();
	/** A list of all the recipes added */
	private final List<IRecipe> recipes = Lists.<IRecipe>newArrayList();

	/**
	 * Returns the static instance of this class
	 */
	public static CreepypastaWorkbenchManager getInstance() {
		return INSTANCE;
	}

	private CreepypastaWorkbenchManager() {
		this.addRecipe(new ItemStack(Items.DIAMOND, 1), "CCC", "CCC", "CCC", 'C', Blocks.COBBLESTONE);
		this.addShapelessRecipe(new ItemStack(Items.DIAMOND, 1), Blocks.COBBLESTONE);
		Collections.sort(this.recipes, new CreepypastaRecipeSorter(this));
	}

	/**
	 * Adds a shaped recipe to the games recipe list.
	 */
	public CreepypastaWorkbenchShapedRecipes addRecipe(ItemStack stack, Object... recipeComponents) {
		String s = "";
		int i = 0;
		int j = 0;
		int k = 0;

		if (recipeComponents[i] instanceof String[]) {
			String[] astring = (String[]) ((String[]) recipeComponents[i++]);

			for (String s2 : astring) {
				++k;
				j = s2.length();
				s = s + s2;
			}
		} else {
			while (recipeComponents[i] instanceof String) {
				String s1 = (String) recipeComponents[i++];
				++k;
				j = s1.length();
				s = s + s1;
			}
		}

		Map<Character, ItemStack> map;

		for (map = Maps.<Character, ItemStack>newHashMap(); i < recipeComponents.length; i += 2) {
			Character character = (Character) recipeComponents[i];
			ItemStack itemstack = ItemStack.EMPTY;

			if (recipeComponents[i + 1] instanceof Item) {
				itemstack = new ItemStack((Item) recipeComponents[i + 1]);
			} else if (recipeComponents[i + 1] instanceof Block) {
				itemstack = new ItemStack((Block) recipeComponents[i + 1], 1, 32767);
			} else if (recipeComponents[i + 1] instanceof ItemStack) {
				itemstack = (ItemStack) recipeComponents[i + 1];
			}

			map.put(character, itemstack);
		}

		ItemStack[] aitemstack = new ItemStack[j * k];

		for (int l = 0; l < j * k; ++l) {
			char c0 = s.charAt(l);

			if (map.containsKey(Character.valueOf(c0))) {
				aitemstack[l] = ((ItemStack) map.get(Character.valueOf(c0))).copy();
			} else {
				aitemstack[l] = ItemStack.EMPTY;
			}
		}

		CreepypastaWorkbenchShapedRecipes shapedrecipes = new CreepypastaWorkbenchShapedRecipes(j, k, aitemstack, stack);
		this.recipes.add(shapedrecipes);
		return shapedrecipes;
	}

	/**
	 * Adds a shapeless crafting recipe to the the game.
	 */
	public void addShapelessRecipe(ItemStack stack, Object... recipeComponents) {
		List<ItemStack> list = Lists.<ItemStack>newArrayList();

		for (Object object : recipeComponents) {
			if (object instanceof ItemStack) {
				list.add(((ItemStack) object).copy());
			} else if (object instanceof Item) {
				list.add(new ItemStack((Item) object));
			} else {
				if (!(object instanceof Block)) {
					throw new IllegalArgumentException("Invalid shapeless recipe: unknown type " + object.getClass().getName() + "!");
				}

				list.add(new ItemStack((Block) object));
			}
		}

		this.recipes.add(new CreepypastaWorkbenchShapelessRecipes(stack, list));
	}

	/**
	 * Adds an IRecipe to the list of crafting recipes.
	 */
	public void addRecipe(IRecipe recipe) {
		this.recipes.add(recipe);
	}

	/**
	 * Retrieves an ItemStack that has multiple recipes for it.
	 */
	public ItemStack findMatchingRecipe(InventoryCrafting craftMatrix, World worldIn) {
		for (IRecipe irecipe : this.recipes) {
			if (irecipe.matches(craftMatrix, worldIn)) {
				return irecipe.getCraftingResult(craftMatrix);
			}
		}

		return ItemStack.EMPTY;
	}

	public ItemStack[] getRemainingItems(InventoryCrafting craftMatrix, World worldIn) {
		for (IRecipe irecipe : this.recipes) {
			if (irecipe.matches(craftMatrix, worldIn)) {
				return irecipe.getRemainingItems(craftMatrix);
			}
		}

		ItemStack[] aitemstack = new ItemStack[craftMatrix.getSizeInventory()];

		for (int i = 0; i < aitemstack.length; ++i) {
			aitemstack[i] = craftMatrix.getStackInSlot(i);
		}

		return aitemstack;
	}

	/**
	 * returns the List<> of all recipes
	 */
	public List<IRecipe> getRecipeList() {
		return this.recipes;
	}
}