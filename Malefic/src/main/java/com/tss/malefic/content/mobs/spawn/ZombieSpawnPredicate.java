package com.tss.malefic.content.mobs.spawn;


import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;

public class ZombieSpawnPredicate extends BiomeMobSpawnPredicateBase{
    public ZombieSpawnPredicate(){
        addSpawnBiome(Biomes.PLAINS);

        addSpawnBiome(Biomes.FOREST);
        addSpawnBiome(Biomes.TAIGA);
        addSpawnBiome(Biomes.SNOWY_TAIGA);
        addSpawnBiome(Biomes.OLD_GROWTH_PINE_TAIGA);
        addSpawnBiome(Biomes.OLD_GROWTH_SPRUCE_TAIGA);
        addSpawnBiome(Biomes.FLOWER_FOREST);
        addSpawnBiome(Biomes.BIRCH_FOREST);
        addSpawnBiome(Biomes.OLD_GROWTH_BIRCH_FOREST);
        addSpawnBiome(Biomes.JUNGLE);
        addSpawnBiome(Biomes.SPARSE_JUNGLE);
        addSpawnBiome(Biomes.BAMBOO_JUNGLE);

        addSpawnBiome(Biomes.SWAMP);
        addSpawnBiome(Biomes.MANGROVE_SWAMP);

        addSpawnBiome(Biomes.PLAINS);
        addSpawnBiome(Biomes.SUNFLOWER_PLAINS);
        addSpawnBiome(Biomes.SNOWY_PLAINS);
        addSpawnBiome(Biomes.ICE_SPIKES);

        addSpawnBiome(Biomes.DRIPSTONE_CAVES);
        addSpawnBiome(Biomes.LUSH_CAVES);


        //addSpawnBiomeTag(BiomeTags.IS_FOREST);
        //addSpawnBiomeTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS); //Trust me, this is swamp
        //addSpawnBiomeTag(BiomeTags.IS_MOUNTAIN);
    }
}

