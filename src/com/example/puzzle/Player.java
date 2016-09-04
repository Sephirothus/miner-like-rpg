package com.example.puzzle;

import java.io.Serializable;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Player implements BattleUnitInterface, Serializable {

    private int mHp = 1;
    private int mStr = 1;

    public void getHit(int dmg) {
        mHp -= dmg;
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
}
