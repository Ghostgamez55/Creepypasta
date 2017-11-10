package com.ocelot.client.render.entity;

import com.ocelot.Reference;
import com.ocelot.entity.passive.EntitySmileMan;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderSmileMan extends RenderLiving<EntitySmileMan> {

	public RenderSmileMan(RenderManager rendermanagerIn) {
		super(rendermanagerIn, new ModelBiped(0, 0, 64, 64), 0.5F);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntitySmileMan entity) {
		return new ResourceLocation(Reference.MOD_ID, "textures/entity/smileman.png");
	}
}