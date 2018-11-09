package com.yuto.MagicScienceExcepiton.Item.dust;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Diamonddust extends Item{
	public Diamonddust(){
        String name = "Diamonddust";

        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "magicscienceexception:diamond_dust" );

        OreDictionary.registerOre("dustDiamond",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
