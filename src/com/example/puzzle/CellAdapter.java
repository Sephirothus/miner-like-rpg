package com.example.puzzle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by konst on 22.08.16.
 */
public class CellAdapter extends BaseAdapter {

    ArrayList<Unit> mUnits = new ArrayList<Unit>();
    Context mContext;

    CellAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mUnits.size();
    }

    @Override
    public Object getItem(int position) {
        return mUnits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(Unit unit) {
        mUnits.add(unit);
    }

    public boolean isEmptyCell(int position) {
        if (mUnits.size() == 0) return false;
        return mUnits.get(position) == null;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell, parent, false);
        }

        return null;
    }
}
