package com.yuto.MagicScienceExcepiton.Item.MagicSwords;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.MagicSword.MagicSword;
import com.yuto.MagicScienceExcepiton.Entity.EntityDeathScythe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class DeathScythe extends MagicSword {

	public DeathScythe(ToolMaterial DS) {
		super(DS, 100);
		String name = "DeathScythe";

        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "magicscienceexception:DeathScythe" );

        GameRegistry.registerItem( this, name );

        return;
	}
	@Override
	public EnumAction getItemUseAction(ItemStack itemstack)
	{
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		boolean creative = entityplayer.capabilities.isCreativeMode;
		float dam = 11.0F;
		int cooltime = 0;
		float speed = 2.0F;
		EntityDeathScythe Scythe = new EntityDeathScythe(world, entityplayer, speed, 0, dam, 0.1, cooltime);
		if (!world.isRemote && useMagicPower(entityplayer)) {
			world.spawnEntityInWorld(Scythe);
			if(!creative){
				itemstack.damageItem(1, entityplayer);
			}
		}
		return itemstack;
	}
	@Override
	public boolean useMagicPower(EntityPlayer entityPlayer) {
		if(!entityPlayer.capabilities.isCreativeMode) {
			int SP = MagicScienceExcepitonAPI.getSplitPowerLevel(entityPlayer);
			if(SP == 0) return false;
			int useMP = Math.round(this.MagicPowerUsageRate / SP);
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
