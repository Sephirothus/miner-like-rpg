package com.example.puzzle.unit;

import android.content.Context;
import com.example.puzzle.R;
import com.example.puzzle.dialog.MerchantDialog;

/**
 * Created by sephirothus on 29.01.17.
 */
public class UnitMerchant extends Unit {

    private Context mContext;
    private MerchantDialog mMerchant;

    UnitMerchant(Context context, Integer position, String location) {
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
