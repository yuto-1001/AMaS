package com.yuto.Scientificmagicmod.Block;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import net.minecraft.block.Block;

public class ScientificmagicBlock {
	public static Block Redbeanore;
	public static Block CA;
	public static void registry( ScientificmagicMod mod )
	{
		Redbeanore = new Redbeanore();
		CA = new CA();
		return;
	}



}
