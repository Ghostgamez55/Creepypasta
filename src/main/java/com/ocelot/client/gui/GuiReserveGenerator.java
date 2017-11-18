package com.ocelot.client.gui;

import com.ocelot.Reference;
import com.ocelot.container.ContainerReserveGenerator;
import com.ocelot.network.NetworkHandler;
import com.ocelot.network.message.MessageGetWorker;
import com.ocelot.tileentity.TileEntityReserveGenerator;

import cjminecraft.core.CJCore;
import cjminecraft.core.client.gui.GuiBase;
import cjminecraft.core.client.gui.element.ElementBase;
import cjminecraft.core.client.gui.element.ElementEnergyBar;
import cjminecraft.core.client.gui.element.ElementProgressBar;
import cjminecraft.core.client.gui.element.ElementProgressBar.ProgressBarDirection;
import cjminecraft.core.energy.EnergyUnits;
import cjminecraft.core.energy.EnergyUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

public class GuiReserveGenerator extends GuiBase {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/reserve_generator.png");

	private TileEntityReserveGenerator te;
	private IEnergyStorage storage;

	private ElementBase progressBar;
	private ElementEnergyBar energyBar;

	public static int cooldown = 0, maxCooldown = 0;

	public GuiReserveGenerator(EntityPlayer player, TileEntityReserveGenerator te) {
		super(new ContainerReserveGenerator(player, te), TEXTURE);
		this.te = te;
		this.storage = te.getCapability(CapabilityEnergy.ENERGY, null);
		
		this.setGuiSize(176, 166);
		this.name = "gui.reserve_generator";
	}

	@Override
	public void initGui() {
		super.initGui();

		this.progressBar = new ElementProgressBar(this, 79, 35, 16, 16).setDirection(ProgressBarDirection.UP_TO_DOWN).setTextureUV(176, 0).setTexture(TEXTURE, 16, 16);
		this.addElement(progressBar);

		this.energyBar = new ElementEnergyBar(this, 7, 7, 18, 64).shouldSync(te.getPos(), null);
		this.addElement(energyBar);
	}

	@Override
	protected void updateElementInformation() {
		super.updateElementInformation();

		energyBar.syncData(te.getPos(), null);
		
		if (cooldown == 0)
			cooldown = maxCooldown;
		NetworkHandler.sendToServer(new MessageGetWorker(te.getPos(), this.mc.player.getHorizontalFacing(), "com.ocelot.client.gui.GuiReserveGenerator", "cooldown", "maxCooldown"));
		((ElementProgressBar) progressBar).setMin(cooldown).setMax(maxCooldown);
	}
}