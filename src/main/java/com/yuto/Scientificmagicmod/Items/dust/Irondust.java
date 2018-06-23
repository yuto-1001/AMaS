package com.yuto.Scientificmagicmod.Items.dust;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Irondust extends Item{
	public Irondust(){
        String name = "Irondust";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "scientificmagicmod:iron_dust" );

        OreDictionary.registerOre("dustIron",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
