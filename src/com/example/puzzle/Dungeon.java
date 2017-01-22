package com.example.puzzle;

import android.content.Context;

/**
 * Created by sephirothus on 22.01.17.
 */
public class Dungeon extends Unit {

    final static String[] mTypes = {"setTrapsAndTreasures", "setLabyrinth"};

    private Context mContext;

    Dungeon(Context context, Integer lvl, Integer position) {
        mContext = context;
    }

    @Override
    public void action() {
        ((MainActivity) mContext).startDungeon();
    }

    @Override
    public Integer getImg() {
        return R.drawable.dungeon;
    }
}
