package com.yuto.Scientificmagicmod.Entity.TileEntity;

import com.yuto.Scientificmagicmod.Api.EnergyItem;
import com.yuto.Scientificmagicmod.Api.Mill.CanMillItemStack;
import com.yuto.Scientificmagicmod.Api.Mill.MillingBlade;
import com.yuto.Scientificmagicmod.Block.PowderMill;
import com.yuto.Scientificmagicmod.Crafting.PowderMillRecipes;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityPowderMill extends TileEntity implements IInventory{
    /** スロットいくつ？ */
    private ItemStack[] PWItemStacks = new ItemStack[4];
    /** あとどのくらいエネルギー残量があるか */
    public int PWEnergyTime;
    /**　今使ってるアイテムはどのくらいのエネルギーを持つか */
    public int currentItemEnergyTime;
    /** 粉砕にかかる時間 */
    public int PWMillTime;

    private int damage = 0;

    private String InventoryName;
    private static final String __OBFID = "CL_00000357";

    /**
     * インベントリーのスロットの数
     */
    @Override
    public int getSizeInventory()
    {
        return this.PWItemStacks.length;
    }

    /**
     * 引数の番号のスロットのアイテムスタック
     */
    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return this.PWItemStacks[slot];
    }

    /**
     * 引数１から２までのスロットのアイテムを消す。
     */
    @Override
    public ItemStack decrStackSize(int slot, int amount) {
    	if (PWItemStacks[slot] == null)
    		return null;
    	ItemStack itemstack;
    	if (PWItemStacks[slot].stackSize <= amount) {
    		itemstack = PWItemStacks[slot];
    		PWItemStacks[slot] = null;
    		return itemstack;
    	}
    	itemstack = PWItemStacks[slot].splitStack(amount);
    	if (PWItemStacks[slot].stackSize < 1) {
    		PWItemStacks[slot] = null;
    	}
    	return itemstack;
    }

    /**
     * インベントリーを閉じると呼び出される。
     * @return　ドロップするアイテムスタックを返す。
     */
    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.PWItemStacks[slot] != null)
        {
            ItemStack itemstack = this.PWItemStacks[slot];
            this.PWItemStacks[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    /**
     * 引数1のスロットに引数2のアイテムスタックを入れる。
     */
    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        this.PWItemStacks[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > this.getInventoryStackLimit())
        {
            itemStack.stackSize = this.getInventoryStackLimit();
        }
    }

    /**
     * インベントリー名を返す。
     */
    @Override
    public String getInventoryName()
    {
        return this.hasCustomInventoryName() ? this.InventoryName : "PowderMill";
    }

    /**
     * インベントリー名をつけられているか。
     */
    @Override
    public boolean hasCustomInventoryName()
    {
        return this.InventoryName != null && this.InventoryName.length() > 0;
    }

    public void setInventoryName(String p_145951_1_)
    {
        this.InventoryName = p_145951_1_;
    }

    @Override
    public void readFromNBT(NBTTagCompound p_145839_1_)
    {
        super.readFromNBT(p_145839_1_);
        NBTTagList nbttaglist = p_145839_1_.getTagList("Items", 10);
        this.PWItemStacks = new ItemStack[this.getSizeInventory()];

        for (int i = 0; i < nbttaglist.tagCount(); ++i)
        {
            NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
            byte b0 = nbttagcompound1.getByte("Slot");

            if (b0 >= 0 && b0 < this.PWItemStacks.length)
            {
                this.PWItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
            }
        }

        this.PWEnergyTime = p_145839_1_.getShort("EnergyTime");
        this.PWMillTime = p_145839_1_.getShort("MillTime");
        this.currentItemEnergyTime = getItemBurnTime(this.PWItemStacks[1]);

        if (p_145839_1_.hasKey("CustomName", 8))
        {
            this.InventoryName = p_145839_1_.getString("CustomName");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbt)
    {
        super.writeToNBT(nbt);
        nbt.setShort("EnergyTime", (short)this.PWEnergyTime);
        nbt.setShort("MillTime", (short)this.PWMillTime);
        NBTTagList nbttaglist = new NBTTagList();

        for (int i = 0; i < this.PWItemStacks.length; ++i)
        {
            if (this.PWItemStacks[i] != null)
            {
                NBTTagCompound nbttagcompound1 = new NBTTagCompound();
                nbttagcompound1.setByte("Slot", (byte)i);
                this.PWItemStacks[i].writeToNBT(nbttagcompound1);
                nbttaglist.appendTag(nbttagcompound1);
            }
        }

        nbt.setTag("Items", nbttaglist);

        if (this.hasCustomInventoryName())
        {
            nbt.setString("CustomName", this.InventoryName);
        }
    }

    /**
     * スロットの最大スタック数
     */
    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }


    @SideOnly(Side.CLIENT)
    public int getCookProgressScaled(int p_145953_1_)
    {
        return this.PWMillTime * p_145953_1_ / 200;
    }
    @SideOnly(Side.CLIENT)
    public int getBurnTimeRemainingScaled(int p_145955_1_)
    {
        if (this.currentItemEnergyTime == 0)
        {
            this.currentItemEnergyTime = 200;
        }

        return this.PWEnergyTime * p_145955_1_ / this.currentItemEnergyTime;
    }

    /**
     * エネルギーを使っているか。
     */
    public boolean isUsingEnergy()
    {
        return this.PWEnergyTime > 0;
    }

    @Override
    public void updateEntity()
    {
        boolean flag = this.PWEnergyTime > 0;
        boolean flag1 = false;

        if (this.PWEnergyTime > 0)
        {
            --this.PWEnergyTime;
        }

        if (!this.worldObj.isRemote)
        {
            if (this.PWEnergyTime != 0 || this.PWItemStacks[1] != null && this.PWItemStacks[0] != null && this.PWItemStacks[2] != null)
            {
                if (this.PWEnergyTime == 0 && this.canMill())
                {
                    this.currentItemEnergyTime = this.PWEnergyTime = getItemBurnTime(this.PWItemStacks[1]);

                    if (this.PWEnergyTime > 0)
                    {
                        flag1 = true;

                        if (this.PWItemStacks[1] != null)
                        {
                            --this.PWItemStacks[1].stackSize;

                            if (this.PWItemStacks[1].stackSize == 0)
                            {
                                this.PWItemStacks[1] = PWItemStacks[1].getItem().getContainerItem(PWItemStacks[1]);
                            }
                        }
                    }
                }

                if (this.isUsingEnergy() && this.canMill())
                {
                    if(this.PWItemStacks[2] != null && this.PWItemStacks[2].getItem() instanceof MillingBlade)
                    	this.PWMillTime += ((MillingBlade) this.PWItemStacks[2].getItem()).getOverClock();
					else
						this.PWMillTime = 0;

                    if (this.PWMillTime >= 200)
                    {
                        this.PWMillTime = 0;
                        this.MillItem();
                        flag1 = true;
                    }
                }
                else
                {
                    this.PWMillTime = 0;
                }
                if (flag != this.PWEnergyTime > 0)
                {
                    flag1 = true;
                    PowderMill.updatePWBlockState(true, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
                }
            }else{
            	PowderMill.updatePWBlockState(false, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
            }
        }

        if (flag1)
        {
            this.markDirty();
        }
    }

    /**
     * 粉砕できるか。
     */
    private boolean canMill()
    {
        if (this.PWItemStacks[0] == null)
        {
            return false;
        }
        else
        {

	       	Block block = null;
			if (this.PWItemStacks[2] != null && this.PWItemStacks[0] != null){
	       		Item item = this.PWItemStacks[0].getItem();
	            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air){
	                    block = Block.getBlockFromItem(item);
	            if(block instanceof CanMillItemStack){
	                damage = ((CanMillItemStack) block).getDamage();
	            }else if(item instanceof CanMillItemStack){
		            damage = ((CanMillItemStack) item).getDamage();
	            }else{
	            	if(block == Blocks.diamond_ore) damage = 500;
	            	if(block == Blocks.obsidian) damage = 100;
	            	if(block == Blocks.iron_ore) damage = 50;
	            	if(block == Blocks.gold_ore) damage = 25;
	            	if(block == Blocks.stone) damage = 5;
	            	if(block == Blocks.cobblestone) damage = 5;
	            	if(block == Blocks.planks) damage = 3;
	            }
			}
		    ItemStack itemstack = PowderMillRecipes.milling().getmillingResult(this.PWItemStacks[0]);
	        if (itemstack == null) return false;
	        if (this.PWItemStacks[3] == null) return true;
	        if (!this.PWItemStacks[3].isItemEqual(itemstack)) return false;
	        int result = PWItemStacks[3].stackSize + itemstack.stackSize;
	        if(this.PWItemStacks[2] != null)
	        	return result <= getInventoryStackLimit() && result <= this.PWItemStacks[3].getMaxStackSize() && this.PWItemStacks[2].getMaxDamage() - this.PWItemStacks[2].getItemDamage() > damage;
        }
		return false;
        }
    }

    /**
     * 粉砕する処理
     */
    public void MillItem()
    {

        if (this.canMill())
        {
            ItemStack itemstack = PowderMillRecipes.milling().getmillingResult(this.PWItemStacks[0]);

            if (this.PWItemStacks[3] == null)
            {
                this.PWItemStacks[3] = itemstack.copy();
            }
            else if (this.PWItemStacks[3].getItem() == itemstack.getItem())
            {
                this.PWItemStacks[3].stackSize += itemstack.stackSize;
            }

            --this.PWItemStacks[0].stackSize;

            if (this.PWItemStacks[0].stackSize <= 0)
            {
                this.PWItemStacks[0] = null;
            }
            if (this.PWItemStacks[2] != null && this.PWItemStacks[0] != null){
                this.PWItemStacks[2].setItemDamage(this.PWItemStacks[2].getItemDamage() + damage);
            }
        }
    }

    /**
     * そのアイテムがそれだけ使えるか。
     */
    public static int getItemBurnTime(ItemStack itemStack)
    {
        if (itemStack == null)
        {
            return 0;
        }
        else
        {

            Item item = itemStack.getItem();

            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air)
            {
                Block block = Block.getBlockFromItem(item);
                if (block == Blocks.coal_block)
                {
                    return 2000;
                }
            }
            if (item == Items.coal) return 200;
            if (item == Items.lava_bucket) return 2500;
            if (item == Items.blaze_rod) return 300;
            if(item instanceof EnergyItem)
            	return ((EnergyItem) item).getEnergyTime();
        }
		return 0;
    }

    public static boolean isItemEnergy(ItemStack itemStack)
    {
        /**
         * それがエネルギーを持っているか。
         */
        return getItemBurnTime(itemStack) > 0;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityPlayer)
    {
        return true;
    }

    @Override
    public void openInventory() {
    	this.readFromNBT(new NBTTagCompound());
    }

    @Override
    public void closeInventory() {
    	this.writeToNBT(new NBTTagCompound());
    }

    /**
     * 最大スタック数を無視してアイテムが入るか。
     */
    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return true;
    }

}
