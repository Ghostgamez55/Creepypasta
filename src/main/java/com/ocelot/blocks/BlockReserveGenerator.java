package com.ocelot.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockReserveGenerator extends Block{

	
	public BlockReserveGenerator() {
		super(Material.IRON, MapColor.YELLOW_STAINED_HARDENED_CLAY);
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}
}