package com.example.puzzle;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by konst on 17.01.17.
 */
public class Config {

    public HashMap<String, String> mCurItem;
    public Context mContext;

    public ArrayList<HashMap<String, String>> mTreasures = new ArrayList<HashMap<String, String>>() {{
        add(new HashMap<String, String>() {{
           put("name", "Battle Axe");
           put("img", "weapon_battle_axe");
           put("stat", "str");
           put("stat_points", "10");
           put("description", "This mighty Battle Axe gives you additional 10 points of strength");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Flail");
            put("img", "weapon_flail");
            put("stat", "str");
            put("stat_points", "5");
            put("description", "This Flail gives you additional 5 points of strength");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Shard Sword");
            put("img", "weapon_shard_sword");
            put("stat", "str");
            put("stat_points", "7");
            put("description", "This Shard Sword gives you additional 7 points of strength");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Bone Knife");
            put("img", "weapon_bone_knife");
            put("stat", "str");
            put("stat_points", "3");
            put("description", "This Bone Knife gives you additional 3 points of strength");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Broadsword");
            put("img", "weapon_broadsword");
            put("stat", "str");
            put("stat_points", "4");
            put("description", "This Broadsword gives you additional 5 points of strength");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Breastplate");
            put("img", "armor_iron_breastplate");
            put("stat", "hp");
            put("stat_points", "10");
            put("description", "This Iron Breastplate gives you additional 10 health points");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Gauntlet");
            put("img", "armor_iron_gauntlet");
            put("stat", "hp");
            put("stat_points", "3");
            put("description", "This Iron Gauntlet gives you additional 3 health points");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Helmet");
            put("img", "armor_iron_helmet");
            put("stat", "hp");
            put("stat_points", "7");
            put("description", "This Iron Helmet gives you additional 5 health points");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Diamond Ring");
            put("img", "treasure_diamond_ring");
            put("stat", "steps");
            put("stat_points", "3");
            put("description", "This Diamond Ring gives you additional 3 steps points");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Pendant of Life");
            put("img", "treasure_pendant_of_life");
            put("stat", "hp");
            put("stat_points", "5");
            put("description", "This Pendant of Life gives you additional 5 health points");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Strength Potion");
            put("img", "treasure_strength_potion");
            put("stat", "str");
            put("stat_points", "3");
            put("description", "This Strength Potion gives you additional 3 points of strength");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Health Potion");
            put("img", "treasure_health_potion");
            put("stat", "hp");
            put("stat_points", "3");
            put("description", "This Health Potion gives you additional 3 health points");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Simple Ring");
            put("img", "junk_simple_ring");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Old Stone Tablet");
            put("img", "junk_old_stone_tablet");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wine Jug");
            put("img", "junk_wine_jug");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Fish-bone");
            put("img", "junk_fishbone");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Unknown Nasty Spill");
            put("img", "junk_nasty_spill");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Tooth");
            put("img", "junk_tooth");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Dice");
            put("img", "junk_dice");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Quill and Ink");
            put("img", "junk_quill_ink");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Old Book");
            put("img", "junk_old_book");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Oily Nut");
            put("img", "junk_oily_nut");
            put("description", "It's a useless junk");
        }});
    }};

    public ArrayList<HashMap<String, String>> mEnemies = new ArrayList<HashMap<String, String>>() {{
        add(new HashMap<String, String>() {{
            put("name", "Barbarian");
            put("img", "enemy_barbarian");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Dead Warrior");
            put("img", "enemy_dead_warrior");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Forest Troll");
            put("img", "enemy_forest_troll");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Orc Archer");
            put("img", "enemy_orc_archer");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wizard Gnome");
            put("img", "enemy_wizard_gnome");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Ice Golem");
            put("img", "enemy_ice_golem");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Water Monk");
            put("img", "enemy_water_monk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Elf Huntress");
            put("img", "enemy_elf_huntress");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Vampire");
            put("img", "enemy_vampire");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Tree Guardian");
            put("img", "enemy_tree_guardian");
        }});
    }};

    Config (Context context) {
        mContext = context;
    }

    public void randomTreasure() {
        mCurItem = mTreasures.get((new Random()).nextInt(mTreasures.size()));
    }

    public void randomEnemy() {
        mCurItem = mEnemies.get((new Random()).nextInt(mEnemies.size()));
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

    public String getCurTreasureStat() {
        return mCurItem.get("stat");
    }

    public int getCurTreasureStatPoints() {
        return Integer.parseInt(mCurItem.get("stat_points"));
    }
}
