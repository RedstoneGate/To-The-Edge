package com.tss.malefic;

import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.random.Weight;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public record EntitySpawnBiomeModifier(HolderSet<Biome> biomes, Holder<PlacedFeature> feature) implements BiomeModifier {

    @Override
    public void modify(Holder<Biome> holder, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
        LogUtils.getLogger().info("BUILDER BUILDING!");
        if(phase == Phase.ADD){

            builder.getMobSpawnSettings().addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.BLAZE, Weight.of(200),4,4));
        }
    }

    @Override
    public Codec<? extends BiomeModifier> codec() {
        return Malefic.ENTITY_SPAWN_BIOME_MODIFIER_CODEC.get();
    }

}
