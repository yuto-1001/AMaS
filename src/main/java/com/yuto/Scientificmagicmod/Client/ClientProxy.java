package com.yuto.Scientificmagicmod.Client;

import com.yuto.Scientificmagicmod.CommonProxy;
import com.yuto.Scientificmagicmod.Client.Render.ScytheRender;
import com.yuto.Scientificmagicmod.Entity.EntityDeathScythe;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{

	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathScythe.class, new ScytheRender());
	}

}
