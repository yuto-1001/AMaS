package com.yuto.MagicScienceExcepiton.Item.MagicSwords;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.MagicSword.MagicSword;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;

public class Kamigoroshi extends MagicSword {
	public static int MPUR = 100;
	public Kamigoroshi(ToolMaterial material) {
		super(material, MPUR);

		String name = "Kamigoroshi";
		this.setCreativeTab(MagicScienceException.MagicScienceExcepiton);
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "magicscienceexception:Kamigoroshi" );

        GameRegistry.registerItem( this, name );
        return;
	}

	@Override
	public boolean useMagicPower(EntityPlayer entityPlayer) {
		int SP = MagicScienceExcepitonAPI.getSplitPowerLevel(entityPlayer);
		int useMP = Math.round(this.MagicPowerUsageRate /SP);
		if(MagicScienceExcepitonAPI.getMagicPowerLevel(entityPlayer) >= useMP){
			MagicScienceExcepitonAPI.addMagicPowerExhaustion(entityPlayer, useMP);
			return true;
		}else{
			return false;
		}
	}

}
