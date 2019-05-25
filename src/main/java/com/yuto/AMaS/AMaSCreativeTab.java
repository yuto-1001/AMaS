package com.yuto.AMaS;

import com.yuto.AMaS.Api.AMaSAPI;
import com.yuto.AMaS.Item.AMaSItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class AMaSCreativeTab {

	public static void initCreativeTabs() {
		AMaSAPI.AMaSTab = new AMaSTab("AMaSTab");
	}

	private static class AMaSTab extends CreativeTabs {

		public AMaSTab(String lable) {
			super(lable);
		}

		@Override
		public Item getTabIconItem() {
			return AMaSItem.Redbeaningot;
		}

	}

}
