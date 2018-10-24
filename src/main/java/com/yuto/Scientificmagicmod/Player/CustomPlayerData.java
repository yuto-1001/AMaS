package com.yuto.Scientificmagicmod.Player;

import com.yuto.Scientificmagicmod.Packet.PacketPlayerData;
import com.yuto.Scientificmagicmod.Packet.SMPacketHandler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class CustomPlayerData implements IExtendedEntityProperties {

	/** 魔力 */
    private MagicPowerStats MagicPower;
    /** 妖力 */
    private SplitPowerStats SplitPower;

    public void onUpdateEntity(EntityPlayer entityPlayer) {

        if (MagicPower.isPacket()) {
            SMPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this), (EntityPlayerMP) entityPlayer);
        }
        if (SplitPower.isPacket()) {
            SMPacketHandler.INSTANCE.sendTo(new PacketPlayerData(this), (EntityPlayerMP) entityPlayer);
        }

        this.SplitPower.onUpdate(entityPlayer);
        this.MagicPower.onUpdate(entityPlayer);

    }

    @Override
    public void saveNBTData(NBTTagCompound compound) {

        this.MagicPower.writeNBT(compound);
        this.SplitPower.writeNBT(compound);

    }

    @Override
    public void loadNBTData(NBTTagCompound compound) {

        this.MagicPower.readNBT(compound);
        this.SplitPower.readNBT(compound);
    }

    @Override
    public void init(Entity entity, World world) {

        if (this.MagicPower == null) this.MagicPower = new MagicPowerStats();
        System.out.println("aaaaaaaaaaa" + this.MagicPower == null);
        if (this.SplitPower == null) this.SplitPower = new SplitPowerStats();
        System.out.println("aaaaaaaaaaa" + this.SplitPower == null);
    }

    public MagicPowerStats getMagicPower() {
        return MagicPower;
    }

    private void setMagicPower(MagicPowerStats MagicPower) {
        this.MagicPower = MagicPower;
    }

    public SplitPowerStats getSplitPower() {
        return SplitPower;
    }

    private void setSplitPower(SplitPowerStats SplitPower) {
        this.SplitPower = SplitPower;
    }
}
