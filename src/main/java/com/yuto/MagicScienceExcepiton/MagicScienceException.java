package com.yuto.MagicScienceExcepiton;

import com.yuto.MagicScienceExcepiton.Api.MagicScienceExcepitonAPI;
import com.yuto.MagicScienceExcepiton.Block.MagicScienceExceptionBlock;
import com.yuto.MagicScienceExcepiton.Crafting.Recipes;
import com.yuto.MagicScienceExcepiton.Entity.EntityDeathScythe;
import com.yuto.MagicScienceExcepiton.Entity.TileEntity.TileEntityPowderMill;
import com.yuto.MagicScienceExcepiton.Event.MSEEvent;
import com.yuto.MagicScienceExcepiton.Generate.MagicScienceExceptionGen;
import com.yuto.MagicScienceExcepiton.Gui.GuiHandler;
import com.yuto.MagicScienceExcepiton.Item.MagicScienceExceptionItem;
import com.yuto.MagicScienceExcepiton.Packet.SMPacketHandler;
import com.yuto.MagicScienceExcepiton.Player.EntityPlayerManager;
import com.yuto.MagicScienceExcepiton.Proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(
	modid = MagicScienceException.MODID,
	name = MagicScienceException.MODNAME,
	version = MagicScienceException.VERSION,
	dependencies = "required-after:Forge@10.12.1.1090,)",
	useMetadata = true)

public class MagicScienceException {
	@SidedProxy(clientSide = "com.yuto.MagicScienceExcepiton.Proxy.ClientProxy",
				serverSide = "com.yuto.MagicScienceExcepiton.Proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "MagicScienceExcepiton";
	public static final String MODNAME = "MagicScienceExcepiton";
	public static final String VERSION = "2.0.0++Alpha";

	@Mod.Instance(MODID)
	public static MagicScienceException INSTANCE;
	public static final int PW = 5000;

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		EntityRegistry.registerModEntity(EntityDeathScythe.class, "Entity_DeathScythe", 1, this, 128, 5, true);

    }
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		MSEConfig.syncConfig();
		SMPacketHandler.init(event);
		MSEEvent.preInit(event);
		MagicScienceExcepitonAPI.playerManager = EntityPlayerManager.instance;
		MSECreativeTab.initCreativeTabs();
		MagicScienceExceptionItem.registry( this );
		MagicScienceExceptionBlock.registry( this );
		Recipes.registry();
		GameRegistry.registerTileEntity(TileEntityPowderMill.class, "TileEntityPowderMill");
		GameRegistry.registerWorldGenerator(new MagicScienceExceptionGen(), 0  );
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}

}
