package com.example.puzzle;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by konst on 03.02.17.
 */
public class Quest {

    public final static int MAX_ENEMIES_COUNT = 10;
    public final static int MIN_ENEMIES_COUNT = 1;
    public final static int MAX_ITEMS_COUNT = 5;
    public final static int MIN_ITEMS_COUNT = 1;
    public final static int QUEST_GOLD_MULTIPLIER = 5;

    public final static int QUEST_TYPE_KILL = 1;
    public final static int QUEST_TYPE_GET_ITEM = 2;
    public final static int QUEST_TYPE_WALK_TO_LOCATION = 3;
    public final static int QUEST_TYPE_TALK = 4;

    private Context mContext;
    private Config mConf;
    private ArrayList<String> mPresentTypes = new ArrayList<>();
    private String[] mTypes = {"killEnemies", "foundItems"/*, "killBoss"*/};

    public static ArrayList<String> mItemTypesSingle = new ArrayList<String>() {{
        add("armor");
        add("weapon");
        add("accessory");
        add("potion");
    }};
    public static ArrayList<String> mItemTypesMultiple = new ArrayList<String>() {{
        add("item");
    }};
    private HashMap<String, String> mKillEnemiesTitles = new HashMap<String, String>(){{
        put("Trouble with $type", "Please help me, I owed a lot of money to $type and they want to kill me. Kill at least $count" +
                " of them and they will leave me alone.");
        put("$type raid our town", "$type is raiding our town, they take all our stuff every time they come. You must kill " +
                "$count $type and they will stop terrorizing us.");
        put("Revenge on $type", "$type killed all my family. I want revenge and if $count will be slain, you will get " +
                "a descent reward.");
    }};
    private HashMap<String, String> mFoundItemsTitles = new HashMap<String, String>(){{
        put("Lost $type", "I lost $count $type, when I was outside town, can you find it?");
        put("Need for $type", "I'm a scientist and my work is very important to our town. I need $count $type for " +
                "my experiment, will you get me it?");
        put("Birthday present - $type", "In a couple of days my brother will have a birthday and I want to make a gift for him. " +
                "Can you bring me $count $type?");
    }};

    public Quest(Context context) {
        mContext = context;
        mConf = new Config(mContext);
    }

    public static String getQuestGiverString(Player player, String questId, HashMap questInfo) {
        return (player.haveQuest(questId) ? "?" : "!") + " Quest: " + questInfo.get("title");
    }

    public static String getQuestLocation(String questId) {
        String[] info = questId.split("_");
        return "Return: " + info[0] + ", cell number " + info[1];
    }

    public static String generateQuestId(String townName, int position, int questNumber) {
        return townName + "_" + position + "_" + questNumber;
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
        do {
            mConf.randomEnemy();
        } while (mPresentTypes.contains(mConf.getCurItemName()));
        mPresentTypes.add(mConf.getCurItemName());

        final String title = mKillEnemiesTitles.keySet()
                .toArray()[(new Random()).nextInt(mKillEnemiesTitles.size())].toString();
        final String count = String.valueOf((new Random()).nextInt(MAX_ENEMIES_COUNT) + MIN_ENEMIES_COUNT);
        return new HashMap<String, String>() {{
            put("action", String.valueOf(QUEST_TYPE_KILL));
            put("title", replacePlaceholders(title, ""));
            put("description", replacePlaceholders(mKillEnemiesTitles.get(title), count));
            put("type", mConf.getCurItemName());
            put("count", count);
            put("progress_count", "0");
        }};
    }

    private HashMap<String, String> foundItems() {
        String type;
        final String count;
        final String title = mFoundItemsTitles.keySet()
                .toArray()[(new Random()).nextInt(mFoundItemsTitles.size())].toString();
        if ((new Random()).nextInt(5) == 4) {
            type = mItemTypesSingle.get((new Random()).nextInt(mItemTypesSingle.size()));
            count = "1";
        } else {
            type = mItemTypesMultiple.get((new Random()).nextInt(mItemTypesMultiple.size()));
            count = String.valueOf((new Random()).nextInt(MAX_ITEMS_COUNT) + MIN_ITEMS_COUNT);
        }
        do {
            mConf.randomTreasureForQuest(type);
        } while (mPresentTypes.contains(mConf.getCurItemName()));
        mPresentTypes.add(mConf.getCurItemName());

        return new HashMap<String, String>() {{
            put("action", String.valueOf(QUEST_TYPE_GET_ITEM));
            put("title", replacePlaceholders(title, ""));
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
