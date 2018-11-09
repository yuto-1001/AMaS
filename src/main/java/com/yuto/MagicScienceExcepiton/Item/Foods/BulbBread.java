package com.yuto.MagicScienceExcepiton.Item.Foods;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;

public class BulbBread extends ItemFood {
	public BulbBread(){
		super(20, 20.0F, false);
		String name = "BulbBread";
        this.setUnlocalizedName( name );
        maxStackSize = 64;
        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
        this.setTextureName( "magicscienceexception:Bbread" );
		GameRegistry.registerItem( this, name );
	}
}
