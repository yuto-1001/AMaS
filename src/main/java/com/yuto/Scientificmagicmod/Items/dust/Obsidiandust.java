package com.yuto.Scientificmagicmod.Items.dust;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class Obsidiandust extends Item {

	public Obsidiandust(){
        String name = "Obsidiandust";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setTextureName( "scientificmagicmod:obsidian_dust" );

        OreDictionary.registerOre("dustObsidian",this);

        GameRegistry.registerItem( this, name );


        return;
    }
}
