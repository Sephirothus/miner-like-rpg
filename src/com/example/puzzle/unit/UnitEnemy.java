package com.example.puzzle.unit;

import android.content.Context;
import com.example.puzzle.Config;
import com.example.puzzle.activity.ExtendActivity;
import com.example.puzzle.battle.BattleUnitInterface;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class UnitEnemy extends Unit implements BattleUnitInterface, Serializable {

    final static int MIN_HP_LVL_PERCENT = 200;
    final static int MAX_HP_LVL_PERCENT = 300;
    final static int MIN_STR_LVL_PERCENT = 30;
    final static int MAX_STR_LVL_PERCENT = 50;
    //final static int MP_LVL_RANGE = 3;

    private Context mContext;
    private HashMap mStats = new HashMap<String, Integer>() {{
        put("hp", 0);
        put("str", 0);
        //put("mp", 0);
    }};
    private HashMap mCurStats = new HashMap<String, Integer>() {{
        putAll(mStats);
    }};

    private int mPosition;
    private Config mConfig;

    public UnitEnemy(Context context, Integer position, String location) {
        Random r = new Random();
        mContext = context;
        mPosition = position;
        int lvl = ((ExtendActivity) mContext).mLvl.getLvl();
        setStat("hp", r.nextInt(lvl * (MAX_HP_LVL_PERCENT - MIN_HP_LVL_PERCENT) / 100 + 1) + lvl * MIN_HP_LVL_PERCENT / 100);
        setStat("str", r.nextInt(lvl * (MAX_STR_LVL_PERCENT - MIN_STR_LVL_PERCENT) / 100 + 1) + lvl * MIN_STR_LVL_PERCENT / 100);
        mConfig = new Config(mContext);
        mConfig.randomEnemy(location);
    }

    public void setStat(String stat, int value) {
        if (value < 1) value = 1;
        mCurStats.put(stat, value);
        mStats.put(stat, value);
    }

    public int getCurStat(String stat) {
        return (int) mCurStats.get(stat);
    }

    public String getLocation() {
        return mConfig.getCurItemLocation();
    }

    public void addCurStat(String stat, int addValue) {
        mCurStats.put(stat, getCurStat(stat) + addValue);
    }

    public void refreshCurStat(String stat) {
        mCurStats.put(stat, mStats.get(stat));
    }

    public void action() {
        ((ExtendActivity) mContext).mLogHistoryFragment.addEnemyMeetupRec(mConfig.getCurItemName());
        ((ExtendActivity) mContext).startBattle(this);
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
        ((ExtendActivity) mContext).mStatsPanelFragment.changeEnemyHp(getHp());
        ((ExtendActivity) mContext).mLogHistoryFragment.addPlayerHitEnemyRec(dmg);
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
