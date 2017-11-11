package com.ocelot.client.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.ocelot.Reference;
import com.ocelot.container.ContainerBattery;
import com.ocelot.lib.GuiUtils;
import com.ocelot.lib.TextureUtils;
import com.ocelot.tileentity.TileEntityBattery;

import cjminecraft.core.client.gui.EnergyBar;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class GuiBattery extends GuiContainer {

	public static int sync = 0;

	private EntityPlayer player;
	private TileEntityBattery te;
	private IItemHandler handler;

	private EnergyBar energyBar;

	public GuiBattery(EntityPlayer player, TileEntityBattery te) {
		super(new ContainerBattery(player, te));
		this.player = player;
		this.te = te;
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
	}

	@Override
	public void initGui() {
		xSize = 176;
		ySize = 166;
		super.initGui();

		this.energyBar = new EnergyBar(0, guiLeft + 80, guiTop + 18, 16, 60, 0, 0);
		this.buttonList.clear();
		this.buttonList.add(energyBar);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

		sync++;
		sync %= 1;
		if (sync == 0) {
			this.energyBar.syncData(this.te.getPos(), EnumFacing.NORTH);
		}
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		renderHoveredToolTip(mouseX, mouseY);

		if (handler.getStackInSlot(0).isEmpty())
			drawTooltip(I18n.format("gui.battery.insert_energy.tooltip"), guiLeft + 43, guiTop + 35, 18, 18, mouseX, mouseY);
		if (handler.getStackInSlot(1).isEmpty())
			drawTooltip(I18n.format("gui.battery.extract_energy.tooltip"), guiLeft + 115, guiTop + 35, 18, 18, mouseX, mouseY);

		if (this.energyBar.isMouseOver()) {
			this.drawHoveringText(Arrays.asList(this.energyBar.energy + " RF / " + this.energyBar.capacity + " RF"), mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		drawDefaultBackground();
		GlStateManager.color(1, 1, 1, 1);
		TextureUtils.bindTexture(getTexture());
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);

		if (handler.getStackInSlot(0) == ItemStack.EMPTY)
			drawTexturedModalRect(guiLeft + 44, guiTop + 36, 177, 1, 16, 16);
		if (handler.getStackInSlot(1) == ItemStack.EMPTY)
			drawTexturedModalRect(guiLeft + 116, guiTop + 36, 177, 17, 16, 16);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.mc.fontRenderer.drawString(I18n.format("gui.battery"), this.xSize / 2 - this.mc.fontRenderer.getStringWidth(I18n.format("gui.battery")) / 2, 6, 4210752);
		this.mc.fontRenderer.drawString(this.player.inventory.getDisplayName().getFormattedText(), 8, 72, 4210752);
	}

	public static ResourceLocation getTexture() {
		return new ResourceLocation(Reference.MOD_ID, "textures/gui/container/battery.png");
	}

	public void drawTooltip(List<String> lines, int posX, int posY, int width, int height, int mouseX, int mouseY) {
		if (GuiUtils.isMouseInside(posX, posY, width, height, mouseX, mouseY)) {
			drawHoveringText(lines, mouseX, mouseY);
		}
	}

	public void drawTooltip(String line, int posX, int posY, int width, int height, int mouseX, int mouseY) {
		if (GuiUtils.isMouseInside(posX, posY, width, height, mouseX, mouseY)) {
			List<String> lines = new ArrayList<String>();
			lines.add(line);
			drawHoveringText(lines, mouseX, mouseY);
		}
	}
}