package com.yuto.MagicScienceExcepiton.Item.MillingBlade;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Api.Mill.MillingBlade;

import cpw.mods.fml.common.registry.GameRegistry;

public class ObMBlade extends MillingBlade {

	public ObMBlade() {
		super(49999);
		String name = "ObMBlade";

        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );

        this.setTextureName( "magicscienceexception:ObMBlade" );

        GameRegistry.registerItem( this, name );

        return;
	}

	@Override
	public int getOverClock() {
		return 8;
	}

}
