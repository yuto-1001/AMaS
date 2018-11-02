package com.yuto.MagicScienceExcepiton.Client;

import com.yuto.MagicScienceExcepiton.Client.Render.ScytheRender;
import com.yuto.MagicScienceExcepiton.Entity.EntityDeathScythe;
import com.yuto.MagicScienceExcepiton.Proxy.CommonProxy;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy{

	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathScythe.class, new ScytheRender());
	}

}
