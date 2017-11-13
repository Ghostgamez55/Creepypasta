package com.ocelot.items.tool;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

public class ItemCustomTool extends ItemTool {

	public ItemCustomTool(ToolMaterial material, Block... blocks) {
		super(material, Sets.newHashSet(blocks));
	}

	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	
	public void setDamage(float damage) {
		this.damageVsEntity = damage;
	}
}