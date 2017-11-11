package com.ocelot.client.render.model.entity;
// Cubik Studio 2.8.445 Beta JAVA exporter

// Designed by Stereo Strider with Cubik Studio - https://cubik.studio

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelSlenderman extends ModelBase {

	// fields
	private ModelRenderer e1;
	private ModelRenderer e2;
	private ModelRenderer e3;
	private ModelRenderer e4;
	private ModelRenderer e5;
	private ModelRenderer e6;
	private ModelRenderer e7;
	private ModelRenderer e8;
	private ModelRenderer e9;
	private ModelRenderer e10;
	private ModelRenderer e11;
	private ModelRenderer e12;
	private ModelRenderer e13;
	private ModelRenderer e14;
	private ModelRenderer e15;
	private ModelRenderer e16;
	private ModelRenderer e17;
	private ModelRenderer e18;

	public ModelSlenderman() {
		textureWidth = 64;
		textureHeight = 64;

		e1 = new ModelRenderer(this, 18, 27);
		e1.addBox(5F, -16F, 7F, 3, 16, 3);
		e1.setRotationPoint(5F, -16F, 7F);
		e1.setTextureSize(64, 64);
		e1.mirror = false;
		setRotation(e1, 0F, 0F, 0F);
		e2 = new ModelRenderer(this, 18, 27);
		e2.addBox(8.5F, -16F, 7F, 3, 16, 3);
		e2.setRotationPoint(8.5F, -16F, 7F);
		e2.setTextureSize(64, 64);
		e2.mirror = false;
		setRotation(e2, 0F, 0F, 0F);
		e3 = new ModelRenderer(this, 0, 25);
		e3.addBox(5F, 0F, 7F, 6, 18, 3);
		e3.setRotationPoint(5F, 0F, 7F);
		e3.setTextureSize(64, 64);
		e3.mirror = false;
		setRotation(e3, 0F, 0F, 0F);
		e4 = new ModelRenderer(this, 0, 4);
		e4.addBox(2F, -1F, 7F, 3, 18, 3);
		e4.setRotationPoint(2F, -1F, 7F);
		e4.setTextureSize(64, 64);
		e4.mirror = false;
		setRotation(e4, 0F, 0F, 0F);
		e5 = new ModelRenderer(this, 0, 4);
		e5.addBox(11F, -1F, 7F, 3, 18, 3);
		e5.setRotationPoint(11F, -1F, 7F);
		e5.setTextureSize(64, 64);
		e5.mirror = false;
		setRotation(e5, 0F, 0F, 0F);
		e6 = new ModelRenderer(this, 28, 62);
		e6.addBox(7.875F, 9F, 2.625F, 9, 1, 1);
		e6.setRotationPoint(7.875F, 9F, 2.625F);
		e6.setTextureSize(64, 64);
		e6.mirror = false;
		setRotation(e6, 0F, 0.3926991F, 0F);
		e7 = new ModelRenderer(this, 28, 62);
		e7.addBox(7.875F, 5F, 2.75F, 9, 1, 1);
		e7.setRotationPoint(7.875F, 5F, 2.75F);
		e7.setTextureSize(64, 64);
		e7.mirror = false;
		setRotation(e7, 0F, 0.3926991F, 0F);
		e8 = new ModelRenderer(this, 28, 62);
		e8.addBox(7.75F, 13F, 2.625F, 9, 1, 1);
		e8.setRotationPoint(7.75F, 13F, 2.625F);
		e8.setTextureSize(64, 64);
		e8.mirror = false;
		setRotation(e8, 0F, 0.3926991F, 0F);
		e9 = new ModelRenderer(this, 28, 62);
		e9.addBox(-1.625F, 9F, 6.125F, 9, 1, 1);
		e9.setRotationPoint(-1.625F, 9F, 6.125F);
		e9.setTextureSize(64, 64);
		e9.mirror = false;
		setRotation(e9, 0F, 5.890486F, 0F);
		e10 = new ModelRenderer(this, 28, 62);
		e10.addBox(-1.75F, 5F, 6.125F, 9, 1, 1);
		e10.setRotationPoint(-1.75F, 5F, 6.125F);
		e10.setTextureSize(64, 64);
		e10.mirror = false;
		setRotation(e10, 0F, 5.890486F, 0F);
		e11 = new ModelRenderer(this, 28, 62);
		e11.addBox(-1.75F, 13F, 6.125F, 9, 1, 1);
		e11.setRotationPoint(-1.75F, 13F, 6.125F);
		e11.setTextureSize(64, 64);
		e11.mirror = false;
		setRotation(e11, 0F, 5.890486F, 0F);
		e12 = new ModelRenderer(this, 28, 62);
		e12.addBox(14.25F, 9F, 9F, 9, 1, 1);
		e12.setRotationPoint(14.25F, 9F, 9F);
		e12.setTextureSize(64, 64);
		e12.mirror = false;
		setRotation(e12, 0F, 5.497787F, 0F);
		e13 = new ModelRenderer(this, 28, 62);
		e13.addBox(14.25F, 5F, 9F, 9, 1, 1);
		e13.setRotationPoint(14.25F, 5F, 9F);
		e13.setTextureSize(64, 64);
		e13.mirror = false;
		setRotation(e13, 0F, 5.497787F, 0F);
		e14 = new ModelRenderer(this, 28, 62);
		e14.addBox(14.25F, 13F, 9F, 9, 1, 1);
		e14.setRotationPoint(14.25F, 13F, 9F);
		e14.setTextureSize(64, 64);
		e14.mirror = false;
		setRotation(e14, 0F, 5.497787F, 0F);
		e15 = new ModelRenderer(this, 28, 62);
		e15.addBox(-10.125F, 9F, 2.75F, 9, 1, 1);
		e15.setRotationPoint(-10.125F, 9F, 2.75F);
		e15.setTextureSize(64, 64);
		e15.mirror = false;
		setRotation(e15, 0F, 0.7853982F, 0F);
		e16 = new ModelRenderer(this, 28, 62);
		e16.addBox(-10.125F, 5F, 2.75F, 9, 1, 1);
		e16.setRotationPoint(-10.125F, 5F, 2.75F);
		e16.setTextureSize(64, 64);
		e16.mirror = false;
		setRotation(e16, 0F, 0.7853982F, 0F);
		e17 = new ModelRenderer(this, 28, 62);
		e17.addBox(-10F, 13F, 2.75F, 9, 1, 1);
		e17.setRotationPoint(-10F, 13F, 2.75F);
		e17.setTextureSize(64, 64);
		e17.mirror = false;
		setRotation(e17, 0F, 0.7853982F, 0F);
		e18 = new ModelRenderer(this, 0, 46);
		e18.addBox(4.5F, 18F, 5F, 7, 11, 7);
		e18.setRotationPoint(4.5F, 18F, 5F);
		e18.setTextureSize(64, 64);
		e18.mirror = false;
		setRotation(e18, 0F, 0F, 0F);
		
		e1.showModel = false;
	}

	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
				
		e1.render(scale);
		e2.render(scale);
		e3.render(scale);
		e4.render(scale);
		e5.render(scale);
		e6.render(scale);
		e7.render(scale);
		e8.render(scale);
		e9.render(scale);
		e10.render(scale);
		e11.render(scale);
		e12.render(scale);
		e13.render(scale);
		e14.render(scale);
		e15.render(scale);
		e16.render(scale);
		e17.render(scale);
		e18.render(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}