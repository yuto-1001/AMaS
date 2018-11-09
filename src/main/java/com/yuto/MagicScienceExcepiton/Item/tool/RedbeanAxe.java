package com.yuto.MagicScienceExcepiton.Item.tool;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemAxe;

public class RedbeanAxe extends ItemAxe {
	public RedbeanAxe(ToolMaterial RBS){
		super(RBS);
        String name = "RedbeanAxe";

        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "magicscienceexception:Redbean_axe" );

        GameRegistry.registerItem( this, name );

        return;
    }
}
