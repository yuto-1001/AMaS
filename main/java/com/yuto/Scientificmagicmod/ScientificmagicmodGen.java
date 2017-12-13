package com.yuto.Scientificmagicmod;

import java.util.Random;

import com.yuto.Scientificmagicmod.Block.ScientificmagicBlock;

import cpw.mods.fml.common.IWorldGenerator;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;

public class ScientificmagicmodGen implements IWorldGenerator {
	public WorldGenerator Redbeanore;
	public WorldGenerator CA_r;
	public WorldGenerator CA_w;
	public WorldGenerator CA_b;
	public WorldGenerator CA_bl;
	public ScientificmagicmodGen(){
		Redbeanore = new WorldGenMinable(ScientificmagicBlock.Redbeanore, 8);
		CA_r = new WorldGenMinable(ScientificmagicBlock.CA_r, 1,20, Blocks.grass);
		CA_w = new WorldGenMinable(ScientificmagicBlock.CA_w, 1,20, Blocks.grass);
		CA_b = new WorldGenMinable(ScientificmagicBlock.CA_b, 1,20, Blocks.grass);
		CA_bl = new WorldGenMinable(ScientificmagicBlock.CA_bl, 1,20, Blocks.grass);
	}
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,IChunkProvider chunkProvider) {
		this.RedGen(Redbeanore, world, random, chunkX, chunkZ, 8, 0, 20);
		this.CAGen(CA_r, world, random, chunkX, chunkZ, 10, 0, 200);
		this.CAGen1(CA_w, world, random, chunkX, chunkZ, 10, 0, 200);
		this.CAGen2(CA_b, world, random, chunkX, chunkZ, 10, 0, 200);
		this.CAGen3(CA_bl, world, random, chunkX, chunkZ, 10, 0, 200);
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
	private void CAGen(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight){
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i ++){
			int x = chunkX * 16 + rand.nextInt(16);
			int y = ( minHeight + rand.nextInt(heightDiff) )+ 2;
			int z = chunkZ * 16 + rand.nextInt(16);
			CA_r.generate(world, rand, x, y, z);
		}
	}
	private void CAGen1(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight){
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i ++){
			int x = chunkX * 16 + rand.nextInt(16);
			int y = ( minHeight + rand.nextInt(heightDiff) )+ 2;
			int z = chunkZ * 16 + rand.nextInt(16);
			CA_w.generate(world, rand, x, y, z);
		}
	}
	private void CAGen2(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight){
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i ++){
			int x = chunkX * 16 + rand.nextInt(16);
			int y = ( minHeight + rand.nextInt(heightDiff) )+ 2;
			int z = chunkZ * 16 + rand.nextInt(16);
			CA_b.generate(world, rand, x, y, z);
		}
	}
	private void CAGen3(WorldGenerator generator, World world, Random rand, int chunkX, int chunkZ, int chancesToSpawn, int minHeight, int maxHeight){
		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i ++){
			int x = chunkX * 16 + rand.nextInt(16);
			int y = ( minHeight + rand.nextInt(heightDiff) )+ 2;
			int z = chunkZ * 16 + rand.nextInt(16);
			CA_bl.generate(world, rand, x, y, z);
		}
	}
}
