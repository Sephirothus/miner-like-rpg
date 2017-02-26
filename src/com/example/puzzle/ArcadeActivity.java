package com.example.puzzle;

import android.os.Bundle;

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
    public void startBattle(UnitEnemy enemy) {
        mMerchantDialog.disableShop();
        super.startBattle(enemy);
    }

    @Override
    public void endBattle() {
        super.endBattle();
        mMerchantDialog.enableShop();
        mFieldFragment.mMainMap.differentChecks(this);
    }
}
