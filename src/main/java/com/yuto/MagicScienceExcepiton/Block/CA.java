package com.yuto.MagicScienceExcepiton.Block;

import java.util.List;
import java.util.Random;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Item.MagicScienceExceptionItem;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class CA extends BlockBush{
	private IIcon[] iicon = new IIcon[4];
	protected CA() {
		super(Material.grass);
		String name = "CA";

		this.setCreativeTab( MagicScienceExcepitonAPI.MagicScienceExceptionTab );
		this.setBlockName( name );
		this.setHardness(0.0F);
		this.setResistance(10.0F);
		this.setStepSound(Block.soundTypeGrass);
		this.setLightLevel(0.1F);
		this.setBlockTextureName("magicscienceexception:CA");

		GameRegistry.registerBlock(this, ItemCABlock.class, "CA");
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
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		for (int i = 0; i < 4; i ++) {
			this.iicon[i] = register.registerIcon(this.getTextureName() + "." + i);
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return iicon[meta];
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < 4; i ++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	@Override
	public int damageDropped(int meta) {
		return meta;
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
		return MagicScienceExceptionItem.Bulb;
	}
	/** そのブロックの上に設置できるか。 */
	@Override
	protected boolean canPlaceBlockOn(Block block) {
	// 草、土の上のみ。
	return block == Blocks.grass || block == Blocks.dirt;
	}
	public CA idDropped1(int i, Random random, int j)
    {
        return this;
    }
	@Override
	public int quantityDropped(Random random) {
		return 3;
	}
}
