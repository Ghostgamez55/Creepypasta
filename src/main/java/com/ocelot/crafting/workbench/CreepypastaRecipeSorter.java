package com.ocelot.crafting.workbench;

import java.util.Comparator;

public class CreepypastaRecipeSorter implements Comparator {

	private CreepypastaWorkbenchManager crafting;

	public CreepypastaRecipeSorter(CreepypastaWorkbenchManager crafting) {
		this.crafting = crafting;
	}

	public int compareRecipes(ICreepypastaWorkbenchRecipe irecipe1, ICreepypastaWorkbenchRecipe irecipe2) {
		return irecipe1 instanceof CreepypastaWorkbenchShapelessRecipes && irecipe2 instanceof CreepypastaWorkbenchShapedRecipes ? 1 : (irecipe2 instanceof CreepypastaWorkbenchShapelessRecipes && irecipe1 instanceof CreepypastaWorkbenchShapedRecipes ? -1 : (irecipe2.getRecipeSize() < irecipe1.getRecipeSize() ? -1 : (irecipe2.getRecipeSize() > irecipe1.getRecipeSize() ? 1 : 0)));
	}

	@Override
	public int compare(Object o1, Object o2) {
		return this.compareRecipes((ICreepypastaWorkbenchRecipe) o1, (ICreepypastaWorkbenchRecipe) o2);
	}
}