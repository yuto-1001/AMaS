package com.yuto.MagicScienceExcepiton.Item.Foods;

import java.util.List;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Block.MagicScienceExceptionBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;


public class Bulb extends ItemSeedFood {
	private IIcon[] iicon = new IIcon[4];
	public Bulb() {
		super(30, 10, MagicScienceExceptionBlock.CA, Blocks.grass);
		this.setCreativeTab(MagicScienceException.MagicScienceExcepiton);
		this.setUnlocalizedName("Bulb");
		this.setTextureName("magicscienceexception:Bulb");
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
		this.setAlwaysEdible();
		this.setPotionEffect(19, 60, 3, 100);
		GameRegistry.registerItem(this, "Bulb");
	}
	/** アイテムを使用した時の処理。 */
	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (side != 1) {
			return false;
		} else if (player.canPlayerEdit(x, y, z, side, itemStack) && player.canPlayerEdit(x, y + 1, z, side, itemStack)) {
			// 上からの使用で、プレイヤーが編集可能で、右クリックしたブロックが耕地であり、その上が空気の時。
			if (world.getBlock(x, y, z).canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
				// 作物を設置する。
				world.setBlock(x, y + 1, z, MagicScienceExceptionBlock.CA,itemStack.getItemDamage(), 0);
				world.setBlockMetadataWithNotify(x, y + 1, z, itemStack.getItemDamage(), 0);
				// スタック数を減らす。
				--itemStack.stackSize;
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iicon) {
		for (int i = 0; i < 4; i ++) {
			this.iicon[i] = iicon.registerIcon(this.getIconString() + "." + i);
		}
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return iicon[meta];
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs creativeTab, List list) {
		for (int i = 0; i < 4; i ++) {
			list.add(new ItemStack(this, 1, i));
		}
	}
	@Override
	public int getMetadata(int meta) {
		return meta;
	}
	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		return super.getUnlocalizedName() + "." + itemStack.getItemDamage();
	}
	/** 作物の種別を返す。 */
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		// IPlantableの実装。作物。耕地の上に設置する。
		return EnumPlantType.Plains;
	}
}
