package com.ocelot.blocks;

import com.ocelot.creativetab.CreepypastaCreativeTabs;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockSmithy extends Block {

	public BlockSmithy() {
		super(Material.IRON, MapColor.IRON);
		setUnlocalizedName("smithy");
		setRegistryName("smithy");
		setSoundType(SoundType.METAL);
		setHarvestLevel("pickaxe", 2);
		setHardness(3.0f);
		setResistance(15.0f);
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}
	
	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		return true;
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