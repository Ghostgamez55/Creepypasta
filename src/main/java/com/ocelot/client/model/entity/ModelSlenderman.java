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

    public ModelRenderer torso;
    public ModelRenderer rightfoot;
    public ModelRenderer rightleg;
    public ModelRenderer leftfoot;
    public ModelRenderer leftleg;
    public ModelRenderer leftarm;
    public ModelRenderer rightarm;
    public ModelRenderer head;

    public ModelSlenderman() {
        this.textureWidth = 132;
        this.textureHeight = 132;
        this.torso = new ModelRenderer(this, 49, 52);
        this.torso.setRotationPoint(-2.9F, -6.9F, -1.5F);
        this.torso.addBox(0.0F, 0.0F, 0.0F, 6, 15, 3, 0.0F);
        this.rightleg = new ModelRenderer(this, 70, 77);
        this.rightleg.setRotationPoint(0.6F, 8.0F, -1.1F);
        this.rightleg.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, 0.0F);
        this.head = new ModelRenderer(this, 51, 6);
        this.head.setRotationPoint(-2.4F, -13.0F, -3.1F);
        this.head.addBox(0.0F, 0.0F, 0.0F, 5, 6, 5, 0.0F);
        this.rightfoot = new ModelRenderer(this, 63, 105);
        this.rightfoot.setRotationPoint(0.6F, 23.0F, -4.0F);
        this.rightfoot.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
        this.leftleg = new ModelRenderer(this, 53, 77);
        this.leftleg.setRotationPoint(-2.6F, 8.0F, -1.0F);
        this.leftleg.addBox(0.0F, 0.0F, 0.0F, 2, 16, 2, -0.1F);
        this.leftarm = new ModelRenderer(this, 29, 44);
        this.leftarm.setRotationPoint(-3.9F, -6.4F, -1.0F);
        this.leftarm.addBox(0.0F, 0.0F, 0.0F, 1, 25, 2, 0.0F);
        this.leftfoot = new ModelRenderer(this, 47, 105);
        this.leftfoot.setRotationPoint(-2.6F, 23.0F, -4.0F);
        this.leftfoot.addBox(0.0F, 0.0F, 0.0F, 2, 1, 4, 0.0F);
        this.rightarm = new ModelRenderer(this, 86, 46);
        this.rightarm.setRotationPoint(3.1F, -6.4F, -1.0F);
        this.rightarm.addBox(0.0F, 0.0F, 0.0F, 1, 25, 2, 0.0F);
    }

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
        this.torso.render(scale);
        this.rightleg.render(scale);
        this.head.render(scale);
        this.rightfoot.render(scale);
        this.leftleg.render(scale);
        this.leftarm.render(scale);
        this.leftfoot.render(scale);
        this.rightarm.render(scale);
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
