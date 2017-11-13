package com.ocelot.items.tool;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;

/**
 * @author Ocelot5836
 */
public class ItemModAxe extends ItemTool {

	private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(new Block[] { Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN, Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE });

	protected ItemModAxe(ToolMaterial material, String unlocalizedName, float damage, float speed) {
		super(material, EFFECTIVE_ON);
		this.setUnlocalizedName(unlocalizedName);
		this.setRegistryName(unlocalizedName);
		this.damageVsEntity = damage;
		this.attackSpeed = speed;
	}

	protected ItemModAxe(ToolMaterial material, String unlocalizedName, float damage) {
		this(material, unlocalizedName, damage, -3.1f);
	}

	protected ItemModAxe(ToolMaterial material, String unlocalizedName) {
		this(material, unlocalizedName, material.getDamageVsEntity(), -3.1f);
	}

	public float getStrVsBlock(ItemStack stack, IBlockState state) {
		Material material = state.getMaterial();
		return material != Material.WOOD && material != Material.PLANTS && material != Material.VINE ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
	}

	public void setAttackSpeed(float attackSpeed) {
		this.attackSpeed = attackSpeed;
	}
	
	public void setDamage(float damage) {
		this.damageVsEntity = damage;
	}
}