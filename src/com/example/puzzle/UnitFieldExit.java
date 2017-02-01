package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by sephirothus on 30.01.17.
 */
public class UnitFieldExit extends Unit {

    private Context mContext;
    private String mTownName;
    private Integer mCountPathLength;

    UnitFieldExit(Context context, Integer lvl, Integer position) {
        mContext = context;
    }

    public UnitFieldExit setTownName(String townName) {
        mTownName = townName;
        return this;
    }

    public UnitFieldExit setCountPathLength(int pathLen) {
        mCountPathLength = pathLen;
        return this;
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
                        AdventureActivity activity = (AdventureActivity) mContext;
                        activity.mCountPathLength = mCountPathLength;
                        activity.mDestinationTown = mTownName;
                        activity.exitField();
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
