package com.example.puzzle;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.HashMap;

/**
 * Created by sephiroth on 28.08.16.
 */
public class CellBattleAdapter extends BaseAdapter {

    private Context mContext;
    private Enemy mEnemy;
    private boolean mIsPlayerMove = true;

    HashMap mDmgs = new HashMap<Integer, Integer>();

    CellBattleAdapter(Context context, Enemy enemy) {
        mContext = context;
        mEnemy = enemy;
    }

    @Override
    public int getCount() {
        return mDmgs.size();
    }

    @Override
    public Integer getItem(int position) {
        return (int) mDmgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(int pos, int dmg) {
        mDmgs.put(pos, dmg);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell, parent, false);
        }
        TextView textView = (TextView) view.findViewById(R.id.title);
        view.setBackgroundColor(Color.RED);

        if (mEnemy.getPosition() == position) {
            textView.setText(mEnemy.getTitle());
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textView.getText().toString() == "") {
                    Integer dmg = getItem(position);
                    if (mIsPlayerMove) {
                        ((BattleActivity) mContext).battlePlayerMove(dmg);
                    }
                    textView.setText(dmg.toString());
                    textView.setTextColor(Color.RED);
                    v.setBackgroundColor(Map.OPENED_CELL_COLOR);
                }
            }
        });
        return view;
    }

    public void setIsPlayerMove(boolean isPlayerMove) {
        mIsPlayerMove = isPlayerMove;
    }
}
