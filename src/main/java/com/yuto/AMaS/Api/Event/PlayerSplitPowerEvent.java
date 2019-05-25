package com.yuto.AMaS.Api.Event;

import cpw.mods.fml.common.eventhandler.Cancelable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class PlayerSplitPowerEvent extends PlayerEvent {

    public final int splitLevel;
    public final float splitSaturationLevel;

    public PlayerSplitPowerEvent(EntityPlayer player, int level, float saturation) {
        super(player);
        this.splitLevel = level;
        this.splitSaturationLevel = saturation;
    }

    @Cancelable
    public static class Add extends PlayerSplitPowerEvent {

        public final int addsplitLevel;
        public final float addsplitSaturationLevel;

        public int newAddsplitLevel;
        public float newAddsplitSaturationLevel;

        public Add(EntityPlayer player, int level, float saturation, int addsplitLevel, float addsplitSaturationLevel) {
            super(player, level, addsplitLevel);
            this.addsplitLevel = addsplitLevel;
            this.addsplitSaturationLevel = addsplitSaturationLevel;
            this.newAddsplitLevel = addsplitLevel;
            this.newAddsplitSaturationLevel = addsplitSaturationLevel;
        }
    }

    @Cancelable
    public static class Exhaustion extends PlayerSplitPowerEvent {

        public final float exhaustionLevel;

        public float newExhaustionLevel;

        public Exhaustion(EntityPlayer player, int level, float saturation, float exhaustionLevel) {
            super(player, level, saturation);
            this.exhaustionLevel = level;
            this.newExhaustionLevel = exhaustionLevel;
        }
    }

}