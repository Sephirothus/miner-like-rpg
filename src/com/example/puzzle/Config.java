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
