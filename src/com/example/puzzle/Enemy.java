package com.example.puzzle;

import android.content.Context;
import android.graphics.Color;
import java.io.Serializable;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Enemy extends Unit implements BattleUnitInterface, Serializable {

    final static int HP_LVL_RANGE = 3;
    final static int STR_LVL_RANGE = 3;

    private String mTitle = "E";
    private int mPosition;
    private int mHp;
    private int mStr;
    private Context mContext;

    Enemy (Context context, Integer lvl, Integer position) {
        Random r = new Random();
        mContext = context;
        mHp = r.nextInt(lvl - HP_LVL_RANGE) + HP_LVL_RANGE;
        mStr = r.nextInt(lvl - STR_LVL_RANGE) + STR_LVL_RANGE;
        mPosition = position;
    }

    public void action() {
        ((MainActivity) mContext).startBattle(this);
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public int getColor() {
        return Color.RED;
    }

    public int getPosition() {
        return mPosition;
    }

    public void getHit(int dmg) {
        mHp -= dmg;
    }

    public int strike(int dmg) {
        return dmg + mStr;
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
