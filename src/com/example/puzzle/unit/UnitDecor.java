package com.example.puzzle.unit;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sephirothus on 29.01.17.
 */
public class UnitDecor extends Unit {

    private Context mContext;
    private ArrayList<String> mImgs = new ArrayList<String>() {{
        add("nature_beech");
        add("nature_daisy");
        add("nature_flowers");
        add("nature_grass");
        add("nature_oak");
        add("nature_wheat");
    }};
    private Integer mCurImg;

    UnitDecor(Context context, Integer position, String location) {
        mContext = context;
        mCurImg = mContext.getResources().getIdentifier(
                mImgs.get((new Random()).nextInt(mImgs.size())),
                "drawable", mContext.getPackageName()
        );
    }

    @Override
    public void action() {
        Toast.makeText(mContext, "It's just a decor", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Integer getImg() {
        return mCurImg;
    }
}
