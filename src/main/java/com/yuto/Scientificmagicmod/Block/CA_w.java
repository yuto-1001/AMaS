package com.yuto.Scientificmagicmod.Block;

import java.util.Random;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Items.ScientificmagicItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

public class CA_w extends BlockBush {

	protected CA_w() {
		super(Material.grass);
		String name = "CA_w";
		
		this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
		this.setBlockName( name );
		this.setHardness(0.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeGrass);
		this.setLightLevel(0.1F);
		this.setBlockTextureName("scientificmagicmod:CA_w");
		
		GameRegistry.registerBlock( this, name );
	}
	/** 設置状態を維持できるかを確認し、維持できなければドロップする。 */
	@Override
	protected void checkAndDropBlock(World world, int x, int y, int z) {
		// 維持できない時。
		if (!this.canBlockStay(world, x, y, z)) {
			// ドロップする。
			this.dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
			// 空気に上書きする。
			world.setBlock(x, y, z, getBlockById(0), 0, 2);
		}
	}
	/** あたり判定を返す。 */
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
		// あたり判定をなくす。
		return null;
	}
 
	/** 不透明なブロックか。 */
	@Override
	public boolean isOpaqueCube() {
		// 透明なブロックなのでfalseを返す。
		return false;
	}
 
	/** 通常と同様に描画するか。 */
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
 
	/** 描画の種別を返す。 */
	@Override
	public int getRenderType() {
		// 上から見て「×」の形になるように配置され、そこにテクスチャが表示される。
		return 1;
	}
	/** ドロップアイテムを返す。 
	 * @return */
	@Override
	public Item getItemDropped(int meta, Random random, int fortune) {
		return ScientificmagicItems.Bulb_w;
	}
	public Block idDropped(int i, Random random, int j)
    {
        return ScientificmagicBlock.CA_w;
    }
	public CA_w idDropped1(int i, Random random, int j)
    {
        return this;
    }
	@Override
	public int quantityDropped(Random random) {
		return 3;
	}
}