package com.ocelot.client.gui.overlay;

import com.ocelot.api.utils.TextureUtils;

import cjminecraft.core.client.gui.GuiOverlay;
import cjminecraft.core.client.gui.overlay.OverlayBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;

public class GuiOverlaySanity extends OverlayBase {

	public static int sanity = 100;
	
	public GuiOverlaySanity(GuiOverlay gui) {
		super(gui, 0, 0);
	}
	
	@Override
	public void drawBackground() {
		if (gui.mc.playerController.gameIsSurvivalOrAdventure()) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.enableAlpha();
			GlStateManager.color(1, 1, 1, 1);
			TextureUtils.bindTexture("textures/gui/sanity.png");
			ScaledResolution resolution = new ScaledResolution(gui.mc);
			float screenWidth = resolution.getScaledWidth() * resolution.getScaleFactor();
			float screenHeight = resolution.getScaledHeight() * resolution.getScaleFactor();
			float x = screenWidth - 229 - 5;
			float y = 5;
			GlStateManager.scale(0.5, 0.5, 0.5);
			gui.drawTexturedModalRect(x, y, 0, 0, 229, 69);
			x /= 2;
			y /= 2;
			GlStateManager.scale(2, 2, 2);
			gui.drawTexturedModalRect(x + 4 + sanity, y + 31, 0, 69, 7, 9);
			GlStateManager.popMatrix();
		}
	}
}