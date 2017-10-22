package com.ocelot.client.render.entity;

import com.ocelot.Reference;
import com.ocelot.entity.hostile.EntityTiccy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelIllager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;

public class RenderTiccy extends RenderLiving<EntityTiccy> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/entity/ticcy.png");

	public RenderTiccy(RenderManager renderManager) {
		super(renderManager, new ModelBiped(0, 0, 64, 64), 0.5F);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityTiccy entity) {
		return TEXTURE;
	}

	/**
	 * Allows the render to do state modifications necessary before the model is rendered.
	 */
	protected void preRenderCallback(EntityTiccy entitylivingbaseIn, float partialTickTime) {
		//GlStateManager.scale(0.9375F, 0.9375F, 0.9375F);
	}
}