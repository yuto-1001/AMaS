package com.yuto.AMaS.Api.ChantSpell;

import com.yuto.AMaS.Spell.SpellGenerate;
import com.yuto.AMaS.Spell.SpellGive;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class ChantSpells {
	public boolean ChantSpell(String spell, EntityPlayer caster) {
		String Words[];
		Words = this.SplitWords(spell);
		if (Words.length != 4) {
			return false;
		}
		for (String i : Words) {
			if (i == "" || i == null) {
				return false;
			}
		}
		for (EnumSpells eSpell : EnumSpells.values()) {
			String s = eSpell.toString();
			if (Words[0].equals(s)) {
				break;
			}
			return false;
		}
		caster.addChatMessage(new ChatComponentText(I18n.format("msg.ChantingSuc", spell)));
		switch (Words[0]) {
			case "Generate" :
				new SpellGenerate(caster, Words[1], Words[2], Words[3]);
				break;
			case "Give" :
				new SpellGive(caster, Words[1], Words[2], Words[3]);
				break;
			default :
				return false;
		}
		return true;
	}

	private String[] SplitWords(String spell) {
		String Words[];

		Words = spell.split(" ", 0);

		return Words;

	}
}
