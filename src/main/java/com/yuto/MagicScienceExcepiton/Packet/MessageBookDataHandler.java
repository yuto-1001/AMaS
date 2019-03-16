package com.yuto.MagicScienceExcepiton.Packet;

import com.yuto.MagicScienceExcepiton.Item.MagicScienceExceptionItem;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class MessageBookDataHandler implements IMessageHandler<MessageBookData, IMessage> {
	@Override
	public IMessage onMessage(MessageBookData message, MessageContext ctx) {
		EntityPlayer player =  ctx.getServerHandler().playerEntity;
		ItemStack itemstack = player.inventory.getCurrentItem();

		if (itemstack == null)
        {
            return null;
        }

        if (message.data != null)
        {
            if (itemstack.getItem() == MagicScienceExceptionItem.Grimoire){
                itemstack.setTagInfo("pages", message.data.getTagList("pages", 8));
            }
        }
		return null;
	}

}
