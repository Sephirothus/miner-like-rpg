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
                adapter.add(pos, Unit.getRandomUnit(mContext, ((MainActivity) mContext).getLvl(), pos));
            } else {
                adapter.add(pos, new Empty());
            }
        }
        mView.setAdapter(adapter);
        setMapClick();
        return this;
    }

    public Map setDmgPoints(Enemy enemy) {
        CellBattleAdapter adapter = new CellBattleAdapter(mContext, enemy);
        for (int pos = 0; pos < TOTAL_CELLS; pos++) {
            if (mRandom.nextInt(2) == 1) {
                adapter.add(pos, mRandom.nextInt(MAX_DMG_POINT));
            } else {
                adapter.add(pos, 0);
            }
        }
        mView.setAdapter(adapter);
        setBattleClick();
        return this;
    }

    private void setMapClick() {
        CellAdapter adapter = (CellAdapter) mView.getAdapter();
        mView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.findViewById(R.id.cell_img) == null) {
                    Unit unit = adapter.getItem(position);
                    unit.addUnitToCell(mContext, view);
                    unit.action();
                }
            }
        });
    }

    private void setBattleClick() {
        CellBattleAdapter adapter = (CellBattleAdapter) mView.getAdapter();
        mView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.text);
                if (textView.getText().toString() == "" && view.findViewById(R.id.cell_img) == null) {
                    Integer dmg = adapter.getItem(position);
                    textView.setText(dmg.toString());
                    textView.setTextColor(Color.RED);

                    ViewGroup.LayoutParams params = textView.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    textView.setLayoutParams(params);

                    view.setBackgroundColor(Map.OPENED_CELL_COLOR);
                    if (adapter.isPlayerMove()) {
                        ((BattleActivity) mContext).battlePlayerMove(dmg);
                    }
                }
            }
        });
    }
}
