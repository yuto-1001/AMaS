package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemFood;

public class BulbBread extends ItemFood {
	public BulbBread(){
		super(20, 20.0F, false);
		String name = "BulbBread";
        this.setUnlocalizedName( name );
        maxStackSize = 64;
        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setTextureName( "scientificmagicmod:Bbread" );
		GameRegistry.registerItem( this, name );
	}
}
