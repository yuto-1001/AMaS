package com.yuto.MagicScienceExcepiton.Item.Magic;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.Magic.MagicBook;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;

public class Grimoire extends MagicBook {
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
}
