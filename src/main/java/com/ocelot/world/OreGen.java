package com.ocelot.world;

import java.util.Random;

import com.google.common.base.Predicate;
import com.ocelot.init.ModBlocks;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

/**
 * @author Ocelot5836
 */
public class OreGen implements IWorldGenerator {

	private WorldGenerator nitrate_powder_overworld;

	public OreGen() {
		nitrate_powder_overworld = new WorldGenMinable(ModBlocks.NITRATE_POWDER_ORE.getDefaultState(), 8);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimension()) {
		/** Nether */
		case -1: {
			break;
		}

		/** Overworld */
		case 0: {
			runGenerator(nitrate_powder_overworld, world, random, chunkX, chunkZ, 20, 0, 64);
			break;
		}

		/** End */
		case 1: {
			break;
		}
		}
	}

	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

//	private class NetherGenPredicate implements Predicate<IBlockState> {
//		@Override
//		public boolean apply(IBlockState input) {
//			return input != null && input.getBlock() == Blocks.NETHERRACK;
//		}
//	}
//	
//	private class EndGenPredicate implements Predicate<IBlockState> {
//		@Override
//		public boolean apply(IBlockState input) {
//			return input != null && input.getBlock() == Blocks.END_STONE;
//		}
//	}
}