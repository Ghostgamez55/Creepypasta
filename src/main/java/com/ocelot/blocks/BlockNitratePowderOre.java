package com.ocelot.blocks;

import com.ocelot.creativetab.CreepypastaCreativeTabs;
import com.ocelot.init.ModItems;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockNitratePowderOre extends Block {

	public BlockNitratePowderOre() {
		super(Material.ROCK, MapColor.STONE);
		setUnlocalizedName("nitrate_powder_ore");
		setRegistryName("nitrate_powder_ore");
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 1);
		setHardness(3.0f);
		setResistance(15.0f);
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}

	@Override
	public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
		int amount = 5 + RANDOM.nextInt(4) - 2 + (fortune * RANDOM.nextInt(2));
		drops.add(new ItemStack(ModItems.NITRATE_POWDER, amount, 0));
	}

	@Override
	protected ItemStack getSilkTouchDrop(IBlockState state) {
		return new ItemStack(this, 1, 0);
	}

	@Override
	protected boolean canSilkHarvest() {
		return true;
	}
}