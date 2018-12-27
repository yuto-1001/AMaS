package com.yuto.MagicScienceExcepiton.Api.Magic;

import com.yuto.MagicScienceExcepiton.Gui.GuiScreenMagicBook;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public abstract class MagicBook extends Item {
	public int MagicPowerUsageRate;
	public GuiScreenMagicBook bookGui;
	/**
	 * @author yuto
	 * @param MPUR 魔力消費量(魔力を消費しないですって!?そんなの許さないわっ!!)
	 */
	public MagicBook(int MPUR) {
		MagicPowerUsageRate = MPUR;
	}
	/**
     * useMagicPower 魔力を使います。</br>
     * 下のリンクのメソッドを呼び出しなさい。。</br>
     * 魔力を使えるかの確認は各自で
     * {@link com.yuto.Scientificmagicmod.Api.MagicScienceExcepitonAPI#addMagicPowerExhaustion(EntityPlayer, float)}
     */
	public abstract boolean useMagicPower(EntityPlayer entityPlayer);
}
