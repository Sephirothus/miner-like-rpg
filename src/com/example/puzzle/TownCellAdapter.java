package com.example.puzzle;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sephirothus on 29.01.17.
 */
public class TownCellAdapter extends CellAdapter {

    TownCellAdapter(Context context) {
        super(context);
    }

    public void changeItem(int pos, Unit newItem) {
        mUnits.remove(pos);
        mUnits.add(pos, newItem);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        Unit unit = getItem(position);
        unit.addUnitToCell(mContext, view, false);
        return view;
    }
}
