package com.yuto.MagicScienceExcepiton.Item;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Api.EnergyItem;
import com.yuto.MagicScienceExcepiton.Api.Mill.MillingBlade;
import com.yuto.MagicScienceExcepiton.Item.Energy.E_pack;
import com.yuto.MagicScienceExcepiton.Item.Foods.Bulb;
import com.yuto.MagicScienceExcepiton.Item.Foods.BulbBread;
import com.yuto.MagicScienceExcepiton.Item.Foods.Drinks.Beer;
import com.yuto.MagicScienceExcepiton.Item.Ingots.CII;
import com.yuto.MagicScienceExcepiton.Item.Ingots.Redbeaningot;
import com.yuto.MagicScienceExcepiton.Item.MagicSwords.DeathScythe;
import com.yuto.MagicScienceExcepiton.Item.MagicSwords.Kagerou;
import com.yuto.MagicScienceExcepiton.Item.MagicSwords.Kamigoroshi;
import com.yuto.MagicScienceExcepiton.Item.Materials.Crystal;
import com.yuto.MagicScienceExcepiton.Item.MillingBlade.DiMBlade;
import com.yuto.MagicScienceExcepiton.Item.MillingBlade.ObMBlade;
import com.yuto.MagicScienceExcepiton.Item.MillingBlade.RbMBlade;
import com.yuto.MagicScienceExcepiton.Item.dust.Diamonddust;
import com.yuto.MagicScienceExcepiton.Item.dust.Golddust;
import com.yuto.MagicScienceExcepiton.Item.dust.Irondust;
import com.yuto.MagicScienceExcepiton.Item.dust.Obsidiandust;
import com.yuto.MagicScienceExcepiton.Item.dust.Redbeandust;
import com.yuto.MagicScienceExcepiton.Item.dust.Stonedust;
import com.yuto.MagicScienceExcepiton.Item.tool.RedbeanAxe;
import com.yuto.MagicScienceExcepiton.Item.tool.RedbeanPickaxe;
import com.yuto.MagicScienceExcepiton.Item.tool.RedbeanShovel;
import com.yuto.MagicScienceExcepiton.Item.tool.RedbeanSword;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.common.util.EnumHelper;

public class MagicScienceExceptionItem {
	public static Item Redbeandust;
	public static Item Diamonddust;
	public static Item Obsidiandust;
	public static Item Irondust;
	public static Item Golddust;
	public static Item Stonedust;

	public static Item Redbeaningot;
	public static Item CompressedIronIngot;

	public static ItemSword Kamigoroshi;
	public static ItemSword Kagerou;
	public static ItemSword DeathScythe;

	public static Item RedbeanSword;
	public static Item RedbeanAxe;
	public static Item RedbeanPickaxe;
	public static Item RedbeanShovel;

	public static Item Beer;
	public static Item Bulb;
	public static Item BulbBread;

	public static Item Crystal;

	public static EnergyItem E_pack;

	public static MillingBlade RbMBlade;
	public static MillingBlade DiMBlade;
	public static MillingBlade ObMBlade;
	public static void registry( MagicScienceException mod )
	{
		Redbeandust = new Redbeandust();
		Diamonddust = new Diamonddust();
		Obsidiandust = new Obsidiandust();
		Irondust = new Irondust();
		Golddust = new Golddust();
		Stonedust = new Stonedust();

		Redbeaningot = new Redbeaningot();
		CompressedIronIngot = new CII();

		Kamigoroshi = new Kamigoroshi(EnumHelper.addToolMaterial("Kamigoroshi", 5, 0, 30F, 41, 64));
		Kagerou = new Kagerou(EnumHelper.addToolMaterial("Kagerou", 5, 0, 30F, 21, 64));
		DeathScythe = new DeathScythe(EnumHelper.addToolMaterial("DeathScythe", 5, 0, 26F, 30, 64));

		RedbeanSword = new RedbeanSword(EnumHelper.addToolMaterial("RedbeanSword", 5, 100000000, 30F, 11, 64));
		RedbeanAxe = new RedbeanAxe(EnumHelper.addToolMaterial("RedbeanAxe", 5, 100000000, 30F, 8, 64));
		RedbeanPickaxe = new RedbeanPickaxe(EnumHelper.addToolMaterial("RedbeanPickaxe", 5, 100000000, 30F, 4, 64));
		RedbeanShovel = new RedbeanShovel(EnumHelper.addToolMaterial("RedbeanShovel", 5, 100000000, 30F, 4, 64));

		Beer = new Beer(new PotionEffect[]{new PotionEffect(Potion.regeneration.id, 200, 0), new PotionEffect(9,200,0)});
		Bulb = new Bulb();
		BulbBread = new BulbBread();

		Crystal = new Crystal();

		E_pack = new E_pack();

		RbMBlade = new RbMBlade();
		DiMBlade = new DiMBlade();
		ObMBlade = new ObMBlade();
		return;
	}
}
