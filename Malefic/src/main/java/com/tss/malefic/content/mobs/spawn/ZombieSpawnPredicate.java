package com.tss.malefic.content.mobs.spawn;


import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biomes;

public class ZombieSpawnPredicate extends BiomeMobSpawnPredicateBase{
    public ZombieSpawnPredicate(){
        addSpawnBiome(Biomes.PLAINS);

        addSpawnBiomeTag(BiomeTags.IS_FOREST);
        addSpawnBiomeTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS); //Trust me, this is swamp
        addSpawnBiomeTag(BiomeTags.IS_MOUNTAIN);
    }
}

