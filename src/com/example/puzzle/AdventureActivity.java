package com.example.puzzle;

import android.app.FragmentTransaction;
import android.os.Bundle;

/**
 * Created by sephirothus on 25.01.17.
 */
public class AdventureActivity extends ExtendActivity {

    public DungeonFieldFragment mDungeonFieldFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFieldFragment = new TownFieldFragment();
        setContentView(R.layout.town);
        init();
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
