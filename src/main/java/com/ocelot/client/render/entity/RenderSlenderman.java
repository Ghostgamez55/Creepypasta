package com.ocelot.client.render.entity;

import com.ocelot.Reference;
import com.ocelot.client.model.entity.ModelSlenderman;
import com.ocelot.entity.hostile.EntitySlenderman;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author Ocelot5836
 */
public class RenderSlenderman extends RenderLiving<EntitySlenderman> {

	public RenderSlenderman(RenderManager renderManager) {
		super(renderManager, new ModelSlenderman(), 0.5F);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySlenderman entity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/slenderman.png");
	}
}