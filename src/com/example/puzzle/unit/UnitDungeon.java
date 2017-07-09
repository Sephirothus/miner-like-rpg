package com.example.puzzle.unit;

import android.content.Context;
import com.example.puzzle.R;

/**
 * Created by sephirothus on 22.01.17.
 */
public class UnitDungeon extends Unit {

    final static String[] mTypes = {"setTrapsAndTreasures", "setLabyrinth"};

    private Context mContext;

    UnitDungeon(Context context, Integer position, String location) {
        mContext = context;
    }

    @Override
    public void action() {
        //((AdventureActivity) mContext).startDungeon();
    }

    @Override
    public Integer getImg() {
        return R.drawable.dungeon;
    }
}
