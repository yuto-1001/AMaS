package com.yuto.MagicScienceExcepiton.Gui;

import org.lwjgl.opengl.GL11;

import com.yuto.MagicScienceExcepiton.Entity.TileEntity.TileEntityPowderMill;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiPowderMill extends GuiContainer{
	 private static final ResourceLocation Textures = new ResourceLocation("magicscienceexception", "textures/gui/PowderMill.png");
	    private TileEntityPowderMill tileEntity;
	    private static final String __OBFID = "CL_00000758";

	    public GuiPowderMill(InventoryPlayer p_i1091_1_, TileEntityPowderMill p_i1091_2_)
	    {
	        super(new ContainerPowderMill(p_i1091_1_, p_i1091_2_));
	        this.tileEntity = p_i1091_2_;
	    }

	    /**
	     * Draw the foreground layer for the GuiContainer (everything in front of the items)
	     */
	    protected void drawGuiContainerForegroundLayer(int p_146979_1_, int p_146979_2_)
	    {
	        String s = this.tileEntity.hasCustomInventoryName() ? this.tileEntity.getInventoryName() : I18n.format(this.tileEntity.getInventoryName(), new Object[0]);
	        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
	        this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 96 + 2, 4210752);
	    }

	    protected void drawGuiContainerBackgroundLayer(float p_146976_1_, int p_146976_2_, int p_146976_3_)
	    {
	        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
	        this.mc.getTextureManager().bindTexture(Textures);
	        int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

	        if (this.tileEntity.isUsingEnergy())
	        {
	            int i1 = this.tileEntity.getBurnTimeRemainingScaled(13);
	            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
	            i1 = this.tileEntity.getCookProgressScaled(24);
	            this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	        }
	    }
}
