package com.yuto.MagicScienceExcepiton.Item.tool;

import com.yuto.MagicScienceExcepiton.MagicScienceException;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemSword;

public class RedbeanSword extends ItemSword {
	public RedbeanSword(ToolMaterial RBS){
		super(RBS);
        String name = "RedbeanSword";

        this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "magicscienceexception:redbean_sword" );

        GameRegistry.registerItem( this, name );

        return;
    }
}