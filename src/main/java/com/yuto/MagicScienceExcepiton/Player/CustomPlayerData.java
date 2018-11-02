package com.yuto.MagicScienceExcepiton.Player;

import com.yuto.MagicScienceExcepiton.Packet.PacketPlayerData;
import com.yuto.MagicScienceExcepiton.Packet.SMPacketHandler;

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
        if (this.SplitPower == null) this.SplitPower = new SplitPowerStats();
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
