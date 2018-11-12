package com.yuto.MagicScienceExcepiton.Item.MagicSwords;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.Magic.MagicSword;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Kamigoroshi extends MagicSword {
	public static int MPUR = 100;
	public static boolean isRightClick = false;
	public int rightClicktimeCount = 0;
	public Kamigoroshi(ToolMaterial material) {
		super(material, MPUR);

		String name = "Kamigoroshi";
		this.setCreativeTab(MagicScienceExcepitonAPI.MagicScienceExceptionTab);
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "magicscienceexception:Kamigoroshi" );

        GameRegistry.registerItem( this, name );
        return;
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
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {
		if(!isHeld) {
			this.isRightClick = false;
		}
		if(this.isRightClick) {
			this.isRightClick = this.useMagicPower((EntityPlayer) entity);
		}
	}

	@Override
	public ItemStack onItemRightClick(ItemStack p_77659_1_, World p_77659_2_, EntityPlayer p_77659_3_)
    {
		this.isRightClick = true;
		((EntityPlayer) p_77659_3_).setItemInUse(p_77659_1_, this.getMaxItemUseDuration(p_77659_1_));
		return p_77659_1_;
    }
}
