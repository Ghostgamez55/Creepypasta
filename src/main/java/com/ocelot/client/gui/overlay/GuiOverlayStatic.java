package com.ocelot.client.gui.overlay;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.ocelot.api.utils.TextureUtils;

import cjminecraft.core.client.gui.GuiOverlay;
import cjminecraft.core.client.gui.overlay.OverlayBase;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiOverlayStatic extends OverlayBase {

	private BufferedImage[] images;

	private Random random = new Random();

	public GuiOverlayStatic(GuiOverlay gui) {
		super(gui, 0, 0);
		images = new BufferedImage[20];

		for (int i = 0; i < images.length; i++) {
			BufferedImage image = new BufferedImage(Minecraft.getMinecraft().displayWidth / 16, Minecraft.getMinecraft().displayHeight / 16, BufferedImage.TYPE_INT_RGB);
			for (int x = 0; x < image.getWidth(); x++) {
				for (int y = 0; y < image.getHeight(); y++) {
					Color color = new Color(random.nextFloat(), random.nextFloat(), random.nextFloat());
					image.setRGB(x, y, new Color((int) Math.round(color.getRed() * .7), (int) Math.round(color.getGreen() * .2), (int) Math.round(color.getBlue() * .1)).getRGB());
				}
			}
			images[i] = image;
		}
	}

	@Override
	public void drawForeground() {
		if (Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
			float alpha = 0;
			BufferedImage image = images[random.nextInt(images.length)];
			ResourceLocation texture = TextureUtils.createBufferedImageTexture(image);
			GlStateManager.pushMatrix();
			GlStateManager.color(1, 1, 1, alpha);
			gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0, Minecraft.getMinecraft().displayWidth, Minecraft.getMinecraft().displayHeight, image.getWidth(), image.getHeight());
			GlStateManager.popMatrix();
			TextureUtils.deleteTexture(texture);
		}
	}
}