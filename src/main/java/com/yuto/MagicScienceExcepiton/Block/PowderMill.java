package com.yuto.MagicScienceExcepiton.Block;

import java.util.Random;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Entity.TileEntity.TileEntityPowderMill;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class PowderMill extends BlockContainer {
	private Random random = new Random();
	private IIcon[] iicon = new IIcon[4];
	private static boolean isBlockChange;
	private boolean isOn;
	public PowderMill(boolean b){
		super(Material.iron);

		this.isOn = b;

		String name = "PowderMill";
		if(!b){
			this.setCreativeTab( MagicScienceException.MagicScienceExcepiton );
			this.setBlockName( name );
			this.setHardness(5.0F);
			this.setResistance(10.0F);
			this.setStepSound(Block.soundTypeMetal);
			this.setHarvestLevel("pickaxe", 2);
			this.setLightLevel(0.1F);
			this.setBlockTextureName("magicscienceexception:PowderMill");
			GameRegistry.registerBlock( this, name );
		}else{
			this.setBlockName( name + "_on" );
			this.setHardness(5.0F);
			this.setResistance(10.0F);
			this.setStepSound(Block.soundTypeMetal);
			this.setHarvestLevel("pickaxe", 2);
			this.setLightLevel(1.0F);
			this.setBlockTextureName("magicscienceexception:PowderMill_on");
			GameRegistry.registerBlock( this, name + "_on" );
		}


        return;
    }
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityPowderMill();
	}
	/** これが右クリックされたときの処理　*/
	@Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		player.openGui(MagicScienceException.INSTANCE, MagicScienceException.PW, world, x, y, z);
        return true;
	}
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityLivingBase, ItemStack itemStack)
    {
		int l = MathHelper.floor_double((double)(entityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            world.setBlockMetadataWithNotify(x, y, z, 2, 2);
        }

        if (l == 1)
        {
            world.setBlockMetadataWithNotify(x, y, z, 5, 2);
        }

        if (l == 2)
        {
            world.setBlockMetadataWithNotify(x, y, z, 3, 2);
        }

        if (l == 3)
        {
            world.setBlockMetadataWithNotify(x, y, z, 4, 2);
        }
    }
	/** これが破壊されたときの処理　*/
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		if(!isBlockChange){
			TileEntityPowderMill tileentity = (TileEntityPowderMill) world.getTileEntity(x, y, z);
			if (tileentity != null) {
				for (int i = 0; i < tileentity.getSizeInventory(); i++) {
					ItemStack itemStack = tileentity.getStackInSlot(i);
					if (itemStack != null) {
						float f = random.nextFloat() * 0.6F + 0.1F;
						float f1 = random.nextFloat() * 0.6F + 0.1F;
						float f2 = random.nextFloat() * 0.6F + 0.1F;
						while (itemStack.stackSize > 0) {
							int j = random.nextInt(21) + 10;
							if (j > itemStack.stackSize) {
								j = itemStack.stackSize;
							}
							itemStack.stackSize -= j;
							EntityItem entityItem = new EntityItem(world, x + f, y + f1, z + f2, new ItemStack(itemStack.getItem(), j, itemStack.getItemDamage()));
							if (itemStack.hasTagCompound()) {
								entityItem.getEntityItem().setTagCompound(((NBTTagCompound) itemStack.getTagCompound().copy()));
							}
							float f3 = 0.025F;
							entityItem.motionX = (float) random.nextGaussian() * f3;
							entityItem.motionY = (float) random.nextGaussian() * f3 + 0.1F;
							entityItem.motionZ = (float) random.nextGaussian() * f3;
							world.spawnEntityInWorld(entityItem);
						}
					}
				}
				world.func_147453_f(x, y, z, block);
			}
		}
		super.breakBlock(world, x, y, z, block, meta);
	}
	/** ブロックのアイコンを登録する処理。 */
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		iicon[0] = register.registerIcon(this.getTextureName() + "-0");
		iicon[1] = register.registerIcon(this.getTextureName() + "-1");
		iicon[2] = register.registerIcon(this.getTextureName() + "-2");

	}
	/** ブロックのアイコンを返す。 */
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? this.iicon[1] : (side == 0 ? this.iicon[1] : (side != meta ? this.iicon[2] : this.iicon[0]));
	}
	public static void updatePWBlockState(boolean b, World worldObj, int xCoord, int yCoord, int zCoord) {
		int l = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
        TileEntity tileentity = worldObj.getTileEntity(xCoord, yCoord, zCoord);

        isBlockChange = true;

        if (b){
            worldObj.setBlock(xCoord, yCoord, zCoord, MagicScienceExceptionBlock.PowderMill_on);
        }
        else{
            worldObj.setBlock(xCoord, yCoord, zCoord, MagicScienceExceptionBlock.PowderMill);
        }

        isBlockChange = false;

        worldObj.setBlockMetadataWithNotify(xCoord, yCoord, zCoord, l, 2);

        if (tileentity != null){
            tileentity.validate();
            worldObj.setTileEntity(xCoord, yCoord, zCoord, tileentity);
        }

	}
}
