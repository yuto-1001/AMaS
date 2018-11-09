package com.yuto.MagicScienceExcepiton.Item.MillingBlade;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.Mill.MillingBlade;

import cpw.mods.fml.common.registry.GameRegistry;

public class DiMBlade extends MillingBlade {
	public DiMBlade() {
		super(9999);
		String name = "DiMBlade";

        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );

        this.setTextureName( "magicscienceexception:DiMBlade" );

        GameRegistry.registerItem( this, name );

        return;
	}

	@Override
	public int getOverClock() {
		return 8;
	}

}
