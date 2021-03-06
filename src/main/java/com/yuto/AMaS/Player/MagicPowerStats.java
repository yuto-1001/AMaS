package com.yuto.AMaS.Player;

import com.yuto.AMaS.AMaSConfig;
import com.yuto.AMaS.Api.AMaSAPI;
import com.yuto.AMaS.Api.Event.PlayerMagicPowerEvent;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraftforge.common.MinecraftForge;

public class MagicPowerStats {
	//魔力
    /** 魔力 */
    private int MagicPowerLevel = 0;
    private final static int MAX_MagicPower_LEVEL = 100;
    private final static int MAX_PREV_STAMINA_LEVEL = 100;

    /** 隠し魔力ゲージ */
    private float MagicPowerSaturationLevel = 5.0F;

    //Packet用
    private int lastMagicPowerLevel = 0;
    private float lastSaturationLevel = 0;

    /** 魔力不足レベル これが増えると魔力ゲージが減る */
    private float MagicPowerExhaustionLevel;

    /** The player's food timer value. */
    private int MagicPowerTimer;
    private int prevMagicPowerLevel = 0;

    /**
     * Args: int foodLevel, float foodSaturationModifier
     */
    public void addStats(EntityPlayer entityPlayer, int par1, float par2) {

        if (!AMaSConfig.statusMagicPower) return;


        PlayerMagicPowerEvent.Add event = new PlayerMagicPowerEvent.Add(entityPlayer, MagicPowerLevel, MagicPowerSaturationLevel, par1, par2);

        if (MinecraftForge.EVENT_BUS.post(event)) {
        } else {
            this.MagicPowerLevel = MathHelper.clamp_int(event.addmagicLevel + this.MagicPowerLevel, 0, MAX_MagicPower_LEVEL);
            this.MagicPowerSaturationLevel = MathHelper.clamp_float(event.addmagicSaturationLevel + this.MagicPowerSaturationLevel, 0.0F, this.MagicPowerLevel);
        }

    }

    /**
     * Handles the MaicPower game logic.
     */
    public void onUpdate(EntityPlayer par1EntityPlayer) {
    	//ここ書き換えて、MagicPowerExhautionLevel分だけMagicPowerLevelを減らすようにする。
        if (!AMaSConfig.statusMagicPower) return;
        EnumDifficulty i = par1EntityPlayer.worldObj.difficultySetting;
        this.prevMagicPowerLevel = this.MagicPowerLevel;
        //ゲームモードがピースフルではない。もしくは、コンフィグで、ピースフルでの魔力消費がオンになってるか。
        if (i.getDifficultyId() > 0 || AMaSConfig.peacefulMagicPower) {
         	//もとの魔力 - 減らす分
        	this.MagicPowerLevel -= this.MagicPowerExhaustionLevel;
            this.MagicPowerExhaustionLevel = 0;
        }
        if(AMaSAPI.getSplitPowerLevel(par1EntityPlayer) * 5 > this.MagicPowerLevel) {
	        if(this.MagicPowerTimer >= 20) {
	        	this.MagicPowerLevel += 1;
	        	this.MagicPowerTimer = 0;
	        }else {
	        	this.MagicPowerTimer ++;
	        }
        }
    }

    /**
     * Reads food stats from an NBT object.
     */
    public void readNBT(NBTTagCompound par1NBTTagCompound) {
        if (par1NBTTagCompound.hasKey("MagicPowerlevel")) {
            this.MagicPowerLevel = par1NBTTagCompound.getInteger("MagicPowerlevel");
            this.MagicPowerTimer = par1NBTTagCompound.getInteger("MagicPowerticktimer");
            this.MagicPowerSaturationLevel = par1NBTTagCompound.getFloat("MagicPowersaturationlevel");
            this.MagicPowerExhaustionLevel = par1NBTTagCompound.getFloat("MagicPowerexhaustionlevel");
        }
    }

    /**
     * Writes food stats to an NBT object.
     */
    public void writeNBT(NBTTagCompound par1NBTTagCompound) {
        par1NBTTagCompound.setInteger("MagicPowerlevel", this.MagicPowerLevel);
        par1NBTTagCompound.setInteger("MagicPowerticktimer", this.MagicPowerTimer);
        par1NBTTagCompound.setFloat("MagicPowersaturationlevel", this.MagicPowerSaturationLevel);
        par1NBTTagCompound.setFloat("MagicPowerexhaustionlevel", this.MagicPowerExhaustionLevel);
    }

    public boolean isPacket() {

        if (!AMaSConfig.statusMagicPower) return false;

        boolean flag = false;

        if (this.MagicPowerLevel != this.lastMagicPowerLevel) {
            flag = true;
            this.lastMagicPowerLevel = this.MagicPowerLevel;
        }

        if (this.MagicPowerSaturationLevel != this.lastSaturationLevel) {

            flag = true;
            this.lastSaturationLevel = this.MagicPowerSaturationLevel;

        }

        return flag;

    }

    /**
     * Get the player's MagicPower level.
     */
    public int getMagicPowerLevel() {

        if (!AMaSConfig.statusMagicPower) return MAX_MagicPower_LEVEL;

        return this.MagicPowerLevel;
    }

    @SideOnly(Side.CLIENT)
    public int getPrevMagicPowerLevel() {
        return this.prevMagicPowerLevel;
    }

    /**
     * If foodLevel is not max.
     */
    public boolean needMagicPower() {

        if (!AMaSConfig.statusMagicPower) return false;

        return this.MagicPowerLevel < MAX_MagicPower_LEVEL;
    }

    /**
     * adds input to foodExhaustionLevel to a max of 40
     */
    public void addExhaustion(EntityPlayer entityPlayer, float par1) {

        if (!AMaSConfig.statusMagicPower) return;

        PlayerMagicPowerEvent.Exhaustion event = new PlayerMagicPowerEvent.Exhaustion(entityPlayer, MagicPowerLevel, MagicPowerExhaustionLevel, par1);

        if (MinecraftForge.EVENT_BUS.post(event)) {
            //this.MagicPowerLevel = prevLevel;
            //this.MagicPowerSaturationLevel = prevSaturation;
        } else {
            this.MagicPowerExhaustionLevel = MathHelper.clamp_float(event.newExhaustionLevel + this.MagicPowerExhaustionLevel, 0, 40.0F);
        }

    }

    /**
     * Get the player's food saturation level.
     */
    public float getSaturationLevel() {

        //if (!SSConfig.statusMagicPower) return MAX_MagicPower_LEVEL;

        return this.MagicPowerSaturationLevel;
    }

    @SideOnly(Side.CLIENT)
    public void setMagicPowerLevel(int par1) {
        this.MagicPowerLevel = par1;
    }

    @SideOnly(Side.CLIENT)
    public void setMagicPowerSaturationLevel(float par1) {
        this.MagicPowerSaturationLevel = par1;
    }

    @SideOnly(Side.SERVER)
    public int getLastMagicPowerLevel() {
        return lastMagicPowerLevel;
    }

    @SideOnly(Side.SERVER)
    public void setLastMagicPowerLevel(int lastMagicPowerLevel) {
        this.lastMagicPowerLevel = lastMagicPowerLevel;
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
