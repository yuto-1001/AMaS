package com.yuto.Scientificmagicmod;

import java.io.IOException;

import com.yuto.Scientificmagicmod.Block.ScientificmagicBlock;
import com.yuto.Scientificmagicmod.Items.ScientificmagicItems;
import com.yuto.Scientificmagicmod.client.CommonSideProxy;
import com.yuto.Scientificmagicmod.entity.EntityDeathScythe;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

@Mod(
	modid = ScientificmagicMod.MODID,
	name = ScientificmagicMod.MODNAME,
	version = ScientificmagicMod.VERSION,
	dependencies = "required-after:Forge@[10.12.1.1090,)",
	useMetadata = true)

public class ScientificmagicMod {
	public static final String MODID = "ScientificmagicMod";
	public static final String MODNAME = "ScientificmagicMod";
	public static final String VERSION = "1.0.0";
	@Mod.Instance(MODID)
	public static ScientificmagicMod INSTANCE;
	public static final int GUI_ID = 0;
	@SidedProxy(clientSide = "com.yuto.Scientificmagicmod.client.ClientSideProxy",
            	serverSide = "com.yuto.Scientificmagicmod.client.CommonSideProxy")
	public static CommonSideProxy proxy;
	@EventHandler
	public void Perinit(FMLInitializationEvent e){
		ScientificmagicItems.registry( this );
		ScientificmagicBlock.registry( this );
		Recipes.registry();
		GameRegistry.registerWorldGenerator(new ScientificmagicmodGen(), 0  );
	}
	@EventHandler
	public void registerRenderers(){
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathScythe.class, new ScytheRender(new ScytheModel()));
	}
	@EventHandler
	public void init1(FMLInitializationEvent event) throws IOException {

		// entity
		proxy.registerRenderers();
		int id = 1;
		EntityRegistry.registerModEntity(EntityDeathScythe.class, "Entity_DeathScythe", id, this, 128, 5, true);
    }
	@EventHandler
	public void init(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
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
