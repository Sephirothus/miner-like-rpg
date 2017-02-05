package com.example.puzzle;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by sephirothus on 25.01.17.
 */
public class AdventureActivity extends ExtendActivity {

    public DungeonFieldFragment mDungeonFieldFragment;
    public Town mTown = new Town();
    public Integer mCountPathLength;
    public String mDestinationTown = Town.HOMETOWN_NAME;
    protected String[] mLvlTargets = {"killEnemies", "raiseStat", /*"completeQuests", */"useSteps"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.town);

        mFieldFragment = new TownFieldFragment();
        Bundle bundle = new Bundle();
        bundle.putString("town_name", Town.HOMETOWN_NAME);
        mFieldFragment.setArguments(bundle);

        init();
        mLvl = new Level(this, mLvlTargets);
        mTown.generateTowns(this);
    }

    public void exitField() {
        mPlayer.refreshSteps();
        if (mCountPathLength == 0) {
            mFieldFragment = new TownFieldFragment();
            Bundle bundle = new Bundle();
            bundle.putString("town_name", mDestinationTown);
            mFieldFragment.setArguments(bundle);

            mFragmentManager.beginTransaction()
                    .replace(R.id.field, mFieldFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        } else {
            TextView textView = (TextView) findViewById(R.id.town_name);
            textView.setText("Forest");
            mCountPathLength--;
            mFieldFragment.mMainMap.create().setForestUnits();
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
