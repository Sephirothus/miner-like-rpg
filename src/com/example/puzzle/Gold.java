package com.example.puzzle;

import android.content.Context;

/**
 * Created by konst on 22.08.16.
 */
public class Gold extends Unit {

    private int mPosition;
    private Context mContext;

    Gold (Context context, Integer lvl, Integer position) {
        mContext = context;
        mPosition = position;
    }

    public void action() {
        ((MainActivity) mContext).mLogHistoryFragment.addTreasureFoundRec();
    }

    @Override
    public Integer getImg() {
        return R.drawable.treasure;
    }
}
