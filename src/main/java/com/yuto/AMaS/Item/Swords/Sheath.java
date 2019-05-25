package com.yuto.AMaS.Item.Swords;

import com.yuto.AMaS.Api.AMaSAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Sheath extends Item {
	public Sheath() {
		String name = "Sheath";

        this.setUnlocalizedName( name );
        this.maxStackSize = 64;
        this.setCreativeTab( AMaSAPI.AMaSTab );
        this.setTextureName( "amas:sheath" );

        GameRegistry.registerItem( this, name );

        return;
	}
}
