package com.yuto.Scientificmagicmod;

import com.yuto.Scientificmagicmod.Api.ScientificMagicAPI;
import com.yuto.Scientificmagicmod.Block.ScientificmagicBlock;
import com.yuto.Scientificmagicmod.Crafting.Recipes;
import com.yuto.Scientificmagicmod.Entity.EntityDeathScythe;
import com.yuto.Scientificmagicmod.Entity.TileEntity.TileEntityPowderMill;
import com.yuto.Scientificmagicmod.Event.SMEvent;
import com.yuto.Scientificmagicmod.Gui.GuiHandler;
import com.yuto.Scientificmagicmod.Items.ScientificmagicItems;
import com.yuto.Scientificmagicmod.Packet.SMPacketHandler;
import com.yuto.Scientificmagicmod.Player.EntityPlayerManager;
import com.yuto.Scientificmagicmod.Proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@Mod(
	modid = ScientificmagicMod.MODID,
	name = ScientificmagicMod.MODNAME,
	version = ScientificmagicMod.VERSION,
	dependencies = "required-after:Forge@10.12.1.1090,)",
	useMetadata = true)

public class ScientificmagicMod {
	@SidedProxy(clientSide = "com.yuto.Scientificmagicmod.Proxy.ClientProxy",
				serverSide = "com.yuto.Scientificmagicmod.Proxy.CommonProxy")
	public static CommonProxy proxy;

	public static final String MODID = "ScientificmagicMod";
	public static final String MODNAME = "ScientificmagicMod";
	public static final String VERSION = "1.3.2";

	@Mod.Instance(MODID)
	public static ScientificmagicMod INSTANCE;
	public static final int PW = 1;

	@EventHandler
	public void Perinit(FMLInitializationEvent e){
		ScientificmagicItems.registry( this );
		ScientificmagicBlock.registry( this );
		Recipes.registry();
		GameRegistry.registerTileEntity(TileEntityPowderMill.class, "TileEntityPowderMill");
		GameRegistry.registerWorldGenerator(new ScientificmagicmodGen(), 0  );
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
	}
	@EventHandler
	public void init(FMLInitializationEvent event) {
		proxy.registerRenderers();
		EntityRegistry.registerModEntity(EntityDeathScythe.class, "Entity_DeathScythe", 1, this, 128, 5, true);

    }
	@Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
		SMEvent.preInit(event);
		ScientificMagicAPI.playerManager = EntityPlayerManager.instance;
		SMConfig.syncConfig();
		SMPacketHandler.init(event);
	}
	 public static CreativeTabs Scientificmagicmod
     = new CreativeTabs( "scientificmagicmodtab" )
	{
		public Item getTabIconItem()
		{
			return ScientificmagicItems.Redbeaningot;
		}
	};

}
