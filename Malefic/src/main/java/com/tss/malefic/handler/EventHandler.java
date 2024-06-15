package com.tss.malefic.handler;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
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
