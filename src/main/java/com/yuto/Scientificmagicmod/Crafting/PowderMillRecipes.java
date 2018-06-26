package com.yuto.Scientificmagicmod.Crafting;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.yuto.Scientificmagicmod.Block.ScientificmagicBlock;
import com.yuto.Scientificmagicmod.Items.ScientificmagicItems;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class PowderMillRecipes {
	private static final PowderMillRecipes millingBase = new PowderMillRecipes();
    /** The list of milling results. */
    private Map millingList = new HashMap();
    private Map experienceList = new HashMap();

    /**
     * Used to call methods addSmelting and getmillingResult.
     */
    public static PowderMillRecipes milling()
    {
        return millingBase;
    }

    private PowderMillRecipes(){
    	this.setRecipeBlocks(ScientificmagicBlock.Redbeanore, new ItemStack(ScientificmagicItems.Redbeandust, 3), 1.0F);
    	this.setRecipeBlocks(Blocks.diamond_ore, new ItemStack(ScientificmagicItems.Diamonddust, 3), 1.0F);
    	this.setRecipeBlocks(Blocks.iron_ore, new ItemStack(ScientificmagicItems.Irondust, 3), 0.7F);
    	this.setRecipeBlocks(Blocks.gold_ore, new ItemStack(ScientificmagicItems.Golddust, 3), 0.5F);
    	this.setRecipeBlocks(Blocks.obsidian, new ItemStack(ScientificmagicItems.Obsidiandust, 3), 0.5F);
    	this.setRecipeBlocks(Blocks.stone, new ItemStack(ScientificmagicItems.Stonedust, 3), 0.3F);
    }

    public void setRecipeBlocks(Block block, ItemStack itemStack, float millingTime)
    {
        this.setRecipeItems(Item.getItemFromBlock(block), itemStack, millingTime);
    }

    public void setRecipeItems(Item item, ItemStack itemStack, float millingTime)
    {
        this.setRecipeItemStacks(new ItemStack(item, 1, 32767), itemStack, millingTime);
    }

    public void setRecipeItemStacks(ItemStack itemStack, ItemStack itemStack1, float millingTime)
    {
        this.millingList.put(itemStack, itemStack1);
        this.experienceList.put(itemStack1, Float.valueOf(millingTime));
    }

    /**
     * Returns the milling result of an item.
     */
    public ItemStack getmillingResult(ItemStack p_151395_1_)
    {
        Iterator iterator = this.millingList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return null;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151395_1_, (ItemStack)entry.getKey()));

        return (ItemStack)entry.getValue();
    }

    private boolean func_151397_a(ItemStack p_151397_1_, ItemStack p_151397_2_)
    {
        return p_151397_2_.getItem() == p_151397_1_.getItem() && (p_151397_2_.getItemDamage() == 32767 || p_151397_2_.getItemDamage() == p_151397_1_.getItemDamage());
    }

    public Map getMillingList()
    {
        return this.millingList;
    }

    public float func_151398_b(ItemStack p_151398_1_)
    {
        float ret = p_151398_1_.getItem().getSmeltingExperience(p_151398_1_);
        if (ret != -1) return ret;

        Iterator iterator = this.experienceList.entrySet().iterator();
        Entry entry;

        do
        {
            if (!iterator.hasNext())
            {
                return 0.0F;
            }

            entry = (Entry)iterator.next();
        }
        while (!this.func_151397_a(p_151398_1_, (ItemStack)entry.getKey()));

        return ((Float)entry.getValue()).floatValue();
    }
}
