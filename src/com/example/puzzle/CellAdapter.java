package com.example.puzzle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
/**
 * Created by konst on 22.08.16.
 */
public class CellAdapter extends BaseAdapter {

    private ArrayList<Unit> mUnits = new ArrayList<>();
    private Context mContext;
    private boolean mIsEnabled = true;

    CellAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return mUnits.size();
    }

    @Override
    public Unit getItem(int position) {
        return mUnits.get(position);
    }

    @Override
    public boolean isEnabled(int position) {
        return mIsEnabled;
    }

    public void disableAdapter() {
        mIsEnabled = false;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void add(int pos, Unit unit) {
        mUnits.add(pos, unit);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.cell, parent, false);
        }
        return view;
    }
}
