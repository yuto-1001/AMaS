package com.yuto.AMaS.Event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBook;
import net.minecraft.item.ItemEditableBook;
import net.minecraft.item.ItemEnchantedBook;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class PlayerEvent {
	@SubscribeEvent
	public void playerAttackedWithBook(AttackEntityEvent event) {
		EntityPlayer player = event.entityPlayer;
		if(player != null) {
			Item heldItem = player.getHeldItem().getItem();
			if(heldItem instanceof ItemBook || heldItem instanceof ItemEnchantedBook || heldItem instanceof ItemEditableBook) {
				event.target.attackEntityFrom(DamageSource.starve, 7.0F);
			}
		}
	}
}
