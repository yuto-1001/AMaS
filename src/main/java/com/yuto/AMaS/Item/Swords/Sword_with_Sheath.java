package com.yuto.AMaS.Item.Swords;

import com.yuto.AMaS.Api.AMaSAPI;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class Sword_with_Sheath extends ItemSword {
	private boolean isPutted = true;
	private IIcon[] iicon = new IIcon[2];

	public Sword_with_Sheath(ToolMaterial p_i45356_1_) {
		super(p_i45356_1_);
		String name = "SwordWSheath";

		this.setCreativeTab(AMaSAPI.AMaSTab);
		this.setMaxStackSize(1);
		this.setUnlocalizedName(name);
		this.setTextureName("iron_sword");

		GameRegistry.registerItem(this, name);
		return;
	}
	@Override
	public void onUpdate(ItemStack itemStack, World world, Entity entity, int slot, boolean isHeld) {
		if (!world.isRemote) {
			if (itemStack.hasTagCompound())
				writeToNBT(itemStack, this.isPutted);
		}
	}
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityPlayer) {
		if (entityPlayer.isSneaking()) {
			boolean isPut;
			if (this.isPutted)
				isPut = false;
			else
				isPut = true;
			writeToNBT(itemStack, isPut);
			updateAttributes(itemStack.getTagCompound());
		}
		return itemStack;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(ItemStack itemStack, int pass) {
		readFromNBT(itemStack);
		if (this.isPutted) {
			return this.iicon[0];
		}
		return this.iicon[1];
	}
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iicon) {
		this.iicon[0] = iicon.registerIcon("amas:sws_i");
		this.iicon[1] = iicon.registerIcon("iron_sword");
	}
	public boolean readFromNBT(ItemStack itemStack) {
		if (itemStack.hasTagCompound()) {
			NBTTagCompound nbt = itemStack.getTagCompound();
			this.isPutted = nbt.getBoolean("isPutted");
			return true;
		}
		return false;
	}
	public boolean writeToNBT(ItemStack itemStack, boolean isPut) {
		if (itemStack.hasTagCompound()) {
			NBTTagCompound nbt = itemStack.getTagCompound();
			nbt.setBoolean("isPutted", isPut);
			return true;
		}else {
			NBTTagCompound nbt = new NBTTagCompound();
			itemStack.setTagCompound(nbt);
			nbt.setBoolean("isPutted", isPut);
			return true;
		}
	}
	private static void updateAttributes(NBTTagCompound nbtData)
	  {
	    int damage = nbtData.getBoolean("isPutted") ? 2 : 20;

	    NBTTagCompound entry = new NBTTagCompound();

	    entry.setString("AttributeName", SharedMonsterAttributes.attackDamage.getAttributeUnlocalizedName());
	    entry.setLong("UUIDMost", field_111210_e.getMostSignificantBits());
	    entry.setLong("UUIDLeast", field_111210_e.getLeastSignificantBits());
	    entry.setString("Name", "Tool modifier");
	    entry.setInteger("Amount", damage);
	    entry.setDouble("Operation", 0);

	    NBTTagList list = new NBTTagList();
	    list.appendTag(entry);

	    nbtData.setTag("AttributeModifiers", list);
	  }
}
