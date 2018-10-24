package com.yuto.Scientificmagicmod.Event;

import com.yuto.Scientificmagicmod.Player.EntityPlayerManager;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.common.MinecraftForge;

public class SMEvent {
	public static void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(EntityPlayerManager.instance);
        FMLCommonHandler.instance().bus().register(EntityPlayerManager.instance);

		if (event.getSide().isClient()) {
	        MinecraftForge.EVENT_BUS.register(new HUDEventHandler());
	    }
	}
}
