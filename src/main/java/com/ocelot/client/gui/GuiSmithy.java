package com.ocelot.client.gui;

import com.ocelot.Reference;
import com.ocelot.container.ContainerCreepypastaWorkbench;
import com.ocelot.network.NetworkHandler;
import com.ocelot.network.message.MessageGetWorker;
import com.ocelot.tileentity.TileEntitySmithy;

import cjminecraft.core.client.gui.GuiBase;
import cjminecraft.core.client.gui.element.ElementBase;
import cjminecraft.core.client.gui.element.ElementProgressBar;
import cjminecraft.core.client.gui.element.ElementProgressBar.ProgressBarDirection;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiSmithy extends GuiBase {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/gui/container/smithy.png");

	private BlockPos pos;
	
	private ElementBase progressBar;
	public static int cooldown = 0, maxCooldown = 0;

	public GuiSmithy(InventoryPlayer playerInv, World world, BlockPos pos) {
		super(new ContainerCreepypastaWorkbench(playerInv, world, pos), TEXTURE);
		this.pos = pos;

		this.setGuiSize(192, 221);
		this.name = "gui.creepypasta_workbench";
	}

	@Override
	public void initGui() {
		super.initGui();

		progressBar = new ElementProgressBar(this, 125, 69).setDirection(ProgressBarDirection.LEFT_TO_RIGHT).setTextureUV(192, 0).setTexture(TEXTURE, 24, 17);
		elements.add(progressBar);
	}

	@Override
	protected void updateElementInformation() {
		super.updateElementInformation();

		if (cooldown == 0)
			cooldown = maxCooldown;
		NetworkHandler.sendToServer(new MessageGetWorker(pos, this.mc.player.getHorizontalFacing(), "com.ocelot.client.gui.GuiSmithy", "cooldown", "maxCooldown"));
		((ElementProgressBar) progressBar).setMin(cooldown).setMax(maxCooldown);
	}
}