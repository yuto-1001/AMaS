package com.yuto.MagicScienceExcepiton.Api;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;

/**
 *
 * @author yuto
 *</br>
 * このModの要素にアクセスするためのClass</br>
 * 基本的にここからほかへアクセスするようにしてね！
 *
 */
public class MagicScienceExcepitonAPI {
	/**
	 * ModID
	 */
    public static final String MODID = "ScientificMagic";

    /**
     * CreativeTabs クリエイティブタブ
     */
    public static CreativeTabs ScientificMagicModTab;

    /**
     * Player プレイヤー
     */
    public static IPlayerManager playerManager;

    /**
     * addMagicPowerStats プレイヤーの魔力ゲージを回復します
     * @param entityPlayer プレイヤー
     * @param mp 魔力
     * @param smp 隠し魔力
     */
    public static void addMagicPowerStats(EntityPlayer entityPlayer, int mp, float smp) {
        playerManager.addMagicPowerStats(entityPlayer, mp, smp);
    }

    public static int getMagicPowerLevel(EntityPlayer entityPlayer) {
        return playerManager.getMagicPowerLevel(entityPlayer);
    }

    /**
     * addMagicPowerExhaustion プレイヤーの魔力ゲージを減らします
     * @param entityPlayer プレイヤー
     * @param amount 減らす量(4.0fで1)
     */
    public static void addMagicPowerExhaustion(EntityPlayer entityPlayer, float amount) {
        playerManager.addMagicPowerExhaustion(entityPlayer, amount);
    }

    /**
     * addMagicPowerStats プレイヤーの霊力ゲージを回復します
     * @param entityPlayer プレイヤー
     * @param mp 霊力
     * @param smp 隠し霊力
     */
    public static void addSplitPowerStats(EntityPlayer entityPlayer, int sp, float ssp) {
        playerManager.addSplitPowerStats(entityPlayer, sp, ssp);
    }

    public static int getSplitPowerLevel(EntityPlayer entityPlayer) {
        return playerManager.getSplitPowerLevel(entityPlayer);
    }
}
