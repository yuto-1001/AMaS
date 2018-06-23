package com.yuto.Scientificmagicmod.Gui;

import com.yuto.Scientificmagicmod.Crafting.PowderMillRecipes;
import com.yuto.Scientificmagicmod.Entity.TileEntity.TileEntityPowderMill;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerPowderMill extends Container {
	private TileEntityPowderMill tileEntity;
    private int lastCookTime;
    private int lastBurnTime;
    private int lastItemBurnTime;
    private static final String __OBFID = "CL_00001748";

    public ContainerPowderMill(InventoryPlayer player, TileEntityPowderMill tileEntity)
    {
        this.tileEntity = tileEntity;
        this.addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
        this.addSlotToContainer(new Slot(tileEntity, 1, 56, 53));
        this.addSlotToContainer(new Slot(tileEntity, 2, 83, 53));
        this.addSlotToContainer(new SlotPWResult(tileEntity, 3, 116, 35));
        int i;

        for (i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void addCraftingToCrafters(ICrafting p_75132_1_)
    {
        super.addCraftingToCrafters(p_75132_1_);
        p_75132_1_.sendProgressBarUpdate(this, 0, this.tileEntity.PWMillTime);
        p_75132_1_.sendProgressBarUpdate(this, 1, this.tileEntity.PWEnergyTime);
        p_75132_1_.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemEnergyTime);
    }

    /**
     * Looks for changes made in the container, sends them to every listener.
     */
    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();

        for (int i = 0; i < this.crafters.size(); ++i)
        {
            ICrafting icrafting = (ICrafting)this.crafters.get(i);

            if (this.lastCookTime != this.tileEntity.PWMillTime)
            {
                icrafting.sendProgressBarUpdate(this, 0, this.tileEntity.PWMillTime);
            }

            if (this.lastBurnTime != this.tileEntity.PWEnergyTime)
            {
                icrafting.sendProgressBarUpdate(this, 1, this.tileEntity.PWEnergyTime);
            }

            if (this.lastItemBurnTime != this.tileEntity.currentItemEnergyTime)
            {
                icrafting.sendProgressBarUpdate(this, 2, this.tileEntity.currentItemEnergyTime);
            }
        }

        this.lastCookTime = this.tileEntity.PWMillTime;
        this.lastBurnTime = this.tileEntity.PWEnergyTime;
        this.lastItemBurnTime = this.tileEntity.currentItemEnergyTime;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int p_75137_1_, int p_75137_2_)
    {
        if (p_75137_1_ == 0)
        {
            this.tileEntity.PWMillTime = p_75137_2_;
        }

        if (p_75137_1_ == 1)
        {
            this.tileEntity.PWEnergyTime = p_75137_2_;
        }

        if (p_75137_1_ == 2)
        {
            this.tileEntity.currentItemEnergyTime = p_75137_2_;
        }
    }

    public boolean canInteractWith(EntityPlayer p_75145_1_)
    {
        return this.tileEntity.isUseableByPlayer(p_75145_1_);
    }

    /**
     * Shiftクリック時の処理
     */
    @Override
    @SideOnly(Side.CLIENT)
    public ItemStack transferStackInSlot(EntityPlayer player, int slotnum)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(slotnum);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (slotnum == 3)
            {
                if (!this.mergeItemStack(itemstack1, 4, 40, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (slotnum != 2 && slotnum != 1 && slotnum != 0)
            {
                if (PowderMillRecipes.milling().getmillingResult(itemstack1) != null)
                {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false))
                    {
                        return null;
                    }
                }
                else if (TileEntityPowderMill.isItemEnergy(itemstack1))
                {
                    if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    {
                        return null;
                    }
                }
                else if (slotnum >= 4 && slotnum < 31)
                {
                    if (!this.mergeItemStack(itemstack1, 31, 39, false))
                    {
                        return null;
                    }
                }
                else if (slotnum >= 31 && slotnum < 39 && !this.mergeItemStack(itemstack1, 4, 31, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 4, 39, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

}
