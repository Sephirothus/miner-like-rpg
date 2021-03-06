package com.example.puzzle.activity;

import android.os.Bundle;
import com.example.puzzle.*;
import com.example.puzzle.dialog.MerchantDialog;
import com.example.puzzle.field.FieldFragment;
import com.example.puzzle.unit.UnitEnemy;

import java.util.ArrayList;

public class ArcadeActivity extends ExtendActivity {

    public MerchantDialog mMerchantDialog;
    protected String[] mLvlTargets = {"killEnemies", "raiseStat", "useSteps"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFieldFragment = new FieldFragment();
        setContentView(R.layout.arcade);
        init();

        mLvl = new Level(this, mLvlTargets);
        mMerchantDialog = new MerchantDialog(this);
        mMerchantDialog.setShopItems().merchantClick();
    }

    @Override
    public void exitField() {
        super.exitField();
        mFieldFragment.mMainMap.create().setUnits();
        mMerchantDialog.setShopItems();
    }

    @Override
    public void startBattle(ArrayList<UnitEnemy> enemies) {
        mMerchantDialog.disableShop();
        super.startBattle(enemies);
    }

    @Override
    public void endBattle() {
        super.endBattle();
        mMerchantDialog.enableShop();
        mFieldFragment.mMainMap.differentChecks(this);
    }
}
