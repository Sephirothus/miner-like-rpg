package com.example.puzzle.unit;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.puzzle.Config;
import com.example.puzzle.R;
import com.example.puzzle.activity.ExtendActivity;

/**
 * Created by konst on 22.08.16.
 */
public class UnitTreasure extends Unit {

    private Context mContext;
    private Config mConfig;

    UnitTreasure(Context context, Integer position, String location) {
        mContext = context;
        setLocation(location);
    }

    public void action() {
        showTreasure();
    }

    private void getTreasure() {
        mConfig = new Config(mContext);
        mConfig.randomTreasure(mLocation);
        ((ExtendActivity) mContext).mLogHistoryFragment.addTreasureFoundRec(mConfig.getCurItemName());
        ((ExtendActivity) mContext).mPlayer.addItemToInventory(mConfig.getCurItemName(), mConfig.getCurTreasureGoldAmount());
    }

    public void showTreasure() {
        getTreasure();
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        View view = ((ExtendActivity) mContext).getLayoutInflater().inflate(R.layout.treasure_window, null);
        ImageView image = (ImageView) view.findViewById(R.id.treasure_img);
        image.setImageResource(mConfig.getCurItemImg());
        TextView name = (TextView) view.findViewById(R.id.treasure_name);
        name.setText(mConfig.getCurItemName());
        TextView description = (TextView) view.findViewById(R.id.treasure_description);
        description.setText(mConfig.getCurTreasureDescription());

        builder.setView(view)
                .setCancelable(false)
                .setNegativeButton("OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            mConfig = null;
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
