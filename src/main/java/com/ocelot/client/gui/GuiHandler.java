package com.ocelot.client.gui;

import com.ocelot.container.ContainerBattery;
import com.ocelot.container.ContainerCreepypastaWorkbench;
import com.ocelot.container.ContainerItemRecolorer;
import com.ocelot.container.ContainerReserveGenerator;
import com.ocelot.tileentity.TileEntityBattery;
import com.ocelot.tileentity.TileEntityItemRecolorer;
import com.ocelot.tileentity.TileEntityReserveGenerator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int CREEPYPASTA_WORKBENCH_ID = 0;
	public static final int ITEM_RECOLORER_ID = 1;
	public static final int BATTERY_ID = 2;
	public static final int RESERVE_GENERATOR_ID = 3;

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);

		if (ID == CREEPYPASTA_WORKBENCH_ID)
			return new ContainerCreepypastaWorkbench(player.inventory, world, pos);
		if (ID == ITEM_RECOLORER_ID)
			return new ContainerItemRecolorer(player, (TileEntityItemRecolorer) te);
		if (ID == BATTERY_ID)
			return new ContainerBattery(player, (TileEntityBattery) te);
		if (ID == RESERVE_GENERATOR_ID)
			return new ContainerReserveGenerator(player, (TileEntityReserveGenerator) te);

		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		BlockPos pos = new BlockPos(x, y, z);
		TileEntity te = world.getTileEntity(pos);

		if (ID == CREEPYPASTA_WORKBENCH_ID)
			return new GuiCreepypastaWorkbench(player.inventory, world, pos);
		if (ID == ITEM_RECOLORER_ID)
			return new GuiItemRecolorer(player, (TileEntityItemRecolorer) te);
		if (ID == BATTERY_ID)
			return new GuiBattery(player, (TileEntityBattery) te);
		if (ID == RESERVE_GENERATOR_ID)
			return new GuiReserveGenerator(player, (TileEntityReserveGenerator) te);

		return null;
	}
}