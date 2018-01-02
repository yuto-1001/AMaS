package com.yuto.Scientificmagicmod;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class ScientificmagicModGuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == ScientificmagicMod.GUI_ID) {
            return new GUIContainer(x, y, z);
        }
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		if (ID == ScientificmagicMod.GUI_ID) {
            return new SMGUIContainer(x, y, z);
        }
		return null;
	}

}
