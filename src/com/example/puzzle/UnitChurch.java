package com.example.puzzle;

import android.content.Context;

/**
 * Created by sephirothus on 29.01.17.
 */
public class UnitChurch extends Unit {

    private Context mContext;

    UnitChurch(Context context, Integer position, String location) {
        mContext = context;
    }

    @Override
    public void action() {

    }

    @Override
    public Integer getImg() {
        return R.drawable.building_church;
    }
}
