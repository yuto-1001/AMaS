package com.yuto.MagicScienceExcepiton;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Item.MagicScienceExceptionItem;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MSECreativeTab {

	public static void initCreativeTabs() {
		MagicScienceExcepitonAPI.MagicScienceExceptionTab = new MagicScienceExceptionCreativeTab("MagicScienceExcepitontab");
	}

	private static class MagicScienceExceptionCreativeTab extends CreativeTabs {

		public MagicScienceExceptionCreativeTab(String lable) {
			super(lable);
		}

		@Override
		public Item getTabIconItem() {
			return MagicScienceExceptionItem.Redbeaningot;
		}

	}

}
