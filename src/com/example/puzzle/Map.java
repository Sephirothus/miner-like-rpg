package com.example.puzzle;

import android.content.Context;
import android.widget.GridView;

import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Map {

    static int TOTAL_CELLS = 16;
    static int MAX_UNITS = 10;

    private GridView mView;
    private Context mContext;
    private int mCountUnits;

    Map(Context context, GridView view) {
        Random r = new Random();
        mCountUnits = r.nextInt(MAX_UNITS);
        mView = view;
        mContext = context;
    }

    public Map generateMap() {
        CellAdapter adapter = new CellAdapter(mContext);
        Random r = new Random();
        while (mCountUnits-- > 0) {
            int curPos = r.nextInt(TOTAL_CELLS);
            if (adapter.isEmptyCell(curPos)) {
                adapter.add(Unit.getRandomUnit());
            } else {
                mCountUnits++;
            }
        }
        mView.setAdapter(adapter);
        return null;
    }
}
