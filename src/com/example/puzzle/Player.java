package com.example.puzzle;

import android.content.Context;

import java.lang.reflect.Field;
import java.util.Random;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Player implements BattleUnitInterface {

    final static int LVL_STAT_INCREASE = 3;

    private Context mContext;

    private int mHp = 20;
    private int mStr = 1;
    private int mSteps = 3;
    private int mCurSteps;

    Player (Context context) {
        mContext = context;
        mCurSteps = mSteps;
    }

    public void getHit(int dmg) {
        mHp -= dmg;
        ((MainActivity) mContext).mStatsPanelFragment.changeHpStat();
        ((MainActivity) mContext).mLogHistoryFragment.addEnemyHitPlayerRec(dmg);
    }

    public int strike(int dmg) {
        return dmg > 0 ? dmg + mStr : 0;
    }

    public boolean checkHp() {
        return mHp > 0;
    }

    public int getHp() {
        return mHp;
    }

    public int getStr() {
        return mStr;
    }

    public int getSteps() {
        return mCurSteps;
    }

    public void removeStep() {
        mCurSteps--;
    }

    public void refreshSteps() {
        mCurSteps = mSteps;
    }

    public void lvlStatIncrease() {
        // TODO add choice of what stat to increase through dialog
        String[] stats = {"mHp", "mStr", "mSteps"};
        try {
            String incrStat = stats[(new Random()).nextInt(stats.length)];
            Field field = getClass().getDeclaredField(incrStat);
            int statVal = ((int) field.get(this)) + LVL_STAT_INCREASE;
            field.set(this, statVal);
            ((MainActivity) mContext).mLogHistoryFragment.addStatIncreaseRec(incrStat);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
