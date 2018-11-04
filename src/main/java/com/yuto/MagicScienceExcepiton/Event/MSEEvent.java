package com.yuto.MagicScienceExcepiton.Event;

import com.yuto.MagicScienceExcepiton.Player.EntityPlayerManager;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class MSEEvent {
	public static void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(EntityPlayerManager.instance);
        FMLCommonHandler.instance().bus().register(EntityPlayerManager.instance);
        MinecraftForge.EVENT_BUS.register(new GrassBrokenEvent());
		if (event.getSide().isClient()) {
	        MinecraftForge.EVENT_BUS.register(new HUDEventHandler());
	    }
	}
}
