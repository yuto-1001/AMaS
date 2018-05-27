package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class RedbeanArmor extends ItemArmor{
	public RedbeanArmor(int type) {
		super(ScientificmagicItems.RBA, 0,type);
		this.setCreativeTab(ScientificmagicMod.Scientificmagicmod);
	}
 
	@Override
	public String getArmorTexture(ItemStack itemStack, Entity entity, int slot, String type) {
		if (this.armorType == 2) {
			return "scientificmagicmod:textures/models/armor/rb_layer_2.png";
		}
		return "scientificmagicmod:textures/models/armor/rb_layer_1.png";
	}

}
