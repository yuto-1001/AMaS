package com.yuto.Scientificmagicmod.Items.Energy;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Api.EnergyItem;

import cpw.mods.fml.common.registry.GameRegistry;

public class E_pack extends EnergyItem {
	public E_pack(){
		String name = "E_pack";

        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );

        this.setTextureName( "scientificmagicmod:E_pack" );

        GameRegistry.registerItem( this, name );

        return;
	}
	@Override
	public int getEnergyTime() {
		return 20000;
	}

}
