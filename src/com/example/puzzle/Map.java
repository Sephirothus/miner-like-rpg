package com.example.puzzle;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Map {

    final static int TOTAL_CELLS = 30;
    final static int MAX_UNITS = 20;
    final static int MIN_UNITS = 5;
    final static int CELLS_PER_LINE = 6;
    final static int OPENED_CELL_COLOR = Color.BLACK;
    final static int MAX_DMG_POINT = 9;

    private Player mPlayer;
    private GridView mGridView;
    private Context mContext;
    private Random mRandom;
    private int mCountUnits;

    Map (Context context) {
        mRandom = new Random();
        mContext = context;
        mPlayer = ((MainActivity) mContext).mPlayer;
        mGridView = (GridView) ((MainActivity) mContext).findViewById(R.id.gridView);
    }

    public Map create() {
        mGridView.setNumColumns(CELLS_PER_LINE);
        return this;
    }

    public Map setUnits() {
        mCountUnits = mRandom.nextInt(MAX_UNITS - MIN_UNITS) + MIN_UNITS;
        CellAdapter adapter = new CellAdapter(mContext);
        for (int pos = 0; pos < TOTAL_CELLS; pos++) {
            if (mRandom.nextInt(2) == 1 && mCountUnits > 0) {
                mCountUnits--;
                adapter.add(pos, Unit.getRandomUnit(mContext, ((MainActivity) mContext).getLvl(), pos));
            } else {
                adapter.add(pos, new Empty());
            }
        }
        mGridView.setAdapter(adapter);
        setMapClick();
        return this;
    }

    public Map setDmgPoints(Enemy enemy) {
        CellBattleAdapter adapter = new CellBattleAdapter(mContext, enemy);
        for (int pos = 0; pos < TOTAL_CELLS; pos++) {
            if (mRandom.nextInt(2) == 1) {
                adapter.add(pos, mRandom.nextInt(MAX_DMG_POINT) + 1);
            } else {
                adapter.add(pos, 0);
            }
        }
        mGridView.setAdapter(adapter);
        setBattleClick();
        return this;
    }

    public void setMapClick() {
        final CellAdapter adapter = (CellAdapter) mGridView.getAdapter();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.findViewById(R.id.cell_img) == null) {
                    mPlayer.removeStep();
                    Unit unit = adapter.getItem(position);
                    unit.addUnitToCell(mContext, view);
                    unit.action();
                    ((MainActivity) mContext).checkIsLvlEnd();
                }
            }
        });
    }

    public void setBattleClick() {
        final CellBattleAdapter adapter = (CellBattleAdapter) mGridView.getAdapter();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.text);
                if (Battle.isCellEmpty(view)) {
                    Integer dmg = adapter.getItem(position);
                    textView.setText(dmg.toString());
                    textView.setTextColor(Color.RED);

                    ViewGroup.LayoutParams params = textView.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    textView.setLayoutParams(params);

                    view.setBackgroundColor(Map.OPENED_CELL_COLOR);
                    if (adapter.isPlayerMove()) {
                        ((MainActivity) mContext).mBattleFieldFragment.mBattle.playerMove(dmg);
                    }
                }
            }
        });
    }
}
