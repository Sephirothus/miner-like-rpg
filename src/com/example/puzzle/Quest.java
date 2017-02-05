package com.example.puzzle;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by konst on 03.02.17.
 */
public class Quest {

    public static int MAX_ENEMIES_COUNT = 10;
    public static int MIN_ENEMIES_COUNT = 1;
    public static int MAX_ITEMS_COUNT = 5;
    public static int MIN_ITEMS_COUNT = 1;

    private Context mContext;
    private Config mConf;
    private String[] mTypes = {"killEnemies", "foundItems"/*, "killBoss"*/};
    private HashMap<String, String> mKillEnemiesTitles = new HashMap<String, String>(){{
        put("Trouble with $type", "Please help me, I owed a lot of money to $type and they want to kill me. Kill at least $count" +
                " of them and they will leave me alone.");
        //put("$type raid our town", "");
        //put("Revenge on $type", "");
    }};
    private HashMap<String, String> mFoundItemsTitles = new HashMap<String, String>(){{
       put("Lost $type", "I lost $count $type, when I was in the forest, can you find it?");
    }};

    Quest(Context context) {
        mContext = context;
        mConf = new Config(mContext);
    }

    public HashMap<String, String> randomQuest() {
        try {
            return (HashMap<String, String>) getClass()
                    .getDeclaredMethod(mTypes[(new Random()).nextInt(mTypes.length)]).invoke(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void mainLine() {

    }

    private HashMap<String, String> killEnemies() {
        mConf.randomEnemy();
        String title = mKillEnemiesTitles.keySet()
                .toArray()[(new Random()).nextInt(mKillEnemiesTitles.size())].toString();
        String count = String.valueOf((new Random()).nextInt(MAX_ENEMIES_COUNT) + MIN_ENEMIES_COUNT);
        return new HashMap<String, String>() {{
            put("title", "Quest: " + replacePlaceholders(title, ""));
            put("description", replacePlaceholders(mKillEnemiesTitles.get(title), count));
            put("type", mConf.getCurItemName());
            put("count", count);
        }};
    }

    private HashMap<String, String> foundItems() {
        mConf.randomTreasure();
        String title = mFoundItemsTitles.keySet()
                .toArray()[(new Random()).nextInt(mFoundItemsTitles.size())].toString();
        String count = String.valueOf((new Random()).nextInt(MAX_ITEMS_COUNT) + MIN_ITEMS_COUNT);
        return new HashMap<String, String>() {{
            put("title", "Quest: " + replacePlaceholders(title, ""));
            put("description", replacePlaceholders(mFoundItemsTitles.get(title), count));
            put("type", mConf.getCurItemName());
            put("count", count);
        }};
    }

    private String replacePlaceholders(String text, String count) {
        String item = mConf.getCurItemName();
        ///*if (Integer.parseInt(count) > 1) */item += item.substring(item.length() - 1) == "s" ? "es" : "s";
        return text.replace("$type", item).replace("$count", count);
    }

    private HashMap<String, String> killBoss() {
        return new HashMap<String, String>();
    }
}
