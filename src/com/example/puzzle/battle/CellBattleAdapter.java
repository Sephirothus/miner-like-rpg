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
    private UnitEnemy mEnemy;
    private boolean mIsPlayerMove = true;
    private boolean mIsEnabled = true;

    private ArrayList<Integer> mDmgs = new ArrayList<>();

    public CellBattleAdapter(Context context, UnitEnemy enemy) {
        mContext = context;
        mEnemy = enemy;
    }

    @Override
    public int getCount() {
        return mDmgs.size();
    }

    @Override
    public Integer getItem(int position) {
        return mDmgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(int pos, int dmg) {
        mDmgs.add(pos, dmg);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell, parent, false);
        }
        view.setBackgroundColor(Color.RED);

        if (mEnemy.getPosition() == position) {
            mEnemy.addUnitToCell(mContext, view, true);
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
