package com.example.puzzle;

import android.content.Context;
import android.graphics.Color;

/**
 * Created by konst on 22.08.16.
 */
public class Gold extends Unit {

    private String mTitle = "G";
    private int mPosition;
    private Context mContext;

    Gold (Context context, Integer lvl, Integer position) {
        mContext = context;
        mPosition = position;
    }

    public void action() {

    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public Integer getImg() {
        return R.drawable.treasure;
    }

    @Override
    public int getColor() {
        return Color.YELLOW;
    }
}
