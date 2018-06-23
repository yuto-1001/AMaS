package com.yuto.Scientificmagicmod.Items.dust;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Diamonddust extends Item{
	public Diamonddust(){
        String name = "Diamonddust";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "scientificmagicmod:diamond_dust" );

        OreDictionary.registerOre("dustDiamond",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
