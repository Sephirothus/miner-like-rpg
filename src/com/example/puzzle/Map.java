package com.example.puzzle;

import android.content.Context;
import android.graphics.Color;
import android.widget.GridView;

import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Map {

    final static int TOTAL_CELLS = 32;
    final static int MAX_UNITS = 20;
    final static int MIN_UNITS = 5;
    final static int CELLS_PER_LINE = 8;
    final static int OPENED_CELL_COLOR = Color.BLACK;
    final static int MAX_DMG_POINT = 10;

    private GridView mView;
    private Context mContext;
    private Random mRandom;
    private int mCountUnits;

    Map(Context context, GridView view) {
        mRandom = new Random();
        mView = view;
        mContext = context;
    }

    public Map create() {
        mView.setNumColumns(CELLS_PER_LINE);
        return this;
    }

    public Map setUnits() {
        mCountUnits = mRandom.nextInt(MAX_UNITS - MIN_UNITS) + MIN_UNITS;
        CellAdapter adapter = new CellAdapter(mContext);
        for (int pos = 0; pos < TOTAL_CELLS; pos++) {
            if (mRandom.nextInt(2) == 1 && mCountUnits > 0) {
                mCountUnits--;
                adapter.add(pos, Unit.getRandomUnit(mContext, 1, pos));
            } else {
                adapter.add(pos, new Empty());
            }
        }
        mView.setAdapter(adapter);
        return this;
    }

    public Map setDmgPoints(Enemy enemy, Battle battle) {
        CellBattleAdapter adapter = new CellBattleAdapter(mContext, enemy, battle);
        for (int pos = 0; pos < TOTAL_CELLS; pos++) {
            if (mRandom.nextInt(2) == 1) {
                adapter.add(pos, mRandom.nextInt(MAX_DMG_POINT));
            } else {
                adapter.add(pos, 0);
            }
        }
        mView.setAdapter(adapter);
        return this;
    }
}
