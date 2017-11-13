package com.ocelot.blocks;

import com.ocelot.blocks.item.IMetaBlockName;
import com.ocelot.creativetab.CreepypastaCreativeTabs;
import com.ocelot.handlers.EnumHandler.CrossTypes;
import com.ocelot.tileentity.TileEntityCross;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

public class BlockCross extends Block implements ITileEntityProvider, IMetaBlockName {

	public static final PropertyInteger TIER = PropertyInteger.create("tier", 0, 2);

	public BlockCross() {
		super(Material.IRON, MapColor.ADOBE);
		setUnlocalizedName("cross");
		setRegistryName("cross");
		setSoundType(SoundType.STONE);
		setHarvestLevel("pickaxe", 2);
		setHardness(3.0f);
		setResistance(15.0f);
		setDefaultState(getDefaultState().withProperty(TIER, 0));
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}

	@Override
	public void getSubBlocks(CreativeTabs item, NonNullList<ItemStack> items) {
		for(int i = 0; i < TIER.getAllowedValues().size(); i++) {
			items.add(new ItemStack(this, 1, i));
		}
	}
	
	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TIER);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TIER, meta);
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { TIER });
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCross();
	}

	@Override
	public String getSpecialName(ItemStack stack) {
		return CrossTypes.values()[stack.getMetadata()].getName();
	}
}