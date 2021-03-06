package com.yuto.AMaS.Generate;

import java.util.Random;

import com.yuto.AMaS.Block.AMaSBlock;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class AMaSGen implements IWorldGenerator {
	public WorldGenerator Redbeanore;
	public WorldGenerator grass;
	public AMaSGen(){
		Redbeanore = new WorldGenMinable(AMaSBlock.Redbeanore, 3);
	}
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,IChunkProvider chunkProvider) {
		this.RedGen(Redbeanore, world, random, chunkX, chunkZ, 8, 0, 20);
	}
	private void RedGen(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight){
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i ++){
			int x = chunkX * 16 + rand.nextInt(16);
			int y = ( minHeight + rand.nextInt(heightDiff) )+ 2;
			int z = chunkZ * 16 + rand.nextInt(16);
			Redbeanore.generate(world, rand, x, y, z);
		}
	}
}
