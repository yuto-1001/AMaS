package com.yuto.Scientificmagicmod.Block;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import net.minecraft.block.Block;

public class ScientificmagicBlock {
	public static Block Redbeanore;
	public static Block TPG;
	public static Block CA_w;
	public static Block CA_r;
	public static Block CA_b;
	public static Block CA_bl;
	public static void registry( ScientificmagicMod mod )
	{
		Redbeanore = new Redbeanore();
		TPG = new ThermalFurnace();
		CA_w = new CA_w();
		CA_r = new CA_r();
		CA_b = new CA_b();
		CA_bl = new CA_bl();
		return;
	}



}
