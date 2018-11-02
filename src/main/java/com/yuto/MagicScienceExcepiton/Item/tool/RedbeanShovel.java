package com.yuto.MagicScienceExcepiton.Item.tool;

import java.util.Set;

import com.google.common.collect.Sets;
import com.yuto.MagicScienceExcepiton.MagicScienceException;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTool;

public class RedbeanShovel extends ItemTool {

	private static final Set canHarvestBlock = Sets.newHashSet(new Item[] { Items.diamond_shovel });
	public RedbeanShovel(ToolMaterial RBS) {
		super(4, RBS, canHarvestBlock);
		String name = "RedbeanShowball";

        this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "magicscienceexception:redbean_showball" );

        GameRegistry.registerItem( this, name );

		return;
	}

}
