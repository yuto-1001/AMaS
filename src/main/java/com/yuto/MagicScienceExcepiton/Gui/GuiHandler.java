package com.yuto.MagicScienceExcepiton.Gui;

import com.yuto.MagicScienceExcepiton.MagicScienceException;
import com.yuto.MagicScienceExcepiton.Entity.TileEntity.TileEntityPowderMill;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler {
	/** クライアント側のGUI登録 */
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileentity = world.getTileEntity(x, y, z);
		switch( ID )
		{
			case MagicScienceException.PW:
				return new GuiPowderMill(player.inventory, (TileEntityPowderMill) tileentity);
			default:
				return null;
		}

	}

	/** サーバー側のGUI登録 */
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileentity = world.getTileEntity(x, y, z);
		switch( ID )
		{
			case MagicScienceException.PW:
				return new ContainerPowderMill(player.inventory, (TileEntityPowderMill) tileentity);
			default:
				return null;
		}
	}

}
