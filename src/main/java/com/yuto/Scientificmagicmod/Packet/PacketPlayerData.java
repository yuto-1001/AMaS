package com.yuto.Scientificmagicmod.Packet;

import com.yuto.Scientificmagicmod.Player.CustomPlayerData;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class PacketPlayerData implements IMessage {

	private NBTTagCompound data;

    public PacketPlayerData() {

    }

    public PacketPlayerData(CustomPlayerData data) {

        NBTTagCompound nbt = new NBTTagCompound();
        data.saveNBTData(nbt);
        this.setData(nbt);

    }

    @Override
    public void fromBytes(ByteBuf buf) {
        setData(ByteBufUtils.readTag(buf));
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, getData());
    }

    public NBTTagCompound getData() {
        return data;
    }

    public void setData(NBTTagCompound data) {
        this.data = data;
    }

}
