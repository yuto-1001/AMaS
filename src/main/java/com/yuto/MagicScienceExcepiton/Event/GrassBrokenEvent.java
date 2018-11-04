package com.yuto.MagicScienceExcepiton.Event;

import java.util.Random;

import com.yuto.MagicScienceExcepiton.Block.MagicScienceExceptionBlock;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

public class GrassBrokenEvent {
	@SubscribeEvent
	public void dropClusterAmaryllis(HarvestDropsEvent event) {
		//壊されたブロックが草（植物のやつ）の場合は
		if(event.block == Blocks.tallgrass) {
			//乱数生成
			Random random = new Random();
			int rndInt = random.nextInt(10);
			//もし、乱数が0なら
			if(rndInt == 0) {
				//乱数生成
				Random randomMeta = new Random();
				int rndMetaInt = random.nextInt(4);
				Block bulb = MagicScienceExceptionBlock.CA;
				//彼岸花の球根をドロップ
				bulb.dropBlockAsItem(event.world, event.x, event.y, event.z, rndMetaInt, event.fortuneLevel);
			}
		}
	}
}
