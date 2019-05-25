package com.yuto.AMaS.Gui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.yuto.AMaS.Item.Magic.SpellCard;
import com.yuto.AMaS.Packet.AMaSPacketHandler;
import com.yuto.AMaS.Packet.MessageBookData;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;

public class GuiScreenSpellCard extends GuiScreen {
	private static final Logger logger = LogManager.getLogger();
    private static final ResourceLocation spellGuiTextures = new ResourceLocation("amas", "textures/gui/SpellCard.png");
    /** The player editing the spell */
    private final EntityPlayer editingPlayer;
    private final ItemStack spellObj;
    private boolean field_146481_r;
    private boolean field_146480_s;
    /** Update ticks since the gui was opened */
    private int updateCount;
    private int spellImageWidth = 192;
    private int spellImageHeight = 192;
    private int spellTotalPages = 1;
    private final int currPage = 0;
    private NBTTagList spellPages;
    private GuiButton buttonDone;
    private static final String __OBFID = "CL_00000744";


    public GuiScreenSpellCard(EntityPlayer entityPlayer, ItemStack itemStack){
        this.editingPlayer = entityPlayer;
        this.spellObj = itemStack;
        SpellCard card = (SpellCard) this.spellObj.getItem();
    	card.bookGui = this;

        if (itemStack.hasTagCompound())
        {
            NBTTagCompound nbttagcompound = itemStack.getTagCompound();
            this.spellPages = nbttagcompound.getTagList("pages", 8);

            if (this.spellPages != null)
            {
                this.spellPages = (NBTTagList)this.spellPages.copy();
                this.spellTotalPages = this.spellPages.tagCount();

                if (this.spellTotalPages < 1)
                {
                    this.spellTotalPages = 1;
                }
            }
        }

        if (this.spellPages == null)
        {
            this.spellPages = new NBTTagList();
            this.spellPages.appendTag(new NBTTagString(""));
            this.spellTotalPages = 1;
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen()
    {
        super.updateScreen();
        ++this.updateCount;
    }

    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
        this.buttonList.clear();
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(this.buttonDone = new GuiButton(0, this.width / 2 - 50 , 4 + this.spellImageHeight, 98, 20, I18n.format("gui.done", new Object[0])));
        this.updateButtons();
    }

    /**
     * Called when the screen is unloaded. Used to disable keyboard repeat events
     */
    @Override
    public void onGuiClosed()
    {
        Keyboard.enableRepeatEvents(false);
    }

    private void updateButtons()
    {
        this.buttonDone.visible = !this.field_146480_s;

    }

    private void sendspellToServer() {
		if (this.spellObj.hasTagCompound()){
			NBTTagCompound nbttagcompound = this.spellObj.getTagCompound();
			nbttagcompound.setTag("pages", this.spellPages);
		}else{
			this.spellObj.setTagInfo("pages", this.spellPages);
		}
		NBTTagCompound sendNBT = this.spellObj.getTagCompound();
		AMaSPacketHandler.INSTANCE.sendToServer(new MessageBookData(sendNBT));
	}
    protected void actionPerformed(GuiButton p_146284_1_)
    {
        if (p_146284_1_.enabled)
        {
            if (p_146284_1_.id == 0)
            {
                this.mc.displayGuiScreen((GuiScreen)null);
                sendspellToServer();
            }
            this.updateButtons();
        }
    }

    private void addNewPage()
    {
        if (this.spellPages != null && this.spellPages.tagCount() < 50)
        {
            this.spellPages.appendTag(new NBTTagString(""));
            ++this.spellTotalPages;
            this.field_146481_r = true;
        }
    }

    /**
     * Fired when a key is typed. This is the equivalent of KeyListener.keyTyped(KeyEvent e).
     */
    protected void keyTyped(char p_73869_1_, int p_73869_2_)
    {
        super.keyTyped(p_73869_1_, p_73869_2_);

        if (this.field_146480_s)
        {
            //this.func_146460_c(p_73869_1_, p_73869_2_);
        }
        else
        {
            this.keyTypedInspell(p_73869_1_, p_73869_2_);
        }
    }

    /**
     * Processes keystrokes when editing the text of a spell
     */
    private void keyTypedInspell(char p_146463_1_, int p_146463_2_)
    {
        switch (p_146463_1_)
        {
            case 22:
                this.func_146459_b(GuiScreen.getClipboardString());
                return;
            default:
                switch (p_146463_2_)
                {
                    case 14:
                        String s = this.func_146456_p();

                        if (s.length() > 0)
                        {
                            this.func_146457_a(s.substring(0, s.length() - 1));
                        }

                        return;
                    case 28:
                    case 156:
                        this.func_146459_b("\n");
                        return;
                    default:
                        if (ChatAllowedCharacters.isAllowedCharacter(p_146463_1_))
                        {
                            this.func_146459_b(Character.toString(p_146463_1_));
                        }
                }
        }
    }

    private String func_146456_p()
    {
        return this.spellPages != null && this.currPage >= 0 && this.currPage < this.spellPages.tagCount() ? this.spellPages.getStringTagAt(this.currPage) : "";
    }

    private void func_146457_a(String p_146457_1_)
    {
        if (this.spellPages != null && this.currPage >= 0 && this.currPage < this.spellPages.tagCount())
        {
            this.spellPages.func_150304_a(this.currPage, new NBTTagString(p_146457_1_));
            this.field_146481_r = true;
        }
    }

    private void func_146459_b(String p_146459_1_)
    {
        String s1 = this.func_146456_p();
        String s2 = s1 + p_146459_1_;
        int i = this.fontRendererObj.splitStringWidth(s2 + "" + EnumChatFormatting.BLACK + "_", 118);

        if (i <= 118 && s2.length() < 256)
        {
            this.func_146457_a(s2);
        }
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(spellGuiTextures);
        int k = (this.width - this.spellImageWidth) / 2;
        byte b0 = 2;
        this.drawTexturedModalRect(k, b0, 0, 0, this.spellImageWidth, this.spellImageHeight);
        String s;
        String s1;
        int l;

        s1 = "";
        if (this.spellPages != null && this.currPage >= 0 && this.currPage < this.spellPages.tagCount())
        {
        	s1 = this.spellPages.getStringTagAt(this.currPage);
        }
        if (this.fontRendererObj.getBidiFlag())
        {
            s1 = s1 + "_";
        }
        else if (this.updateCount / 6 % 2 == 0)
        {
            s1 = s1 + "" + EnumChatFormatting.BLACK + "_";
        }
        else
        {
            s1 = s1 + "" + EnumChatFormatting.GRAY + "_";
        }
        this.fontRendererObj.drawSplitString(s1, k + 36, b0 + 16 + 16, 116, 0);

        super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
    }
    public String getspellPageString() {
    	String string = this.spellPages.getStringTagAt(this.currPage);
		return string;

    }
}
