package com.example.puzzle.unit;

import android.content.Context;
import android.widget.Toast;
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
        Toast.makeText(mContext, "The church is closed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Integer getImg() {
        return R.drawable.building_church;
    }
}
