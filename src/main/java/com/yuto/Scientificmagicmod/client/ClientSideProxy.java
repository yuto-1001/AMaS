package com.yuto.Scientificmagicmod.client;

import com.yuto.Scientificmagicmod.CommonSideProxy;
import com.yuto.Scientificmagicmod.client.Render.ScytheRender;
import com.yuto.Scientificmagicmod.entity.EntityDeathScythe;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.world.World;

public class ClientSideProxy extends CommonSideProxy{

	public World getClientWorld()
	{
		return FMLClientHandler.instance().getClient().theWorld;
	}

	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathScythe.class, new ScytheRender());
	}

}
