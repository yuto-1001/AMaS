package com.yuto.Scientificmagicmod;

import com.yuto.Scientificmagicmod.Block.ScientificmagicBlock;
import com.yuto.Scientificmagicmod.Items.ScientificmagicItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Recipes {
	static ItemStack craftedItem = new ItemStack(ScientificmagicItems.BlackSteel);
	static ItemStack craftedItem1 = new ItemStack(ScientificmagicItems.WhiteSteel);
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

		craftedItem.addEnchantment(Enchantment.knockback, 5);
        GameRegistry.addRecipe(
        		craftedItem,
				new Object[] {
						"  x",
						"yx ",
						"yy ",
						'x',ScientificmagicItems.CompressedIronIngot,
						'y',ScientificmagicItems.Redbeaningot,
				}
				);
        craftedItem1.addEnchantment(Enchantment.sharpness, 5);
        GameRegistry.addRecipe(
        		craftedItem1,
				new Object[] {
						"  x",
						"yx ",
						"yy ",
						'x',Items.iron_ingot,
						'y',ScientificmagicItems.Redbeaningot,
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
		GameRegistry.addShapelessRecipe(new ItemStack(ScientificmagicBlock.CA_w),
				new ItemStack(Items.dye,1,15), ScientificmagicItems.Bulb_w);

	}
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player) {
		craftedItem.addEnchantment(Enchantment.knockback,5);
    }
	public void onCreated1(ItemStack itemStack, World world, EntityPlayer player) {
		craftedItem1.addEnchantment(Enchantment.sharpness,5);
    }
	public void onCreated2(ItemStack itemStack, World world, EntityPlayer player) {
		try{
			  Thread.sleep(10000);
			  craftedItem2.addEnchantment(Enchantment.sharpness,1);
			  Thread.sleep(10000);
			  craftedItem2.addEnchantment(Enchantment.fireAspect,3);
			  Thread.sleep(10000);
			  craftedItem2.addEnchantment(Enchantment.knockback,5);
			}catch(InterruptedException e){}

    }
}
