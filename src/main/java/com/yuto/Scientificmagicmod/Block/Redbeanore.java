package com.yuto.Scientificmagicmod.Block;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class Redbeanore extends Block{

	protected Redbeanore() {
		super(Material.rock);
		
		
		String name = "Redbeanore";
		
		this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
		this.setBlockName( name );
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setLightLevel(0.1F);
		this.setBlockTextureName("scientificmagicmod:redbean_ore");
		
		GameRegistry.registerBlock( this, name );
		
		

		
		return;
	}
}