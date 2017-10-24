package com.ocelot.client.render.entity;

import com.ocelot.Reference;
import com.ocelot.entity.living.EntitySmileDog;

import net.minecraft.client.model.ModelWolf;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

/**
 * @author Ocelot5836
 */
public class RenderSmileDog extends RenderLiving<EntitySmileDog> {

	private static final ResourceLocation WOLF_TEXTURES = new ResourceLocation(Reference.MOD_ID, "textures/entity/smile_dog/normal.png");

	public RenderSmileDog(RenderManager renderManager) {
		super(renderManager, new ModelWolf(), 0.5F);
	}

	/**
	 * Defines what float the third param in setRotationAngles of ModelBase is
	 */
	protected float handleRotationFloat(EntitySmileDog livingBase, float partialTicks) {
		return livingBase.getTailRotation();
	}

	/**
	 * Renders the desired {@code T} type Entity.
	 */
	public void doRender(EntitySmileDog entity, double x, double y, double z, float entityYaw, float partialTicks) {
		if (entity.isWolfWet()) {
			float f = entity.getBrightness() * entity.getShadingWhileWet(partialTicks);
			GlStateManager.color(f, f, f);
		}

		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntitySmileDog entity) {
		return WOLF_TEXTURES;
	}
}