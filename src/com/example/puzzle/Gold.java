package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import org.w3c.dom.Text;

import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class Gold extends Unit {

    private int mPosition;
    private Context mContext;
    private Config mConfig;

    Gold (Context context, Integer lvl, Integer position) {
        mContext = context;
        mPosition = position;
    }

    public void action() {
        showTreasure();
    }

    private void getTreasure() {
        LogHistoryFragment log = ((MainActivity) mContext).mLogHistoryFragment;
        mConfig = new Config(mContext);
        mConfig.randomTreasure();
        log.addTreasureFoundRec(mConfig.getCurItemName());
        if (mConfig.getCurTreasureStat() != null) {
            ((MainActivity) mContext).mPlayer.addStat(mConfig.getCurTreasureStat(), mConfig.getCurTreasureStatPoints());
            log.addStatIncreaseRec(mConfig.getCurTreasureStat(), mConfig.getCurTreasureStatPoints());
        }
    }

    public void showTreasure() {
        getTreasure();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = (((MainActivity) mContext).getLayoutInflater()).inflate(R.layout.treasure_window, null);
        ImageView image = (ImageView) view.findViewById(R.id.treasure_img);
        image.setImageResource(mConfig.getCurItemImg());
        TextView name = (TextView) view.findViewById(R.id.treasure_name);
        name.setText(mConfig.getCurItemName());
        TextView description = (TextView) view.findViewById(R.id.treasure_description);
        description.setText(mConfig.getCurItemDescription());

        builder.setView(view)
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
