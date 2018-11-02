package com.yuto.MagicScienceExcepiton.Item.dust;

import com.yuto.MagicScienceExcepiton.MagicScienceException;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Stonedust extends Item{
	public Stonedust(){
        String name = "Stonedust";

        this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "magicscienceexception:stone_dust" );

        OreDictionary.registerOre("dustStone",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
