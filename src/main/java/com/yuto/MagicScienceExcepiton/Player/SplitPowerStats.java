package com.yuto.MagicScienceExcepiton.Player;

import com.yuto.MagicScienceExcepiton.MSEConfig;
import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Api.Event.PlayerSplitPowerEvent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.MinecraftForge;

public class SplitPowerStats {

	//水分
    /** 水分 */
    private int splitPowerLevel = 0;
    private final static int MAX_SPLITPOWER_LEVEL = 20;
    private final static int MAX_PREV_SPLITPOWER_LEVEL = 20;

    /** 隠し水分ゲージ */
    private float splitPowerSaturationLevel = 5.0F;

    //Packet用
    private int lastSplitPowerLevel = 0;
    private float lastSaturationLevel = 0;


    /** The player's food timer value. */
    private int splitPowerTimer;
    private int prevSplitPowerLevel = 0;

    /**
     * Args: int foodLevel, float foodSaturationModifier
     */
    public void addStats(EntityPlayer entityPlayer, int par1, float par2) {

        if (!MSEConfig.statusSplitPower) return;

        //this.splitPowerLevel = Math.min(par1 + this.splitPowerLevel, 20);
        //this.splitPowerSaturationLevel = Math.min(Math.min(this.splitPowerSaturationLevel + par2, this.splitPowerLevel), MAX_PREV_STAMINA_LEVEL);

        PlayerSplitPowerEvent.Add event = new PlayerSplitPowerEvent.Add(entityPlayer, splitPowerLevel, splitPowerSaturationLevel, par1, par2);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            //this.splitPowerLevel = prevLevel;
            //this.splitPowerSaturationLevel = prevSaturation;
        } else {
            this.splitPowerLevel = MathHelper.clamp_int(event.addsplitLevel + this.splitPowerLevel, 0, MAX_SPLITPOWER_LEVEL);
            this.splitPowerSaturationLevel = MathHelper.clamp_float(event.newAddsplitSaturationLevel + this.splitPowerSaturationLevel, 0.0F, this.splitPowerLevel);
        }

    }

    /**
     * Reads food stats from an NBT object.
     */
    public void readNBT(NBTTagCompound par1NBTTagCompound) {
        if (par1NBTTagCompound.hasKey("splitPowerlevel")) {
            this.splitPowerLevel = par1NBTTagCompound.getInteger("splitPowerlevel");
            this.splitPowerTimer = par1NBTTagCompound.getInteger("splitPowerticktimer");
            this.splitPowerSaturationLevel = par1NBTTagCompound.getFloat("splitPowersaturationlevel");
        }
    }

    /**
     * Writes food stats to an NBT object.
     */
    public void writeNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("splitPowerlevel", this.splitPowerLevel);
        par1NBTTagCompound.setInteger("splitPowerticktimer", this.splitPowerTimer);
        par1NBTTagCompound.setFloat("splitPowersaturationlevel", this.splitPowerSaturationLevel);
    }

    public boolean isPacket() {

        if (!MSEConfig.statusSplitPower) return false;

        boolean flag = false;

        if (this.splitPowerLevel != this.lastSplitPowerLevel) {
            flag = true;
            this.lastSplitPowerLevel = this.splitPowerLevel;
        }

        if (this.splitPowerSaturationLevel != this.lastSaturationLevel) {

            flag = true;
            this.lastSaturationLevel = this.splitPowerSaturationLevel;

        }

        return flag;

    }

    /**
     * Get the player's splitPower level.
     */
    public int getSplitPowerLevel() {

        if (!MSEConfig.statusSplitPower) return MAX_SPLITPOWER_LEVEL;

        return this.splitPowerLevel;
    }

    @SideOnly(Side.CLIENT)
    public int getPrevSplitPowerLevel() {
        return this.prevSplitPowerLevel;
    }

    /**
     * If foodLevel is not max.
     */
    public boolean needSplitPower() {

        if (!MSEConfig.statusSplitPower) return false;

        return this.splitPowerLevel < MAX_SPLITPOWER_LEVEL;
    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel() {

        if (!MSEConfig.statusSplitPower) return MAX_SPLITPOWER_LEVEL;

        return this.splitPowerSaturationLevel;
    }

    @SideOnly(Side.CLIENT)
    public void setSplitPowerLevel(int par1) {
        this.splitPowerLevel = par1;
    }

    @SideOnly(Side.CLIENT)
    public void setSplitPowerSaturationLevel(float par1) {
        this.splitPowerSaturationLevel = par1;
    }

    @SideOnly(Side.SERVER)
    public int getLastSplitPowerLevel() {
        return lastSplitPowerLevel;
    }

    @SideOnly(Side.SERVER)
    public void setLastSplitPowerLevel(int lastSplitPowerLevel) {
        this.lastSplitPowerLevel = lastSplitPowerLevel;
    }

    @SideOnly(Side.SERVER)
    public float getLastExhaustionLevel() {
        return lastSaturationLevel;
    }

    @SideOnly(Side.SERVER)
    public void setLastExhaustionLevel(float lastExhaustionLevel) {
        this.lastSaturationLevel = lastExhaustionLevel;
    }


}
