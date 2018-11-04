package com.yuto.MagicScienceExcepiton.Api;

import net.minecraft.entity.player.EntityPlayer;

public interface IPlayerManager {
	public void addMagicPowerStats(EntityPlayer entityPlayer, int par1, float par2);

    public int getMagicPowerLevel(EntityPlayer entityPlayer);

    public void addMagicPowerExhaustion(EntityPlayer entityPlayer, float par1);

	public void addSplitPowerStats(EntityPlayer entityPlayer, int sp, float ssp);

	public int getSplitPowerLevel(EntityPlayer entityPlayer);
}
