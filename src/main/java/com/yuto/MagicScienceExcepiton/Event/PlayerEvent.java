package com.yuto.MagicScienceExcepiton.Event;

import com.yuto.MagicScienceExcepiton.Item.MagicScienceExceptionItem;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

public class PlayerEvent {
	@SubscribeEvent
	public void playerAttackedEvent(LivingAttackEvent LAEvent) {
		if(LAEvent.entityLiving != null && LAEvent.entityLiving instanceof EntityPlayer) {
			EntityPlayer entity = (EntityPlayer) LAEvent.entityLiving;
			if(!entity.isDead && entity.getHeldItem() != null && entity.getHeldItem().getItem() == MagicScienceExceptionItem.Kamigoroshi) {
				ItemStack useInItem = entity.getItemInUse();
				if(useInItem != null && MagicScienceExceptionItem.Kamigoroshi.useMagicPower(entity)) {
					LAEvent.setCanceled(true);
				}
			}
		}
	}

}
