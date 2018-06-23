package com.yuto.Scientificmagicmod.Crafting;

import com.yuto.Scientificmagicmod.Block.ScientificmagicBlock;
import com.yuto.Scientificmagicmod.Items.ScientificmagicItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {
	public static void registry(){
		GameRegistry.addSmelting(new ItemStack(ScientificmagicItems.Redbeandust, 2), new ItemStack(ScientificmagicItems.Redbeaningot,1), 0.7F);

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
				new ItemStack(ScientificmagicItems.Crystal, 1, 0),
				new Object[]{
						"rGr",
						"RDR",
						"rGr",
						'r',Items.redstone,
						'G',Items.gold_ingot,
						'D',Items.diamond,
						'R',ScientificmagicItems.Redbeaningot
				}

				);
		GameRegistry.addRecipe(
				new ItemStack(ScientificmagicBlock.PowderMill, 1),
				new Object[]{
						"IPI",
						"R R",
						"ICI",
						'I',Items.iron_ingot,
						'P',Blocks.piston,
						'C',ScientificmagicItems.Crystal,
						'R',ScientificmagicItems.Redbeaningot
				}

				);
	}

}
