package com.yuto.AMaS.Item;

import com.yuto.AMaS.AMaS;
import com.yuto.AMaS.Api.Magic.MagicBook;
import com.yuto.AMaS.Api.Magic.MagicItem;
import com.yuto.AMaS.Item.Ingots.Redbeaningot;
import com.yuto.AMaS.Item.Magic.Grimoire;
import com.yuto.AMaS.Item.Magic.SpellCard;
import com.yuto.AMaS.Item.Swords.Sheath;
import com.yuto.AMaS.Item.Swords.SwS;

import net.minecraft.item.Item;
import net.minecraftforge.common.util.EnumHelper;

public class AMaSItem {
	public static Item Redbeaningot;
	public static MagicBook Grimoire;
	public static MagicItem SpellCard;
	public static Item Sheath;
	public static Item DiamondSwS;
	public static void registry( AMaS mod )
	{
		Redbeaningot = new Redbeaningot();
		Grimoire = new Grimoire(10);
		SpellCard = new SpellCard(10);
		Sheath = new Sheath();
		DiamondSwS = new SwS(EnumHelper.addToolMaterial("diamond", -2, 1562, 10, 3, 20));
		return;
	}
}
