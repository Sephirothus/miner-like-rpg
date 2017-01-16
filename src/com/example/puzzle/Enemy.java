package com.example.puzzle;

import android.content.Context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Enemy extends Unit implements BattleUnitInterface, Serializable {

    final static int HP_LVL_RANGE = 3;
    final static int STR_LVL_RANGE = 3;
    final static int MP_LVL_RANGE = 3;

    private Context mContext;
    private HashMap mStats = new HashMap<String, Integer>() {{
        put("hp", 0);
        put("str", 0);
        put("mp", 0);
    }};
    private HashMap mCurStats = new HashMap<String, Integer>() {{
        putAll(mStats);
    }};

    private int mPosition;
    private int mImg;
    private String mName;

    Enemy (Context context, Integer lvl, Integer position) {
        Random r = new Random();
        mContext = context;
        mPosition = position;
        setStat("hp", r.nextInt((lvl + HP_LVL_RANGE) - (lvl - HP_LVL_RANGE)) + (lvl - HP_LVL_RANGE));
        setStat("str", r.nextInt((lvl + STR_LVL_RANGE) - (lvl - STR_LVL_RANGE)) + (lvl - STR_LVL_RANGE));

        String[] enemies = context.getResources().getStringArray(R.array.enemies);
        String[] curEnemy = (enemies[r.nextInt(enemies.length)]).split(":");
        mImg = context.getResources().getIdentifier(curEnemy[0], "drawable", context.getPackageName());
        mName = curEnemy[1];
    }

    public void setStat(String stat, int value) {
        if (value < 1) value = 1;
        mCurStats.put(stat, value);
        mStats.put(stat, value);
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

    public void action() {
        ((MainActivity) mContext).mLogHistoryFragment.addEnemyMeetupRec(mName);
        ((MainActivity) mContext).startBattle(this);
    }

    @Override
    public Integer getImg() {
        return mImg;
    }

    public int getPosition() {
        return mPosition;
    }

    public void getHit(int dmg) {
        addCurStat("hp", -dmg);
        ((MainActivity) mContext).mStatsPanelFragment.changeEnemyHp(getHp());
        ((MainActivity) mContext).mLogHistoryFragment.addPlayerHitEnemyRec(dmg);
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

    public String getName() {
        return mName;
    }
}
