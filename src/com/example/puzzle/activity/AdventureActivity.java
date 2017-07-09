package com.example.puzzle.activity;

import android.app.FragmentTransaction;
import android.os.Bundle;
import com.example.puzzle.*;
import com.example.puzzle.dialog.QuestDialog;
import com.example.puzzle.field.Location;
import com.example.puzzle.town.Town;
import com.example.puzzle.town.TownFieldFragment;

import java.util.ArrayList;

/**
 * Created by sephirothus on 25.01.17.
 */
public class AdventureActivity extends ExtendActivity {

    public DungeonFieldFragment mDungeonFieldFragment;
    public Town mTown = new Town();
    public ArrayList<String> mPathLocations;
    public String mDestinationTown = Town.HOMETOWN_NAME;
    protected String[] mLvlTargets = {"killEnemies", "raiseStat", /*"completeQuests", */"useSteps"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adventure);

        mFieldFragment = new TownFieldFragment();
        Bundle bundle = new Bundle();
        bundle.putString("town_name", Town.HOMETOWN_NAME);
        mFieldFragment.setArguments(bundle);

        init();
        mLvl = new Level(this, mLvlTargets);
        mTown.generateTowns(this);
        (new QuestDialog(this)).questsClick();
    }

    @Override
    public void exitField() {
        super.exitField();
        mFieldFragment.mMainMap.disableNextLocButton();
        if (mPathLocations.isEmpty()) {
            moveToTown();
        } else {
            String location = mPathLocations.get(0);
            mFieldFragment.mMainMap.setLocationNameAndImage(location).create().setOutsideUnits();
            mPathLocations.remove(0);
        }
    }

    public void moveToTown() {
        findViewById(R.id.main_layout).setBackgroundResource(Location.getImgByLocation(this, "town"));
        mFieldFragment = new TownFieldFragment();
        Bundle bundle = new Bundle();
        bundle.putString("town_name", mDestinationTown);
        mFieldFragment.setArguments(bundle);

        mFragmentManager.beginTransaction()
                .replace(R.id.field, mFieldFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void gameOver() {
        mDestinationTown = Town.HOMETOWN_NAME;
        mPlayer.refreshCurStat("hp");
        mLogHistoryFragment.addDieAndReturnToTownRec();
        mStatsPanelFragment.changeHpStat();
        moveToTown();
    }

    @Override
    public void endBattle() {
        super.endBattle();
        mFieldFragment.mMainMap.differentChecks(this);
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
