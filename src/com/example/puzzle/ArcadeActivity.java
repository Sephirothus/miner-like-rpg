package com.example.puzzle;

import android.os.Bundle;

public class ArcadeActivity extends ExtendActivity {

    private MerchantDialog mMerchantDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFieldFragment = new FieldFragment();
        setContentView(R.layout.arcade);
        init();

        mMerchantDialog = new MerchantDialog(this);
        mMerchantDialog.setShopItems().merchantClick();
    }

    @Override
    public void nextLvl() {
        super.nextLvl();
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
    }
}
