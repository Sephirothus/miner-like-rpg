package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

/**
 * Created by sephirothus on 18.01.17.
 */
public class EquipmentDialog {

    private Context mContext;

    EquipmentDialog (Context context) {
        mContext = context;
    }

    public void equipmentClick() {
        ImageView view = (ImageView) ((MainActivity) mContext).findViewById(R.id.equipment);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View view = (((MainActivity) mContext).getLayoutInflater()).inflate(R.layout.equipment, null);
                showEquipment(view);
                showStats(view);
                showInventory(view);
                builder.setView(view)
                    .setCancelable(false)
                    .setNegativeButton("Close",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
    }

    private void showEquipment(View view) {
        Player player = ((MainActivity) mContext).mPlayer;
        Config conf = new Config(mContext);
        for (String item : player.getEquipmentItems()) {
            conf.treasureByName(item);
            ImageView imageView = (ImageView) view.findViewById(mContext.getResources()
                    .getIdentifier(conf.getCurTreasureEquipSlot(), "id", mContext.getPackageName()));
            imageView.setImageResource(conf.getCurItemImg());
            setInventoryItemActions(imageView, conf);
        }
    }

    private void showStats(View view) {
        Player player = ((MainActivity) mContext).mPlayer;
        LinearLayout eqStats = (LinearLayout) view.findViewById(R.id.equipment_stats);
        eqStats.removeAllViews();
        addTextViewToStats(eqStats, Config.getFullStatName("lvl"), String.valueOf(((MainActivity) mContext).getLvl()));
        for (Object stat : player.getAllStats()) {
            addTextViewToStats(
                    eqStats,
                    Config.getShortStatName(stat.toString()),
                    player.getCurStat(stat.toString()) + "/" + player.getStat(stat.toString())
            );
        }
        addTextViewToStats(eqStats, Config.getFullStatName("gold"), String.valueOf(player.getGold()));
    }

    private void addTextViewToStats(LinearLayout eqStats, String text, String value) {
        TextView textView = new TextView(mContext);
        textView.setTextSize(18);
        Spannable span = new SpannableString(text + ": ");
        span.setSpan(new ForegroundColorSpan(Color.YELLOW), 0,
                text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(span);
        textView.append(value);
        eqStats.addView(textView);
    }

    private void showInventory(View view) {
        Player player = ((MainActivity) mContext).mPlayer;
        GridLayout eqStats = (GridLayout) view.findViewById(R.id.inventory);
        Config conf = new Config(mContext);
        int size = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics()
        );
        for (String item : player.getInventoryItems()) {
            conf.treasureByName(item);
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(conf.getCurItemImg());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(size, size));
            eqStats.addView(imageView);
            setInventoryItemActions(imageView, conf);
        }
    }

    private void setInventoryItemActions(final ImageView imageView, Config conf) {
        final String desc = conf.getCurItemDescription();
        final String name = conf.getCurItemName();
        final GestureDetector gesture = (new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                int color = Color.TRANSPARENT;
                Drawable background = imageView.getBackground();
                if (background instanceof ColorDrawable)
                    color = ((ColorDrawable) background).getColor();

                imageView.setBackgroundColor(color == Color.WHITE ? Color.TRANSPARENT : Color.WHITE);
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                Toast.makeText(mContext, desc, Toast.LENGTH_SHORT).show();
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                useInventoryItem(name);
                return false;
            }
        }));
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
    }

    private void useInventoryItem(String name) {
        Player player = ((MainActivity) mContext).mPlayer;

    }
}
