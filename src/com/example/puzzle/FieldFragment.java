package com.example.puzzle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

/**
 * Created by sephirothus on 12.01.17.
 */
public class FieldFragment extends Fragment {

    public Map mMap;
    public Battle mBattle;
    public GridView mGridView;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mGridView = (GridView) activity.findViewById(R.id.gridView);
        mMap = new Map(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.field, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        createPlayField();
    }

    public void createPlayField() {
        mMap.create().setUnits();
    }

    public void createBattleField(Enemy enemy) {
        mMap.create().setDmgPoints(enemy);
        mBattle = new Battle(getActivity(), enemy);
        mBattle.move();
    }
}
