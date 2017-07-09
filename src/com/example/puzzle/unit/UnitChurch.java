package com.example.puzzle.unit;

import android.content.Context;
import com.example.puzzle.R;

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
