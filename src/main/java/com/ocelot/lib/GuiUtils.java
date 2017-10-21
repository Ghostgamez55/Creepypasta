package com.ocelot.lib;

import java.util.List;

import com.ocelot.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * This class is the basis for the entire creepypasta mod.<br>
 * </br>
 * <em><b>Copyright (c) 2017 The Creepypasta Minecraft Mod Team.</b></em>
 * 
 * @author Ocelot5836
 */
public class GuiUtils {

	private static final Minecraft MC = Minecraft.getMinecraft();

	public static void playButtonClick() {
		MC.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));
	}

	public static void renderItem(ItemStack stack, int x, int y) {
		try {
			MC.getRenderItem().renderItemAndEffectIntoGUI(stack, x, y);
		} catch (Exception e) {
		}
	}

	public static void drawLines(List<String> lines, int posX, int posY, int color) {
		for (int i = 0; i < lines.size(); i++) {
			MC.fontRenderer.drawString(lines.get(i), posX, posY + (i * 8), color);
		}
	}

	public static boolean isMouseInside(int posX, int posY, int width, int height, int mouseX, int mouseY) {
		if (mouseX >= posX && mouseX <= (posX + width) && mouseY >= posY && mouseY < (posY + height)) {
			return true;
		}
		return false;
	}

	public static void bindTexture(String path, String domain) {
		MC.getTextureManager().bindTexture(new ResourceLocation(domain, path));
	}

	public static void bindTexture(String path) {
		bindTexture(path, Reference.MOD_ID);
	}
}