package com.yuto.Scientificmagicmod.Event;

import net.minecraftforge.event.entity.living.LivingFallEvent;

public class ScientificMagicEvent {
	//落下ダメージ無効
	public void LivingFallEvent(LivingFallEvent e ){
	    e.distance = 0;
	    return;

	}
}
