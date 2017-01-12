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
    private int mCurSteps = 3;

    Player (Context context) {
        mContext = context;
    }

    public void getHit(int dmg) {
        mHp -= dmg;
        ((MainActivity) mContext).mStatsPanelFragment.changeStatText("hp", mHp, "HP");
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
