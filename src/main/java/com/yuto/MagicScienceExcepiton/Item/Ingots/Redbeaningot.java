package com.yuto.MagicScienceExcepiton.Item.Ingots;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
public class Redbeaningot extends Item{
    public Redbeaningot(){

		String name = "Redbeaningot";

        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );

        this.setTextureName( "magicscienceexception:redbean_ingot" );

        OreDictionary.registerOre("ingotRbnium",this);

        GameRegistry.registerItem( this, name );

        return;
    }

}
