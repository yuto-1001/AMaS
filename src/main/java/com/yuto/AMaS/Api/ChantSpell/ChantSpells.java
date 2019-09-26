package com.yuto.AMaS.Api.ChantSpell;

import com.yuto.AMaS.Spell.SpellGenerate;
import com.yuto.AMaS.Spell.SpellGive;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class ChantSpells {
	public boolean ChantSpell(String spellInput, EntityPlayer caster) {
		String[] spells = spellInput.split("\n", 0);
		for (String spell : spells) {
			String[] words = spell.split(" ", 0);
			if (words.length != 4) {
				return false;
			}
			for (EnumSpells eSpell : EnumSpells.values()) {
				String s = eSpell.toString();
				if (words[0].equals(s)) {
					break;
				}
				return false;
			}
			caster.addChatMessage(new ChatComponentText(I18n.format("msg.ChantingSuc", spell)));
			switch (words[0]) {
				case "Generate" :
					new SpellGenerate(caster, words[1], words[2], words[3]);
					break;
				case "Give" :
					new SpellGive(caster, words[1], words[2], words[3]);
					break;
				default :
					return false;
			}
		}
		return true;

	}
}
