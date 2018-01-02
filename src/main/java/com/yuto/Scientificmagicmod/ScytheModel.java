package com.yuto.Scientificmagicmod;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ScytheModel extends ModelBase {
	ModelRenderer Entity;

  public ScytheModel()
  {
    textureWidth = 64;
    textureHeight = 64;

    
    Entity = new ModelRenderer(this, 0, 0);
    Entity.addBox(-4F, -5F, -4F, 8, 10, 8);
    Entity.setRotationPoint(0F, 16F, 0F);
    Entity.setTextureSize(64, 64);
    Entity.mirror = true;
    setRotation(Entity, 0F, 0F, 0F);
  }

	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    /*super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);*/
	    Entity.render(f5);
	  }

	  public void renderEntity(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	  }

	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }

	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
	  {
	    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
            //飛ばしたエンティティが回転しながら飛んで行く。
	    this.Entity.rotateAngleY += 0.5F / (180F / (float)Math.PI);
	  }


}