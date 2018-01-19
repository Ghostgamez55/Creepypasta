package com.ocelot.init;

import java.util.ArrayList;
import java.util.List;

import com.ocelot.Reference;
import com.ocelot.effect.potion.EffectMissingKidney;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class ModPotions {

	public static final ResourceLocation ICONS = new ResourceLocation(Reference.MOD_ID, "textures/gui/potions.png");

	public static Potion MISSING_KIDNEY;

	public static void preInit() {
		instantiate();
		register();
	}

	private static void instantiate() {
		MISSING_KIDNEY = new EffectMissingKidney();
	}

	private static void register() {
		registerPotion(MISSING_KIDNEY, "missing_kidney");
	}

	private static void registerPotion(Potion potion, String potionEffectName) {
		ForgeRegistries.POTIONS.register(potion.setRegistryName(potionEffectName));
	}

	private static void registerPotion(Potion potion, String potionEffectName, PotionType type) {
		ForgeRegistries.POTIONS.register(potion);
		ForgeRegistries.POTION_TYPES.register(type.setRegistryName(potionEffectName));
	}
}