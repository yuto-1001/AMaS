package com.yuto.MagicScienceExcepiton.Item.Foods.Drinks;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class MagicBeer extends ItemFood {
	/**付与するポーション効果の配列*/
	private final PotionEffect[] effects;
	public MagicBeer(PotionEffect[] effects) {
		super(2, 20, false);
		this.effects = effects;
		this.setCreativeTab(MagicScienceException.MagicScienceExcepiton);
		this.setUnlocalizedName("MagicBeer");
		this.setTextureName("magicscienceexception:Beer");
		this.setAlwaysEdible();
		GameRegistry.registerItem(this, "MagicBeer");
	}
	@Override
	public int getMaxItemUseDuration(ItemStack itemStack) {
		return 15;
	}
	@Override
	public ItemStack onEaten(ItemStack itemStack, World world, EntityPlayer player) {
		//
		if (!player.capabilities.isCreativeMode) {
			--itemStack.stackSize;
		}
		//つけるポーション効果の数だけ繰り返す。
		for (int i = 0; i < effects.length; i ++) {
			//サーバー側の処理で、情報が正常なら処理を続ける。
			if (!world.isRemote && effects[i] != null && effects[i].getPotionID() > 0) {
				//即時回復なら、
				if (effects[i].getPotionID() == Potion.heal.id) {
					//ID・効果時間を固定して、
					PotionEffect effect = new PotionEffect(Potion.heal.id, 1, effects[i].getAmplifier());
					//プレイヤーに付与。
					player.addPotionEffect(effect);
				//即時回復以外なら、
				} else {
					//効果時間のみ固定して、
					PotionEffect effect = new PotionEffect(effects[i].getPotionID(), effects[i].getDuration(), effects[i].getAmplifier());
					//プレイヤーに付与。
					player.addPotionEffect(effect);
				}
			}
		}
		MagicScienceExcepitonAPI.addMagicPowerStats(player, 10, 100);
		world.playSoundAtEntity(player, "random.burp", 0.5F, world.rand.nextFloat() * 0.1F + 0.9F);
		return itemStack;
	}
}
