package com.yuto.MagicScienceExcepiton.Block;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Block.Plants.CA;

import net.minecraft.block.Block;

public class MagicScienceExceptionBlock {
	public static Block Redbeanore;
	public static Block CA;
	public static Block PowderMill;
	public static Block PowderMill_on;
	public static void registry( MagicScienceException magicScienceException )
	{
		Redbeanore = new Redbeanore();
		CA = new CA();
		PowderMill = new PowderMill(false);
		PowderMill_on = new PowderMill(true);
		return;
	}



}
