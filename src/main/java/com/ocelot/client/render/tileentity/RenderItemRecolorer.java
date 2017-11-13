package com.ocelot.client.render.tileentity;

import com.ocelot.tileentity.TileEntityItemRecolorer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;

public class RenderItemRecolorer extends TileEntitySpecialRenderer<TileEntityItemRecolorer> {

	@Override
	public void render(TileEntityItemRecolorer te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		if (te.getStack() != null) {
			EntityItem item = new EntityItem(getWorld(), 0, 0, 0, te.getStack());

			item.hoverStart = 0;

			GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.translate(0.0625*8, 0.0625*4, 0.0625*9.5);
			Minecraft.getMinecraft().getRenderManager().doRenderEntity(item, 0, 0, 0, 0, 0, false);
			GlStateManager.popMatrix();
		}
	}
}