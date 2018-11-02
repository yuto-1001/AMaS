package com.yuto.MagicScienceExcepiton.Item.MagicSwords;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.MagicSword.MagicSword;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class Kagerou extends MagicSword {

    int Attack;

    boolean ench = false;

	protected int count = 0;

	public static int MPUR = 100;

	public Kagerou(ToolMaterial kagerou) {
		super(kagerou, MPUR);
		String name = "Kagerou";

        this.setCreativeTab(MagicScienceException.MagicScienceExcepiton);
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "magicscienceexception:Kagerou" );

        GameRegistry.registerItem( this, name );

        return;
	}

	@Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {

		if(isHeld){
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(8, 0, 10));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(22, 0, 10));
		}
		if(this.count == 0){
			this.ench = false;
		}
		if(this.count >= 1){
			if(this.ench == false){
				itemStack.addEnchantment(Enchantment.knockback,5);
				this.ench = true;
			}
		}
		if(this.count >= 2){
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(1, 600, 5));
		}
		if(this.count >= 3){
		}
		if(this.count >= 4){
		}
		if(this.count >= 5){
		}
		if(this.count >= 6){
		}
		if(this.count >= 7){
		}
		if(this.count >= 8){
		}
		if(this.count >= 9){
		}
		if(this.count >= 10){
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(9, 300, 10));
		}
		if(this.count >= 11){
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(20, 300, 10));
		}
		if(this.count >= 12){
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(15, 300, 10));
		}
    }
	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer){
		if(!world.isRemote && useMagicPower(entityplayer)) {
			if(this.count < 11){
				this.count ++;
			}else if(entityplayer.isSneaking() && this.count <= 11){
				this.count = 12;
			}else{
				this.count = 0;
			}
			String msg = "LunaticLevel:" + String.valueOf(this.count + 1);
			entityplayer.addChatMessage(new ChatComponentText(msg));
		}
		return itemstack ;

	}

	@Override
	public boolean useMagicPower(EntityPlayer entityPlayer) {
		int SP = MagicScienceExcepitonAPI.getSplitPowerLevel(entityPlayer);
		int useMP = Math.round(this.MagicPowerUsageRate /SP);
		if(MagicScienceExcepitonAPI.getMagicPowerLevel(entityPlayer) >= useMP){
			MagicScienceExcepitonAPI.addMagicPowerExhaustion(entityPlayer, useMP * 4);
			return true;
		}else{
			return false;
		}
	}

}
