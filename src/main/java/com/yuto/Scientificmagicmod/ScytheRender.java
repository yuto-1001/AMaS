package com.yuto.Scientificmagicmod;

import com.yuto.Scientificmagicmod.entity.EntityDeathScythe;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ScytheRender extends Render{
	private static final ResourceLocation Scythe = new ResourceLocation("scientificmagicmod", "textures/entity/DeathScythe.png");

    protected ModelBase modelBullet;

    public ScytheRender(ModelBase par1ModelBase) {
		super();
		this.modelBullet = par1ModelBase;
		this.shadowSize = 0.0F;
	}

	public void renderArrow(EntityDeathScythe par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        ScytheModel model = (ScytheModel) this.modelBullet;

		this.bindEntityTexture(par1Entity);
        model.render((Entity)null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
    }

    protected ResourceLocation getArrowTextures(EntityDeathScythe par1EntityArrow)
    {
        return Scythe;
    }

    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return this.getArrowTextures((EntityDeathScythe)par1Entity);
    }

    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderArrow((EntityDeathScythe)par1Entity, par2, par4, par6, par8, par9);
    }
}
