package com.yuto.MagicScienceExcepiton.Crafting;

import com.yuto.MagicScienceExcepiton.Block.MagicScienceExceptionBlock;
import com.yuto.MagicScienceExcepiton.Item.MagicScienceExceptionItem;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class Recipes {

	public static void registry(){
		GameRegistry.addSmelting(new ItemStack(MagicScienceExceptionItem.Redbeandust), new ItemStack(MagicScienceExceptionItem.Redbeaningot,1), 1.0F);
		GameRegistry.addSmelting(new ItemStack(MagicScienceExceptionItem.Diamonddust), new ItemStack(Items.diamond,1), 1.0F);
		GameRegistry.addSmelting(new ItemStack(MagicScienceExceptionItem.Obsidiandust), new ItemStack(Blocks.obsidian,1), 0.7F);
		GameRegistry.addSmelting(new ItemStack(MagicScienceExceptionItem.Irondust), new ItemStack(Items.iron_ingot,1), 0.5F);
		GameRegistry.addSmelting(new ItemStack(MagicScienceExceptionItem.Golddust), new ItemStack(Items.gold_ingot,1), 0.4F);
		GameRegistry.addSmelting(new ItemStack(MagicScienceExceptionItem.Stonedust), new ItemStack(Blocks.stone,1), 0.3F);

		GameRegistry.addSmelting(new ItemStack(MagicScienceExceptionBlock.Redbeanore), new ItemStack(MagicScienceExceptionItem.Redbeaningot,1), 0.7F);

		GameRegistry.addRecipe(
	            new ItemStack( MagicScienceExceptionItem.RedbeanPickaxe, 1 ),
	            new Object[] {
	                "xxx",
	                " y ",
	                " y ",
	                'x', MagicScienceExceptionItem.Redbeaningot,
	                'y', Items.stick
	                }
	            );

		GameRegistry.addRecipe(
	            new ItemStack( MagicScienceExceptionItem.RedbeanSword, 1 ),
	            new Object[] {
	                " x ",
	                " x ",
	                " y ",
	                'x', MagicScienceExceptionItem.Redbeaningot,
	                'y', Items.stick
	                }
	            );

		GameRegistry.addRecipe(
	            new ItemStack( MagicScienceExceptionItem.RedbeanShovel, 1 ),
	            new Object[] {
	                " x ",
	                " y ",
	                " y ",
	                'x', MagicScienceExceptionItem.Redbeaningot,
	                'y', Items.stick
	                }
	            );

		GameRegistry.addRecipe(
				new ItemStack ( MagicScienceExceptionItem.CompressedIronIngot,1),
				new Object[] {
						"xyx",
						"yxy",
						"xyx",
						'x',MagicScienceExceptionItem.Redbeaningot,
						'y',Blocks.iron_block
					}
				);
		GameRegistry.addRecipe(
				new ItemStack(MagicScienceExceptionItem.Crystal, 1, 0),
				new Object[]{
						"rGr",
						"RDR",
						"rGr",
						'r',Items.redstone,
						'G',Items.gold_ingot,
						'D',Items.diamond,
						'R',MagicScienceExceptionItem.Redbeaningot
				}

				);
		GameRegistry.addRecipe(
				new ItemStack(MagicScienceExceptionBlock.PowderMill, 1),
				new Object[]{
						"IPI",
						"R R",
						"ICI",
						'I',Items.iron_ingot,
						'P',Blocks.piston,
						'C',MagicScienceExceptionItem.Crystal,
						'R',MagicScienceExceptionItem.Redbeaningot
				}

				);
	}

}
