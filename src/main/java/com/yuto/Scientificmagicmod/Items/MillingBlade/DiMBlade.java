package com.yuto.Scientificmagicmod.Items.MillingBlade;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Api.MillingBlade;

import cpw.mods.fml.common.registry.GameRegistry;

public class DiMBlade extends MillingBlade {
	public DiMBlade() {
		super(9999);
		String name = "DiMBlade";

        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );

        this.setTextureName( "scientificmagicmod:DiMBlade" );

        GameRegistry.registerItem( this, name );

        return;
	}

	@Override
	public int getOverClock() {
		return 8;
	}

}
