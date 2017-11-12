package com.ocelot.client.gui;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import com.ocelot.Reference;
import com.ocelot.container.ContainerItemRecolorer;
import com.ocelot.lib.GuiUtils;
import com.ocelot.lib.TextureUtils;
import com.ocelot.tileentity.TileEntityItemRecolorer;

import cjminecraft.core.client.gui.EnergyBar;
import cjminecraft.core.energy.EnergyUnits;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class GuiItemRecolorer extends GuiContainer {

	private EntityPlayer player;
	private TileEntityItemRecolorer te;
	private IItemHandler handler;
	private IEnergyStorage storage;

	private List<GuiTextField> textFields = new ArrayList<GuiTextField>();

	private static final Pattern COLOR = Pattern.compile("[1-9]");

	private GuiTextField color;
	private EnergyBar energyBar;

	public GuiItemRecolorer(EntityPlayer player, TileEntityItemRecolorer te) {
		super(new ContainerItemRecolorer(player, te));
		this.player = player;
		this.te = te;
		this.handler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		this.storage = te.getCapability(CapabilityEnergy.ENERGY, null);
	}

	@Override
	public void initGui() {
		xSize = 176;
		ySize = 192;
		super.initGui();

		energyBar = new EnergyBar(0, guiLeft + 150, guiTop + 10, 16, 90, 0, 0);
		buttonList.clear();
		buttonList.add(energyBar);

		color = new GuiTextField(0, fontRenderer, 10 + guiLeft, 22 + guiTop, 50, 16);

		textFields.clear();
		textFields.add(color);
	}

	@Override
	public void updateScreen() {
		super.updateScreen();

//		ItemStack stack = handler.getStackInSlot(0);
//		if (this.energyBar.energy > 0 && stack != null) {
//			NBTTagCompound nbt;
//			if (stack.hasTagCompound()) {
//				nbt = stack.getTagCompound();
//			} else {
//				nbt = new NBTTagCompound();
//			}
//
//			if (color.getText() != null && color.getText().length() > 0) {
//				nbt.setInteger("color", Color.decode(color.getText()).getRGB());
//				stack.setTagCompound(nbt);
//			}
//		}

		this.energyBar.syncData(this.te.getPos(), EnumFacing.NORTH);
	}

	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
		super.drawScreen(mouseX, mouseY, partialTicks);
		RenderHelper.disableStandardItemLighting();
		for (GuiTextField field : textFields) {
			field.drawTextBox();
		}
		RenderHelper.enableStandardItemLighting();
		renderHoveredToolTip(mouseX, mouseY);

		for (int i = 0; i < textFields.size(); i++) {
			GuiTextField field = textFields.get(i);
			drawTooltip(I18n.format("gui.item_recolorer.color"), field.x, field.y, field.width, field.height, mouseX, mouseY);
		}

		if (this.energyBar.isMouseOver()) {
			this.drawHoveringText(Arrays.asList(this.energyBar.energy + " " + EnergyUnits.REDSTONE_FLUX.getSuffix() + " / " + this.energyBar.capacity + " " + EnergyUnits.REDSTONE_FLUX.getSuffix()), mouseX, mouseY);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		drawDefaultBackground();
		GlStateManager.color(1, 1, 1, 1);
		TextureUtils.bindTexture(getTexture());
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		this.mc.fontRenderer.drawString(I18n.format("gui.item_recolorer"), this.xSize / 2 - this.mc.fontRenderer.getStringWidth(I18n.format("gui.item_recolorer")) / 2, 6, 4210752);
		this.mc.fontRenderer.drawString(this.player.inventory.getDisplayName().getFormattedText(), 8, 98, 4210752);
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

	public static ResourceLocation getTexture() {
		return new ResourceLocation(Reference.MOD_ID, "textures/gui/container/item_recolorer.png");
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