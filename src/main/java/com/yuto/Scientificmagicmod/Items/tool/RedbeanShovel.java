package com.yuto.Scientificmagicmod.Items.tool;

import java.util.Set;

import com.google.common.collect.Sets;
import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

public class RedbeanShovel extends ItemTool {

	private static final Set canHarvestBlock = Sets.newHashSet(new Item[] { Items.diamond_shovel });
	public RedbeanShovel(ToolMaterial RBS) {
		super(4, RBS, canHarvestBlock);
		String name = "RedbeanShowball";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "scientificmagicmod:redbean_showball" );

        GameRegistry.registerItem( this, name );

		return;
	}

}
