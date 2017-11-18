package com.ocelot.client.gui;

import com.ocelot.Reference;
import com.ocelot.container.ContainerCreepypastaWorkbench;

import cjminecraft.core.client.gui.GuiBase;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiCreepypastaWorkbench extends GuiBase {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/creepypasta_workbench.png");

	public GuiCreepypastaWorkbench(InventoryPlayer playerInv, World world, BlockPos pos) {
		super(new ContainerCreepypastaWorkbench(playerInv, world, pos), TEXTURE);

		this.setGuiSize(222, 248);
		this.name = "gui.creepypasta_workbench";
	}
}