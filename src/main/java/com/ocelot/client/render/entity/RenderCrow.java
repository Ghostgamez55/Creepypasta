package com.ocelot.client.render.entity;

import com.ocelot.Reference;
import com.ocelot.client.model.entity.ModelCrow;
import com.ocelot.entity.passive.EntityCrow;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderCrow extends RenderLiving<EntityCrow> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MOD_ID, "textures/entity/crow.png");

	public RenderCrow(RenderManager renderManager) {
		super(renderManager, new ModelCrow(), 0.4f);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityCrow entity) {
		return TEXTURE;
	}
}