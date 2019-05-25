package com.yuto.AMaS.Packet;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class MessageBookData implements IMessage {
	public NBTTagCompound data;
	public MessageBookData() {}
	public MessageBookData(NBTTagCompound par1) {
        this.data = par1;
    }

    @Override//IMessageのメソッド。ByteBufからデータを読み取る。
    public void fromBytes(ByteBuf buf) {
        this.data = ByteBufUtils.readTag(buf);
    }

    @Override//IMessageのメソッド。ByteBufにデータを書き込む。
    public void toBytes(ByteBuf buf) {
    	ByteBufUtils.writeTag(buf, this.data);
    }
}
