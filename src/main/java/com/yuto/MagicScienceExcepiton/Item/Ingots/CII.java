package com.yuto.MagicScienceExcepiton.Item.Ingots;

import com.yuto.MagicScienceExcepiton.MagicScienceException;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class CII extends Item {
	public CII(){

		String name = "CompressedIronIngot";

        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );

        this.setTextureName( "magicscienceexception:CII" );

        GameRegistry.registerItem( this, name );

        return;
    }
}
