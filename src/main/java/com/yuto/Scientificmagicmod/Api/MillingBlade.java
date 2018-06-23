package com.yuto.Scientificmagicmod.Api;

import net.minecraft.item.Item;

public abstract class MillingBlade extends Item{
	/**
	 *
	 * @return Overclocking at milling.(How many times to make)</br>
	 * 	オーバクロック（何倍にするか）を返す。</br>
	 */
	public abstract int getOverClock();
}
