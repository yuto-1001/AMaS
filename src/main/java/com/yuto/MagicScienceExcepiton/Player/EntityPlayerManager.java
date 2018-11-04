package com.yuto.MagicScienceExcepiton.Player;

import com.yuto.MagicScienceExcepiton.Api.IPlayerManager;
import com.yuto.MagicScienceExcepiton.Packet.PacketPlayerData;
import com.yuto.MagicScienceExcepiton.Packet.SMPacketHandler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import cpw.mods.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerDropsEvent;

public class EntityPlayerManager implements IPlayerManager {

    private final static String key = "magicscienceexception";

    private final static int MAX_SPLITPOWER_LEVEL = 20;
    private final static int MAX_PREVSPLITPOWER_LEVEL = 20;

    private final static int MAX_MAGICPOWER_LEVEL = 100;
    private final static int MAX_PREV_MAGICPOWER_LEVEL = 20;

    public static EntityPlayerManager instance = new EntityPlayerManager();

    private EntityPlayerManager() {

    }

    @Override
    public void addSplitPowerStats(EntityPlayer entityPlayer, int par1, float par2) {
        if (!entityPlayer.worldObj.isRemote) {

            getSplitPowerStats(entityPlayer).addStats(entityPlayer, par1, par2);
            //Achievement
            //entityPlayer.addStat(SSAchievement.SplitPower, 1);
        }
    }

    @Override
    public void addMagicPowerStats(EntityPlayer entityPlayer, int par1, float par2) {
        if (!entityPlayer.worldObj.isRemote) {
            getMagicPowerStats(entityPlayer).addStats(entityPlayer, par1, par2);
        }
    }

    @Override
    public void addMagicPowerExhaustion(EntityPlayer entityPlayer, float par1) {
        if (!entityPlayer.worldObj.isRemote) {
            getMagicPowerStats(entityPlayer).addExhaustion(entityPlayer, par1);
        }
    }

    @Override
    public int getSplitPowerLevel(EntityPlayer entityPlayer) {
        return getSplitPowerStats(entityPlayer).getSplitPowerLevel();
    }

    @Override
    public int getMagicPowerLevel(EntityPlayer entityPlayer) {
        return getMagicPowerStats(entityPlayer).getMagicPowerLevel();
    }

    //触らない

    //tick
    @SubscribeEvent
    public void LivingUpdateEvent(LivingUpdateEvent event) {
        if (!event.entityLiving.worldObj.isRemote && event.entityLiving instanceof EntityPlayer) {

            onUpdateEntity((EntityPlayer) event.entityLiving);

        }

    }

    public static void onUpdateEntity(EntityPlayer entityPlayer) {

        getCustomPlayerData(entityPlayer).onUpdateEntity(entityPlayer);



    }

    @SubscribeEvent
    public void onPlayerDropsEvent(PlayerDropsEvent event) {
        if (!event.entityPlayer.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory")) {
            //this.getCustomPlayerData(event.entityPlayer).getEquipmentStats().inventory.dropAllItems(event.entityPlayer);
        }

    }

    @SubscribeEvent
    public void onPlayerCloneEvent(net.minecraftforge.event.entity.player.PlayerEvent.Clone event) {

        //死んでいない場合
        if (!event.wasDeath) {

            return;
        }
    }

    private void oneton() {

    }

    /** Playerのデータの登録*/
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event) {
        if (event.entity instanceof EntityPlayer) {
            EntityPlayerManager.register((EntityPlayer) event.entity);
        }
    }

    public static void register(EntityPlayer entityPlayer) {
        entityPlayer.registerExtendedProperties(key, new CustomPlayerData());
    }

    public static CustomPlayerData getCustomPlayerData(EntityPlayer entityPlayer) {
        return (CustomPlayerData) entityPlayer.getExtendedProperties(key);
    }

    public static SplitPowerStats getSplitPowerStats(EntityPlayer entityPlayer) {
        return getCustomPlayerData(entityPlayer).getSplitPower();


    }

    public static MagicPowerStats getMagicPowerStats(EntityPlayer entityPlayer) {

    	return getCustomPlayerData(entityPlayer).getMagicPower();


    }


    //GUI用
    public static int getPrevSplitPowerLevel(EntityPlayer entityPlayer) {

        return getSplitPowerStats(entityPlayer).getSplitPowerLevel() / (MAX_SPLITPOWER_LEVEL / MAX_PREVSPLITPOWER_LEVEL);

    }

    public static int getPrevMagicPowerLevel(EntityPlayer entityPlayer) {

        return getMagicPowerStats(entityPlayer).getMagicPowerLevel() / (MAX_MAGICPOWER_LEVEL / MAX_PREV_MAGICPOWER_LEVEL);

    }



    @SubscribeEvent
    /* ワールドに入った時に呼ばれるイベント。 */
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {

        if (!event.entity.worldObj.isRemote && event.entity instanceof EntityPlayer) {

            SMPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this.getCustomPlayerData((EntityPlayer) event.entity)), (EntityPlayerMP) event.entity);

            this.sendOtherPlayer((EntityPlayer) event.entity);

        }
    }

    @SubscribeEvent
    public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
        //プレイヤーがディメンション間を移動したときの処理

        if (!event.player.worldObj.isRemote) {
            SMPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this.getCustomPlayerData(event.player)), (EntityPlayerMP) event.player);

            this.sendOtherPlayer(event.player);
        }

    }

    @SubscribeEvent
    public void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event) {
        //プレイヤーがリスポーンした時の処理
        //System.out.println("onPlayerRespawn");
        if (!event.player.worldObj.isRemote) {

            SMPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this.getCustomPlayerData(event.player)), (EntityPlayerMP) event.player);

            this.sendOtherPlayer(event.player);
        }

    }

    public void sendOtherPlayer(EntityPlayer player) {
        //他のプレイヤーに送る
        PacketPlayerData d = new PacketPlayerData(this.getCustomPlayerData(player));
        d.getData().setString("uuid", player.getUniqueID().toString());
        SMPacketHandler.INSTANCE.sendToAllAround(d, new TargetPoint(player.dimension, player.posX, player.posY, player.posZ, 64));
    }

}