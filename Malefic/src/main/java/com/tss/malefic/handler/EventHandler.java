package com.tss.malefic.handler;

import com.mojang.logging.LogUtils;
import com.tss.malefic.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.datafix.fixes.EntityCustomNameToComponentFix;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    /*@SubscribeEvent
    public void pickupItem(EntityItemPickupEvent e){
        System.out.println("Item picked up!");
        e.getItem().kill();
        e.setCanceled(true);
    }*/
    private float timeBuffer=0;

    @SubscribeEvent
    public void onTick(TickEvent.LevelTickEvent e){
        if(!e.level.isClientSide()){
            ServerLevel serverLevel = (ServerLevel)e.level;
            float dayTime = Math.floorMod(serverLevel.getDayTime(),24000);
            float normalizedTime = 0;
            float timeMultiplier = 1;
            if(dayTime>=6000&&dayTime<18000){
                normalizedTime = (18000f-dayTime)/12000f;
            } else if(dayTime<6000){
                normalizedTime = (dayTime+6000f)/12000f;
            } else {
                normalizedTime = (dayTime-18000f)/12000f;
            }
            timeMultiplier = (float) ((1-2.7*Math.pow(normalizedTime,2)+1.8*Math.pow(normalizedTime,3)));
            timeMultiplier= Math.max((float) Math.pow(timeMultiplier,4),0.025f)/0.292696f;
            timeBuffer+=1/(timeMultiplier*20);
            while (timeBuffer>=1){
                timeBuffer--;
                serverLevel.setDayTime(serverLevel.getDayTime()+1);
            }
        }
    }

    @SubscribeEvent
    public void onMobSpawn(MobSpawnEvent.FinalizeSpawn e){
        if(!e.getLevel().isClientSide()){
            float random = e.getLevel().getRandom().nextFloat();
            float compositeRandom = e.getLevel().getRandom().nextFloat();
            float legendaryCriteria = Config.legendaryChance;
            float eliteCriteria = legendaryCriteria+Config.eliteChance;
            float veteranCriteria = legendaryCriteria+eliteCriteria+Config.veteranChance;
            Holder<Biome> biome = e.getLevel().getBiome(e.getEntity().blockPosition());


            if(e.getEntity().getType().equals(EntityType.ZOMBIE)){
                int mobLevel = 0;
                if(random<veteranCriteria){
                    mobLevel++;
                }
                if(random<eliteCriteria){
                    mobLevel++;
                }
                if(random<legendaryCriteria){
                    mobLevel++;
                }
                if((biome.is(Biomes.SWAMP)|| biome.is(Biomes.MANGROVE_SWAMP)) && compositeRandom<Config.compositeMobChance){
                    spawnMob("zombieGuardian",mobLevel,0,e.getLevel().getLevel(),e.getEntity().blockPosition());
                } else if((biome.is(Biomes.PLAINS)|| biome.is(Biomes.SUNFLOWER_PLAINS)
                        ||(biome.is(Biomes.BIRCH_FOREST))
                ) && compositeRandom<Config.compositeMobChance){
                    spawnMob("zombieTower",mobLevel,0,e.getLevel().getLevel(),e.getEntity().blockPosition());
                } else {
                    spawnMob("zombie",mobLevel,0,e.getLevel().getLevel(),e.getEntity().blockPosition());

                }

                e.setSpawnCancelled(true);
            }
        }


    }

    private void spawnMob(String mobType, int mobLevel, int difficulty, ServerLevel level, BlockPos blockPos){
        switch (mobType){
            case "zombie":
            {
                Zombie m = new Zombie(EntityType.ZOMBIE, level);
                m.setHealth(1);
                m.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(m);
            }
            break;
            case "ravager":
            {
                Ravager m = new Ravager(EntityType.RAVAGER, level);
                m.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(m);
            }
            break;
        }
    }
}
