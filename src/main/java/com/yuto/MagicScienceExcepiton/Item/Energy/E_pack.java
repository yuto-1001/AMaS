package com.yuto.MagicScienceExcepiton.Item.Energy;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Api.EnergyItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class E_pack extends EnergyItem {
	public E_pack(){
		String name = "E_pack";

        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );

        this.setTextureName( "magicscienceexception:E_pack" );

        GameRegistry.registerItem( this, name );

        return;
	}
	@Override
	public int getEnergyTime() {
		return 20000;
	}

}
