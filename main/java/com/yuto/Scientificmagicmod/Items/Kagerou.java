package com.yuto.Scientificmagicmod.Items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

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
	@Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {

    }
}
