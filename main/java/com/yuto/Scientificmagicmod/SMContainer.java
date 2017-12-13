package com.yuto.Scientificmagicmod;
 
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
 
public class SMContainer extends Container {
    //座標でGUIを開くか判定するためのもの。
    int xCoord, yCoord, zCoord;
    public SMContainer(int x, int y, int z) {
        this.xCoord = x;
        this.yCoord = y;
        this.zCoord = z;
    }
 
    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }
}