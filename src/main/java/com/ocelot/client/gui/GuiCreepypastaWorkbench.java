package com.ocelot.client.gui;

import com.ocelot.Reference;
import com.ocelot.container.ContainerCreepypastaWorkbench;
import com.ocelot.lib.TextureUtils;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiCreepypastaWorkbench extends GuiContainer {

	public GuiCreepypastaWorkbench(InventoryPlayer playerInv, World world, BlockPos pos) {
		super(new ContainerCreepypastaWorkbench(playerInv, world, pos));

		this.xSize = 222;
		this.ySize = 248;
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		drawDefaultBackground();
		GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
		super.drawScreen(mouseX, mouseY, partialTicks);
		this.renderHoveredToolTip(mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		GlStateManager.color(1, 1, 1, 1);
		TextureUtils.bindTexture(getTexture());
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		fontRenderer.drawString(I18n.format("gui.creepypasta_workbench"), 185 - fontRenderer.getStringWidth(I18n.format("gui.creepypasta_workbench")) / 2, 10, 4210752);
	}

	public ResourceLocation getTexture() {
		return new ResourceLocation(Reference.MOD_ID, "textures/gui/container/creepypasta_workbench.png");
	}
}