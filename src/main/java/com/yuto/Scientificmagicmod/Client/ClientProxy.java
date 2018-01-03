package com.yuto.Scientificmagicmod.Client;

import com.yuto.Scientificmagicmod.CommonProxy;
import com.yuto.Scientificmagicmod.Client.Render.ScytheRender;
import com.yuto.Scientificmagicmod.Entity.EntityDeathScythe;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy{

	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathScythe.class, new ScytheRender());
	}

}
