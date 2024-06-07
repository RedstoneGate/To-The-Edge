import crafttweaker.api.recipe.IRecipeManager;
import crafttweaker.api.item.IItemStack;
import crafttweaker.api.tag.manager.ITagManager;

<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_tile>);
//<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_brick1>);
//<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_brick2>);
//<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_brick3>);
//<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_brick_assorted>);
<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_stone>);
<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_polished>);
//<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_tilespecial>);
<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_smallbrick>);
//<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_pillar_center>);
//<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_pillar_special>);
//<tagmanager:items>.addId(<tag:items:bloodmagic:demonstone>, <resource:bloodmagic:dungeon_pillar_cap>);

<tagmanager:items>.addId(<tag:items:bloodmagic:orb1>, <resource:bloodmagic:weakbloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb1>, <resource:bloodmagic:apprenticebloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb1>, <resource:bloodmagic:magicianbloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb1>, <resource:bloodmagic:masterbloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb1>, <resource:bloodmagic:archmagebloodorb>);

<tagmanager:items>.addId(<tag:items:bloodmagic:orb2>, <resource:bloodmagic:apprenticebloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb2>, <resource:bloodmagic:magicianbloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb2>, <resource:bloodmagic:masterbloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb2>, <resource:bloodmagic:archmagebloodorb>);

<tagmanager:items>.addId(<tag:items:bloodmagic:orb3>, <resource:bloodmagic:magicianbloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb3>, <resource:bloodmagic:masterbloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb3>, <resource:bloodmagic:archmagebloodorb>);

<tagmanager:items>.addId(<tag:items:bloodmagic:orb4>, <resource:bloodmagic:masterbloodorb>);
<tagmanager:items>.addId(<tag:items:bloodmagic:orb4>, <resource:bloodmagic:archmagebloodorb>);

<tagmanager:items>.addId(<tag:items:bloodmagic:orb5>, <resource:bloodmagic:archmagebloodorb>);

<recipetype:bloodmagic:altar>.removeByName("bloodmagic:altar/weakbloodorb");
<recipetype:bloodmagic:altar>.removeByName("bloodmagic:altar/apprenticebloodorb");
<recipetype:bloodmagic:altar>.removeByName("bloodmagic:altar/magicianbloodorb");
<recipetype:bloodmagic:altar>.removeByName("bloodmagic:altar/masterbloodorb");
<recipetype:bloodmagic:altar>.removeByName("bloodmagic:altar/archmagebloodorb");
<recipetype:bloodmagic:altar>.addJsonRecipe("d1_orb", {
	input:<item:blue_skies:pyrope_gem>,
	output:<item:bloodmagic:weakbloodorb>,
	upgradeLevel:0,
	altarSyphon:2000,
	consumptionRate:5,
	drainRate:1
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d2_orb", {
	input:<item:evilcraft:potentia_sphere>,
	output:<item:bloodmagic:apprenticebloodorb>,
	upgradeLevel:1,
	altarSyphon:5000,
	consumptionRate:5,
	drainRate:5
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d3_orb", {
	input:<item:cataclysm:lionfish>,
	output:<item:bloodmagic:magicianbloodorb>,
	upgradeLevel:2,
	altarSyphon:25000,
	consumptionRate:20,
	drainRate:20
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d4_orb", {
	input:<item:cataclysm:ancient_metal_nugget>,
	output:<item:bloodmagic:masterbloodorb>,
	upgradeLevel:3,
	altarSyphon:40000,
	consumptionRate:30,
	drainRate:50
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d5_orb", {
	input:<item:cataclysm:void_stone>,
	output:<item:bloodmagic:archmagebloodorb>,
	upgradeLevel:4,
	altarSyphon:80000,
	consumptionRate:50,
	drainRate:100
});




craftingTable.remove(<item:botania:fertilizer>);
craftingTable.remove(<item:bloodmagic:blankrune>);
craftingTable.remove(<item:bloodmagic:soulforge>);
craftingTable.remove(<item:bloodmagic:alchemytable>);
craftingTable.remove(<item:bloodmagic:alchemicalreactionchamber>);
craftingTable.remove(<item:bloodmagic:furnacecell_primitive>);
craftingTable.remove(<item:bloodmagic:primitive_hydration_cell>);
craftingTable.remove(<item:bloodmagic:ritualstone>);
craftingTable.remove(<item:bloodmagic:masterritualstone>);
craftingTable.remove(<item:bloodmagic:lavacrystal>);

craftingTable.addShaped("d_rune", <item:bloodmagic:blankrune>, [
    [<tag:items:forge:stone>, <item:bloodmagic:blankslate>, <tag:items:forge:stone>],
    [<tag:items:forge:stone>, <tag:items:bloodmagic:orb1>, <tag:items:forge:stone>], 
    [<tag:items:forge:stone>, <item:blue_skies:moonstone_block>, <tag:items:forge:stone>]]);

craftingTable.addShaped("d_forge", <item:bloodmagic:soulforge>, [
    [<item:blue_skies:moonstone_shard>, <item:minecraft:air>, <item:blue_skies:moonstone_shard>],
    [<item:blue_skies:lunar_stone>, <item:blue_skies:vitreous_moonstone>, <item:blue_skies:lunar_stone>], 
    [<item:blue_skies:lunar_stone>, <item:blue_skies:horizonite_forge>, <item:blue_skies:lunar_stone>]]);

craftingTable.addShaped("d_alchemy", <item:bloodmagic:alchemytable>, [
    [<tag:items:forge:stone>, <tag:items:forge:stone>, <tag:items:forge:stone>],
    [<item:minecraft:iron_ingot>, <item:blue_skies:alchemy_table>, <item:minecraft:iron_ingot>], 
    [<item:minecraft:gold_ingot>, <item:bloodmagic:blankslate>, <item:minecraft:gold_ingot>]]);

craftingTable.addShaped("d_ritualstone", <item:bloodmagic:ritualstone>, [
    [<item:minecraft:chiseled_polished_blackstone>, <item:bloodmagic:reinforcedslate>, <item:minecraft:chiseled_polished_blackstone>],
    [<item:bloodmagic:reinforcedslate>, <tag:items:bloodmagic:orb2>, <item:bloodmagic:reinforcedslate>], 
    [<item:minecraft:chiseled_polished_blackstone>,<item:bloodmagic:reinforcedslate>, <item:minecraft:chiseled_polished_blackstone>]]);

craftingTable.addShaped("d_masterritualstone", <item:bloodmagic:masterritualstone>, [
    [<item:minecraft:netherite_scrap>, <item:bloodmagic:ritualstone>, <item:minecraft:netherite_scrap>],
    [<item:bloodmagic:ritualstone>, <tag:items:bloodmagic:orb3>, <item:bloodmagic:ritualstone>], 
    [<item:minecraft:netherite_scrap>, <item:bloodmagic:ritualstone>, <item:minecraft:netherite_scrap>]]);

craftingTable.addShaped("d_lavacrystal", <item:bloodmagic:lavacrystal>, [
    [<item:blue_skies:vitreous_moonstone>, <item:evilcraft:dark_power_gem>, <item:blue_skies:vitreous_moonstone>],
    [<item:evilcraft:dark_power_gem>, <tag:items:bloodmagic:orb1>, <item:evilcraft:dark_power_gem>], 
    [<item:blue_skies:sunstone_block>, <item:bloodmagic:reagentlava>, <item:blue_skies:sunstone_block>]]);


craftingTable.addShaped("d_arc", <item:bloodmagic:alchemicalreactionchamber>, [
    [<tag:items:bloodmagic:demonstone>, <tag:items:bloodmagic:demonstone>, <tag:items:bloodmagic:demonstone>],
    [<item:bloodmagic:infusedslate>, <tag:items:bloodmagic:orb3>, <item:bloodmagic:infusedslate>], 
    [<item:evilcraft:dark_block>, <item:minecraft:blast_furnace>, <item:evilcraft:dark_block>]]);

craftingTable.addShapeless("g_zlighter", <item:blue_skies:zeal_lighter>, [<item:minecraft:diamond>, <item:minecraft:amethyst_shard>]);

craftingTable.addShapeless("l_floralfertilizer", <item:botania:fertilizer>, [<item:rootsclassic:growth_powder>, <item:rootsclassic:growth_powder>,<item:rootsclassic:verdant_sprig>,<tag:items:forge:dyes>]);


//Pure Daisy I
<recipetype:botania:pure_daisy>.removeByName("botania:pure_daisy/livingrock");
<recipetype:botania:pure_daisy>.removeByName("botania:pure_daisy/livingwood");
<recipetype:botania:pure_daisy>.addJsonRecipe("l1_portal", {
  "input": {
    "type": "block",
    "block": "minecraft:stone"
  },
  "output": {
    "name": "blue_skies:turquoise_stone"
  }
});
<recipetype:botania:pure_daisy>.addJsonRecipe("l1_amethyst", {
  "input": {
    "type": "block",
    "block": "minecraft:lapis_block"
  },
  "output": {
    "name": "minecraft:amethyst_cluster"
  }
});

//Altar I
<recipetype:bloodmagic:altar>.removeByName("bloodmagic:altar/slate");
<recipetype:bloodmagic:altar>.removeByName("bloodmagic:altar/bucket_life");
<recipetype:bloodmagic:altar>.addJsonRecipe("d1_bonemeal", {
	input:<item:blue_skies:bug_guts>,
	output:<item:minecraft:bone_meal>,
	upgradeLevel:0,
	altarSyphon:100,
	consumptionRate:10,
	drainRate:1
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d1_sugarcane", {
	input:<item:blue_skies:glimmer_reed>,
	output:<item:minecraft:sugar_cane>,
	upgradeLevel:0,
	altarSyphon:100,
	consumptionRate:10,
	drainRate:1
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d1_string", {
	input:<item:rootsclassic:old_root>,
	output:<item:minecraft:string>,
	upgradeLevel:0,
	altarSyphon:500,
	consumptionRate:10,
	drainRate:1
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d1_portal", {
	input:<item:minecraft:deepslate_tiles>,
	output:<item:blue_skies:lunar_stonebrick>,
	upgradeLevel:0,
	altarSyphon:1000,
	consumptionRate:10,
	drainRate:1
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d1_blood", {
	input:<item:minecraft:bucket>,
	output:<item:evilcraft:bucket_blood>,
	upgradeLevel:0,
	altarSyphon:1000,
	consumptionRate:50,
	drainRate:0
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d1_amethyst", {
	input:<item:minecraft:diamond>,
	output:<item:minecraft:amethyst_shard>.withTag({display:{Lore:['{"text":"Hard as diamond."}']}}),
	upgradeLevel:0,
	altarSyphon:2500,
	consumptionRate:10,
	drainRate:1
});
<recipetype:bloodmagic:altar>.addJsonRecipe("d1_slate", {
	input:<item:minecraft:deepslate>,
	output:<item:bloodmagic:blankslate>,
	upgradeLevel:0,
	altarSyphon:1000,
	consumptionRate:10,
	drainRate:10
});

//Forge I
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/pettytartaricgem");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/lessertartaricgem");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/commontartaricgem");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/greatertartaricgem");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/aug_shaped_charge");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_deep");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_2");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_2");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_2");

<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_smelting");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_smelting");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_smelting");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_smelting");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_silk_touch");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_silk_touch");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_silk_touch");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_silk_touch");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_fortune_1");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_fortune_1");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_fortune_1");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_fortune_1");

<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/aug_shaped_charge_fortune_1_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_deep_fortune_1_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_2_fortune_1_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_2_fortune_1_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_2_fortune_1_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/aug_shaped_charge_smelting_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_deep_smelting_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_2_smelting_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_2_smelting_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_2_smelting_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/aug_shaped_charge_fortune_2_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_deep_fortune_2_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_2_fortune_2_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_2_fortune_2_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_2_fortune_2_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/aug_shaped_charge_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_deep_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_2_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_2_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_2_voiding");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/aug_shaped_charge_silk_touch_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/shaped_charge_deep_silk_touch_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/deforester_charge_2_silk_touch_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/vein_charge_2_silk_touch_l");
<recipetype:bloodmagic:soulforge>.removeByName("bloodmagic:soulforge/fungal_charge_2_silk_touch_l");

<recipetype:bloodmagic:soulforge>.addJsonRecipe("d1_gem1", {
	input0:<item:blue_skies:vitreous_moonstone>,
	input1:<item:blue_skies:horizonite_ingot>,
	input2:<item:blue_skies:pyrope_gem>,
	input3:<item:blue_skies:aquite>,
	output:<item:bloodmagic:soulgempetty>,
	minimumDrain:1,
	drain:1
});

<recipetype:bloodmagic:soulforge>.addJsonRecipe("d1_gem2", {
	input0:<item:bloodmagic:soulgempetty>,
	input1:<item:blue_skies:charoite>,
	input2:<item:blue_skies:pyrope_block>,
	input3:<item:blue_skies:aquite_block>,
	output:<item:bloodmagic:soulgemlesser>,
	minimumDrain:60,
	drain:5
});

<recipetype:bloodmagic:soulforge>.addJsonRecipe("d1_gem3", {
	input0:<item:bloodmagic:soulgemlesser>,
	input1:<item:evilcraft:dark_power_gem>,
	input2:<item:blue_skies:horizonite_block>,
	input3:<item:minecraft:prismarine>,
	output:<item:bloodmagic:soulgemcommon>,
	minimumDrain:250,
	drain:15
});

<recipetype:bloodmagic:soulforge>.addJsonRecipe("d1_gem4", {
	input0:<item:bloodmagic:soulgemcommon>,
	input1:<item:bloodmagic:demonslate>,
	input2:<item:bloodmagic:largebloodstonebrick>,
	input3:<item:minecraft:sculk>,
	output:<item:bloodmagic:soulgemgreater>,
	minimumDrain:1000,
	drain:50
});

<recipetype:bloodmagic:soulforge>.addJsonRecipe("aquite_dagger", {
	input0:<item:blue_skies:aquite>,
	input1:<item:blue_skies:aquite>,
	input2:<item:minecraft:string>,
	output:<item:bloodmagic:throwing_dagger> * 16,
	minimumDrain:32,
	drain:2
});