package com.ocelot.crafting;

import java.util.Comparator;

public class CreepypastaRecipeSorter implements Comparator {

	private CreepypastaWorkbenchManager crafting;

	public CreepypastaRecipeSorter(CreepypastaWorkbenchManager crafting) {
		this.crafting = crafting;
	}

	public int compareRecipes(IRecipe irecipe1, IRecipe irecipe2) {
		return irecipe1 instanceof CreepypastaWorkbenchShapelessRecipes && irecipe2 instanceof CreepypastaWorkbenchShapedRecipes ? 1 : (irecipe2 instanceof CreepypastaWorkbenchShapelessRecipes && irecipe1 instanceof CreepypastaWorkbenchShapedRecipes ? -1 : (irecipe2.getRecipeSize() < irecipe1.getRecipeSize() ? -1 : (irecipe2.getRecipeSize() > irecipe1.getRecipeSize() ? 1 : 0)));
	}

	@Override
	public int compare(Object o1, Object o2) {
		return this.compareRecipes((IRecipe) o1, (IRecipe) o2);
	}
}