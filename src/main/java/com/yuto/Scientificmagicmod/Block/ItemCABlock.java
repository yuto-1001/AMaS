package com.yuto.Scientificmagicmod.Block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
public class ItemCABlock extends ItemBlockWithMetadata {
	public ItemCABlock(Block block) {
		super(block, block);
	}
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return this.getUnlocalizedName() + "." + itemStack.getItemDamage();
	}
}