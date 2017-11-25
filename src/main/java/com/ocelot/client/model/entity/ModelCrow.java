package com.ocelot.client.model.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

/**
 * Crow - Ocelot5836 Created using Tabula 7.0.0
 */
public class ModelCrow extends ModelBase {
	public ModelRenderer Leg;
	public ModelRenderer toe1;
	public ModelRenderer toe2;
	public ModelRenderer toe3;
	public ModelRenderer Leg_1;
	public ModelRenderer toe1_1;
	public ModelRenderer toe2_1;
	public ModelRenderer toe3_1;
	public ModelRenderer Main;
	public ModelRenderer Tail;
	public ModelRenderer LeftWing;
	public ModelRenderer RightWing;
	public ModelRenderer Neck;
	public ModelRenderer Main_1;
	public ModelRenderer Beak;
	public ModelRenderer LeftEye;
	public ModelRenderer RightEye;

	public ModelCrow() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.Tail = new ModelRenderer(this, 28, 2);
		this.Tail.setRotationPoint(-2.0F, 17.0F, 5.0F);
		this.Tail.addBox(0.0F, 0.0F, 0.0F, 4, 1, 4, 0.0F);
		this.setRotateAngle(Tail, -0.6108652381980153F, 0.0F, 0.0F);
		this.RightEye = new ModelRenderer(this, 40, 2);
		this.RightEye.setRotationPoint(-2.5F, 12.5F, -8.5F);
		this.RightEye.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.LeftWing = new ModelRenderer(this, 37, 2);
		this.LeftWing.setRotationPoint(4.0F, 15.0F, -5.0F);
		this.LeftWing.addBox(0.0F, 0.0F, 0.0F, 1, 4, 7, 0.0F);
		this.Leg_1 = new ModelRenderer(this, 28, 0);
		this.Leg_1.setRotationPoint(-3.0F, 23.0F, 0.0F);
		this.Leg_1.addBox(0.0F, -3.0F, 0.0F, 1, 4, 1, 0.0F);
		this.toe3 = new ModelRenderer(this, 20, 0);
		this.toe3.setRotationPoint(0.2F, 0.0F, 0.8F);
		this.toe3.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(toe3, 0.0F, 2.356194490192345F, 0.0F);
		this.RightWing = new ModelRenderer(this, 46, 6);
		this.RightWing.setRotationPoint(-5.0F, 15.0F, -5.0F);
		this.RightWing.addBox(0.0F, 0.0F, 0.0F, 1, 4, 7, 0.0F);
		this.Main_1 = new ModelRenderer(this, 0, 17);
		this.Main_1.setRotationPoint(-2.0F, 12.0F, -9.0F);
		this.Main_1.addBox(0.0F, -0.1F, 0.0F, 4, 3, 4, 0.0F);
		this.setRotateAngle(Main_1, -0.17453292519943295F, 0.0F, 0.0F);
		this.toe3_1 = new ModelRenderer(this, 48, 0);
		this.toe3_1.setRotationPoint(0.0F, 0.0F, 0.8F);
		this.toe3_1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(toe3_1, 0.0F, 2.356194490192345F, 0.0F);
		this.Main = new ModelRenderer(this, 0, 2);
		this.Main.setRotationPoint(-4.0F, 16.0F, -5.0F);
		this.Main.addBox(0.0F, -1.0F, 0.0F, 8, 5, 10, 0.0F);
		this.toe2 = new ModelRenderer(this, 12, 0);
		this.toe2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toe2.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(toe2, 0.0F, 1.5707963267948966F, 0.0F);
		this.toe1_1 = new ModelRenderer(this, 32, 0);
		this.toe1_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toe1_1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(toe1_1, 0.0F, 0.7853981633974483F, 0.0F);
		this.Neck = new ModelRenderer(this, 33, 14);
		this.Neck.setRotationPoint(-2.0F, 14.9F, -7.2F);
		this.Neck.addBox(0.0F, -0.1F, 0.0F, 4, 3, 3, 0.0F);
		this.setRotateAngle(Neck, 0.7853981633974483F, 0.0F, 0.0F);
		this.Leg = new ModelRenderer(this, 0, 0);
		this.Leg.setRotationPoint(2.0F, 23.0F, 0.0F);
		this.Leg.addBox(0.0F, -3.0F, -0.1F, 1, 4, 1, 0.0F);
		this.toe2_1 = new ModelRenderer(this, 40, 0);
		this.toe2_1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toe2_1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(toe2_1, 0.0F, 1.5707963267948966F, 0.0F);
		this.toe1 = new ModelRenderer(this, 4, 0);
		this.toe1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.toe1.addBox(0.0F, 0.0F, 0.0F, 3, 1, 1, 0.0F);
		this.setRotateAngle(toe1, 0.0F, 0.7853981633974483F, 0.0F);
		this.Beak = new ModelRenderer(this, 53, 0);
		this.Beak.setRotationPoint(-1.0F, 13.0F, -12.0F);
		this.Beak.addBox(0.0F, 0.0F, 0.0F, 2, 1, 3, 0.0F);
		this.LeftEye = new ModelRenderer(this, 4, 2);
		this.LeftEye.setRotationPoint(1.5F, 12.5F, -8.5F);
		this.LeftEye.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1, 0.0F);
		this.Leg.addChild(this.toe3);
		this.Leg_1.addChild(this.toe3_1);
		this.Leg.addChild(this.toe2);
		this.Leg_1.addChild(this.toe1_1);
		this.Leg_1.addChild(this.toe2_1);
		this.Leg.addChild(this.toe1);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		this.Tail.render(scale);
		this.RightEye.render(scale);
		this.LeftWing.render(scale);
		this.Leg_1.render(scale);
		this.RightWing.render(scale);
		this.Main_1.render(scale);
		this.Main.render(scale);
		this.Neck.render(scale);
		this.Leg.render(scale);
		this.Beak.render(scale);
		this.LeftEye.render(scale);
	}

	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scaleFactor, Entity entity) {
		this.Main_1.rotateAngleX = headPitch * 0.017453292F;
		this.Main_1.rotateAngleY = netHeadYaw * 0.017453292F;
	}

	@Override
	public void setLivingAnimations(EntityLivingBase entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTickTime) {
		float ageInTicks = entitylivingbaseIn.ticksExisted + partialTickTime;
		this.RightWing.rotateAngleZ = MathHelper.cos(limbSwing * 1.3F) * 0.4F * limbSwingAmount;
		this.LeftWing.rotateAngleZ = -MathHelper.cos(limbSwing * 1.3F) * 0.4F * limbSwingAmount;
		this.Leg.rotateAngleX = MathHelper.cos(limbSwing * 1.3F) * 0.058F * limbSwingAmount;
		this.Leg_1.rotateAngleX = -MathHelper.cos(limbSwing * 1.3F) * 0.058F * limbSwingAmount;
		this.Tail.rotateAngleX = MathHelper.cos(ageInTicks * 1.3F) * 0.058F;
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
