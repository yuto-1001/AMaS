package com.yuto.MagicScienceExcepiton.Client.Render;

import org.lwjgl.opengl.GL11;

import com.yuto.MagicScienceExcepiton.Item.Magic.Grimoire;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBook;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

@SideOnly(Side.CLIENT)
public class RenderGrimoire implements IItemRenderer {

	private static final ResourceLocation resourceOff = new ResourceLocation("magicscienceexception", "textures/entity/Grimoire_off.png");
	private static final ResourceLocation resource = new ResourceLocation("magicscienceexception", "textures/entity/Grimoire.png");
	private ModelBook model;

	public RenderGrimoire() {
		model = new ModelBook();
	}

	@Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {

        switch(type.ordinal())
        {
        //case 1: //entity
        case 3: //item
        	return false;
        case 2: //handitem
        //case 3:
        //case 4: //?
        case 1:
        	return true;
        }
        return false;
    }

	@Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper)
    {

    	switch(type.ordinal())
        {
        //case 1:
        case 3:
        	return false;
        case 2:
        //case 3:
        //case 4:
        case 1:
        	return true;
        }
        return false;
    }

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		GL11.glPushMatrix();
		/*
		 * 描画する種類によって回転, 平行移動を行う.
		 */
		switch (type) {
		case INVENTORY:
			glMatrixForRenderInInventory();
			break;
		case EQUIPPED_FIRST_PERSON:
			glMatrixForRenderInFirstPerson();
			break;
		case EQUIPPED:
			glMatrixForRenderInEquipped();
			break;
		case ENTITY:
			glMatrixForRenderInEntity();
		default:
			break;
		}

		//モデルを変更するGrimoireのSpineCountを取得。
		Grimoire grimoire = (Grimoire) item.getItem();
		float spineCount = grimoire.SpineCount;
		if(spineCount == 1.0) {
			// テクスチャのオーバーライド
			FMLClientHandler.instance().getClient().getTextureManager().bindTexture(resource);
		}else {
			FMLClientHandler.instance().getClient().getTextureManager().bindTexture(resourceOff);
		}
		// モデルのrenderメソッドを呼ぶ
		model.render((Entity) null, 0.0F, 0.0F, 0.0F, spineCount, 0.0F, 0.0625F);
		GL11.glPopMatrix();
	}

	/*
	 * インベントリ内での描画位置の調整.
	 */
	private void glMatrixForRenderInInventory() {
		GL11.glRotatef(-180F, 1.0F, 0.0F, 0.0F);
		GL11.glTranslatef(0.0F, -1.0F, 0.0F);
	}

	/*
	 * 装備状態での描画位置の調整.
	 */
	private void glMatrixForRenderInEquipped() {
		GL11.glRotatef(50F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-100F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(105F, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(-1.5F, 1.5F, 1.5F);
		GL11.glTranslatef(-0.3F, -0.0F, -0.3F);
	}

	private void glMatrixForRenderInFirstPerson() {
		GL11.glRotatef(200F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(20F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(1.5F, 1.5F, 1.5F);
		GL11.glTranslatef(0.0F, 0.7F, -0.3F);

	}

	/*
	 * ドロップ状態での描画位置の調整.
	 */
	private void glMatrixForRenderInEntity() {
		GL11.glScalef(1.0F, -1.0F, 1.0F);
		GL11.glTranslatef(0.0F, -1.5F, 0.0F);
	}

}
