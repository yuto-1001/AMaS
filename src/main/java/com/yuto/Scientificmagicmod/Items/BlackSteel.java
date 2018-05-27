package com.yuto.Scientificmagicmod.Items;

import java.util.List;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class BlackSteel extends ItemSword {
	ItemStack craftedItem = new ItemStack(ScientificmagicItems.BlackSteel);
	public BlackSteel(ToolMaterial BlackSteel){
		super(BlackSteel);
        String name = "BlackSteel";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "scientificmagicmod:BlackSteel" );

        GameRegistry.registerItem( this, name );

        return;
    }
	
	@Override
    public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {
		if(isHeld){
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(16, 0, 1));
			((EntityLivingBase)entity).addPotionEffect(new PotionEffect(1, 0, 10));
			
		}
    }
	
	@Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1, 0);
        itemStack.addEnchantment(Enchantment.knockback,5);
        itemList.add(itemStack);
    }
	
    
	
}