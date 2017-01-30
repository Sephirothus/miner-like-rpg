package com.example.puzzle;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by sephirothus on 25.01.17.
 */
public class AdventureActivity extends ExtendActivity {

    public DungeonFieldFragment mDungeonFieldFragment;
    public Town mTown = new Town();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.town);

        mFieldFragment = new TownFieldFragment();
        Bundle bundle = new Bundle();
        bundle.putString("town_name", Town.HOMETOWN_NAME);
        mFieldFragment.setArguments(bundle);

        mTown.generateTowns(this);
        init();
    }

    public void exitField(String townName) {
        if ((new Random()).nextInt(2) == 0) {
            mFieldFragment = new TownFieldFragment();
            Bundle bundle = new Bundle();
            bundle.putString("town_name", townName);
            mFieldFragment.setArguments(bundle);

            mFragmentManager.beginTransaction()
                    .replace(R.id.field, mFieldFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            TextView textView = (TextView) findViewById(R.id.town_name);
            textView.setText("Forest");
            mFieldFragment.mMainMap.create().setUnits();
        }
    }

    public void startDungeon() {
        mDungeonFieldFragment = new DungeonFieldFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.field, mDungeonFieldFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void endDungeon() {
        mFragmentManager.beginTransaction()
                .replace(R.id.field, mFieldFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        mDungeonFieldFragment = null;
    }
}
