package com.ocelot.blocks;

import java.util.List;

import com.ocelot.Creepypasta;
import com.ocelot.client.gui.GuiHandler;
import com.ocelot.creativetab.CreepypastaCreativeTabs;
import com.ocelot.init.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCreepypastaWorkbench extends Block {

	public static final PropertyEnum<Part> PART = PropertyEnum.create("type", Part.class);
	public static final PropertyDirection FACING = BlockHorizontal.FACING;

	public BlockCreepypastaWorkbench() {
		super(Material.WOOD, MapColor.WOOD);
		setUnlocalizedName("creepypasta_workbench");
		setRegistryName("creepypasta_workbench");
		setSoundType(SoundType.WOOD);
		setHarvestLevel("pickaxe", 2);
		setHardness(3.0f);
		setResistance(15.0f);
		setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(PART, Part.BOTTOM));
		setCreativeTab(CreepypastaCreativeTabs.CREEPYPASTA);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
//		if (world.isRemote)
			player.openGui(Creepypasta.instance, GuiHandler.CREEPYPASTA_WORKBENCH_ID, world, (int) hitX, (int) hitY, (int) hitZ);
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

	@Override
	public EnumBlockRenderType getRenderType(IBlockState state) {
		return state.getValue(PART) == Part.TOP ? EnumBlockRenderType.INVISIBLE : EnumBlockRenderType.MODEL;
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(FACING).getHorizontalIndex() + (state.getValue(PART) == Part.BOTTOM ? 4 : 0);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(FACING, EnumFacing.getHorizontal(meta % 4)).withProperty(PART, meta / 4 > 0 ? Part.TOP : Part.BOTTOM);
	}

	@Override
	public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
		return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
	}

	@Override
	public IBlockState withRotation(IBlockState state, Rotation rot) {
		return state.withProperty(FACING, rot.rotate((EnumFacing) state.getValue(FACING)));
	}

	@Override
	public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
		return state.withRotation(mirrorIn.toRotation((EnumFacing) state.getValue(FACING)));
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[] { FACING, PART });
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
		if (state.getValue(PART) == Part.TOP)
			return new AxisAlignedBB(-5.5 * 0.0625, 1, 0, 22 * 0.0625, -1, 1);
		else
			return new AxisAlignedBB(-5.5 * 0.0625, 0, 0, 22 * 0.0625, 2, 1);
	}

	@Override
	public void addCollisionBoxToList(IBlockState state, World world, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, Entity entityIn, boolean p_185477_7_) {
		if (state.getValue(PART) == Part.TOP) {

		} else {
			super.addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getCollisionBoundingBox(world, pos));
		}
	}

	@Override
	public boolean canPlaceBlockAt(World world, BlockPos pos) {
		for (int x = -1; x < 2; x++) {
			for (int z = -1; z < 2; z++) {
				if (!world.isAirBlock(new BlockPos(pos.getX() + x, pos.getY(), pos.getZ() + z))) {
					return false;
				}
			}
		}
		return world.isAirBlock(pos) && world.isAirBlock(pos.up());
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
		if (state.getValue(PART) == Part.BOTTOM) {
			world.setBlockState(pos.up(), ModBlocks.CREEPYPASTA_WORKBENCH.getDefaultState().withProperty(FACING, state.getValue(FACING)).withProperty(PART, Part.TOP));
		}
	}

	@Override
	public void onBlockHarvested(World world, BlockPos pos, IBlockState state, EntityPlayer player) {
		if (state.getValue(PART) == Part.BOTTOM) {
			world.destroyBlock(pos.up(), false);
		} else {
			world.destroyBlock(pos.down(), false);
		}
	}

	public enum Part implements IStringSerializable {
		BOTTOM("bottom", 0), TOP("top", 1);

		private String name;
		private byte id;

		private Part(String name, int id) {
			this.name = name;
			this.id = (byte) id;
		}

		@Override
		public String getName() {
			return name;
		}

		public byte getId() {
			return id;
		}
	}

	// public static class InterfaceCraftingTable implements IInteractionObject {
	// private final World world;
	// private final BlockPos position;
	//
	// public InterfaceCraftingTable(World world, BlockPos pos) {
	// this.world = world;
	// this.position = pos;
	// }
	//
	// /**
	// * Get the name of this object. For players this returns their username
	// */
	// public String getName() {
	// return "creepypasta_workbench";
	// }
	//
	// /**
	// * Returns true if this thing is named
	// */
	// public boolean hasCustomName() {
	// return false;
	// }
	//
	// /**
	// * Get the formatted ChatComponent that will be used for the sender's username in chat
	// */
	// public ITextComponent getDisplayName() {
	// return new TextComponentTranslation(ModBlocks.CREEPYPASTA_WORKBENCH.getUnlocalizedName() + ".name", new Object[0]);
	// }
	//
	// public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn) {
	// return new ContainerCreepypastaWorkbench(playerInventory, this.world, this.position);
	// }
	//
	// public String getGuiID() {
	// return Reference.MOD_ID + ":creepypasta_workbench";
	// }
	// }
}