package com.tss.malefic.content.mobs.spawn;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.ServerLevelAccessor;

/**
 * dont spawn anyway
 */
public class EmptyPredicate implements SpawnPlacements.SpawnPredicate{

    @Override
    public boolean test(EntityType p_217081_, ServerLevelAccessor p_217082_, MobSpawnType p_217083_, BlockPos p_217084_, RandomSource p_217085_) {
        return false;
    }
}
