package com.yuto.Scientificmagicmod.Items.tool;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemSword;

public class RedbeanSword extends ItemSword {
	public RedbeanSword(ToolMaterial RBS){
		super(RBS);
        String name = "RedbeanSword";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "scientificmagicmod:redbean_sword" );

        GameRegistry.registerItem( this, name );

        return;
    }
}
