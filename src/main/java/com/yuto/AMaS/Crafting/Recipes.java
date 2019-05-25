package com.yuto.AMaS.Crafting;

import com.yuto.AMaS.Block.AMaSBlock;
import com.yuto.AMaS.Item.AMaSItem;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;

public class Recipes {

	public static void registry(){
		GameRegistry.addSmelting(new ItemStack(AMaSBlock.Redbeanore), new ItemStack(AMaSItem.Redbeaningot,1), 0.7F);

	}

}
