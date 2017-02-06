package com.example.puzzle;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Player implements BattleUnitInterface {

    final static int LVL_STAT_INCREASE = 1;

    private Context mContext;
    private int mGold = 0;
    private int mKilledEnemies = 0;
    private HashMap<String, HashMap<String, String>> mQuests = new HashMap<>();
    private ArrayList<String> mInventory = new ArrayList<>();
    private HashMap<String, String> mEquipment = new HashMap<String, String>() {{
        put("Hat", "equip_head");
        put("White Shirt", "equip_chest");
        put("Trousers", "equip_legs");
        put("Wooden Clogs", "equip_boots");
    }};
    private HashMap mStats = new HashMap<String, Integer>() {{
        put("hp", 20);
        put("str", 1);
        //put("mp", 0);
        put("steps", 3);
    }};
    private HashMap mCurStats = new HashMap<String, Integer>() {{
        putAll(mStats);
    }};

    Player (Context context) {
        mContext = context;
    }

    public int getCurStat(String stat) {
        return (int) mCurStats.get(stat);
    }

    public void addCurStat(String stat, int addValue) {
        mCurStats.put(stat, getCurStat(stat) + addValue);
    }

    public void refreshCurStat(String stat) {
        mCurStats.put(stat, mStats.get(stat));
    }

    public int getStat(String stat) {
        return (int) mStats.get(stat);
    }

    public void addStat(String stat, int addValue) {
        mStats.put(stat, getStat(stat) + addValue);
    }

    public void increaseStat(String stat, int addValue) {
        if (addValue == 0) {
            refreshCurStat(stat);
        } else {
            addStat(stat, addValue);
            addCurStat(stat, addValue);
        }
        changeStatsPanel(stat);
    }

    public void decreaseStat(String stat, int removeValue) {
        addStat(stat, -removeValue);
        addCurStat(stat, -removeValue);
        changeStatsPanel(stat);
    }

    public void changeStatsPanel(String stat) {
        // change stats panel
        String statName = Character.toUpperCase(stat.charAt(0)) + stat.substring(1);
        StatsPanelFragment statsPanel = ((ExtendActivity) mContext).mStatsPanelFragment;
        try {
            statsPanel.getClass()
                    .getDeclaredMethod("change" + statName + "Stat")
                    .invoke(statsPanel);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public void addItemToInventory(String itemName) {
        mInventory.add(itemName);
    }

    public void removeItemFromInventory(String itemName) {
        mInventory.remove(itemName);
    }

    public void addItemToEquipment(String itemName, String slot) {
        mEquipment.put(itemName, slot);
    }

    public void removeItemFromEquipment(String itemName) {
        mEquipment.remove(itemName);
    }

    public String getEquipmentSlotByItem(String item) {
        return mEquipment.get(item);
    }

    public String getItemByEquipmentSlot(String slot) {
        for (Object item : getEquipmentItems()) {
            if (slot.equals(mEquipment.get(item))) {
                return item.toString();
            }
        }
        return null;
    }

    public Boolean isItemEquiped(String item) {
        return mEquipment.containsKey(item);
    }

    public ArrayList<String> getInventoryItems() {
        return mInventory;
    }

    public Object[] getEquipmentItems() {
        return mEquipment.keySet().toArray();
    }

    public void getHit(int dmg) {
        addCurStat("hp", -dmg);
        ((ExtendActivity) mContext).mStatsPanelFragment.changeHpStat();
        ((ExtendActivity) mContext).mLogHistoryFragment.addEnemyHitPlayerRec(dmg);
    }

    public int strike(int dmg) {
        return dmg > 0 ? dmg + getStr() : 0;
    }

    public boolean checkHp() {
        return getHp() > 0;
    }

    public int getHp() {
        return getCurStat("hp");
    }

    public int getStr() {
        return getCurStat("str");
    }

    public int getMp() {
        return getCurStat("mp");
    }

    public int getSteps() {
        return getCurStat("steps");
    }

    public int getGold() {
        return mGold;
    }

    public void addKilledEnemy(String name) {
        mKilledEnemies++;
        for (Object questId : mQuests.keySet().toArray()) {
            HashMap questInfo = mQuests.get(questId);
            if (questInfo.get("type") == name && Integer.parseInt(questInfo.get("count").toString()) > 0) {
                questInfo.put("count", Integer.parseInt(questInfo.get("count").toString()) - 1);
            }
        }
    }

    public int getKilledEnemies() {
        return mKilledEnemies;
    }

    public void increaseGold(int value) {
        mGold += value;
    }

    public void decreaseGold(int value) {
        mGold -= value;
    }

    public void removeStep() {
        addCurStat("steps", -1);
        ((ExtendActivity) mContext).mStatsPanelFragment.changeStepsStat();
    }

    public void refreshSteps() {
        refreshCurStat("steps");
        ((ExtendActivity) mContext).mStatsPanelFragment.changeStepsStat();
    }

    public void lvlStatIncrease() {
        Object[] stats = getAllStats();
        String incrStat = stats[(new Random()).nextInt(stats.length)].toString();
        increaseStat(incrStat, LVL_STAT_INCREASE);
        ((ExtendActivity) mContext).mLogHistoryFragment.addStatIncreaseRec(incrStat, LVL_STAT_INCREASE);
    }

    public Object[] getAllStats() {
        return mStats.keySet().toArray();
    }

    public void setPlayerHeadImg(View view) {
        String head = getItemByEquipmentSlot("equip_head");
        if (head != null) {
            ImageView image = (ImageView) view.findViewById(R.id.player_head);
            Config conf = new Config(mContext);
            conf.treasureByName(head);
            image.setImageResource(conf.getCurItemImg());
            image.setVisibility(View.VISIBLE);
        }
    }

    public void addQuest(String questId, HashMap questInfo) {
        mQuests.put(questId, questInfo);
    }

    public Boolean haveQuest(String questId) {
        return mQuests.get(questId) != null ? true : false;
    }

    public Boolean isQuestComplete(String questId) {
        Boolean isComplete = false;
        HashMap questInfo = mQuests.get(questId);
        switch (Integer.parseInt(questInfo.get("action").toString())) {
            case Quest.QUEST_TYPE_KILL:
                isComplete = questInfo.get("count").toString() == "0";
                break;
            case Quest.QUEST_TYPE_GET_ITEM:
                isComplete = Collections.frequency(mInventory, questInfo.get("type").toString()) >= Integer.parseInt(
                        questInfo.get("count").toString());
                break;
        }
        return isComplete;
    }

    public void completeQuestGetItem(String questId) {
        HashMap questInfo = mQuests.get(questId);
        for (int i = 0; i < Integer.parseInt(questInfo.get("count").toString()); i++) {
            removeItemFromInventory(questInfo.get("type").toString());
        }
    }

    public void saveObject() {
        SharedPreferences sharedPreferences = ((ExtendActivity) mContext).getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("lvl", ((ExtendActivity) mContext).mLvl.getLvl());
        editor.putInt("gold", mGold);
    }

    public void loadObject() {
        ExtendActivity activity = ((ExtendActivity) mContext);
        SharedPreferences sharedPreferences = activity.getPreferences(MODE_PRIVATE);
        activity.mLvl.setLvl(sharedPreferences.getInt("lvl", ((ExtendActivity) mContext).mLvl.getLvl()));
        mGold = sharedPreferences.getInt("gold", mGold);
    }
}
