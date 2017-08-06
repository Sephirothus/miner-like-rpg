package com.example.puzzle.unit;

import android.content.Context;
import android.widget.Toast;

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
    private Integer mCurImg;

    UnitHouse(Context context, Integer position, String location) {
        mContext = context;
        mCurImg = mContext.getResources().getIdentifier(
                mImgs.get((new Random()).nextInt(mImgs.size())),
                "drawable", mContext.getPackageName()
        );
    }

    @Override
    public void action() {
        Toast.makeText(mContext, "This house is closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Integer getImg() {
        return mCurImg;
    }
}
