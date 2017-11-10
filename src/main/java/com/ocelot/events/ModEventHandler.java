package com.ocelot.events;

import com.ocelot.lib.GuiUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/**
 * @author Ocelot5836
 */
public class ModEventHandler {

	public static int sanity = 100;

	/**
	 * TODO add the ability to spawn smile dog when holding his image
	 */
	@SubscribeEvent
	public void onLivingUpdateEvent(LivingUpdateEvent event) {
		// World world = event.getEntity().world;
		// if (event.getEntity() instanceof EntityPlayer) {
		// EntityPlayer player = (EntityPlayer) event.getEntity();
		// boolean hasSmileDog = (player.getHeldItemMainhand() != null && player.getHeldItemMainhand().getItem() == ModItems.SMILE_DOG) || (player.getHeldItemOffhand() != null && player.getHeldItemOffhand().getItem() == ModItems.SMILE_DOG);
		// if (hasSmileDog && !world.isRemote) {
		// if (world.getEntitiesWithinAABBExcludingEntity(player, new AxisAlignedBB(new BlockPos(player.posX, player.posY, player.posZ))).size() < 1) {
		// world.spawnEntity(new EntitySmileDog(world, player.posX, player.posY, player.posZ));
		// }
		// }
		// }
	}

	@SubscribeEvent
	public void onRenderGameOverlayEvent(RenderGameOverlayEvent event) {
		ElementType type = event.getType();
		Minecraft mc = Minecraft.getMinecraft();
		Gui gui = mc.ingameGUI;
		if (mc.playerController.gameIsSurvivalOrAdventure()) {
			GlStateManager.pushMatrix();
			GlStateManager.enableBlend();
			GlStateManager.enableAlpha();
			GlStateManager.color(1, 1, 1, 1);
			GuiUtils.bindTexture("textures/gui/sanity.png");
			ScaledResolution resolution = event.getResolution();
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
		GuiUtils.bindTexture("minecraft", "textures/gui/icons.png");
	}
}