package com.yuto.Scientificmagicmod.Items;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Block.ScientificmagicBlock;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

public class Bulb_bl extends ItemSeedFood {

	public Bulb_bl() {
		super(30,30,ScientificmagicBlock.CA_bl, Blocks.grass);
		String name = "Bulb_bl";
        this.setUnlocalizedName( name );
        maxStackSize = 64;
        this.setCreativeTab( ScientificmagicMod.Scientificmagicmod );
        this.setTextureName( "scientificmagicmod:Bulb_b" );
		this.setAlwaysEdible();
		this.setPotionEffect(19, 60, 3, 100);
		GameRegistry.registerItem( this, name );
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
				world.setBlock(x, y + 1, z, ScientificmagicBlock.CA_bl);
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
	/** 作物の種別を返す。 */
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		// IPlantableの実装。作物。耕地の上に設置する。
		return EnumPlantType.Plains;
	}
}
