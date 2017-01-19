package com.example.puzzle;

import android.content.Context;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Player implements BattleUnitInterface {

    final static int LVL_STAT_INCREASE = 1;

    private Context mContext;
    private int mGold = 0;
    private ArrayList<String> mInventory = new ArrayList();
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
        addCurStat(stat, addValue);
        mStats.put(stat, (int) mStats.get(stat) + addValue);
        // change stats panel
        String statName = Character.toUpperCase(stat.charAt(0)) + stat.substring(1);
        StatsPanelFragment statsPanel = ((MainActivity) mContext).mStatsPanelFragment;
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

    public ArrayList<String> getInventoryItems() {
        return mInventory;
    }

    public void getHit(int dmg) {
        addCurStat("hp", -dmg);
        ((MainActivity) mContext).mStatsPanelFragment.changeHpStat();
        ((MainActivity) mContext).mLogHistoryFragment.addEnemyHitPlayerRec(dmg);
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

    public void removeStep() {
        addCurStat("steps", -1);
        ((MainActivity) mContext).mStatsPanelFragment.changeStepsStat();
    }

    public void refreshSteps() {
        refreshCurStat("steps");
        ((MainActivity) mContext).mStatsPanelFragment.changeStepsStat();
    }

    public void lvlStatIncrease() {
        Object[] stats = mStats.keySet().toArray();
        String incrStat = stats[(new Random()).nextInt(stats.length)].toString();
        addStat(incrStat, LVL_STAT_INCREASE);
        ((MainActivity) mContext).mLogHistoryFragment.addStatIncreaseRec(incrStat, LVL_STAT_INCREASE);
    }

    public Object[] getAllStats() {
        return mStats.keySet().toArray();
    }
}
