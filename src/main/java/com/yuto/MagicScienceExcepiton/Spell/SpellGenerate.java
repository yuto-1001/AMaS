package com.yuto.MagicScienceExcepiton.Spell;

import com.yuto.MagicScienceExcepiton.Api.Spell.Spell;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class SpellGenerate extends Spell {
	protected Block GenBlock;
	public SpellGenerate(EntityPlayer caster, String word1, String word2, String word3) {
		super(caster, word1, word2, word3);
		caster.addChatMessage(new ChatComponentText(I18n.format("msg.doing", "Generate")));
		boolean flag[] = {true, true, true};
		int posX = 0;
		int posY = 0;
		int posZ = 0;
		Block hereBlock;
		this.GenBlock = getTarget(word1);
		if (this.GenBlock == null) {
			caster.addChatMessage(new ChatComponentText(I18n.format("msg.nullpo", word1)));
			flag[0] = false;
		}
		this.Pos = getPos(word2);
		if (this.Pos == null) {
			caster.addChatMessage(new ChatComponentText(I18n.format("msg.nullpo", word2)));
			flag[1] = false;
		}
		this.Doer = getDoer(word3);
		if (this.Doer == null) {
			caster.addChatMessage(new ChatComponentText(I18n.format("msg.nullpo", word3)));
			flag[2] = false;
		}
		if (flag[0] == false || flag[1] == false || flag[2] == false) {
			caster.addChatMessage(new ChatComponentText(I18n.format("msg.fail", "Generate")));
			return ;
		}
		Minecraft minecraft = Minecraft.getMinecraft();
		int side = minecraft.objectMouseOver.sideHit;
		switch (side) {
			case 0 :
				this.Pos[1] -= 1;
				break;
			case 1 :
				this.Pos[1] += 1;
				break;
			case 2 :
				this.Pos[2] -= 1;
				break;
			case 3 :
				this.Pos[2] += 1;
				break;
			case 4 :
				this.Pos[0] -= 1;
				break;
			case 5 :
				this.Pos[0] += 1;
				break;
			default :
				break;
		}
		this.CASTER.getEntityWorld().setBlock((int) Math.floor((double) this.Pos[0]), (int) Math.floor((double) this.Pos[1]), (int) Math.floor((double) this.Pos[2]), this.GenBlock);
		caster.addChatMessage(new ChatComponentText(I18n.format("msg.suc", "Generate")));
	}

	private Block getTarget(String name) {
		if (Block.getBlockFromName(name) != null) {
			Block genBlock = Block.getBlockFromName(name);
			return genBlock;
		}else {
			return null;
		}

	}


}
