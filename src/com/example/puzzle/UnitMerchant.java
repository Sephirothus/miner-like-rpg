package com.example.puzzle;

import android.content.Context;

/**
 * Created by sephirothus on 29.01.17.
 */
public class UnitMerchant extends Unit {

    private Context mContext;
    private MerchantDialog mMerchant;

    UnitMerchant(Context context, Integer lvl, Integer position) {
        mContext = context;
        mMerchant = new MerchantDialog(mContext);
        mMerchant.setShopItems();
    }

    @Override
    public void action() {
        mMerchant.createDialog();
    }

    @Override
    public Integer getImg() {
        return R.drawable.merchant_tent;
    }
}
