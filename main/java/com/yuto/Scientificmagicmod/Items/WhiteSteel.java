package com.yuto.Scientificmagicmod.Items;

import java.util.List;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class WhiteSteel extends ItemSword {

	public WhiteSteel(ToolMaterial WhiteSteel){
		super(WhiteSteel);
        String name = "WhiteSteel";

        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setUnlocalizedName( name );
        maxStackSize = 1;

        this.setTextureName( "scientificmagicmod:WhiteSteel" );

        GameRegistry.registerItem( this, name );

        return;
    }
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if(isSelected){
			((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(21, 600, 10));
			((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(22, 600, 10));
			((EntityLivingBase)entityIn).addPotionEffect(new PotionEffect(10, 600, 10));
		}
		
	}
	@Override
    public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List itemList) {
        ItemStack itemStack = new ItemStack(this, 1, 0);
        itemStack.addEnchantment(Enchantment.sharpness,5);
        itemList.add(itemStack);
    }
	@Override
    public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
        itemStack.addEnchantment(Enchantment.sharpness,5);
        NBTTagCompound nbt = new NBTTagCompound();
        itemStack.setTagCompound(nbt);
    }
}