package com.example.puzzle;

import android.content.Context;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Player implements BattleUnitInterface {

    private Context mContext;

    private int mHp = 1;
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
        ((MainActivity) mContext).mLogHistoryFragment.addRecord("Enemy hit you on " + dmg + " points");
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
}
