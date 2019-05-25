package com.yuto.AMaS.Api.Magic;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;

public abstract class MagicSword extends ItemSword {
	public int MagicPowerUsageRate;
	public MagicSword(ToolMaterial material, int MPUR) {
		super(material);
		MagicPowerUsageRate = MPUR;
	}
	/**
     * useMagicPower 魔力を使います。</br>
     * 下のリンクのメソッドを呼び出しなさい。。</br>
     * 魔力を使えるかの確認は各自で
     * {@link com.yuto.Scientificmagicmod.Api.AMaSAPI#addMagicPowerExhaustion(EntityPlayer, float)}
     */
	public abstract boolean useMagicPower(EntityPlayer entityPlayer);

}
