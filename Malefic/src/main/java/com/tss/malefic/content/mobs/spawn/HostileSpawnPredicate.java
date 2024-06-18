package com.tss.malefic.content.mobs.spawn;


import com.mojang.logging.LogUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biomes;


import static net.minecraft.world.entity.Mob.checkMobSpawnRules;

public class HostileSpawnPredicate implements SpawnPlacements.SpawnPredicate{
    public HostileSpawnPredicate(){



        //addSpawnBiomeTag(BiomeTags.IS_FOREST);
        //addSpawnBiomeTag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS); //Trust me, this is swamp
        //addSpawnBiomeTag(BiomeTags.IS_MOUNTAIN);
    }

    @Override
    public boolean test(EntityType entityType, ServerLevelAccessor level, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        if (level.getDifficulty() != Difficulty.PEACEFUL){
            if (mobSpawnType.equals(MobSpawnType.CHUNK_GENERATION)||mobSpawnType.equals(MobSpawnType.NATURAL)){
                int brightness = Math.max(level.getBrightness(LightLayer.SKY, blockPos) - level.getSkyDarken(),level.getBrightness(LightLayer.BLOCK,blockPos));
                return brightness<=7;
                //return level.getRawBrightness(blockPos,0)<=7;
            }
            return checkMobSpawnRules(entityType,level,mobSpawnType,blockPos,randomSource);
        }
        return false;
    }
}

