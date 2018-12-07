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

	private static final ResourceLocation resource = new ResourceLocation("textures/entity/enchanting_table_book.png");
	private ModelBook model = new ModelBook();

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return canRendering(item, type);
	}

	// どのItemRenderTypeを上書きするかの設定。
	// 今回は全て変更しているが、一人称だけモデルを使う、ドロップ状態の時だけ、など通常レンダーとの使い分けも可能。
	private boolean canRendering(ItemStack item, ItemRenderType type) {
		switch (type) {
		// ドロップ状態
		case ENTITY:
			return false;
			// 三人称の装備中
		case EQUIPPED:
			return false;
			// 一人称の装備中
		case EQUIPPED_FIRST_PERSON:
			return true;
			// インベントリ
		case INVENTORY:
			return false;
		default:
			return false;
		}
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		switch (helper) {
		case INVENTORY_BLOCK:
		case ENTITY_BOBBING:
		case ENTITY_ROTATION:
			return true;
		default:
			return false;
		}
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		if (canRendering(item, type)) {

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
			case EQUIPPED:
				glMatrixForRenderInEquipped();
				break;
			case ENTITY:
				glMatrixForRenderInEntity();
			default:
				break;
			}

			// テクスチャのオーバーライド
			FMLClientHandler.instance().getClient().getTextureManager().bindTexture(resource);
			//モデルを変更するGrimoireのSpineCountを取得。
			Grimoire grimoire = (Grimoire) item.getItem();
			float spineCount = grimoire.SpineCount;
			// モデルのrenderメソッドを呼ぶ
			model.render((Entity) null, 0.0F, 0.0F, 0.0F, spineCount, 0.0F, 0.0625F);
			GL11.glPopMatrix();
		}
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
		GL11.glRotatef(-270F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(-90F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(35F, 0.0F, 1.0F, 0.0F);
		GL11.glScalef(-1.1F, 1.1F, 1.1F);
		GL11.glTranslatef(0.0F, -0.0F, -0.6F);
	}

	private void glMatrixForRenderInFirstPerson() {
		GL11.glScalef(1.5F, 1.5F, -1.5F);
	    GL11.glRotatef(-110.0F, 0.0F, 0.0F, 1.0F);
	    GL11.glRotatef(45.0F, 1.0F, 0.0F, 0.0F);
	    GL11.glTranslatef(-0.5F, -0.2F, -0.6F);
	}

	/*
	 * ドロップ状態での描画位置の調整.
	 */
	private void glMatrixForRenderInEntity() {
		GL11.glScalef(1.0F, -1.0F, 1.0F);
		GL11.glTranslatef(0.0F, -1.5F, 0.0F);
	}

}
