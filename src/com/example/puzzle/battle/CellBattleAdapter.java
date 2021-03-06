package com.example.puzzle.battle;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.example.puzzle.R;
import com.example.puzzle.unit.UnitEnemy;

import java.util.ArrayList;

/**
 * Created by sephiroth on 28.08.16.
 */
public class CellBattleAdapter extends BaseAdapter {

    private Context mContext;
    private boolean mIsPlayerMove = true;
    private boolean mIsEnabled = true;

    private ArrayList<Integer> mNumbers = new ArrayList<>();
    private ArrayList<UnitEnemy> mEnemies = new ArrayList<>();

    public CellBattleAdapter(Context context, ArrayList<UnitEnemy> enemies) {
        mContext = context;
        mEnemies = enemies;
    }

    @Override
    public int getCount() {
        return mNumbers.size();
    }

    @Override
    public Integer getItem(int position) {
        return mNumbers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(int pos, int num) {
        mNumbers.add(pos, num);
    }

    public void set(int pos, int num) {
        mNumbers.set(pos, num);
    }

    public void add(UnitEnemy enemy) {
        mEnemies.add(enemy);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell, parent, false);
        }
        view.setBackgroundResource(R.drawable.battle_tile);

        for (UnitEnemy enemy : mEnemies) {
            if (enemy.getPosition() == position) {
                enemy.addUnitToCell(mContext, view, true);
            }
        }
        return view;
    }

    @Override
    public boolean isEnabled(int position) {
        return mIsEnabled;
    }

    public void disableAdapter() {
        mIsEnabled = false;
    }

    public void enableAdapter() {
        mIsEnabled = true;
    }

    public void setIsPlayerMove(boolean isPlayerMove) {
        mIsPlayerMove = isPlayerMove;
    }

    public boolean isPlayerMove() {
        return mIsPlayerMove;
    }
}
