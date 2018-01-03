package com.yuto.Scientificmagicmod.Client.Render;

import org.lwjgl.opengl.GL11;

import com.yuto.Scientificmagicmod.Entity.EntityDeathScythe;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ScytheRender extends Render{
	private static final ResourceLocation ScytheTextures = new ResourceLocation("scientificmagicmod", "textures/entity/DeathScythe.png");

    protected ModelBase modelBullet;

    public ScytheRender() {

	}

    public void doRender(Entity entity, double x, double y, double z, float yaw, float pitch)
    {
        doRenderDeathScythe((EntityDeathScythe)entity, x, y, z, yaw, pitch);
    }

    public void doRenderDeathScythe(EntityDeathScythe deathscythe, double x, double y, double z, float yaw, float pitch)
    {
        GL11.glPushMatrix();

        GL11.glTranslatef((float)x, (float)y, (float)z);
    	GL11.glDisable(GL11.GL_LIGHTING);
    	//GL11.glEnable(GL11.GL_NORMALIZE);
    	GL11.glEnable(GL11.GL_BLEND);
    	GL11.glDisable(GL11.GL_CULL_FACE);//両面描画
    	GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE_MINUS_SRC_COLOR);

    	this.bindTexture(getEntityTexture(deathscythe));

        float size = 0.5F;
        GL11.glScalef(size, size, size);

        Tessellator tessellator = Tessellator.instance;
    	float xLength = 0.5F;
    	double zLength = 0.5F;
    	float uMin = 0.0F;
    	float uMax = 0.5F;
    	float vMin = 0.0F;
    	float vMax = 1.0F;

     	GL11.glRotatef(-deathscythe.rotationPitch, 1.0F, 0.0F, 0.0F);
    	tessellator.startDrawingQuads();
    	tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-xLength, 0.0F,  zLength, uMin, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F,  zLength, uMax, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F, -zLength, uMax, vMax);
        tessellator.addVertexWithUV(-xLength, 0.0F, -zLength, uMin, vMax);
		tessellator.draw();

    	size *= 1.1F;
    	GL11.glScalef(size, size, size);

    	tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV(-xLength, 0.0F,  zLength, uMin, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F,  zLength, uMax, vMin);
        tessellator.addVertexWithUV( xLength, 0.0F, -zLength, uMax, vMax);
        tessellator.addVertexWithUV(-xLength, 0.0F, -zLength, uMin, vMax);
		tessellator.draw();

        GL11.glDisable(GL11.GL_BLEND);
    	GL11.glEnable(GL11.GL_LIGHTING);
    	GL11.glEnable(GL11.GL_CULL_FACE);//表綿描画
        GL11.glPopMatrix();
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return getEntityTexture((EntityDeathScythe)entity);
	}

    protected ResourceLocation getEntityTexture(EntityDeathScythe deathscythe)
    {
    	return ScytheTextures;
    }
}
