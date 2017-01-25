package com.example.puzzle;

import android.content.Context;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Enemy extends Unit implements BattleUnitInterface, Serializable {

    final static int MIN_HP_LVL_PERCENT = 120;
    final static int MAX_HP_LVL_PERCENT = 150;
    final static int MIN_STR_LVL_PERCENT = 30;
    final static int MAX_STR_LVL_PERCENT = 50;
    //final static int MP_LVL_RANGE = 3;

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
    private Config mConfig;

    Enemy (Context context, Integer lvl, Integer position) {
        Random r = new Random();
        mContext = context;
        mPosition = position;
        setStat("hp", r.nextInt(lvl * (MAX_HP_LVL_PERCENT - MIN_HP_LVL_PERCENT) / 100 + 1) + lvl * MIN_HP_LVL_PERCENT / 100);
        setStat("str", r.nextInt(lvl * (MAX_STR_LVL_PERCENT - MIN_STR_LVL_PERCENT) / 100 + 1) + lvl * MIN_STR_LVL_PERCENT / 100);
        mConfig = new Config(mContext);
        mConfig.randomEnemy();
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
        ((ArcadeActivity) mContext).mLogHistoryFragment.addEnemyMeetupRec(mConfig.getCurItemName());
        ((ArcadeActivity) mContext).startBattle(this);
    }

    @Override
    public Integer getImg() {
        return mConfig.getCurItemImg();
    }

    public int getPosition() {
        return mPosition;
    }

    public void getHit(int dmg) {
        addCurStat("hp", -dmg);
        ((ArcadeActivity) mContext).mStatsPanelFragment.changeEnemyHp(getHp());
        ((ArcadeActivity) mContext).mLogHistoryFragment.addPlayerHitEnemyRec(dmg);
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
        return mConfig.getCurItemName();
    }
}
