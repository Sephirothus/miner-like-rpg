package com.example.puzzle;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.example.puzzle.activity.ExtendActivity;
import com.example.puzzle.dialog.EquipmentDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by konst on 17.01.17.
 */
public class Config {

    final static int MIN_GOLD_AMOUNT = 1;
    final static int MAX_GOLD_AMOUNT = 5;

    public HashMap<String, String> mCurItem;
    public Context mContext;

    public static ArrayList<HashMap<String, String>> mTreasures = new ArrayList<HashMap<String, String>>() {{
        add(new HashMap<String, String>() {{
           put("name", "Battle Axe");
           put("img", "weapon_battle_axe");
           put("stat", "str");
           put("stat_points", "10");
           put("equip_slot", "equip_weapon");
           put("price", "100");
           put("drop_percent", "20");
           put("description", "This mighty Battle Axe");
           put("type", "weapon");
           put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Flail");
            put("img", "weapon_flail");
            put("stat", "str");
            put("stat_points", "5");
            put("equip_slot", "equip_weapon");
            put("price", "50");
            put("drop_percent", "50");
            put("type", "weapon");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Shard Sword");
            put("img", "weapon_shard_sword");
            put("stat", "str");
            put("stat_points", "7");
            put("equip_slot", "equip_weapon");
            put("price", "70");
            put("drop_percent", "30");
            put("type", "weapon");
            put("location", "ruins");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Broadsword");
            put("img", "weapon_broadsword");
            put("stat", "str");
            put("stat_points", "4");
            put("equip_slot", "equip_weapon");
            put("price", "35");
            put("drop_percent", "70");
            put("type", "weapon");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Bone Knife");
            put("img", "weapon_bone_knife");
            put("stat", "str");
            put("stat_points", "3");
            put("equip_slot", "equip_weapon_or_shield");
            put("price", "25");
            put("drop_percent", "50");
            put("type", "weapon");
            put("location", "dead_forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Spider Shield");
            put("img", "armor_spider_shield");
            put("stat", "hp");
            put("stat_points", "15");
            put("equip_slot", "equip_weapon_or_shield");
            put("price", "250");
            put("drop_percent", "10");
            put("type", "armor");
            put("location", "dead_forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Round Wooden Shield");
            put("img", "armor_round_shield");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_weapon_or_shield");
            put("price", "70");
            put("drop_percent", "57");
            put("type", "armor");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Templar Shield");
            put("img", "armor_templar_shield");
            put("stat", "hp");
            put("stat_points", "10");
            put("equip_slot", "equip_weapon_or_shield");
            put("price", "150");
            put("drop_percent", "35");
            put("type", "armor");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Breastplate");
            put("img", "armor_iron_breastplate");
            put("stat", "hp");
            put("stat_points", "10");
            put("equip_slot", "equip_chest");
            put("price", "100");
            put("drop_percent", "33");
            put("type", "armor");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wizard's Shirt");
            put("img", "armor_wizard_shirt");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_chest");
            put("price", "60");
            put("drop_percent", "63");
            put("type", "armor");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Steel Vest");
            put("img", "armor_steel_vest");
            put("stat", "hp");
            put("stat_points", "15");
            put("equip_slot", "equip_chest");
            put("price", "200");
            put("drop_percent", "13");
            put("type", "armor");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "White Shirt");
            put("img", "armor_shirt");
            put("equip_slot", "equip_chest");
            put("price", "3");
            put("type", "item");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Gauntlet");
            put("img", "armor_iron_gauntlet");
            put("stat", "hp");
            put("stat_points", "10");
            put("equip_slot", "equip_gauntlets");
            put("price", "100");
            put("drop_percent", "25");
            put("type", "armor");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Boxing Gloves");
            put("img", "armor_boxing_glove");
            put("stat", "hp");
            put("stat_points", "3");
            put("equip_slot", "equip_gauntlets");
            put("price", "30");
            put("drop_percent", "70");
            put("type", "armor");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Leather Gloves");
            put("img", "armor_leather_gloves");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_gauntlets");
            put("price", "50");
            put("drop_percent", "53");
            put("type", "armor");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Hat");
            put("img", "armor_hat");
            put("equip_slot", "equip_head");
            put("price", "1");
            put("type", "item");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Helmet");
            put("img", "armor_iron_helmet");
            put("stat", "hp");
            put("stat_points", "7");
            put("equip_slot", "equip_head");
            put("price", "70");
            put("drop_percent", "40");
            put("type", "armor");
            put("location", "dead_forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wizard's Hat");
            put("img", "armor_wizard_hat");
            put("stat", "hp");
            put("stat_points", "3");
            put("equip_slot", "equip_head");
            put("price", "50");
            put("drop_percent", "60");
            put("type", "armor");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Elf Helmet");
            put("img", "armor_elf_helmet");
            put("stat", "hp");
            put("stat_points", "10");
            put("equip_slot", "equip_head");
            put("price", "120");
            put("drop_percent", "23");
            put("type", "armor");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Greaves");
            put("img", "armor_iron_greaves");
            put("stat", "hp");
            put("stat_points", "7");
            put("equip_slot", "equip_legs");
            put("price", "80");
            put("drop_percent", "45");
            put("type", "armor");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Armored Pants");
            put("img", "armor_armored_pants");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_legs");
            put("price", "50");
            put("drop_percent", "67");
            put("type", "armor");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Pteruges");
            put("img", "armor_pteruges");
            put("stat", "hp");
            put("stat_points", "4");
            put("equip_slot", "equip_legs");
            put("price", "35");
            put("drop_percent", "80");
            put("type", "armor");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Trousers");
            put("img", "armor_trousers");
            put("equip_slot", "equip_legs");
            put("price", "2");
            put("type", "item");
            put("location", "dead_forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wooden Clogs");
            put("img", "armor_wooden_clogs");
            put("equip_slot", "equip_boots");
            put("price", "3");
            put("type", "item");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Boots");
            put("img", "armor_boots");
            put("stat", "hp");
            put("stat_points", "3");
            put("equip_slot", "equip_boots");
            put("price", "30");
            put("drop_percent", "65");
            put("type", "armor");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Leather Boots");
            put("img", "armor_leather_boot");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_boots");
            put("price", "40");
            put("drop_percent", "40");
            put("type", "armor");
            put("location", "ruins");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Fur Boots");
            put("img", "armor_fur_boot");
            put("stat", "hp");
            put("stat_points", "7");
            put("equip_slot", "equip_boots");
            put("price", "80");
            put("drop_percent", "35");
            put("type", "armor");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Diamond Ring");
            put("img", "treasure_diamond_ring");
            put("stat", "steps");
            put("stat_points", "3");
            put("equip_slot", "equip_ring");
            put("price", "15");
            put("drop_percent", "60");
            put("type", "accessory");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Simple Ring");
            put("img", "treasure_simple_ring");
            put("stat", "hp");
            put("stat_points", "1");
            put("equip_slot", "equip_ring");
            put("price", "10");
            put("drop_percent", "80");
            put("type", "accessory");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Skull Signet");
            put("img", "treasure_skull_signet");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_ring");
            put("price", "40");
            put("drop_percent", "40");
            put("type", "accessory");
            put("location", "dead_forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Skull Ring");
            put("img", "treasure_skull_ring");
            put("stat", "str");
            put("stat_points", "5");
            put("equip_slot", "equip_ring");
            put("price", "40");
            put("drop_percent", "40");
            put("type", "accessory");
            put("location", "ruins");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Bow Tie");
            put("img", "armor_bow_tie");
            put("equip_slot", "equip_necklace");
            put("price", "1");
            put("type", "item");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Pendant of Life");
            put("img", "treasure_pendant_of_life");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_necklace");
            put("price", "35");
            put("drop_percent", "50");
            put("type", "accessory");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Necklace Star");
            put("img", "treasure_necklace_star");
            put("stat", "steps");
            put("stat_points", "5");
            put("equip_slot", "equip_necklace");
            put("price", "30");
            put("drop_percent", "50");
            put("type", "accessory");
            put("location", "ruins");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Tribal Pendant");
            put("img", "treasure_tribal_pendant");
            put("stat", "str");
            put("stat_points", "5");
            put("equip_slot", "equip_necklace");
            put("price", "35");
            put("drop_percent", "50");
            put("type", "accessory");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Strength Potion");
            put("img", "treasure_strength_potion");
            put("stat", "str");
            put("stat_points", "3");
            put("price", "20");
            put("drop_percent", "45");
            put("type", "potion");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Health Potion");
            put("img", "treasure_health_potion");
            put("stat", "hp");
            put("stat_points", "3");
            put("price", "20");
            put("drop_percent", "45");
            put("type", "potion");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Potion of Health Restoration");
            put("img", "treasure_restore_health_potion");
            put("stat", "hp");
            put("stat_points", "0");
            put("price", "50");
            put("drop_percent", "35");
            put("type", "potion");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Dice");
            put("img", "item_dice");
            //put("description", "");
            put("type", "item");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Quill and Ink");
            put("img", "item_quill_ink");
            put("price", "1");
            //put("description", "");
            put("type", "item");
            put("location", "ruins");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Old Book");
            put("img", "item_old_book");
            put("price", "2");
            //put("description", "");
            put("type", "item");
            put("location", "ruins");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Screw-nut");
            put("img", "item_oily_nut");
            //put("description", "");
            put("type", "item");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Old Stone Tablet");
            put("img", "item_old_stone_tablet");
            put("price", "5");
            //put("description", "");
            put("type", "item");
            put("location", "dead_forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wine Jug");
            put("img", "item_wine_jug");
            put("price", "1");
            //put("description", "");
            put("type", "item");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Crystal eye");
            put("img", "item_crystal_eye");
            //put("description", "");
            put("price", "10");
            put("type", "item");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Paddles");
            put("img", "item_paddles");
            //put("description", "");
            put("price", "3");
            put("type", "item");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Rope");
            put("img", "item_rope");
            //put("description", "");
            put("price", "2");
            put("type", "item");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Ruby");
            put("img", "item_ruby");
            //put("description", "");
            put("price", "20");
            put("type", "item");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Fish-bone");
            put("img", "junk_fishbone");
            put("description", "It's a useless junk");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Unknown Nasty Spill");
            put("img", "junk_nasty_spill");
            put("description", "It's a useless junk");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Tooth");
            put("img", "junk_tooth");
            put("description", "It's a useless junk");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Gold");
            put("img", "treasure_coins");
        }});
    }};

    public static ArrayList<HashMap<String, String>> mEnemies = new ArrayList<HashMap<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("name", "Barbarian");
            put("img", "enemy_barbarian");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Dead Warrior");
            put("img", "enemy_dead_warrior");
            put("location", "dead_forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Forest Troll");
            put("img", "enemy_forest_troll");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Orc Archer");
            put("img", "enemy_orc_archer");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wizard Gnome");
            put("img", "enemy_wizard_gnome");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Ice Golem");
            put("img", "enemy_ice_golem");
            put("location", "mountains");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Water Monk");
            put("img", "enemy_water_monk");
            put("location", "desert");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Elf Huntress");
            put("img", "enemy_elf_huntress");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Vampire");
            put("img", "enemy_vampire");
            put("location", "dead_forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Tree Guardian");
            put("img", "enemy_tree_guardian");
            put("location", "forest");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Haunting");
            put("img", "enemy_haunting");
            put("location", "ruins");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Evil Book");
            put("img", "enemy_evil_book");
            put("location", "ruins");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Slime");
            put("img", "enemy_slime");
            put("location", "swamp");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Fish Monster");
            put("img", "enemy_fish_monster");
            put("location", "wasteland");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Pirate Skeleton");
            put("img", "enemy_pirate_skeleton");
            put("location", "desert");
        }});
    }};

    public static HashMap<String, String> mTowns = new HashMap<String, String>() {{
        put("Goodlight", "It's our capital.");
        put("Dusk town", "It's just a town, nothing to say");
        put("Village of Grass", "Here we all love grass, it's a symbol of life");
        put("Serpent village", "It was given this name, 'cause this town infested with this vermins.");
        put("Sunless", "In this town you'll never see the sun, that's why it's Sunless :)");
        put("Home of Farmers", "It's more a big farm, than a town");
        put("Old Keep", "We have here, one of the oldest sights in our homeland. It's an Old Keep, " +
                "which once were one of the strongest fortresses in the east and which not survived the legendary " +
                "War of the elves. Then it was brought here, all that's left, and rebuild stone by stone");
    }};

    public static HashMap<String, String> mPathLocations = new HashMap<String, String>() {{
        put("forest", "Forest");
        put("mountains", "Mountains");
        put("desert", "Desert");
        put("dead_forest", "Dead Forest");
        put("swamp", "Swamp");
        put("wasteland", "Wasteland");
        put("ruins", "Old Ruins");
    }};

    private static HashMap mShortStatNames = new HashMap<String, String>() {{
        put("lvl", "Lvl");
        put("hp", "HP");
        put("str", "Str");
        put("mp", "MP");
        put("steps", "Steps");
        put("gold", "Gold");
    }};
    private static HashMap mFullStatNames = new HashMap<String, String>() {{
        put("lvl", "Level");
        put("hp", "Health Points");
        put("str", "Strength");
        put("mp", "Mana Points");
    }};

    public static ArrayList<HashMap<String, String>> mBattleCombos = new ArrayList<HashMap<String, String>>() {{
        /*add(new HashMap<String, String>() {{
            put("type", "default|special");
            put("position", "horizontal|vertical|diagonal");
            put("title", "War cry");
            put("min_lvl", "5");
            put("action", "strike|heal|block|skip_move|clear_line");
            put("name", "war_cry");
            put("pattern", "");
        }});*/
        add(new HashMap<String, String>() {{
            put("type", "default");
            put("position", "horizontal");
            put("title", "Thunder strike");
            put("min_lvl", "1");
            put("action", "strike");
            put("name", "thunder_strike");
            put("pattern", "=,=");
        }});
        add(new HashMap<String, String>() {{
            put("type", "default");
            put("position", "diagonal");
            put("title", "War cry");
            put("min_lvl", "1");
            put("action", "strike");
            put("name", "war_cry");
            put("pattern", "=,=");
        }});
        add(new HashMap<String, String>() {{
            put("type", "default");
            put("position", "vertical");
            put("title", "Heal me");
            put("min_lvl", "1");
            put("action", "heal");
            put("name", "heal_me");
            put("pattern", ">,<");
        }});
        add(new HashMap<String, String>() {{
            put("type", "default");
            put("position", "horizontal");
            put("title", "Block");
            put("min_lvl", "1");
            put("action", "block");
            put("name", "block");
            put("pattern", "<,<");
        }});
        add(new HashMap<String, String>() {{
            put("type", "default");
            put("position", "diagonal");
            put("title", "Clear out");
            put("min_lvl", "1");
            put("action", "clear_line");
            put("name", "clear_out");
            put("pattern", ">,>");
        }});
    }};

    public static ArrayList<HashMap<String, String>> mTalks = new ArrayList<HashMap<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("id", "1");
            put("unit_id", "0");
            put("text", "Got any news?");
            put("parent_id", "0");
            put("is_player", "1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "2");
            put("unit_id", "0");
            put("text", "Nothing new here");
            put("parent_id", "1");
            put("is_player", "0");
        }});
        add(new HashMap<String, String>() {{
            put("id", "3");
            put("unit_id", "0");
            put("text", "Tell me something interesting");
            put("parent_id", "0");
            put("is_player", "1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "4");
            put("unit_id", "0");
            put("text", "Do you heard about The well of souls?");
            put("parent_id", "3");
            put("is_player", "0");
        }});
        add(new HashMap<String, String>() {{
            put("id", "5");
            put("unit_id", "0");
            put("text", "Yes, not interested");
            put("parent_id", "4");
            put("is_player", "1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "6");
            put("unit_id", "0");
            put("text", "No, please tell me about it");
            put("parent_id", "4");
            put("is_player", "1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "7");
            put("unit_id", "0");
            put("text", "In the south-east of this town you can find the well of souls");
            put("parent_id", "6");
            put("is_player", "0");
        }});
        add(new HashMap<String, String>() {{
            put("id", "8");
            put("unit_id", "0");
            put("text", "And what's in there?");
            put("parent_id", "7");
            put("is_player", "1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "9");
            put("unit_id", "0");
            put("text", "I bet there can be gold");
            put("parent_id", "7");
            put("is_player", "1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "10");
            put("unit_id", "0");
            put("text", "Ah, I know this story");
            put("parent_id", "7");
            put("is_player", "1");
        }});
        add(new HashMap<String, String>() {{
            put("id", "11");
            put("unit_id", "0");
            put("text", "No, you're wrong, there's nothing there");
            put("parent_id", "9");
            put("is_player", "0");
        }});
        add(new HashMap<String, String>() {{
            put("id", "12");
            put("unit_id", "0");
            put("text", "I wish I could know");
            put("parent_id", "8");
            put("is_player", "0");
        }});
    }};

    public Config(Context context) {
        mContext = context;
    }

    public static ArrayList<HashMap<String, String>> getQuestions(int unitId, int talkId) {
        ArrayList<HashMap<String, String>> talks = new ArrayList<>();
        for (HashMap<String, String> item : mTalks) {
            if (item.get("unit_id").equals(String.valueOf(unitId)) && item.get("is_player").equals("1") && item.get("parent_id").equals(String.valueOf(talkId))) {
                talks.add(item);
            }
        }
        return talks;
    }

    public static HashMap<String, String> getTalkById(String talkId) {
        for (HashMap<String, String> item : mTalks) {
            if (item.get("id") == talkId) {
                return item;
            }
        }
        return null;
    }

    public static ArrayList<HashMap<String, String>> getNextTalks(String talkId) {
        ArrayList<HashMap<String, String>> talks = new ArrayList<>();
        for (HashMap<String, String> item : mTalks) {
            if (item.get("parent_id") == talkId) {
                talks.add(item);
            }
        }
        return talks;
    }

    public void treasureByName(String name) {
        for (HashMap<String, String> item : mTreasures) {
            if (item.get("name") == name) {
                mCurItem = item;
                break;
            }
        }
    }

    public void randomTreasure() {
        randomTreasure(null);
    }

    public void randomTreasure(String location) {
        ArrayList<HashMap<String, String>> foundItems = new ArrayList<>();
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            foundItems = ((ExtendActivity) mContext).mPlayer.getQuestItemsByLocation(location);
            if (foundItems.isEmpty()) {
                float dropPerc = getDropPercent();
                for (HashMap<String, String> item : mTreasures) {
                    String curItemDropPerc = item.get("drop_percent") != null ? item.get("drop_percent") : "100";
                    if (Float.parseFloat(curItemDropPerc) >= dropPerc && (
                            location == null || (item.containsKey("location") && location == item.get("location"))
                    )) {
                        foundItems.add(item);
                    }
                }
            }
        }
        if (foundItems.size() == 0) {
            treasureByName("Gold");
            String count = String.valueOf(random.nextInt(MAX_GOLD_AMOUNT) + MIN_GOLD_AMOUNT);
            mCurItem.put("description", "This is a pile of " + count + " gold");
            mCurItem.put("gold_amount", count);
        } else {
            mCurItem = foundItems.get(random.nextInt(foundItems.size()));
        }
    }

    public void randomTreasureForQuest(String type) {
        ArrayList<HashMap<String, String>> foundItems = new ArrayList<>();
        for (HashMap<String, String> item: mTreasures) {
            if (item.containsKey("type") && type == item.get("type")) {
                foundItems.add(item);
            }
        }
        mCurItem = foundItems.get((new Random()).nextInt(foundItems.size()));
    }

    public static float getDropPercent() {
        return (new Random()).nextFloat() * 100;
    }

    public void enemyByName(String name) {
        for (HashMap<String, String> item : mEnemies) {
            if (item.get("name") == name) {
                mCurItem = item;
                break;
            }
        }
    }

    public void randomEnemy() {
        randomEnemy(null);
    }

    public void randomEnemy(String location) {
        HashMap enemy;
        if (location == null) {
            enemy = mEnemies.get((new Random()).nextInt(mEnemies.size()));
        } else {
            do {
                enemy = mEnemies.get((new Random()).nextInt(mEnemies.size()));
            } while (enemy.get("location") != location);
        }
        mCurItem = enemy;
    }

    public int getCurItemImg() {
        return mContext.getResources().getIdentifier(mCurItem.get("img"), "drawable", mContext.getPackageName());
    }

    public String getCurItemName() {
        return mCurItem.get("name");
    }

    public String getCurItemDescription() {
        return mCurItem.get("description");
    }

    public String getCurItemLocation() {
        return mCurItem.get("location");
    }

    public String getCurTreasureStat() {
        return mCurItem.get("stat");
    }

    public String getCurTreasureGoldAmount() {
        return mCurItem.get("gold_amount");
    }

    public int getCurTreasureStatPoints() {
        return Integer.parseInt(mCurItem.get("stat_points"));
    }

    public int getCurTreasurePrice() {
        return mCurItem.get("price") != null ? Integer.parseInt(mCurItem.get("price")) : 0;
    }

    public String getCurTreasureDescription() {
        String desc = getCurItemDescription() != null ? getCurItemDescription() + "\n" : "";
        if (getCurTreasureStat() != null) {
            String stat = getFullStatName(getCurTreasureStat());
            if (getCurTreasureStatPoints() == 0) {
                desc += "It will restore your " + stat + "\n";
            } else {
                desc += "It will increase your " + stat
                        + " by " + getCurTreasureStatPoints() + "\n";
            }
        }
        if (getCurTreasurePrice() > 0) {
            desc += "Item price is " + getCurTreasurePrice() + " gold";
        }
        return desc;
    }

    public String getCurTreasureEquipSlot(View view) {
        String slot = mCurItem.get("equip_slot");
        if (slot == null) return null;

        int id = getIdByStr(slot);
        String lastID = slot;
        int slotsCount = 1;
        if (id == 0) {
            int slotsLeft = EquipmentDialog.MAX_IDENTICAL_SLOTS;
            String defSlot = slot;
            while (slotsLeft > 0) {
                slot = defSlot + slotsCount;
                id = getIdByStr(slot);
                if (id > 0) {
                    lastID = slot;
                    ImageView imageView = (ImageView) view.findViewById(id);
                    if (imageView.getDrawable() == null) break;
                }
                slotsLeft--;
                slotsCount++;
            }
        }
        return lastID;
    }

    public int getIdByStr(String id) {
        return mContext.getResources().getIdentifier(id, "id", mContext.getPackageName());
    }

    public static String getShortStatName(String name) {
        return mShortStatNames.get(name).toString();
    }

    public static String getFullStatName(String name) {
        Object str = mFullStatNames.get(name);
        return str == null ? getShortStatName(name) : str.toString();
    }
}
