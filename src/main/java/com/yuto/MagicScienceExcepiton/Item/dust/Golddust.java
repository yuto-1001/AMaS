package com.yuto.MagicScienceExcepiton.Item.dust;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Golddust extends Item {
	public Golddust(){
        String name = "Golddust";

        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "magicscienceexception:gold_dust" );

        OreDictionary.registerOre("dustGold",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
