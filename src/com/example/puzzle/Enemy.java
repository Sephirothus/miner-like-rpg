package com.example.puzzle;

import android.content.Context;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Enemy extends Unit implements BattleUnitInterface {

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
        mHp = r.nextInt((lvl + HP_LVL_RANGE) - (lvl - HP_LVL_RANGE)) + (lvl - HP_LVL_RANGE);
        if (mHp < 1) mHp = 1;
        mStr = r.nextInt((lvl + STR_LVL_RANGE) - (lvl - STR_LVL_RANGE)) + (lvl - STR_LVL_RANGE);
        if (mStr < 1) mStr = 1;
        mPosition = position;
    }

    public void action() {
        ((MainActivity) mContext).mLogHistoryFragment.addRecord("You have met an enemy");
        ((MainActivity) mContext).mFieldFragment.createBattleField(this);
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public Integer getImg() {
        return R.drawable.enemy;
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
        ((MainActivity) mContext).mLogHistoryFragment.addRecord("You hit enemy on " + dmg + " points");
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
