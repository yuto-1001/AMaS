package com.yuto.Scientificmagicmod.Items.tool;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemAxe;

public class RedbeanAxe extends ItemAxe {
	public RedbeanAxe(ToolMaterial RBS){
		super(RBS);
        String name = "RedbeanAxe";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "scientificmagicmod:Redbean_axe" );

        GameRegistry.registerItem( this, name );

        return;
    }
}
