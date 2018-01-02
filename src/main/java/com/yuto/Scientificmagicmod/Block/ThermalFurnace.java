package com.yuto.Scientificmagicmod.Block;

import com.yuto.Scientificmagicmod.ScientificmagicMod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class ThermalFurnace extends Block {

	public ThermalFurnace() {
		super(Material.rock);

		String name = "ThermalFurnace";

		this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
		this.setBlockName( name );
		this.setHardness(5.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeStone);
		this.setHarvestLevel("pickaxe", 2);
		this.setLightLevel(0.1F);
		this.setBlockTextureName("scientificmagicmod:TF");

		GameRegistry.registerBlock( this, name );
	}
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityThermalFurnace();
	}
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(ScientificmagicMod.INSTANCE, ScientificmagicMod.GUI_ID, world, x, y, z);
        return true;
	}
}
