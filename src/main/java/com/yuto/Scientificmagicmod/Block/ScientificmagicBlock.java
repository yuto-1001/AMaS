package com.yuto.Scientificmagicmod.Block;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import net.minecraft.block.Block;

public class ScientificmagicBlock {
	public static Block Redbeanore;
	public static Block CA;
	public static Block PowderMill;
	public static Block PowderMill_on;
	public static Block TF;
	public static void registry( ScientificmagicMod mod )
	{
		Redbeanore = new Redbeanore();
		CA = new CA();
		PowderMill = new PowderMill(false);
		PowderMill_on = new PowderMill(true);
		return;
	}



}
