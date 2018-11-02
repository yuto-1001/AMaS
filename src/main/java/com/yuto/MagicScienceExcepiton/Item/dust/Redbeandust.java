package com.yuto.MagicScienceExcepiton.Item.dust;

import com.yuto.MagicScienceExcepiton.MagicScienceException;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Redbeandust extends Item {

	public Redbeandust(){
        String name = "Redbeandust";

        this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "magicscienceexception:redbean_dust" );

        OreDictionary.registerOre("dustRbnium",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
