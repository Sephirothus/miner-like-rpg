package com.example.puzzle;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by konst on 17.01.17.
 */
public class Config {

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
        }});
        add(new HashMap<String, String>() {{
            put("name", "Flail");
            put("img", "weapon_flail");
            put("stat", "str");
            put("stat_points", "5");
            put("equip_slot", "equip_weapon");
            put("price", "50");
            put("drop_percent", "50");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Shard Sword");
            put("img", "weapon_shard_sword");
            put("stat", "str");
            put("stat_points", "7");
            put("equip_slot", "equip_weapon");
            put("price", "70");
            put("drop_percent", "30");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Broadsword");
            put("img", "weapon_broadsword");
            put("stat", "str");
            put("stat_points", "4");
            put("equip_slot", "equip_weapon");
            put("price", "35");
            put("drop_percent", "70");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Bone Knife");
            put("img", "weapon_bone_knife");
            put("stat", "str");
            put("stat_points", "3");
            put("equip_slot", "equip_weapon_or_shield");
            put("price", "25");
            put("drop_percent", "50");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Spider Shield");
            put("img", "armor_spider_shield");
            put("stat", "hp");
            put("stat_points", "15");
            put("equip_slot", "equip_weapon_or_shield");
            put("price", "250");
            put("drop_percent", "10");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Round Wooden Shield");
            put("img", "armor_round_shield");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_weapon_or_shield");
            put("price", "70");
            put("drop_percent", "57");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Templar Shield");
            put("img", "armor_templar_shield");
            put("stat", "hp");
            put("stat_points", "10");
            put("equip_slot", "equip_weapon_or_shield");
            put("price", "150");
            put("drop_percent", "35");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Breastplate");
            put("img", "armor_iron_breastplate");
            put("stat", "hp");
            put("stat_points", "10");
            put("equip_slot", "equip_chest");
            put("price", "100");
            put("drop_percent", "33");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wizard's Shirt");
            put("img", "armor_wizard_shirt");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_chest");
            put("price", "60");
            put("drop_percent", "63");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Steel Vest");
            put("img", "armor_steel_vest");
            put("stat", "hp");
            put("stat_points", "15");
            put("equip_slot", "equip_chest");
            put("price", "200");
            put("drop_percent", "13");
        }});
        add(new HashMap<String, String>() {{
            put("name", "White Shirt");
            put("img", "armor_shirt");
            put("equip_slot", "equip_chest");
            put("price", "3");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Gauntlet");
            put("img", "armor_iron_gauntlet");
            put("stat", "hp");
            put("stat_points", "10");
            put("equip_slot", "equip_gauntlets");
            put("price", "100");
            put("drop_percent", "25");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Boxing Gloves");
            put("img", "armor_boxing_glove");
            put("stat", "hp");
            put("stat_points", "3");
            put("equip_slot", "equip_gauntlets");
            put("price", "30");
            put("drop_percent", "70");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Leather Gloves");
            put("img", "armor_leather_gloves");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_gauntlets");
            put("price", "50");
            put("drop_percent", "53");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Hat");
            put("img", "armor_hat");
            put("equip_slot", "equip_head");
            put("price", "1");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Helmet");
            put("img", "armor_iron_helmet");
            put("stat", "hp");
            put("stat_points", "7");
            put("equip_slot", "equip_head");
            put("price", "70");
            put("drop_percent", "40");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wizard's Hat");
            put("img", "armor_wizard_hat");
            put("stat", "hp");
            put("stat_points", "3");
            put("equip_slot", "equip_head");
            put("price", "50");
            put("drop_percent", "60");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Elf Helmet");
            put("img", "armor_elf_helmet");
            put("stat", "hp");
            put("stat_points", "10");
            put("equip_slot", "equip_head");
            put("price", "120");
            put("drop_percent", "23");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Iron Greaves");
            put("img", "armor_iron_greaves");
            put("stat", "hp");
            put("stat_points", "7");
            put("equip_slot", "equip_legs");
            put("price", "80");
            put("drop_percent", "45");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Armored Pants");
            put("img", "armor_armored_pants");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_legs");
            put("price", "50");
            put("drop_percent", "67");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Pteruges");
            put("img", "armor_pteruges");
            put("stat", "hp");
            put("stat_points", "4");
            put("equip_slot", "equip_legs");
            put("price", "35");
            put("drop_percent", "80");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Trousers");
            put("img", "armor_trousers");
            put("equip_slot", "equip_legs");
            put("price", "2");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wooden Clogs");
            put("img", "armor_wooden_clogs");
            put("equip_slot", "equip_boots");
            put("price", "3");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Boots");
            put("img", "armor_boots");
            put("stat", "hp");
            put("stat_points", "3");
            put("equip_slot", "equip_boots");
            put("price", "30");
            put("drop_percent", "65");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Leather Boots");
            put("img", "armor_leather_boot");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_boots");
            put("price", "40");
            put("drop_percent", "40");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Fur Boots");
            put("img", "armor_fur_boot");
            put("stat", "hp");
            put("stat_points", "7");
            put("equip_slot", "equip_boots");
            put("price", "80");
            put("drop_percent", "35");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Diamond Ring");
            put("img", "treasure_diamond_ring");
            put("stat", "steps");
            put("stat_points", "3");
            put("equip_slot", "equip_ring");
            put("price", "15");
            put("drop_percent", "60");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Simple Ring");
            put("img", "treasure_simple_ring");
            put("stat", "hp");
            put("stat_points", "1");
            put("equip_slot", "equip_ring");
            put("price", "10");
            put("drop_percent", "80");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Skull Signet");
            put("img", "treasure_skull_signet");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_ring");
            put("price", "40");
            put("drop_percent", "40");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Skull Ring");
            put("img", "treasure_skull_ring");
            put("stat", "str");
            put("stat_points", "5");
            put("equip_slot", "equip_ring");
            put("price", "40");
            put("drop_percent", "40");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Bow Tie");
            put("img", "armor_bow_tie");
            put("equip_slot", "equip_necklace");
            put("price", "1");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Pendant of Life");
            put("img", "treasure_pendant_of_life");
            put("stat", "hp");
            put("stat_points", "5");
            put("equip_slot", "equip_necklace");
            put("price", "35");
            put("drop_percent", "50");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Necklace Star");
            put("img", "treasure_necklace_star");
            put("stat", "steps");
            put("stat_points", "5");
            put("equip_slot", "equip_necklace");
            put("price", "30");
            put("drop_percent", "50");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Tribal Pendant");
            put("img", "treasure_tribal_pendant");
            put("stat", "str");
            put("stat_points", "5");
            put("equip_slot", "equip_necklace");
            put("price", "35");
            put("drop_percent", "50");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Strength Potion");
            put("img", "treasure_strength_potion");
            put("stat", "str");
            put("stat_points", "3");
            put("price", "20");
            put("drop_percent", "45");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Health Potion");
            put("img", "treasure_health_potion");
            put("stat", "hp");
            put("stat_points", "3");
            put("price", "20");
            put("drop_percent", "45");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Potion of Health Restoration");
            put("img", "treasure_restore_health_potion");
            put("stat", "hp");
            put("stat_points", "0");
            put("price", "50");
            put("drop_percent", "35");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Old Stone Tablet");
            put("img", "junk_old_stone_tablet");
            put("price", "5");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Wine Jug");
            put("img", "junk_wine_jug");
            put("price", "1");
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
            put("price", "1");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Old Book");
            put("img", "junk_old_book");
            put("price", "2");
            put("description", "It's a useless junk");
        }});
        add(new HashMap<String, String>() {{
            put("name", "Oily Nut");
            put("img", "junk_oily_nut");
            put("description", "It's a useless junk");
        }});
    }};

    public static ArrayList<HashMap<String, String>> mEnemies = new ArrayList<HashMap<String, String>>() {{
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

    Config (Context context) {
        mContext = context;
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
        ArrayList<HashMap<String, String>> foundItems = new ArrayList<>();
        float dropPerc = getDropPercent();
        for (HashMap<String, String> item: mTreasures) {
            String curItemDropPerc = item.get("drop_percent") != null ? item.get("drop_percent") : "100";
            if (Float.parseFloat(curItemDropPerc) >= dropPerc) {
                foundItems.add(item);
            }
        }
        mCurItem = foundItems.get((new Random()).nextInt(foundItems.size()));
    }

    public static float getDropPercent() {
        return (new Random()).nextFloat() * 100;
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
