package com.ocelot.jei;

import com.ocelot.client.gui.GuiReserveGenerator;
import com.ocelot.init.ModBlocks;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.IVanillaRecipeFactory;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

/**
 * @author Ocelot5836
 */
@JEIPlugin
public class CreepypastaJEIPlugin implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		IVanillaRecipeFactory factory = registry.getJeiHelpers().getVanillaRecipeFactory();

		for (Item item : Item.REGISTRY) {
			if (item != null && item instanceof IJeiTooltip) {
				IJeiTooltip tooltip = (IJeiTooltip) item;
				for (int j = 0; j < 16; j++) {
					if (tooltip.getTooltip(j) != null) {
						registry.addIngredientInfo(new ItemStack(item, 1, 0), ItemStack.class, tooltip.getTooltip(j));
					}
				}
			}
		}

		registry.addRecipeClickArea(GuiReserveGenerator.class, 79, 35, 16, 16, VanillaRecipeCategoryUid.FUEL);
		registry.addRecipeCatalyst(new ItemStack(ModBlocks.RESERVE_GENERATOR), VanillaRecipeCategoryUid.FUEL);
	}
}