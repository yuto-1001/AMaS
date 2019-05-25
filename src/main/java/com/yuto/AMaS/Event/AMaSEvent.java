package com.yuto.AMaS.Event;

import com.yuto.AMaS.Player.EntityPlayerManager;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class AMaSEvent {
	public static void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(EntityPlayerManager.instance);
        FMLCommonHandler.instance().bus().register(EntityPlayerManager.instance);
        MinecraftForge.EVENT_BUS.register(new PlayerEvent());
		if (event.getSide().isClient()) {
	        MinecraftForge.EVENT_BUS.register(new HUDEventHandler());
	    }
	}
}
