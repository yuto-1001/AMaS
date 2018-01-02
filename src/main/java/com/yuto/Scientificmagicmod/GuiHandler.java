package com.yuto.Scientificmagicmod;
 
import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
 
public class GuiHandler implements IGuiHandler {
    /*サーバー側の処理*/
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ScientificmagicMod.GUI_ID) {
            return new SMContainer(x, y, z);
        }
        return null;
    }
 
    /*クライアント側の処理*/
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if (ID == ScientificmagicMod.GUI_ID) {
            return new SMGUIContainer(x, y, z);
        }
        return null;
    }
}