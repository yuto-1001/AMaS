package com.yuto.MagicScienceExcepiton.Item.Magic;

import java.util.Random;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.Magic.MagicBook;
import com.yuto.MagicScienceExcepiton.Entity.EntityDeathScythe;
import com.yuto.MagicScienceExcepiton.Gui.GuiScreenMagicBook;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class Grimoire extends MagicBook{
	public float SpineCount;
	public float FlippingRight = 0F;
	public float FlippingLeft = 0F;
	public int useCount;
	public EntityPlayer user;
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
		this.setCreativeTab(MagicScienceExcepitonAPI.MagicScienceExceptionTab);
		this.maxStackSize = 1;
		this.setTextureName("magicscienceexception:grimoire");
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
		entity.attackEntityFrom(DamageSource.starve, 7.0F);
        return true;
    }
	@Override
	public boolean useMagicPower(EntityPlayer entityPlayer) {
		if(!entityPlayer.capabilities.isCreativeMode) {
			int useMP = Math.round(this.MagicPowerUsageRate);
			if(MagicScienceExcepitonAPI.getMagicPowerLevel(entityPlayer) >= useMP){
				MagicScienceExcepitonAPI.addMagicPowerExhaustion(entityPlayer, useMP * 4);
				return true;
			}else{
				return false;
			}
		}else {
			return true;
		}
	}
	@Override
	public void onPlayerStoppedUsing(ItemStack p_77615_1_, World p_77615_2_, EntityPlayer p_77615_3_, int p_77615_4_)
    {

        boolean flag = p_77615_3_.capabilities.isCreativeMode;

        if (flag || true)
        {
            float dam = 11.0F;
    		int cooltime = 0;
    		float speed = 2.0F;
    		if(this.SpineCount >= 1.0F) {
	    		EntityDeathScythe Scythe = new EntityDeathScythe(p_77615_2_, p_77615_3_, speed, 0, dam, 0.1, cooltime);
                if(!p_77615_2_.isRemote)
                	p_77615_2_.spawnEntityInWorld(Scythe);
    		}
        }
    }
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer)
    {
		if(entityPlayer.isSneaking()) {
			Minecraft.getMinecraft().displayGuiScreen(new GuiScreenMagicBook(entityPlayer, itemStack));
	        return itemStack;
		}
		ArrowNockEvent event = new ArrowNockEvent(entityPlayer, itemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }

        if (entityPlayer.capabilities.isCreativeMode || true)
        {
            entityPlayer.setItemInUse(itemStack, this.getMaxItemUseDuration(itemStack));
        }


        return itemStack;
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
}

