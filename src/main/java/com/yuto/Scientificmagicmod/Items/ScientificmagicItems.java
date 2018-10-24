package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Api.EnergyItem;
import com.yuto.Scientificmagicmod.Api.Mill.MillingBlade;
import com.yuto.Scientificmagicmod.Items.Energy.E_pack;
import com.yuto.Scientificmagicmod.Items.Foods.Bulb;
import com.yuto.Scientificmagicmod.Items.Foods.BulbBread;
import com.yuto.Scientificmagicmod.Items.Ingots.CII;
import com.yuto.Scientificmagicmod.Items.Ingots.Redbeaningot;
import com.yuto.Scientificmagicmod.Items.MagicSwords.DeathScythe;
import com.yuto.Scientificmagicmod.Items.MagicSwords.Kagerou;
import com.yuto.Scientificmagicmod.Items.Materials.Crystal;
import com.yuto.Scientificmagicmod.Items.MillingBlade.DiMBlade;
import com.yuto.Scientificmagicmod.Items.MillingBlade.ObMBlade;
import com.yuto.Scientificmagicmod.Items.MillingBlade.RbMBlade;
import com.yuto.Scientificmagicmod.Items.dust.Diamonddust;
import com.yuto.Scientificmagicmod.Items.dust.Golddust;
import com.yuto.Scientificmagicmod.Items.dust.Irondust;
import com.yuto.Scientificmagicmod.Items.dust.Obsidiandust;
import com.yuto.Scientificmagicmod.Items.dust.Redbeandust;
import com.yuto.Scientificmagicmod.Items.dust.Stonedust;
import com.yuto.Scientificmagicmod.Items.tool.RedbeanAxe;
import com.yuto.Scientificmagicmod.Items.tool.RedbeanPickaxe;
import com.yuto.Scientificmagicmod.Items.tool.RedbeanShovel;
import com.yuto.Scientificmagicmod.Items.tool.RedbeanSword;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSword;
import net.minecraftforge.common.util.EnumHelper;

public class ScientificmagicItems {
	public static Item Redbeandust;
	public static Item Diamonddust;
	public static Item Obsidiandust;
	public static Item Irondust;
	public static Item Golddust;
	public static Item Stonedust;

	public static Item Redbeaningot;
	public static Item CompressedIronIngot;

	public static ItemSword Kagerou;
	public static ItemSword DeathScythe;

	public static Item RedbeanSword;
	public static Item RedbeanAxe;
	public static Item RedbeanPickaxe;
	public static Item RedbeanShovel;

	public static Item Bulb;
	public static Item BulbBread;

	public static Item Crystal;

	public static EnergyItem E_pack;

	public static MillingBlade RbMBlade;
	public static MillingBlade DiMBlade;
	public static MillingBlade ObMBlade;
	public static void registry( ScientificmagicMod mod )
	{
		Redbeandust = new Redbeandust();
		Diamonddust = new Diamonddust();
		Obsidiandust = new Obsidiandust();
		Irondust = new Irondust();
		Golddust = new Golddust();
		Stonedust = new Stonedust();

		Redbeaningot = new Redbeaningot();
		CompressedIronIngot = new CII();

		Kagerou = new Kagerou(EnumHelper.addToolMaterial("Kagerou", 5, 999999999, 30F, 21, 64));
		DeathScythe = new DeathScythe(EnumHelper.addToolMaterial("DeathScythe", 5, 999999999, 26F, 30, 64));

		RedbeanSword = new RedbeanSword(EnumHelper.addToolMaterial("RedbeanSword", 5, 100000000, 30F, 11, 64));
		RedbeanAxe = new RedbeanAxe(EnumHelper.addToolMaterial("RedbeanAxe", 5, 100000000, 30F, 8, 64));
		RedbeanPickaxe = new RedbeanPickaxe(EnumHelper.addToolMaterial("RedbeanPickaxe", 5, 100000000, 30F, 4, 64));
		RedbeanShovel = new RedbeanShovel(EnumHelper.addToolMaterial("RedbeanShovel", 5, 100000000, 30F, 4, 64));

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
