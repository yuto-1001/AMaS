package com.yuto.MagicScienceExcepiton.Api.Spell;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;

public abstract class Spell {
	public final EntityPlayer CASTER;
	protected EntityPlayer Doer;
	protected float[] Pos;
	public Spell(EntityPlayer caster, String word1, String word2, String word3) {
		this.CASTER = caster;
	}
	protected EntityPlayer getDoer(String caster) {
		int number = caster.indexOf("-");
		if (number == -1) {
			return null;
		}
		String caster1 = caster.substring(number + 1);
		switch (caster1) {
			case "I" :
				return this.CASTER;
			case "You" :
				Entity you = this.CASTER.worldObj.getClosestPlayerToEntity(this.CASTER, 10.0D);
				if (you instanceof EntityPlayer && you != null && you != this.CASTER) {
					return (EntityPlayer) you;
				}else {
					return null;
				}
			default :
				return null;
		}
	}
	protected float[] getPos(String position) {
		float posX;
		float posY;
		float posZ;
		if (position.equals("here")) {
			MovingObjectPosition pos = Minecraft.getMinecraft().renderViewEntity.rayTrace(5.0D, 1.0F);
			if (pos != null) {
				posX = pos.blockX;
				posY = pos.blockY;
				posZ = pos.blockZ;
			}else {
				float yaw = this.CASTER.rotationYaw;
				float pitch = this.CASTER.rotationPitch;
				posX = (float) (this.CASTER.posX + (MathHelper.sin(pitch) * MathHelper.cos(yaw)));
				posZ = (float) (this.CASTER.posZ + (MathHelper.sin(pitch) * MathHelper.sin(yaw)));
				posY = (float) (this.CASTER.posY + MathHelper.cos(pitch));
			}
		}else if (position.matches("pos\\{\\-?[0-9]+,\\-?[0-9]+,\\-?[0-9]+\\}")) {
			int index = position.indexOf("{") + 1;
			int endIndex = position.indexOf("}");
			String[] pos = (position.substring(index, endIndex)).split(",", 0);
			posX = Float.parseFloat(pos[0]);
			posY = Float.parseFloat(pos[1]);
			posZ = Float.parseFloat(pos[2]);
		}else {
			return null;
		}
		float pos[] = {posX, posY, posZ};
		return pos;
	}
}
