package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class ScientificmagicItems {
	public static Item Redbeandust;
	public static Item Redbeaningot;
	public static Item CompressedIronIngot;
	public static Item Kagerou;
	public static Item DeathScythe;
	public static Item RedbeanSword;
	public static Item RedbeanAxe;
	public static Item RedbeanPickaxe;
	public static Item RedbeanShovel;
	public static Item Bulb;
	public static Item BulbBread;
	public static void registry( ScientificmagicMod mod )
	{
		Redbeandust = new Redbeandust();
		Redbeaningot = new Redbeaningot();
		CompressedIronIngot = new CII();
		Kagerou = new Kagerou(EnumHelper.addToolMaterial("Kagerou", 5, 999999999, 30F, 21, 64));
		DeathScythe = new DeathScythe(EnumHelper.addToolMaterial("DeathScythe", 5, 999999999, 26F, 30, 64));
		RedbeanSword = new RedbeanSword(EnumHelper.addToolMaterial("RedbeanSword", 5, 100000000, 30F, 11, 64));
		RedbeanAxe = new RedbeanAxe(EnumHelper.addToolMaterial("RedbeanAxe", 5, 100000000, 30F, 8, 64));
		RedbeanPickaxe = new RedbeanPickaxe(EnumHelper.addToolMaterial("RedbeanPickaxe", 5, 100000000, 30F, 4, 64));
		RedbeanShovel = new RedbeanShovel(EnumHelper.addToolMaterial("RedbeanShowball", 5, 100000000, 30F, 4, 64));
		Bulb = new Bulb();
		BulbBread = new BulbBread();
		return;
	}
}
