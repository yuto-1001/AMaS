package com.yuto.AMaS.Packet;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class AMaSPacketHandler {
	public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("MagicScience");

    public static void init(FMLPreInitializationEvent event) {
        /*
         * Messageクラスの登録。 第一引数：IMessageHandlerクラス 第二引数：送るMessageクラス
         * 第三引数：登録番号。255個まで 第四引数：ClientとServerのどちらに送るか。送り先
         */
        INSTANCE.registerMessage(MassagePlayer.class, PacketPlayerData.class, 0, Side.CLIENT);

        INSTANCE.registerMessage(MessageBookDataHandler.class, MessageBookData.class, 1, Side.SERVER);

    }
}
