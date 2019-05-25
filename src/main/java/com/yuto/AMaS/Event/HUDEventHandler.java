package com.yuto.AMaS.Event;

import java.util.Random;

import com.yuto.AMaS.AMaSConfig;
import com.yuto.AMaS.Player.EntityPlayerManager;
import com.yuto.AMaS.Player.SplitPowerStats;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class HUDEventHandler {
	public static final ResourceLocation icons = new ResourceLocation("magicscienceexception:textures/gui/icons.png");

    public static int left_height = 39;
    public static int right_height = 39;

    protected final Random rand = new Random();
    public static boolean visibleMagicPower = false;
    public static boolean visibleSplitPower = false;

    public static Minecraft mc = FMLClientHandler.instance().getClient();


    //描写のEvent
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderGameOverlayEventPre(RenderGameOverlayEvent.Pre event) {

        int width = event.resolution.getScaledWidth();
        int height = event.resolution.getScaledHeight();


        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            visibleMagicPower = GuiIngameForge.renderFood | GuiIngameForge.renderHealthMount | GuiIngameForge.renderHealth;
            visibleSplitPower = GuiIngameForge.renderFood | GuiIngameForge.renderHealthMount | GuiIngameForge.renderHealth;
        } else if (event.type == RenderGameOverlayEvent.ElementType.ARMOR) {
            if (visibleMagicPower) {
                renderMagicPower(width, height);
                visibleMagicPower = false;
            }
        } else if (event.type == RenderGameOverlayEvent.ElementType.HOTBAR && mc.playerController.shouldDrawHUD()) {
            if (visibleSplitPower) {
                renderSplitPower(width, height);
                visibleSplitPower = false;
            }
            if (visibleMagicPower) {
                renderMagicPower(width, height);
                visibleMagicPower = false;
            }
        }


    }

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onRenderGameOverlayEventPost(RenderGameOverlayEvent.Post event) {

        int width = event.resolution.getScaledWidth();
        int height = event.resolution.getScaledHeight();

        if (event.type == RenderGameOverlayEvent.ElementType.FOOD || event.type == RenderGameOverlayEvent.ElementType.HEALTHMOUNT) {
            if (visibleSplitPower) {
                renderSplitPower(width, height);
                visibleSplitPower = false;
            }
            if (visibleMagicPower) {
                renderMagicPower(width, height);
                visibleMagicPower = false;
            }

        }

    }

    protected void renderSplitPower(int width, int height) {

        if (!visibleSplitPower || !AMaSConfig.statusSplitPower) return;
        visibleSplitPower = false;

        mc.mcProfiler.startSection("moisture");

        bind(icons);

        int updateCounter = mc.ingameGUI.getUpdateCounter() + 2;

        int left = width / 2 + 91;
        int top = height - GuiIngameForge.right_height;

        GuiIngameForge.right_height += 10;
        boolean unused = false;

        SplitPowerStats stats = EntityPlayerManager.getSplitPowerStats((EntityPlayer) mc.thePlayer);
        int level = EntityPlayerManager.getPrevSplitPowerLevel((EntityClientPlayerMP) mc.thePlayer);

        for (int i = 0; i < 10; ++i) {
            int idx = i * 2 + 1;
            int x = left - i * 8 - 9;
            int y = top;
            int icon = 0;
            byte backgound = 0;

            int iconY = 0;
            int iconX = 0;

            if (mc.thePlayer.isPotionActive(Potion.hunger)) {
                iconX += 27;
                backgound = 3;
            }

            if (unused) backgound = 1;

            if (stats.getSaturationLevel() <= 0.0F && updateCounter % (level * 3 + 1) == 0) {
                y = top + rand.nextInt(3) - 1;
            }

            drawTexturedModalRect(x, y, iconX, iconY, 9, 9);


            if (idx < level) {
                drawTexturedModalRect(x, y, iconX + 9, iconY, 9, 9);
            } else if (idx == level) {
                drawTexturedModalRect(x, y, iconX + 18, iconY, 9, 9);
            }
        }

        mc.mcProfiler.endSection();
        bind(Gui.icons);

    }

    protected void renderMagicPower(int width, int height) {

        if (!visibleMagicPower || !AMaSConfig.statusMagicPower) return;
        visibleMagicPower = false;

        mc.mcProfiler.startSection("stamina");

        bind(icons);

        int updateCounter = mc.ingameGUI.getUpdateCounter() + 1;

        int left = width / 2 - 82;
        int top = height - GuiIngameForge.left_height;
        GuiIngameForge.left_height += 10;
        boolean unused = false;

        int level = EntityPlayerManager.instance.getMagicPowerLevel(mc.thePlayer);

        for (int i = 0; i < 10; ++i) {
            //int idx = i * 2 + 1;
            int idx = (i + 1) * 10;
            int idx2 = level % 10;
            int x = left + i * 8 - 9;
            int y = top;
            int icon = 0;
            byte backgound = 0;

            int iconY = 9;
            int iconX = 0;

            if (unused) backgound = 1;

            drawTexturedModalRect(x, y, icon + backgound * 9, iconY, 9, 9);


            if (idx <= level) {
                drawTexturedModalRect(x, y, icon + 9, iconY, 9, 9);
            } else if (idx - level < 10 && idx2 != 0) {
                drawTexturedModalRect(x, y, icon + 9 * (idx2 + 1), iconY, 9, 9);
            }
        }

        mc.mcProfiler.endSection();
        bind(Gui.icons);

    }

    private void bind(ResourceLocation res) {
        FMLClientHandler.instance().getClient().getTextureManager().bindTexture(res);
    }

    /** 描写位でのX Y テクスチャでのX Y 大きさ X Y */
    public void drawTexturedModalRect(int par1, int par2, int par3, int par4, int par5, int par6) {
        float zLevel = -90.0F;

        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(par1 + 0, par2 + par6, zLevel, (par3 + 0) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + par6, zLevel, (par3 + par5) * f, (par4 + par6) * f1);
        tessellator.addVertexWithUV(par1 + par5, par2 + 0, zLevel, (par3 + par5) * f, (par4 + 0) * f1);
        tessellator.addVertexWithUV(par1 + 0, par2 + 0, zLevel, (par3 + 0) * f, (par4 + 0) * f1);
        tessellator.draw();
    }

}
