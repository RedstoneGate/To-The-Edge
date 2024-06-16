package com.tss.malefic.content.mobs.spawn;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;


import java.util.HashSet;

import static net.minecraft.world.entity.Mob.checkMobSpawnRules;

/** 用于怪物生成的条件
 * 1.和平模式不会生成。
 * 2.仅应用于自然生成和区块创建的怪物。
 * 3.检查 biomes 里的群系白名单，使用 addSpawnBiome 添加群系。
 * 4.检查 tags 里的标签白名单，使用 addSpawnBiomeTag 添加群系标签。
 *      3 与 4 之间是「或」的关系，满足二者之一就能生成。
 */

public abstract class BiomeMobSpawnPredicateBase implements SpawnPlacements.SpawnPredicate{


    private HashSet<ResourceKey<Biome>> biomes = new HashSet<>();
    private HashSet<TagKey<Biome>> tags = new HashSet<>();




    public boolean checkSpawnBiome(Holder<Biome> biome){
        // check if biome in biomeSet
        for (ResourceKey<Biome> biome_available: biomes){
            if (biome.is(biome_available)){
                return true;
            }
        }

        // check if biome has tag
        for(TagKey<Biome> tag : tags){
            if (biome.is(tag)){
                return true;
            }
        }

        return false;
    }
    public void addSpawnBiome(ResourceKey<Biome> biome){
        biomes.add(biome);
    }

    public void addSpawnBiomeTag(TagKey<Biome> tag){
        tags.add(tag);
    }

    @Override
    public boolean test(EntityType entityType, ServerLevelAccessor level, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        if (level.getDifficulty() != Difficulty.PEACEFUL){
            if (mobSpawnType.equals(MobSpawnType.CHUNK_GENERATION)||mobSpawnType.equals(MobSpawnType.NATURAL)){
                return checkSpawnBiome(level.getBiome(blockPos));
            }
            return checkMobSpawnRules(entityType,level,mobSpawnType,blockPos,randomSource);
        }
        return false;
    }
}
