package com.yuto.AMaS.Api.Magic;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public abstract class MagicItem extends Item {
	public int MagicPowerUsageRate;
	public GuiScreen bookGui;
	public MagicItem(int MPUR) {
		this.MagicPowerUsageRate = MPUR;
	}
	/**
     * useMagicPower 魔力を使います。</br>
     * 下のリンクのメソッドを呼び出しなさい。。</br>
     * 魔力を使えるかの確認は各自で
     * {@link com.yuto.Scientificmagicmod.Api.AMaSAPI#addMagicPowerExhaustion(EntityPlayer, float)}
     */
	public abstract boolean useMagicPower(EntityPlayer entityPlayer);
}
