package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.ArrayList;

/**
 * Created by sephirothus on 30.01.17.
 */
public class UnitFieldExit extends Unit {

    private Context mContext;
    private ArrayList<String> mPathLocations;

    UnitFieldExit(Context context, Integer position, String location) {
        mContext = context;
    }

    public UnitFieldExit setPathLocations(ArrayList<String> path) {
        mPathLocations = path;
        return this;
    }

    @Override
    public void action() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("Sign says: to " + mLocation)
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
                        activity.mPathLocations = new ArrayList<>(mPathLocations);
                        activity.mDestinationTown = mLocation;
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
