package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class Redbeandust extends Item {

	public Redbeandust(){
        String name = "Redbeandust";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "scientificmagicmod:redbean_dust" );

        GameRegistry.registerItem( this, name );


        return;
    }


}
