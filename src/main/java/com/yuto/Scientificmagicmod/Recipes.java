package com.yuto.Scientificmagicmod;

import com.yuto.Scientificmagicmod.Block.ScientificmagicBlock;
import com.yuto.Scientificmagicmod.Items.ScientificmagicItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {
	static ItemStack craftedItem2 = new ItemStack(ScientificmagicItems.Kagerou);
	public static void registry(){
		GameRegistry.addSmelting(new ItemStack(ScientificmagicItems.Redbeandust), new ItemStack(ScientificmagicItems.Redbeaningot,1), 0.7F);

		GameRegistry.addSmelting(new ItemStack(ScientificmagicBlock.Redbeanore), new ItemStack(ScientificmagicItems.Redbeaningot,1), 0.7F);

		GameRegistry.addRecipe(
	            new ItemStack( ScientificmagicItems.RedbeanPickaxe, 1 ),
	            new Object[] {
	                "xxx",
	                " y ",
	                " y ",
	                'x', ScientificmagicItems.Redbeaningot,
	                'y', Items.stick
	                }
	            );

		GameRegistry.addRecipe(
	            new ItemStack( ScientificmagicItems.RedbeanSword, 1 ),
	            new Object[] {
	                " x ",
	                " x ",
	                " y ",
	                'x', ScientificmagicItems.Redbeaningot,
	                'y', Items.stick
	                }
	            );

		GameRegistry.addRecipe(
	            new ItemStack( ScientificmagicItems.RedbeanShovel, 1 ),
	            new Object[] {
	                " x ",
	                " y ",
	                " y ",
	                'x', ScientificmagicItems.Redbeaningot,
	                'y', Items.stick
	                }
	            );

		GameRegistry.addRecipe(
				new ItemStack ( ScientificmagicItems.CompressedIronIngot,1),
				new Object[] {
						"xyx",
						"yxy",
						"xyx",
						'x',ScientificmagicItems.Redbeaningot,
						'y',Blocks.iron_block
				}
				);


        GameRegistry.addRecipe(
        		craftedItem2,
        		new Object[] {
        				"  x",
        				"yx ",
        				"yy ",
        				'x',ScientificmagicItems.Redbeaningot,
        				'y',Items.iron_ingot
        		}
        		);


	}

}
