package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Api.MillingBlade;

import cpw.mods.fml.common.registry.GameRegistry;

public class ObMBlade extends MillingBlade {

	public ObMBlade() {
		super(49999);
		String name = "ObMBlade";

        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );

        this.setTextureName( "scientificmagicmod:ObMBlade" );

        GameRegistry.registerItem( this, name );

        return;
	}

	@Override
	public int getOverClock() {
		return 8;
	}

}
