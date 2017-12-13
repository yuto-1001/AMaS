package com.yuto.Scientificmagicmod;

import com.yuto.Scientificmagicmod.Block.TileEntityThermalFurnace;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerTF extends Container {

	private TileEntityThermalFurnace tileEntity;

	public ContainerTF(EntityPlayer player, TileEntityThermalFurnace tileEntity) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.tileEntity = tileEntity;
	}

	@Override
	public boolean canInteractWith(EntityPlayer p_75145_1_) {
		// TODO 自動生成されたメソッド・スタブ
		return false;
	}

}
