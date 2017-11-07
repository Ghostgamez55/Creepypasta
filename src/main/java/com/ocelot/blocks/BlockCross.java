package com.ocelot.blocks;

import com.ocelot.creativetab.CreepypastaCreativeTabs;
import com.ocelot.tileentity.TileEntityCross;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockCross extends Block implements ITileEntityProvider {

	public BlockCross() {
		super(Material.IRON, MapColor.ADOBE);
		setUnlocalizedName("cross");
		setRegistryName("cross");
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 2);
		setHardness(3.0f);
		setResistance(15.0f);
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCross();
	}
}