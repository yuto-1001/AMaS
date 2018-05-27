package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Bulb extends Item {
	public Bulb() {
		String name = "Bulb";

        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );

        this.setTextureName( "scientificmagicmod:Bulb" );

        GameRegistry.registerItem( this, name );

        return;
	}
}
