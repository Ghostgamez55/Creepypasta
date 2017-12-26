package com.ocelot.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * slenderman - Undefined Created using Tabula 7.0.0
 */
public class ModelSlenderman extends ModelBase {

	public ModelRenderer body;
	public ModelRenderer neck;
	public ModelRenderer head;
	public ModelRenderer LeftLeg;
	public ModelRenderer RightLeg;
	public ModelRenderer Leftfoot;
	public ModelRenderer Rightfoot;
	public ModelRenderer rightarm;
	public ModelRenderer leftarm;

	public ModelSlenderman() {
		this.textureWidth = 130;
		this.textureHeight = 130;
		this.neck = new ModelRenderer(this, 61, 31);
		this.neck.setRotationPoint(-1.9F, -11.9F, -4.3F);
		this.neck.addBox(0.0F, 0.0F, 0.0F, 4, 3, 4, 0.0F);
		this.body = new ModelRenderer(this, 51, 49);
		this.body.setRotationPoint(-4.8F, -8.8F, -5.1F);
		this.body.addBox(0.0F, 0.0F, 0.0F, 10, 17, 6, 0.0F);
		this.leftarm = new ModelRenderer(this, 32, 51);
		this.leftarm.setRotationPoint(-4.8F, -7.5F, -0.8F);
		this.leftarm.addBox(0.0F, 0.0F, 0.0F, 2, 22, 2, 0.0F);
		this.setRotateAngle(leftarm, 0.0F, 3.141592653589793F, 0.0F);
		this.Leftfoot = new ModelRenderer(this, 49, 112);
		this.Leftfoot.setRotationPoint(-3.7F, 23.0F, -5.2F);
		this.Leftfoot.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
		this.LeftLeg = new ModelRenderer(this, 53, 83);
		this.LeftLeg.setRotationPoint(-0.7F, 7.5F, -2.9F);
		this.LeftLeg.addBox(0.0F, 0.0F, 0.0F, 2, 16, 3, 0.0F);
		this.setRotateAngle(LeftLeg, 0.0F, -1.5667820695153094F, 0.0F);
		this.Rightfoot = new ModelRenderer(this, 73, 111);
		this.Rightfoot.setRotationPoint(1.5F, 23.0F, -5.2F);
		this.Rightfoot.addBox(0.0F, 0.0F, 0.0F, 3, 1, 4, 0.0F);
		this.RightLeg = new ModelRenderer(this, 75, 84);
		this.RightLeg.setRotationPoint(1.5F, 7.5F, -2.8F);
		this.RightLeg.addBox(0.0F, 0.0F, 0.0F, 3, 16, 2, 0.0F);
		this.rightarm = new ModelRenderer(this, 96, 49);
		this.rightarm.setRotationPoint(5.1F, -7.3F, -3.0F);
		this.rightarm.addBox(0.0F, 0.0F, 0.0F, 2, 22, 2, 0.0F);
		this.head = new ModelRenderer(this, 54, 5);
		this.head.setRotationPoint(-3.9F, -21.0F, -5.1F);
		this.head.addBox(0.0F, 0.0F, 0.0F, 8, 9, 6, 0.0F);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		this.neck.render(scale);
		this.body.render(scale);
		this.leftarm.render(scale);
		this.Leftfoot.render(scale);
		this.LeftLeg.render(scale);
		this.Rightfoot.render(scale);
		this.RightLeg.render(scale);
		this.rightarm.render(scale);
		this.head.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale, Entity entity) {
		// this.head.rotateAngleX = headPitch * 0.017453292F;
		// this.head.rotateAngleY = netHeadYaw * 0.017453292F;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entity, float limbSwing, float limbSwingAmount, float partialTickTime) {
		this.leftarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 1.5f * limbSwingAmount;
		this.rightarm.rotateAngleX = MathHelper.cos(limbSwing * 0.6F) * 1.5F * limbSwingAmount;
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
