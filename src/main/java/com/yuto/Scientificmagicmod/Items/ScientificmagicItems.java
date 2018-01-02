package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraftforge.common.util.EnumHelper;

public class ScientificmagicItems {
	public static Item Redbeandust;
	public static Item Redbeaningot;
	public static Item CompressedIronIngot;
	public static Item BlackSteel;
	public static Item WhiteSteel;
	public static Item Kagerou;
	public static Item RedbeanSword;
	public static Item RedbeanAxe;
	public static Item RedbeanPickaxe;
	public static Item RedbeanShovel;
	public static Item Bulb_w;
	public static Item Bulb_r;
	public static Item Bulb_b;
	public static Item Bulb_bl;
	public static ArmorMaterial RBA;
	public static Item RBH;
	public static Item RBC;
	public static Item RBL;
	public static Item RBB;
	public static void registry( ScientificmagicMod mod )
	{
		Redbeandust = new Redbeandust();
		Redbeaningot = new Redbeaningot();
		CompressedIronIngot = new CII();
		BlackSteel = new BlackSteel(EnumHelper.addToolMaterial("BlackSteel", 5, 0, 30F, 30, 64));
		WhiteSteel = new WhiteSteel(EnumHelper.addToolMaterial("WhiteSteel", 5, 0, 30F, 20, 64));
		Kagerou = new Kagerou(EnumHelper.addToolMaterial("Kagerou", 5, 1, 30F, 21, 64));
		RedbeanSword = new RedbeanSword(EnumHelper.addToolMaterial("RedbeanSword", 5, 100000000, 30F, 11, 64));
		RedbeanAxe = new RedbeanAxe(EnumHelper.addToolMaterial("RedbeanAxe", 5, 100000000, 30F, 8, 64));
		RedbeanPickaxe = new RedbeanPickaxe(EnumHelper.addToolMaterial("RedbeanPickaxe", 5, 100000000, 30F, 4, 64));
		RedbeanShovel = new RedbeanShovel(EnumHelper.addToolMaterial("RedbeanShowball", 5, 100000000, 30F, 4, 64));
		Bulb_w = new Bulb_w();
		Bulb_r = new Bulb_r();
		Bulb_b = new Bulb_b();
		Bulb_bl = new Bulb_bl();
		RBA = EnumHelper.addArmorMaterial("ALUMINIUMARMOR", 33, new int[] { 10, 10, 10, 10 }, 10);
		RBH = new RedbeanArmor(0)
				.setUnlocalizedName("RedbeanHelmet")
				.setTextureName("scientificmagicmod:Redbean_helmet");
		GameRegistry.registerItem(RBH, "RedbeanHelmet");
		RBC = new RedbeanArmor(1)
				.setUnlocalizedName("RedbeanChest")
				.setTextureName("scientificmagicmod:Redbean_chestplate");
		GameRegistry.registerItem(RBC, "RedbeanChest");
		RBL = new RedbeanArmor(2)
				.setUnlocalizedName("RedbeanLeggings")
				.setTextureName("scientificmagicmod:Redbean_leggings");
		GameRegistry.registerItem(RBL, "RedbeanLeggings");
		RBB = new RedbeanArmor(3)
				.setUnlocalizedName("RedbeanBoots")
				.setTextureName("scientificmagicmod:Redbaen_boots");
		GameRegistry.registerItem(RBB, "RedbeanBoots");
		return;
	}
}
