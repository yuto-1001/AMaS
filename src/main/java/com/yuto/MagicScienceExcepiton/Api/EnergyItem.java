package com.yuto.MagicScienceExcepiton.Api;

import net.minecraft.item.Item;

abstract public class EnergyItem extends Item{
	/**
	 *
	 * @return You must write "How much energy the item has".</br>
	 *  The reference value is 200.</br>
	 *  どれだけのエネルギーをそのアイテムが持っているかを書く。(必ず)</br>
	 *  基準値は200。</br>
	 */
	public abstract int getEnergyTime();
}
