package com.yuto.Scientificmagicmod.Packet;

import java.util.List;
import java.util.UUID;

import com.yuto.Scientificmagicmod.ScientificmagicMod;
import com.yuto.Scientificmagicmod.Player.CustomPlayerData;
import com.yuto.Scientificmagicmod.Player.EntityPlayerManager;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class MassagePlayer implements IMessageHandler<PacketPlayerData, IMessage> {

	@Override
    public IMessage onMessage(PacketPlayerData message, MessageContext ctx) {

        EntityPlayer p = this.getPlayer(message);

        if (p == null) return null;

        CustomPlayerData data = EntityPlayerManager.getCustomPlayerData(p);

        data.loadNBTData(message.getData());


        return null;

    }

    public EntityPlayer getPlayer(PacketPlayerData message) {

        NBTTagCompound nbt = message.getData();

        if (nbt.hasKey("uuid")) {

            UUID uuid = UUID.fromString(nbt.getString("uuid"));

            //自分自身は更新しない
            if (uuid.equals(ScientificmagicMod.INSTANCE.proxy.getClientPlayer().getUniqueID())) return null;

            List<EntityPlayer> player = ScientificmagicMod.INSTANCE.proxy.getClientPlayer().worldObj.playerEntities;

            for (EntityPlayer p : player) {
                if (p.getUniqueID().equals(uuid)) {

                    return p;

                }
            }

        } else {
            return ScientificmagicMod.INSTANCE.proxy.getClientPlayer();
        }

        return null;

    }

}
