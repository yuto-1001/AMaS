package com.yuto.Scientificmagicmod.Proxy;

import com.yuto.Scientificmagicmod.Client.Render.ScytheRender;
import com.yuto.Scientificmagicmod.Entity.EntityDeathScythe;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy {
	@Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathScythe.class, new ScytheRender());
	}
}
