package com.tss.malefic.content.mobs.spawn;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.WorldgenRandom;

import static net.minecraft.world.entity.Mob.checkMobSpawnRules;

public class SlimeSpawnPredicate implements SpawnPlacements.SpawnPredicate{

    @Override
    public boolean test(EntityType entityType, ServerLevelAccessor level, MobSpawnType mobSpawnType, BlockPos blockPos, RandomSource randomSource) {
        if (level.getDifficulty() != Difficulty.PEACEFUL){
            if (!mobSpawnType.equals(MobSpawnType.SPAWNER)){
                ChunkPos chunkpos = new ChunkPos(blockPos);
                //flag check slime chunk
                boolean flag = WorldgenRandom.seedSlimeChunk(chunkpos.x, chunkpos.z, ((WorldGenLevel) level).getSeed(), 987234911L).nextInt(10) == 0;
                if (flag && blockPos.getY() >= 90) {
                    return checkMobSpawnRules(entityType, level, mobSpawnType, blockPos, randomSource);
                } else {
                    return false;
                }
            }
            return checkMobSpawnRules(entityType,level,mobSpawnType,blockPos,randomSource);
        }
        return false;
    }
}
