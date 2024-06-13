package com.tss.malefic;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Spider;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class EventHandler {
    /*@SubscribeEvent
    public void pickupItem(EntityItemPickupEvent e){
        System.out.println("Item picked up!");
        e.getItem().kill();
        e.setCanceled(true);
    }*/
    @SubscribeEvent
    public void onMobSpawn(MobSpawnEvent.FinalizeSpawn e){
        //if(e.getEntity().getSpawnType()==null||e.getEntity().getSpawnType().equals(MobSpawnType.SPAWN_EGG)){return;}
        String biomeName = "Unrestricted";
        Level level = e.getEntity().getCommandSenderWorld();
        BlockPos blockPos = e.getEntity().blockPosition();


        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.LUKEWARM_OCEAN))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.COLD_OCEAN))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.FROZEN_OCEAN))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.OCEAN))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.WARM_OCEAN))
        ){
            biomeName = "Ocean";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.DEEP_COLD_OCEAN))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.DEEP_FROZEN_OCEAN))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.DEEP_LUKEWARM_OCEAN))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.DEEP_OCEAN))
        ){
            biomeName = "DeepOcean";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.CHERRY_GROVE))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.MEADOW))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.STONY_PEAKS))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.WINDSWEPT_FOREST))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.WINDSWEPT_HILLS))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.WINDSWEPT_SAVANNA))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.WINDSWEPT_GRAVELLY_HILLS))
        ){
            biomeName = "Mountain";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.GROVE))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.FROZEN_PEAKS))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.JAGGED_PEAKS))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.SNOWY_SLOPES))
        ){
            biomeName = "SnowMountain";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.BAMBOO_JUNGLE))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.BIRCH_FOREST))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.FLOWER_FOREST))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.FOREST))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.JUNGLE))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.OLD_GROWTH_BIRCH_FOREST))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.OLD_GROWTH_PINE_TAIGA))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.OLD_GROWTH_SPRUCE_TAIGA))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.SNOWY_TAIGA))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.SPARSE_JUNGLE))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.TAIGA))
        ){
            biomeName = "Forest";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.DARK_FOREST))
        ){
            biomeName = "DarkForest";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.BEACH))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.FROZEN_RIVER))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.RIVER))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.SNOWY_BEACH))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.STONY_SHORE))
        ){
            biomeName = "River";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.MANGROVE_SWAMP))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.SWAMP))
        ){
            biomeName = "Swamp";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.PLAINS))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.SUNFLOWER_PLAINS))
        ){
            biomeName = "Plains";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.ICE_SPIKES))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.SNOWY_PLAINS))
        ){
            biomeName = "SnowPlains";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.DESERT))
        ){
            biomeName = "Desert";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.SAVANNA))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.SAVANNA_PLATEAU))
        ){
            biomeName = "Savanna";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.BADLANDS))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.ERODED_BADLANDS))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.WOODED_BADLANDS))
        ){
            biomeName = "Badlands";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.DRIPSTONE_CAVES))
                ||level.getBiome(e.getEntity().blockPosition()).is((Biomes.LUSH_CAVES))
        ){
            biomeName = "Caves";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.SOUL_SAND_VALLEY))
        ){
            biomeName = "NetherSoulsand";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.NETHER_WASTES))
        ){
            biomeName = "NetherWastes";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.BASALT_DELTAS))
        ){
            biomeName = "NetherBasalt";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.CRIMSON_FOREST))
        ){
            biomeName = "NetherCrimson";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.WARPED_FOREST))
        ){
            biomeName = "NetherWarped";
        }

        if(level.getBiome(e.getEntity().blockPosition()).is((Biomes.END_HIGHLANDS))
        ){
            biomeName = "EndHighlands";
        }



        boolean spawnCustomEntity = false;


        if(e.getEntity().getType().equals(EntityType.DROWNED)||
                e.getEntity().getType().equals(EntityType.CREEPER)||
                e.getEntity().getType().equals(EntityType.SKELETON)||
                e.getEntity().getType().equals(EntityType.SPIDER)||
                e.getEntity().getType().equals(EntityType.ZOMBIE)||
                e.getEntity().getType().equals(EntityType.DROWNED)||
                e.getEntity().getType().equals(EntityType.STRAY)||
                e.getEntity().getType().equals(EntityType.HUSK)
        ){
            e.getEntity().remove(Entity.RemovalReason.DISCARDED);
            spawnCustomEntity = true;
            System.out.println(e.getEntity().getType()+" spawned in "+biomeName);
        }
        if (!level.isClientSide() && spawnCustomEntity) {
            switch (biomeName) {
                case "Unrestricted":
                    break;
                case "Ocean":
                    break;
                case "DeepOcean":
                    break;
                case "Mountain":
                    break;
                case "SnowMountain":

                        spawnMob("ravager",1,level,blockPos);

                    break;
                case "Forest":
                    break;
                case "DarkForest":
                    break;
                case "River":
                    break;
                case "Swamp":
                    break;
                case "Plains":
                    break;
                case "SnowPlains":
                    break;
                case "Desert":
                    break;
                case "Savanna":
                    break;
                case "Badlands":
                    break;
                case "Caves":
                    break;
                case "NetherSoulsand":
                    break;
                case "NetherWastes":
                    break;
                case "NetherBasalt":
                    break;
                case "NetherCrimson":
                    break;
                case "NetherWarped":
                    break;
                case "EndHighlands":
                    break;



            }
        }
    }
    private void spawnMob(String mobType, int difficulty, Level level, BlockPos blockPos){
        switch (mobType){
            case "ravager":
                Ravager ravager = new Ravager(EntityType.RAVAGER, level);
                ravager.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(ravager);
                break;
        }
    }
}
