package com.ocelot.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.ocelot.Reference;
import com.ocelot.api.utils.GuiUtils;
import com.ocelot.api.utils.TextureUtils;
import com.ocelot.container.ContainerBattery;
import com.ocelot.tileentity.TileEntityBattery;

import cjminecraft.core.client.gui.GuiBase;
import cjminecraft.core.client.gui.element.ElementEnergyBar;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class GuiBattery extends GuiBase {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/battery.png");

	private EntityPlayer player;
	private TileEntityBattery te;
	private IItemHandler handler;
	private IEnergyStorage storage;

	private ElementEnergyBar energyBar;

	public GuiBattery(EntityPlayer player, TileEntityBattery te) {
		super(new ContainerBattery(player, te), TEXTURE);
		this.player = player;
		this.te = te;
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.storage = te.getCapability(CapabilityEnergy.ENERGY, null);

		this.setGuiSize(176, 166);
	}

	@Override
	public void initGui() {
		super.initGui();
		this.name = "gui.battery";

		this.energyBar = new ElementEnergyBar(this, 80, 18, 16, 60).shouldSync(te.getPos(), null);
		addElement(energyBar);
	}

	@Override
	protected void updateElementInformation() {
		energyBar.syncData(te.getPos(), null);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

		if (handler.getStackInSlot(0).isEmpty())
			drawTooltip(I18n.format("gui.battery.insert_energy.tooltip"), guiLeft + 43, guiTop + 35, 18, 18, mouseX, mouseY);
		if (handler.getStackInSlot(1).isEmpty())
			drawTooltip(I18n.format("gui.battery.extract_energy.tooltip"), guiLeft + 115, guiTop + 35, 18, 18, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);

		TextureUtils.bindTexture(TEXTURE);
		if (handler.getStackInSlot(0).isEmpty())
			drawTexturedModalRect(guiLeft + 44, guiTop + 36, 177, 1, 16, 16);
		if (handler.getStackInSlot(1).isEmpty())
			drawTexturedModalRect(guiLeft + 116, guiTop + 36, 177, 17, 16, 16);
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