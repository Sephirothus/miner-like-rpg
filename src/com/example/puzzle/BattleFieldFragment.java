package com.example.puzzle;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by sephirothus on 13.01.17.
 */
public class BattleFieldFragment extends Fragment {

    public MainMap mMainMap;
    public Battle mBattle;

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
        mMainMap = new MainMap(getActivity());
        createBattleField();
    }

    public void createBattleField() {
        UnitEnemy enemy = (UnitEnemy) getArguments().getSerializable("enemy");
        mMainMap.create().setDmgPoints(enemy);
        mBattle = new Battle(getActivity(), enemy);
        mBattle.move();
    }
}
