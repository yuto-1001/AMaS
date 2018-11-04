package com.yuto.MagicScienceExcepiton.Item.MillingBlade;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Api.Mill.MillingBlade;

import cpw.mods.fml.common.registry.GameRegistry;

public class RbMBlade extends MillingBlade {
	public RbMBlade() {
		super(999999);
		String name = "RbMBlade";

        this.setUnlocalizedName( name );

        this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );

        this.setTextureName( "magicscienceexception:RbMBlade" );

        GameRegistry.registerItem( this, name );

        return;
	}

	@Override
	public int getOverClock() {
		return 16;
	}

}