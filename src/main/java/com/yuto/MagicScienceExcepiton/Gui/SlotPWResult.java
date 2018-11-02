package com.yuto.MagicScienceExcepiton.Gui;

import com.yuto.MagicScienceExcepiton.Entity.TileEntity.TileEntityPowderMill;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotPWResult extends Slot{

	private int amountCrafted;
	public SlotPWResult(TileEntityPowderMill tileEntity, int i, int j, int k) {
		super(tileEntity, i, j, k);

	}
	public ItemStack decrStackSize(int par1)
    {
        if (this.getHasStack())
        {
            this.amountCrafted += Math.min(par1, this.getStack().stackSize);
        }

        return super.decrStackSize(par1);
    }

    //スロットにアイテムを入れられるか。　falseでは入れられない
    public boolean isItemValid(ItemStack itemStack)
    {
    	return false;

    }

    //スロットから出した時の処理
    public void onPickupFromSlot(EntityPlayer par1EntityPlayer, ItemStack par2ItemStack)
    {
        this.onCrafting(par2ItemStack);

    }

    //スロットの最大スタック数を取得
    public int getSlotStackLimit()
    {
    	return 64;
    }

}
