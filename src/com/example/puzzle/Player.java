package com.example.puzzle;

import android.content.Context;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;

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
        loadObject();
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
        //if (getCurStat(stat) < 1) mCurStats.put(stat, 1);
        changeStatsPanel(stat);
    }

    public void changeStatsPanel(String stat) {
        // change stats panel
        String statName = Character.toUpperCase(stat.charAt(0)) + stat.substring(1);
        StatsPanelFragment statsPanel = ((ArcadeActivity) mContext).mStatsPanelFragment;
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
            if (mEquipment.get(item) == slot) return item.toString();
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
        ((ArcadeActivity) mContext).mStatsPanelFragment.changeHpStat();
        ((ArcadeActivity) mContext).mLogHistoryFragment.addEnemyHitPlayerRec(dmg);
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

    public void increaseGold(int value) {
        mGold += value;
    }

    public void decreaseGold(int value) {
        mGold -= value;
    }

    public void removeStep() {
        addCurStat("steps", -1);
        ((ArcadeActivity) mContext).mStatsPanelFragment.changeStepsStat();
    }

    public void refreshSteps() {
        refreshCurStat("steps");
        ((ArcadeActivity) mContext).mStatsPanelFragment.changeStepsStat();
    }

    public void lvlStatIncrease() {
        Object[] stats = mStats.keySet().toArray();
        String incrStat = stats[(new Random()).nextInt(stats.length)].toString();
        increaseStat(incrStat, LVL_STAT_INCREASE);
        ((ArcadeActivity) mContext).mLogHistoryFragment.addStatIncreaseRec(incrStat, LVL_STAT_INCREASE);
    }

    public Object[] getAllStats() {
        return mStats.keySet().toArray();
    }

    public void saveObject() {
        SharedPreferences sharedPreferences = ((ArcadeActivity) mContext).getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("lvl", ((ArcadeActivity) mContext).getLvl());
        editor.putInt("gold", mGold);
    }

    public void loadObject() {
        ArcadeActivity activity = ((ArcadeActivity) mContext);
        SharedPreferences sharedPreferences = activity.getPreferences(MODE_PRIVATE);
        activity.setLvl(sharedPreferences.getInt("lvl", ((ArcadeActivity) mContext).getLvl()));
        mGold = sharedPreferences.getInt("gold", mGold);
    }
}
