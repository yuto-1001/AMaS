package com.yuto.AMaS.Proxy;

import com.yuto.AMaS.Client.Render.RenderGrimoire;
import com.yuto.AMaS.Client.Render.ScytheRender;
import com.yuto.AMaS.Entity.EntityDeathScythe;
import com.yuto.AMaS.Item.AMaSItem;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.MinecraftForgeClient;

public class ClientProxy extends CommonProxy {
	@Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }
	public void registerRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(EntityDeathScythe.class, new ScytheRender());
		MinecraftForgeClient.registerItemRenderer(AMaSItem.Grimoire, new RenderGrimoire());
	}
}
