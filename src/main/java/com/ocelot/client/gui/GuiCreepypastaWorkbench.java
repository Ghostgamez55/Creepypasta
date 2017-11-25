package com.ocelot.client.gui;

import com.ocelot.Reference;
import com.ocelot.container.ContainerCreepypastaWorkbench;

import cjminecraft.core.client.gui.GuiBase;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

public class GuiCreepypastaWorkbench extends GuiBase {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/creepypasta_workbench.png");

	public GuiCreepypastaWorkbench(InventoryPlayer playerInv, World world, BlockPos pos) {
		super(new ContainerCreepypastaWorkbench(playerInv, world, pos), TEXTURE);

		this.setGuiSize(222, 248);
		this.name = "gui.creepypasta_workbench";
		this.drawTitle = false;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		super.drawGuiContainerForegroundLayer(mouseX, mouseY);
		this.fontRenderer.drawString(I18n.format(this.name), xSize - fontRenderer.getStringWidth(name) / 2 + 6, 6, 0x404040);
	}
}