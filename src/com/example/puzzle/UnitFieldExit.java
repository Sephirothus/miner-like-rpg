package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.GridView;

/**
 * Created by sephirothus on 30.01.17.
 */
public class UnitFieldExit extends Unit {

    private Context mContext;
    private String mTownName;

    UnitFieldExit(Context context, Integer lvl, Integer position) {
        mContext = context;
    }

    public void setTownName(String townName) {
        mTownName = townName;
    }

    @Override
    public void action() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Sign says: to " + mTownName)
                .setMessage("Will you travel there?")
                .setCancelable(false)
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ((AdventureActivity) mContext).exitField(mTownName);
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public Integer getImg() {
        return R.drawable.field_exit;
    }
}
