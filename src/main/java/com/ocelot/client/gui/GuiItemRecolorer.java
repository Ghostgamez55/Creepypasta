package com.ocelot.client.gui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.ocelot.Reference;
import com.ocelot.container.ContainerItemRecolorer;
import com.ocelot.lib.GuiUtils;
import com.ocelot.tileentity.TileEntityItemRecolorer;

import cjminecraft.core.CJCore;
import cjminecraft.core.client.gui.GuiBase;
import cjminecraft.core.client.gui.element.ElementEnergyBar;
import cjminecraft.core.energy.EnergyData;
import cjminecraft.core.energy.EnergyUnits;
import cjminecraft.core.energy.EnergyUtils;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class GuiItemRecolorer extends GuiBase {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/item_recolorer.png");

	private EntityPlayer player;
	private TileEntityItemRecolorer te;
	private IItemHandler handler;
	private IEnergyStorage storage;

	private ElementEnergyBar energyBar;

	private List<GuiTextField> textFields = new ArrayList<GuiTextField>();
	private static final Pattern COLOR = Pattern.compile("[1-9]");
	private GuiTextField color;

	public GuiItemRecolorer(EntityPlayer player, TileEntityItemRecolorer te) {
		super(new ContainerItemRecolorer(player, te), TEXTURE);
		this.player = player;
		this.te = te;
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.storage = te.getCapability(CapabilityEnergy.ENERGY, null);

		this.setGuiSize(176, 192);
		this.name = "gui.item_recolorer";
	}

	@Override
	public void initGui() {
		super.initGui();

		this.energyBar = new ElementEnergyBar(this, 150, 10, 16, 90).shouldSync(te.getPos(), null);
		addElement(energyBar);

		color = new GuiTextField(0, fontRenderer, 10 + guiLeft, 22 + guiTop, 50, 16);

		textFields.clear();
		textFields.add(color);
	}

	@Override
	protected void updateElementInformation() {
		energyBar.syncData(te.getPos(), null);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);

		for (int i = 0; i < textFields.size(); i++) {
			GuiTextField field = textFields.get(i);
			drawTooltip(I18n.format("gui.item_recolorer.color"), field.x, field.y, field.width, field.height, mouseX, mouseY);
		}
	}

	@Override
	public void drawExtraElements(int mouseX, int mouseY) {
		RenderHelper.disableStandardItemLighting();
		for (GuiTextField field : textFields) {
			field.drawTextBox();
		}
		RenderHelper.enableStandardItemLighting();
	}

	@Override
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
		super.mouseClicked(mouseX, mouseY, mouseButton);
		for (GuiTextField field : textFields) {
			field.mouseClicked(mouseX, mouseY, mouseButton);
		}
	}

	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		for (GuiTextField field : textFields) {
			field.textboxKeyTyped(typedChar, keyCode);
		}
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