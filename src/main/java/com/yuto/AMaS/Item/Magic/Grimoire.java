package com.yuto.AMaS.Item.Magic;

import java.util.Random;

import com.yuto.AMaS.Api.AMaSAPI;
import com.yuto.AMaS.Api.ChantSpell.ChantSpells;
import com.yuto.AMaS.Api.Magic.MagicBook;
import com.yuto.AMaS.Gui.GuiScreenMagicBook;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Grimoire extends MagicBook{
	public float SpineCount;
	public int useCount;
	private boolean Sflag = true;
	private boolean GuiFlag = false;
	/**
	 * <b>
	 * 	魔導書だよ！</br>
	 * 	誰かさん曰く"知ってた？魔導書は鈍器にもなるのよ。"
	 * </b>
	 * @param MPUR 魔力消費量
	 */
	public Grimoire(int MPUR) {
		super(MPUR);
		String name = "Grimoire";
		this.setUnlocalizedName(name);
		this.setCreativeTab(AMaSAPI.AMaSTab);
		this.maxStackSize = 1;
		this.setTextureName("amas:grimoire");
		GameRegistry.registerItem(this, name);
		return;
	}
	@Override
	public boolean hasEffect(ItemStack itemStack) {
		return true;

	}
	@Override
	public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
		//本の角で殴ると痛い！！
		entity.attackEntityFrom(DamageSource.starve, 7.0F);
        return true;
    }
	@Override
	public boolean useMagicPower(EntityPlayer entityPlayer) {
		if(!entityPlayer.capabilities.isCreativeMode) {
			int useMP = Math.round(this.MagicPowerUsageRate);
			if(AMaSAPI.getMagicPowerLevel(entityPlayer) >= useMP){
				AMaSAPI.addMagicPowerExhaustion(entityPlayer, useMP * 4);
				return true;
			}else{
				return false;
			}
		}else {
			return true;
		}
	}
	@Override
	public void onPlayerStoppedUsing(ItemStack itemStack, World world, EntityPlayer entityPlayer, int p_77615_4_)
    {
		if (!world.isRemote) {
		    ChantSpells chantSpells = new ChantSpells();
		    boolean flag = entityPlayer.capabilities.isCreativeMode;
		    if (this.bookGui == null)
		    	this.bookGui = new GuiScreenMagicBook(entityPlayer, itemStack);
		    String spell = ((GuiScreenMagicBook) this.bookGui).getBookPageString();
		    if (this.SpineCount >= 1.0F) {
		    	entityPlayer.addChatMessage(new ChatComponentText(I18n.format("msg.Chanting", spell)));
				if (!((flag || (this.useMagicPower(entityPlayer))) && chantSpells.ChantSpell(spell, entityPlayer))){
					entityPlayer.addChatMessage(new ChatComponentText(I18n.format("msg.ChantingFai", spell)));
				}
		    }
		}
    }
	@SideOnly(Side.CLIENT)
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
		if(entityPlayer.isSneaking()) {
			if (world.isRemote)
				Minecraft.getMinecraft().displayGuiScreen(new GuiScreenMagicBook(entityPlayer, itemStack));
			return itemStack;
		}else {
			if (entityPlayer.capabilities.isCreativeMode || this.useMagicPower(entityPlayer)){
				entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
			}
			return itemStack;
		}
    }
	@Override
	public int getMaxItemUseDuration(ItemStack itemstack) {
		return 72000;
	}
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {
		if(!world.isRemote) {
			if(!entity.isDead && isHeld) {
				EntityPlayer player = (EntityPlayer) entity;
				if(player.isUsingItem()) {
					this.useCount ++;
					float f = (float)this.useCount / 50.0F;
			        f = (f * f + f * 2.0F) / 3.0F;

			        if ((double)f < 0.1D)
			        {
			            return;
			        }

			        if (f > 1.0F)
			        {
			            f = 1.0F;
			        }
			        this.SpineCount = f;
				}else {
					this.useCount = 0;
					this.SpineCount = 0F;
				}
			}else{
				this.useCount = 0;
				this.SpineCount = 0F;
			}
		}
        if(this.SpineCount >= 0.1F) {
        	Random rnd = new Random();
        	// パーティクル発生地点。ブロック上面中心から半径0.8の円周上のランダムな場所
        	double r = 0.1D + rnd.nextDouble();
        	double t = rnd.nextDouble() * 2 * Math.PI;

        	double d0 = entity.posX + r * Math.sin(t);
        	double d1 = entity.posY - 0.8D + rnd.nextDouble();
        	double d2 = entity.posZ + r * Math.cos(t);

        	// パーティクルの移動速度。+0.03Dで上昇する
	        double d3 = d0 - entity.posX + Math.sin(t);
        	double d4 = -0.03D;
        	double d5 = d2 - entity.posZ + Math.cos(t);
        	world.spawnParticle("enchantmenttable", d0, d1, d2, d3, d4, d5);
        }
	}
	@Override
	public boolean getShareTag()
    {
        return true;
    }
}

