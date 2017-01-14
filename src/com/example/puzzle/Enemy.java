package com.example.puzzle;

import android.content.Context;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Enemy extends Unit implements BattleUnitInterface, Serializable {

    final static int HP_LVL_RANGE = 3;
    final static int STR_LVL_RANGE = 3;

    private Context mContext;

    private int mPosition;
    private int mHp;
    private int mStr;
    private int mImg;
    private String mName;

    Enemy (Context context, Integer lvl, Integer position) {
        Random r = new Random();
        mContext = context;
        mHp = r.nextInt((lvl + HP_LVL_RANGE) - (lvl - HP_LVL_RANGE)) + (lvl - HP_LVL_RANGE);
        if (mHp < 1) mHp = 1;
        mStr = r.nextInt((lvl + STR_LVL_RANGE) - (lvl - STR_LVL_RANGE)) + (lvl - STR_LVL_RANGE);
        if (mStr < 1) mStr = 1;
        mPosition = position;
        String[] enemies = context.getResources().getStringArray(R.array.enemies);
        String[] curEnemy = (enemies[r.nextInt(enemies.length)]).split(":");
        mImg = context.getResources().getIdentifier(curEnemy[0], "drawable", context.getPackageName());
        mName = curEnemy[1];
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
        mHp -= dmg;
        ((MainActivity) mContext).mStatsPanelFragment.changeEnemyHp(mHp);
        ((MainActivity) mContext).mLogHistoryFragment.addPlayerHitEnemyRec(dmg);
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

    public String getName() {
        return mName;
    }
}
