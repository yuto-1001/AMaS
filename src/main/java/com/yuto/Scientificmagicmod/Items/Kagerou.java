package com.yuto.Scientificmagicmod.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemSword;

public class Kagerou extends ItemSword {

	public Kagerou(ToolMaterial kagerou) {
		super(kagerou);
		String name = "Kagerou";

        this.setCreativeTab(null);
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "scientificmagicmod:Kagerou" );

        GameRegistry.registerItem( this, name );

        return;
	}
}
