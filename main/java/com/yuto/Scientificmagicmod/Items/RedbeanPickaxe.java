package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemPickaxe;

public class RedbeanPickaxe extends ItemPickaxe {

	protected RedbeanPickaxe(ToolMaterial RBP) {
		super(RBP);
		
		String name = "RedbeanPickaxe";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "scientificmagicmod:redbean_pickaxe" );

        GameRegistry.registerItem( this, name );
		
		return;
	}

}
