package com.yuto.Scientificmagicmod.Items.MillingBlade;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Api.Mill.MillingBlade;

import cpw.mods.fml.common.registry.GameRegistry;

public class RbMBlade extends MillingBlade {
	public RbMBlade() {
		super(999999);
		String name = "RbMBlade";

        this.setUnlocalizedName( name );

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );

        this.setTextureName( "scientificmagicmod:RbMBlade" );

        GameRegistry.registerItem( this, name );

        return;
	}

	@Override
	public int getOverClock() {
		return 16;
	}

}
