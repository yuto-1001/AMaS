package com.yuto.Scientificmagicmod.Items.dust;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Golddust extends Item {
	public Golddust(){
        String name = "Golddust";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "scientificmagicmod:gold_dust" );

        OreDictionary.registerOre("dustGold",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
