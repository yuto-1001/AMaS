package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Entity.EntityDeathScythe;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.world.World;

public class DeathScythe extends ItemSword {

	public DeathScythe(ToolMaterial DS) {
		super(DS);
		String name = "DeathScythe";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "scientificmagicmod:DeathScythe" );

        GameRegistry.registerItem( this, name );

        return;
	}
	@Override
	public EnumAction getItemUseAction(ItemStack itemstack)
	{
		return EnumAction.bow;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		boolean creative = entityplayer.capabilities.isCreativeMode;
		float dam = 11.0F;
		int cooltime = 0;
		float speed = 5.0F;
		EntityDeathScythe Scythe = new EntityDeathScythe(world, entityplayer, speed, 0, dam, 0.1, cooltime);
		if (!world.isRemote) {
			world.spawnEntityInWorld(Scythe);
			if(!creative){
				itemstack.damageItem(1, entityplayer);
			}
		}
		return itemstack;
	}
}
