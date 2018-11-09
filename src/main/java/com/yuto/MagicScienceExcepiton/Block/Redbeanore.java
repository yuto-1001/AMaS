package com.yuto.MagicScienceExcepiton.Block;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.Mill.CanMillItemStack;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class Redbeanore extends Block implements CanMillItemStack{

	protected Redbeanore() {
		super(Material.rock);

		String name = "Redbeanore";

		this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
		this.setBlockName( name );
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setLightLevel(0.1F);
		this.setBlockTextureName("magicscienceexception:redbean_ore");

		OreDictionary.registerOre("oreRbnium",this);

		GameRegistry.registerBlock( this, name );

		return;
	}
	public int getDamage(){
		return 500;

	}
}