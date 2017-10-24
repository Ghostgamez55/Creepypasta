package com.ocelot.client.render.tileentity;

import java.util.Calendar;

import com.ocelot.Reference;
import com.ocelot.blocks.BlockSafe;
import com.ocelot.client.render.model.ModelSafe;
import com.ocelot.tileentity.TileEntitySafe;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

/**
 * @author Ocelot5836
 */
public class RenderSafe extends TileEntitySpecialRenderer<TileEntitySafe> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/models/safe/safe.png");
	private ModelSafe safe = new ModelSafe();
	private boolean isChristmas;

	public RenderSafe() {
		Calendar calendar = Calendar.getInstance();

		if (calendar.get(2) + 1 == 12 && calendar.get(5) >= 24 && calendar.get(5) <= 26) {
			this.isChristmas = true;
		}
	}

	public void render(TileEntitySafe te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
		GlStateManager.enableDepth();
		GlStateManager.depthFunc(515);
		GlStateManager.depthMask(true);
		int i;

		if (te.hasWorld()) {
			Block block = te.getBlockType();
			i = te.getBlockMetadata();

			if (block instanceof BlockSafe && i == 0) {
				i = te.getBlockMetadata();
			}
		} else {
			i = 0;
		}

		ModelSafe modelchest;

		modelchest = this.safe;

		if (destroyStage >= 0) {
			this.bindTexture(DESTROY_STAGES[destroyStage]);
			GlStateManager.matrixMode(5890);
			GlStateManager.pushMatrix();
			GlStateManager.scale(4.0F, 4.0F, 1.0F);
			GlStateManager.translate(0.0625F, 0.0625F, 0.0625F);
			GlStateManager.matrixMode(5888);
		} else if (this.isChristmas) {
			this.bindTexture(TEXTURE);
		} else {
			this.bindTexture(TEXTURE);
		}

		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();

		if (destroyStage < 0) {
			GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);
		}

		GlStateManager.translate((float) x, (float) y + 1.0F, (float) z + 1.0F);
		GlStateManager.scale(1.0F, -1.0F, -1.0F);
		GlStateManager.translate(0.5F, 0.5F, 0.5F);
		int j = 0;

		if (i == 2) {
			j = 180;
		}

		if (i == 3) {
			j = 0;
		}

		if (i == 4) {
			j = 90;
		}

		if (i == 5) {
			j = -90;
		}

		GlStateManager.rotate((float) j + 180, 0.0F, 1.0F, 0.0F);
		GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		float f = te.prevLidAngle + (te.lidAngle - te.prevLidAngle) * partialTicks;

		f = 1.0F - f;
		f = 1.0F - f * f * f;
		modelchest.lid.rotateAngleY = (f * ((float) Math.PI / 2F));
		modelchest.renderAll();
		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

		if (destroyStage >= 0)

		{
			GlStateManager.matrixMode(5890);
			GlStateManager.popMatrix();
			GlStateManager.matrixMode(5888);
		}
	}
}