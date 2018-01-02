package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class CII extends Item {
	public CII(){

		String name = "CompressedIronIngot";

        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );

        this.setTextureName( "scientificmagicmod:CII" );

        GameRegistry.registerItem( this, name );

        return;
    }
}
