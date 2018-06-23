package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
public class Redbeaningot extends Item{
    public Redbeaningot(){

		String name = "Redbeaningot";

        this.setUnlocalizedName( name );
        maxStackSize = 64;

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );

        this.setTextureName( "scientificmagicmod:redbean_ingot" );

        OreDictionary.registerOre("ingotRbnium",this);

        GameRegistry.registerItem( this, name );

        return;
    }

}
