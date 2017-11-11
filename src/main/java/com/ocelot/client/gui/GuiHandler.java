package com.ocelot.client.gui;

import com.ocelot.container.ContainerCreepypastaWorkbench;
import com.ocelot.container.ContainerItemRecolorer;
import com.ocelot.tileentity.TileEntityItemRecolorer;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int CREEPYPASTA_WORKBENCH_ID = 0;
	public static final int ITEM_RECOLORER_ID = 1;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		if (ID == CREEPYPASTA_WORKBENCH_ID)
			return new ContainerCreepypastaWorkbench(player.inventory, world, pos);
		if (ID == ITEM_RECOLORER_ID) {
			return new ContainerItemRecolorer(player, (TileEntityItemRecolorer) world.getTileEntity(pos));
		}
		return null; 
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);

		if (ID == CREEPYPASTA_WORKBENCH_ID)
			return new GuiCreepypastaWorkbench(player.inventory, world, pos);
		if (ID == ITEM_RECOLORER_ID)
			return new GuiItemRecolorer(player, (TileEntityItemRecolorer) world.getTileEntity(pos));
		return null;
	}
}