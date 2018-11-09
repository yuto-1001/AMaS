package com.yuto.MagicScienceExcepiton.Item.tool;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemPickaxe;

public class RedbeanPickaxe extends ItemPickaxe {

	public RedbeanPickaxe(ToolMaterial RBP) {
		super(RBP);

		String name = "RedbeanPickaxe";

        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "magicscienceexception:redbean_pickaxe" );

        GameRegistry.registerItem( this, name );

		return;
	}

}
