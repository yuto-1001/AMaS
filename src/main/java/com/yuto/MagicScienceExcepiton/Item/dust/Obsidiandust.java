package com.yuto.MagicScienceExcepiton.Item.dust;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Obsidiandust extends Item {

	public Obsidiandust(){
        String name = "Obsidiandust";

        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "magicscienceexception:obsidian_dust" );

        OreDictionary.registerOre("dustObsidian",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
