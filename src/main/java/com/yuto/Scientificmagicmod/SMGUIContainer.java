package com.yuto.Scientificmagicmod;

import com.yuto.Scientificmagicmod.Block.TileEntityThermalFurnace;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.ResourceLocation;

public class SMGUIContainer extends GuiContainer {
	private static final ResourceLocation TEXTURE = new ResourceLocation("scientificmagicmod","textures/gui/Thermalfurnace.png");
	private TileEntityThermalFurnace tileEntity;
	private static final int index0 = 0;
	private static final int index1 = 1;
	private static final int index2 = 27;
    public SMGUIContainer(int x, int y, int z) {
        super(new SMContainer(x, y, z));
    }

    /*GUIの文字等の描画処理*/
    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseZ) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseZ);
        fontRendererObj.drawString("文字", 80, 53, 0x000000);
    }

    /*GUIの背景の描画処理*/
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseZ) {
        this.mc.renderEngine.bindTexture(TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, xSize, ySize);
    }

    /*GUIが開いている時にゲームの処理を止めるかどうか。*/
    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
    /*
    public SMGUIContainer(EntityPlayer player, TileEntityThermalFurnace tileEntity) {
		super(new ContainerTF(player, tileEntity));
		this.tileEntity = tileEntity;
		for (int iy = 0 ; iy < 1 ; iy ++){
			for (int ix = 0; ix < 1; ix++) {
				this.addSlotToContainer(new Slot(tileEntity, 0, ix, iy ,);
			}
		}
		for (int iy = 0; iy < 3; iy++) {
			for (int ix = 0; ix < 9; ix++) {
				this.addSlotToContainer(new Slot(player.inventory, ix + (iy * 9) + 9, 8 + (ix * 18), 140 + (iy * 18)));
			}
		}
		for (int ix = 0; ix < 9; ix++) {
			this.addSlotToContainer(new Slot(player.inventory, ix, 8 + (ix * 18), 198));
		}
	}*/

}