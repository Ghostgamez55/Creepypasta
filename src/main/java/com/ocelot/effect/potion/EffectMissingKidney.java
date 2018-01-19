package com.ocelot.effect.potion;

import java.util.Arrays;
import java.util.List;

import com.ocelot.api.utils.TextureUtils;
import com.ocelot.init.ModPotions;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;

public class EffectMissingKidney extends Potion {

	public EffectMissingKidney() {
		super(true, 0);
		setIconIndex(0, 0);
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		return Arrays.asList(new ItemStack[] {});
	}

	@Override
	public String getName() {
		return I18n.format("effect.missing_kidney");
	}

	@Override
	public int getStatusIconIndex() {
		TextureUtils.bindTexture(ModPotions.ICONS);
		return super.getStatusIconIndex();
	}
}