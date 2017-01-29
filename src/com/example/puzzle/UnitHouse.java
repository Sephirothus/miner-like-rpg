package com.example.puzzle;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sephirothus on 29.01.17.
 */
public class UnitHouse extends Unit {

    private Context mContext;
    private ArrayList<String> mImgs = new ArrayList<String>() {{
        add("building_barn");
        add("building_big_house");
        add("building_house");
    }};

    UnitHouse(Context context, Integer lvl, Integer position) {
        mContext = context;
    }

    @Override
    public void action() {

    }

    @Override
    public Integer getImg() {
        return mContext.getResources().getIdentifier(
                mImgs.get((new Random()).nextInt(mImgs.size())),
                "drawable", mContext.getPackageName()
        );
    }
}
