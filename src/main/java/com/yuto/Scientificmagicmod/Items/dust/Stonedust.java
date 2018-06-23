package com.yuto.Scientificmagicmod.Items.dust;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Stonedust extends Item{
	public Stonedust(){
        String name = "Stonedust";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "scientificmagicmod:stone_dust" );

        OreDictionary.registerOre("dustStone",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
