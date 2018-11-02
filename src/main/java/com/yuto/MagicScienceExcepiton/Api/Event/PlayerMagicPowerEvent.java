package com.yuto.MagicScienceExcepiton.Api.Event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerMagicPowerEvent extends PlayerEvent {

	public final int magicLevel;
    public final float magicSaturationLevel;

    public PlayerMagicPowerEvent(EntityPlayer player, int level, float saturation) {
        super(player);
        this.magicLevel = level;
        this.magicSaturationLevel = saturation;
    }

    @Cancelable
    public static class Add extends PlayerMagicPowerEvent {

        public final int addmagicLevel;
        public final float addmagicSaturationLevel;

        public int newAddSplitPowerLevel;
        public float newAddSplitPowerSaturationLevel;

        public Add(EntityPlayer player, int level, float saturation, int addmagicLevel, float addmagicSaturationLevel) {
            super(player, level, saturation);
            this.addmagicLevel = addmagicLevel;
            this.addmagicSaturationLevel = addmagicSaturationLevel;
            this.newAddSplitPowerLevel = addmagicLevel;
            this.newAddSplitPowerSaturationLevel = addmagicSaturationLevel;
        }
    }

    @Cancelable
    public static class Exhaustion extends PlayerMagicPowerEvent {

        public final float exhaustionLevel;

        public float newExhaustionLevel;

        public Exhaustion(EntityPlayer player, int level, float saturation, float exhaustionLevel) {
            super(player, level, saturation);
            this.exhaustionLevel = level;
            this.newExhaustionLevel = exhaustionLevel;
        }
    }

}
