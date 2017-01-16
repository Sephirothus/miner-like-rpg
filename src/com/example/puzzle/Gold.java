package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Gold extends Unit {

    private int mPosition;
    private Context mContext;
    private int mImg;
    private String mName;

    Gold (Context context, Integer lvl, Integer position) {
        mContext = context;
        mPosition = position;
    }

    public void action() {
        showTreasure();
    }

    public String getTreasure() {
        LogHistoryFragment log = ((MainActivity) mContext).mLogHistoryFragment;
        String[] treasures = mContext.getResources().getStringArray(R.array.treasures);
        String[] curTreasure = (treasures[(new Random()).nextInt(treasures.length)]).split(":");
        String text = "It's a useless junk";
        mImg = mContext.getResources().getIdentifier(curTreasure[0], "drawable", mContext.getPackageName());
        mName = curTreasure[1];
        log.addTreasureFoundRec(mName);
        if (curTreasure.length > 2) {
            int value = Integer.parseInt(curTreasure[3]);
            ((MainActivity) mContext).mPlayer.addStat(curTreasure[2], value);
            text = log.addStatIncreaseRec(curTreasure[2], value);
        }
        return text;
    }

    public void showTreasure() {
        String incrStatText = getTreasure();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(mName)
                .setMessage(incrStatText)
                .setIcon(mImg)
                .setCancelable(false)
                .setNegativeButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public Integer getImg() {
        return R.drawable.treasure;
    }
}
