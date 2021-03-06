package com.yuto.AMaS.Block;

import com.yuto.AMaS.Api.AMaSAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.oredict.OreDictionary;

public class Redbeanore extends Block{

	protected Redbeanore() {
		super(Material.rock);

		String name = "Redbeanore";

		this.setCreativeTab( AMaSAPI.AMaSTab );
		this.setBlockName( name );
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setLightLevel(0.1F);
		this.setBlockTextureName("amas:redbean_ore");

		OreDictionary.registerOre("oreRbnium",this);

		GameRegistry.registerBlock( this, name );

		return;
	}
	public int getDamage(){
		return 500;

	}
}