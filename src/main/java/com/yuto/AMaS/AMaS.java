package com.yuto.AMaS;

import com.yuto.AMaS.Api.AMaSAPI;
import com.yuto.AMaS.Block.AMaSBlock;
import com.yuto.AMaS.Crafting.Recipes;
import com.yuto.AMaS.Entity.EntityDeathScythe;
import com.yuto.AMaS.Event.AMaSEvent;
import com.yuto.AMaS.Generate.AMaSGen;
import com.yuto.AMaS.Gui.GuiHandler;
import com.yuto.AMaS.Item.AMaSItem;
import com.yuto.AMaS.Packet.AMaSPacketHandler;
import com.yuto.AMaS.Player.EntityPlayerManager;
import com.yuto.AMaS.Proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
	modid = AMaS.MODID,
	name = AMaS.MODNAME,
	version = AMaS.VERSION,
	dependencies = "required-after:Forge@10.12.1.1090,)",
	useMetadata = true)

public class AMaS {
	@SidedProxy(clientSide = "com.yuto.AMaS.Proxy.ClientProxy",
				serverSide = "com.yuto.AMaS.Proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "AMaS";
	public static final String MODNAME = "AMaS";
	public static final String VERSION = "2.0.1Alpha";

	@Mod.Instance(MODID)
	public static AMaS INSTANCE;
	public static final int PW = 5000;

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		EntityRegistry.registerModEntity(EntityDeathScythe.class, "Entity_DeathScythe", 1, this, 128, 5, true);

    }
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		AMaSConfig.syncConfig();
		AMaSPacketHandler.init(event);
		AMaSEvent.preInit(event);
		AMaSAPI.playerManager = EntityPlayerManager.instance;
		AMaSCreativeTab.initCreativeTabs();
		AMaSItem.registry( this );
		AMaSBlock.registry( this );
		Recipes.registry();
		GameRegistry.registerWorldGenerator(new AMaSGen(), 0  );
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

}
