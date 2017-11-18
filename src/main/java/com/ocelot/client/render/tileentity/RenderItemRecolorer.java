package com.ocelot.client.render.tileentity;

import com.ocelot.blocks.BlockItemRecolorer;
import com.ocelot.tileentity.TileEntityItemRecolorer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumFacing;

public class RenderItemRecolorer extends TileEntitySpecialRenderer<TileEntityItemRecolorer> {

	@Override
	public void render(TileEntityItemRecolorer te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		super.render(te, x, y, z, partialTicks, destroyStage, alpha);
		BlockItemRecolorer block = (BlockItemRecolorer) te.getBlockType();
		EnumFacing facing = getWorld().getBlockState(te.getPos()).getValue(block.FACING);

		if (te.getStack() != null) {
			EntityItem item = new EntityItem(getWorld(), 0, 0, 0, te.getStack());

			item.hoverStart = 0;

			GlStateManager.pushMatrix();
			GlStateManager.translate(x, y, z);
			GlStateManager.rotate(facing.getHorizontalIndex() * 90, 0, 1, 0);

			switch (facing) {
			case SOUTH:
				GlStateManager.translate(0.0625 * 8, 0.0625 * 4, 0.0625 * 9.3);
				break;
			case WEST:
				GlStateManager.translate(0.0625 * -8, 0.0625 * 4, 0.0625 * 6.7);
				break;
			case NORTH:
				GlStateManager.translate(0.0625 * -8, 0.0625 * 4, 0.0625 * -6.7);
				break;
			case EAST:
				GlStateManager.translate(0.0625 * 8, 0.0625 * 4, 0.0625 * -9.3);
				break;
			default:
				GlStateManager.popMatrix();
				return;
			}

			Minecraft.getMinecraft().getRenderManager().doRenderEntity(item, 0, 0, 0, 0, 0, false);
			GlStateManager.popMatrix();
		}
	}
}