package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
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

    final static int MAX_IDENTICAL_SLOTS = 5;

    private Context mContext;
    private int mInventorySize;
    private Config mConf;
    private View mView;

    EquipmentDialog (Context context) {
        mContext = context;
        mConf = new Config(mContext);
        mInventorySize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics()
        );
    }

    public void equipmentClick() {
        ImageView view = (ImageView) ((ExtendActivity) mContext).findViewById(R.id.equipment);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mView = (((ExtendActivity) mContext).getLayoutInflater()).inflate(R.layout.equipment, null);
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                showEquipment();
                showStats();
                showInventory();
                builder.setView(mView)
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

    private void showEquipment() {
        Player player = ((ExtendActivity) mContext).mPlayer;
        for (Object item : player.getEquipmentItems()) {
            addItemToEquipmentOrUse(item.toString(), player.getEquipmentSlotByItem(item.toString()));
        }
    }

    private String addItemToEquipmentOrUse(String item, String slot) {
        mConf.treasureByName(item);
        if (slot == null) slot = mConf.getCurTreasureEquipSlot(mView);
        if (slot != null) {
            ImageView imageView = (ImageView) mView.findViewById(mConf.getIdByStr(slot));
            imageView.setImageResource(mConf.getCurItemImg());
            setInventoryItemActions(imageView, false);
        }
        return slot;
    }

    private void showStats() {
        Player player = ((ExtendActivity) mContext).mPlayer;
        LinearLayout eqStats = (LinearLayout) mView.findViewById(R.id.equipment_stats);
        eqStats.removeAllViews();
        addTextViewToStats(eqStats,
                Config.getFullStatName("lvl"),
                String.valueOf(((ExtendActivity) mContext).mLvl.getLvl())
        );
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

    private void showInventory() {
        Player player = ((ExtendActivity) mContext).mPlayer;
        GridLayout inventory = (GridLayout) mView.findViewById(R.id.inventory);
        inventory.removeAllViews();
        for (String item : player.getInventoryItems()) {
            mConf.treasureByName(item);
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mConf.getCurItemImg());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(mInventorySize, mInventorySize));
            inventory.addView(imageView);
            setInventoryItemActions(imageView, true);
        }
    }

    private void setInventoryItemActions(final ImageView imageView, final Boolean fromInventory) {
        final String desc = mConf.getCurTreasureDescription();
        final String name = mConf.getCurItemName();
        final GestureDetector gesture = (new GestureDetector(mContext, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                Toast.makeText(mContext, name + "\n" + desc, Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                useItem(imageView, name, fromInventory);
                return true;
            }
        }));
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gesture.onTouchEvent(event);
            }
        });
    }

    private void useItem(ImageView selectedImage, String name, Boolean fromInventory) {
        Player player = ((ExtendActivity) mContext).mPlayer;
        if (fromInventory) {
            if (player.isItemEquiped(name)) {
                Toast.makeText(mContext, "You are already wearing such item", Toast.LENGTH_SHORT).show();
                return;
            }

            String slot = addItemToEquipmentOrUse(name, null);
            if (slot != null || mConf.getCurTreasureStat() != null) {
                player.removeItemFromInventory(name);
            }
            if (slot != null) {
                String item = player.getItemByEquipmentSlot(slot);
                if (item != null) {
                    player.addItemToInventory(item);
                    player.removeItemFromEquipment(item);
                    // set temp treasure
                    mConf.treasureByName(item);
                    if (mConf.getCurTreasureStat() != null) {
                        player.decreaseStat(mConf.getCurTreasureStat(), mConf.getCurTreasureStatPoints());
                    }
                    // return current treasure
                    mConf.treasureByName(name);
                }
                player.addItemToEquipment(name, slot);
            }
            if (mConf.getCurTreasureStat() != null) {
                player.increaseStat(mConf.getCurTreasureStat(), mConf.getCurTreasureStatPoints());
                if (slot == null) {
                    (((ExtendActivity) mContext).mLogHistoryFragment)
                            .addStatIncreaseRec(mConf.getCurTreasureStat(), mConf.getCurTreasureStatPoints());
                }
            }
            showStats();
        } else {
            player.addItemToInventory(name);
            player.removeItemFromEquipment(name);
            mConf.treasureByName(name);
            if (mConf.getCurTreasureStat() != null) {
                player.decreaseStat(mConf.getCurTreasureStat(), mConf.getCurTreasureStatPoints());
                showStats();
            }
            selectedImage.setImageResource(0);
        }
        showInventory();
    }
}
