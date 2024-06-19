package com.tss.malefic.handler;

import com.mojang.logging.LogUtils;
import com.tss.malefic.Config;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.inventory.SmithingMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.MobSpawnEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class EventHandler {
    /*@SubscribeEvent
    public void pickupItem(EntityItemPickupEvent e){
        System.out.println("Item picked up!");
        e.getItem().kill();
        e.setCanceled(true);
    }*/
    private float timeBuffer=0;
    private float ticks=0;

    @SubscribeEvent
    public void onTick(TickEvent.ServerTickEvent e){
        if(e.phase.equals(TickEvent.Phase.START)){
            ServerLevel serverLevel = e.getServer().getLevel(Level.OVERWORLD);
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


   /* @SubscribeEvent
    public void onSmithingTableCraft(Smith e){
        if(e.getInventory() instanceof SmithingMenu){
            LogUtils.getLogger().info("SMITHING!");
        }
        LogUtils.getLogger().info("SMITHING?");


    }*/


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
                        ||(biome.is(Biomes.BIRCH_FOREST))||(biome.is(Biomes.TAIGA))||(biome.is(Biomes.SNOWY_TAIGA))||(biome.is(Biomes.OLD_GROWTH_BIRCH_FOREST))||(biome.is(Biomes.FOREST))||(biome.is(Biomes.OLD_GROWTH_SPRUCE_TAIGA))||(biome.is(Biomes.OLD_GROWTH_PINE_TAIGA))||(biome.is(Biomes.FLOWER_FOREST))||(biome.is(Biomes.JUNGLE))||(biome.is(Biomes.SPARSE_JUNGLE))||(biome.is(Biomes.BAMBOO_JUNGLE))
                        ||(biome.is(Biomes.LUSH_CAVES))||(biome.is(Biomes.DRIPSTONE_CAVES))
                ) && compositeRandom<Config.compositeMobChance){
                    spawnMob("zombieTower",mobLevel,0,e.getLevel().getLevel(),e.getEntity().blockPosition());
                } else {
                    spawnMob("zombie",mobLevel,0,e.getLevel().getLevel(),e.getEntity().blockPosition());

                }

                e.setSpawnCancelled(true);
            }
        }


    }


    private float getHealthMultiplier(int mobLevel){
        return switch (mobLevel) {
            case 1 -> Config.veteranHealthMultiplier;
            case 2 -> Config.eliteHealthMultiplier;
            case 3 -> Config.legendaryHealthMultiplier;
            default -> 1;
        };
    }
    private float getSpeedMultiplier(int mobLevel){
        return switch (mobLevel) {
            case 1 -> Config.veteranSpeedMultiplier;
            case 2 -> Config.eliteSpeedMultiplier;
            case 3 -> Config.legendarySpeedMultiplier;
            default -> 1;
        };
    }
    private float getAttackAddition(int mobLevel){
        return switch (mobLevel) {
            case 1 -> Config.veteranAttackAddition;
            case 2 -> Config.eliteAttackAddition;
            case 3 -> Config.legendaryAttackAddition;
            default -> 0;
        };
    }
    private float getEnchantLevel(int mobLevel){
        return switch (mobLevel) {
            case 1 -> Config.veteranEnchantMultiplier;
            case 2 -> Config.eliteEnchantMultiplier;
            case 3 -> Config.legendaryEnchantMultiplier;
            default -> 0;
        };
    }
    private ItemStack getItemWithEnchant(Item item, int randomEnchantLevel, RandomEnchantType type){
        ItemStack is = new ItemStack(item);
        int enchantPower = randomEnchantLevel*3;
        int primitiveEnchantPower = enchantPower;
        ArrayList<Enchantment> possibleEnchantments= new ArrayList<>();
        Enchantment selectedEnchantment;
        switch (type){
            case ARMOR:
                possibleEnchantments.add(Enchantments.PROJECTILE_PROTECTION);
                possibleEnchantments.add(Enchantments.ALL_DAMAGE_PROTECTION);
                possibleEnchantments.add(Enchantments.BLAST_PROTECTION);
                possibleEnchantments.add(Enchantments.FIRE_PROTECTION);
                possibleEnchantments.add(Enchantments.UNBREAKING);
                break;
            case CHEST_ARMOR:
                possibleEnchantments.add(Enchantments.PROJECTILE_PROTECTION);
                possibleEnchantments.add(Enchantments.ALL_DAMAGE_PROTECTION);
                possibleEnchantments.add(Enchantments.BLAST_PROTECTION);
                possibleEnchantments.add(Enchantments.FIRE_PROTECTION);
                possibleEnchantments.add(Enchantments.THORNS);
                possibleEnchantments.add(Enchantments.UNBREAKING);

                break;
            case MELEE_WEAPON:
                possibleEnchantments.add(Enchantments.SHARPNESS);
                possibleEnchantments.add(Enchantments.FIRE_ASPECT);
                possibleEnchantments.add(Enchantments.KNOCKBACK);
                possibleEnchantments.add(Enchantments.UNBREAKING);

                break;
            case BOW:

                break;
        }
        while (primitiveEnchantPower==enchantPower||enchantPower>49*Math.random()){
            selectedEnchantment=null;
            int totalWeight = 0;
            for(Enchantment e : possibleEnchantments){
                totalWeight += e.getRarity().getWeight();
            }
            float randomEnchant = (float) (totalWeight*Math.random());
            for(Enchantment e : possibleEnchantments){
                randomEnchant-= e.getRarity().getWeight();
                if(randomEnchant<0){
                    selectedEnchantment = e;
                    break;
                }
            }
            if(selectedEnchantment!=null){
                is.enchant(selectedEnchantment,getEnchantmentLevel(selectedEnchantment,primitiveEnchantPower));
                ArrayList<Enchantment> removeEnchantmentsList= new ArrayList<>();
                for(Enchantment e : possibleEnchantments){
                    if (!selectedEnchantment.isCompatibleWith(e)){
                        removeEnchantmentsList.add(e);
                    }
                }
                possibleEnchantments.removeAll(removeEnchantmentsList);
            }
            enchantPower/=2;
        }
        is.setDamageValue((int)((float)Math.random()*0.9f*(float)is.getMaxDamage()));
        return is;
    }

    private int getEnchantmentLevel(Enchantment enchantment, int enchantLevel){
        if(enchantment.equals(Enchantments.ALL_DAMAGE_PROTECTION)){
            if (enchantLevel<=12) return 1;
            else if (enchantLevel<=23) return 2;
            else if (enchantLevel<=34) return 3;
            else if (enchantLevel<=45) return 4;
            else return 5;
        }
        if(enchantment.equals(Enchantments.PROJECTILE_PROTECTION)){
            if (enchantLevel<=9) return 1;
            else if (enchantLevel<=15) return 2;
            else if (enchantLevel<=21) return 3;
            else if (enchantLevel<=27) return 4;
            else return 5;
        }
        if(enchantment.equals(Enchantments.FIRE_PROTECTION)){
            if (enchantLevel<=18) return 1;
            else if (enchantLevel<=26) return 2;
            else if (enchantLevel<=34) return 3;
            else if (enchantLevel<=42) return 4;
            else return 5;
        }
        if(enchantment.equals(Enchantments.BLAST_PROTECTION)){
            if (enchantLevel<=13) return 1;
            else if (enchantLevel<=21) return 2;
            else if (enchantLevel<=29) return 3;
            else if (enchantLevel<=37) return 4;
            else return 5;
        }
        if(enchantment.equals(Enchantments.THORNS)){
            if (enchantLevel<=10) return 1;
            else if (enchantLevel<=25) return 2;
            else if (enchantLevel<=35) return 3;
            else if (enchantLevel<=45) return 4;
            else return 5;
        }
        if(enchantment.equals(Enchantments.UNBREAKING)){
            if (enchantLevel<=5) return 1;
            else if (enchantLevel<=15) return 2;
            else if (enchantLevel<=30) return 3;
            else if (enchantLevel<=45) return 4;
            else return 5;
        }
        if(enchantment.equals(Enchantments.SHARPNESS)){
            if (enchantLevel<=12) return 1;
            else if (enchantLevel<=23) return 2;
            else if (enchantLevel<=34) return 3;
            else if (enchantLevel<=45) return 4;
            else return 5;
        }
        if(enchantment.equals(Enchantments.KNOCKBACK)){
            if (enchantLevel<=10) return 1;
            else if (enchantLevel<=20) return 2;
            else if (enchantLevel<=30) return 3;
            else if (enchantLevel<=40) return 4;
            else return 5;
        }
        if(enchantment.equals(Enchantments.FIRE_ASPECT)){
            if (enchantLevel<=10) return 1;
            else if (enchantLevel<=30) return 2;
            else if (enchantLevel<=40) return 3;
            else if (enchantLevel<=50) return 4;
            else return 5;
        }

        return 1;
    }

    private ItemStack getSpecialDrop(int mobLevel){
        return switch (mobLevel) {
            case 1 -> new ItemStack(Items.EXPERIENCE_BOTTLE);
            case 2 -> new ItemStack(Items.EXPERIENCE_BOTTLE,3);
            case 3 -> new ItemStack(Items.DRAGON_BREATH);
            default -> new ItemStack(Items.AIR);
        };
    }

    private void gearEliteMob(Mob m, int mobLevel){
        int enchantLevel = (int) getEnchantLevel(mobLevel);
        Item head = Items.LEATHER_HELMET;
        Item chest = Items.LEATHER_CHESTPLATE;
        Item legs = Items.LEATHER_LEGGINGS;
        Item feet = Items.LEATHER_BOOTS;
        Item mainhand = Items.IRON_SHOVEL;
        switch (mobLevel){
            case 1:
                if(Math.random()>0.5){
                    head = Items.LEATHER_HELMET;
                } else{
                    head = Items.CHAINMAIL_HELMET;
                }
                if(Math.random()>0.5){
                    chest = Items.LEATHER_CHESTPLATE;
                } else{
                    chest = Items.CHAINMAIL_CHESTPLATE;
                }
                if(Math.random()>0.5){
                    legs = Items.LEATHER_LEGGINGS;
                } else{
                    legs = Items.CHAINMAIL_LEGGINGS;
                }
                if(Math.random()>0.5){
                    feet = Items.LEATHER_BOOTS;
                } else{
                    feet = Items.CHAINMAIL_BOOTS;
                }
                if(Math.random()>0.5){
                    mainhand = Items.IRON_SHOVEL;
                } else{
                    mainhand = Items.IRON_SWORD;
                }
                break;

            case 2:
                if(Math.random()>0.6){
                    head = Items.IRON_HELMET;
                } else{
                    head = Items.GOLDEN_HELMET;
                }
                if(Math.random()>0.6){
                    chest = Items.IRON_CHESTPLATE;
                } else{
                    chest = Items.GOLDEN_CHESTPLATE;
                }
                if(Math.random()>0.6){
                    legs = Items.IRON_LEGGINGS;
                } else{
                    legs = Items.GOLDEN_LEGGINGS;
                }
                if(Math.random()>0.6){
                    feet = Items.IRON_BOOTS;
                } else{
                    feet = Items.GOLDEN_BOOTS;
                }
                if(Math.random()>0.6){
                    mainhand = Items.DIAMOND_SHOVEL;
                } else{
                    mainhand = Items.DIAMOND_SWORD;
                }
                break;

            case 3:
                if(Math.random()>0.7){
                    head = Items.DIAMOND_HELMET;
                } else{
                    head = Items.NETHERITE_HELMET;
                }
                if(Math.random()>0.7){
                    chest = Items.DIAMOND_CHESTPLATE;
                } else{
                    chest = Items.NETHERITE_CHESTPLATE;
                }
                if(Math.random()>0.7){
                    legs = Items.DIAMOND_LEGGINGS;
                } else{
                    legs = Items.NETHERITE_LEGGINGS;
                }
                if(Math.random()>0.7){
                    feet = Items.DIAMOND_BOOTS;
                } else{
                    feet = Items.NETHERITE_BOOTS;
                }
                if(Math.random()>0.7){
                    mainhand = Items.NETHERITE_SHOVEL;
                } else{
                    mainhand = Items.NETHERITE_SWORD;
                }
                break;
        }
        m.setItemSlot(EquipmentSlot.HEAD,getItemWithEnchant(head,enchantLevel, RandomEnchantType.ARMOR));
        m.setItemSlot(EquipmentSlot.CHEST,getItemWithEnchant(chest,enchantLevel, RandomEnchantType.CHEST_ARMOR));
        m.setItemSlot(EquipmentSlot.LEGS,getItemWithEnchant(legs,enchantLevel, RandomEnchantType.ARMOR));
        m.setItemSlot(EquipmentSlot.FEET,getItemWithEnchant(feet,enchantLevel, RandomEnchantType.ARMOR));
        m.setItemSlot(EquipmentSlot.MAINHAND,getItemWithEnchant(mainhand,enchantLevel, RandomEnchantType.MELEE_WEAPON));
        m.setItemSlot(EquipmentSlot.OFFHAND,getSpecialDrop(mobLevel));
        m.setDropChance(EquipmentSlot.HEAD,0.085f);
        m.setDropChance(EquipmentSlot.CHEST,0.085f);
        m.setDropChance(EquipmentSlot.LEGS,0.085f);
        m.setDropChance(EquipmentSlot.FEET,0.085f);
        m.setDropChance(EquipmentSlot.MAINHAND,0.085f);
        m.setGuaranteedDrop(EquipmentSlot.OFFHAND);
    }


    private void spawnMob(String mobType, int mobLevel, int difficulty, ServerLevel level, BlockPos blockPos){
        float healthMultiplier = getHealthMultiplier(mobLevel);
        float speedMultiplier = getSpeedMultiplier(mobLevel);
        float attackAddition = getAttackAddition(mobLevel);
        switch (mobType){
            case "zombie":
            {

                Zombie m = new Zombie(EntityType.ZOMBIE, level);
                m.setItemSlot(EquipmentSlot.HEAD,new ItemStack(Items.STONE_BUTTON));
                Objects.requireNonNull(m.getAttribute(Attributes.KNOCKBACK_RESISTANCE)).setBaseValue(4);
                Objects.requireNonNull(m.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(healthMultiplier *20);
                Objects.requireNonNull(m.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(Objects.requireNonNull(m.getAttribute(Attributes.MOVEMENT_SPEED)).getBaseValue()*speedMultiplier);
                Objects.requireNonNull(m.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(Objects.requireNonNull(m.getAttribute(Attributes.ATTACK_DAMAGE)).getBaseValue()+attackAddition);
                if(mobLevel>0){
                    gearEliteMob(m,mobLevel);
                }
                m.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(m);
            }
            break;
            case "zombieTower":
            {
                Zombie m = new Zombie(EntityType.ZOMBIE, level);
                m.setItemSlot(EquipmentSlot.HEAD,new ItemStack(Items.STONE_BUTTON));
                m.setHealth(healthMultiplier *20);
                m.setSpeed(m.getSpeed()*speedMultiplier);
                m.setBaby(true);
                Zombie m1 = new Zombie(EntityType.ZOMBIE, level);
                m1.setItemSlot(EquipmentSlot.HEAD,new ItemStack(Items.STONE_BUTTON));
                m1.setHealth(healthMultiplier *20);
                m1.setBaby(true);
                Zombie m2 = new Zombie(EntityType.ZOMBIE, level);
                m2.setItemSlot(EquipmentSlot.HEAD,new ItemStack(Items.STONE_BUTTON));
                m2.setHealth(healthMultiplier *20);
                m2.setBaby(true);
                if(mobLevel>0){
                    gearEliteMob(m,mobLevel);
                    gearEliteMob(m1,mobLevel);
                    gearEliteMob(m2,mobLevel);
                }
                m.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                m1.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                m2.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(m);
                level.addFreshEntity(m1);
                level.addFreshEntity(m2);
                m2.startRiding(m1);
                m1.startRiding(m);
            }
            break;
            case "zombieGuardian":
            {
                Zombie m = new Zombie(EntityType.ZOMBIE, level);
                m.setItemSlot(EquipmentSlot.HEAD,new ItemStack(Items.STONE_BUTTON));
                m.setHealth(healthMultiplier *20);
                Guardian m1 = new Guardian(EntityType.GUARDIAN, level);
                m1.setHealth(healthMultiplier *30);
                if(mobLevel>0){
                    gearEliteMob(m,mobLevel);
                }
                m.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                m1.moveTo((double)blockPos.getX() + 0.5D, (double)blockPos.getY(), (double)blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                level.addFreshEntity(m);
                level.addFreshEntity(m1);
                m1.startRiding(m);
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

enum RandomEnchantType {
    ARMOR, CHEST_ARMOR, MELEE_WEAPON, BOW;
}