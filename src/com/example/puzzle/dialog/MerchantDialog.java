package com.example.puzzle.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;
import com.example.puzzle.Config;
import com.example.puzzle.Player;
import com.example.puzzle.R;
import com.example.puzzle.activity.ExtendActivity;

import java.util.ArrayList;

/**
 * Created by sephirothus on 22.01.17.
 */
public class MerchantDialog {

    final static int MAX_COUNT_SHOP_ITEMS = 10;
    final static int ITEMS_DISCOUNT_PERCENT_FOR_SELL = 30;

    private Context mContext;
    private View mView;
    private Config mConf;
    private int mInventorySize;
    private ArrayList<String> mShopItems = new ArrayList<>();

    public MerchantDialog(Context context) {
        mContext = context;
        mConf = new Config(mContext);
        mInventorySize = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 50, mContext.getResources().getDisplayMetrics()
        );
    }

    public void merchantClick() {
        ImageView view = (ImageView) ((ExtendActivity) mContext).findViewById(R.id.merchant);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog();
            }
        });
    }

    public void createDialog() {
        mView = (((ExtendActivity) mContext).getLayoutInflater()).inflate(R.layout.merchant, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        setPlayerHeadImg();
        setPlayerGold();
        showShopItems();
        showInventory();
        builder.setView(mView)
                .setCancelable(false)
                .setNegativeButton("Bye",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void disableShop() {
        ImageView view = (ImageView) ((ExtendActivity) mContext).findViewById(R.id.merchant);
        view.setClickable(false);
    }

    public void enableShop() {
        ImageView view = (ImageView) ((ExtendActivity) mContext).findViewById(R.id.merchant);
        view.setClickable(true);
    }

    public MerchantDialog setShopItems() {
        mShopItems.clear();
        for (int i = 0; i < MAX_COUNT_SHOP_ITEMS; i++) {
            mConf.randomTreasure();
            while (mShopItems.contains(mConf.getCurItemName()) || mConf.getCurTreasurePrice() == 0) {
                mConf.randomTreasure();
            }
            mShopItems.add(mConf.getCurItemName());
        }
        return this;
    }

    private void showShopItems() {
        GridLayout grid = (GridLayout) mView.findViewById(R.id.shop_items);
        grid.removeAllViews();
        for (String item : mShopItems) {
            mConf.treasureByName(item);
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mConf.getCurItemImg());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(mInventorySize, mInventorySize));
            grid.addView(imageView);
            setShopItemActions(imageView, false);
        }
    }

    private void setPlayerGold() {
        Player player = ((ExtendActivity) mContext).mPlayer;
        TextView text = (TextView) mView.findViewById(R.id.player_gold);
        text.setText(player.getGold() + " gold");
        text.setTextColor(Color.YELLOW);
    }

    private void setPlayerHeadImg() {
        (((ExtendActivity) mContext).mPlayer).setPlayerHeadImg(mView);
    }

    private void showInventory() {
        Player player = ((ExtendActivity) mContext).mPlayer;
        GridLayout inventory = (GridLayout) mView.findViewById(R.id.inventory_in_shop);
        inventory.removeAllViews();
        for (String item : player.getInventoryItems()) {
            mConf.treasureByName(item);
            ImageView imageView = new ImageView(mContext);
            imageView.setImageResource(mConf.getCurItemImg());
            imageView.setLayoutParams(new LinearLayout.LayoutParams(mInventorySize, mInventorySize));
            inventory.addView(imageView);
            setShopItemActions(imageView, true);
        }
    }

    private void setShopItemActions(final ImageView imageView, final Boolean fromInventory) {
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
                useItem(name, fromInventory);
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

    private void useItem(final String name, Boolean fromInventory) {
        final Player player = ((ExtendActivity) mContext).mPlayer;
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        mConf.treasureByName(name);
        final int curPrice = mConf.getCurTreasurePrice();
        if (fromInventory) {
            final int price = curPrice - Math.round(curPrice * ITEMS_DISCOUNT_PERCENT_FOR_SELL / 100f);
            if (price < 1) {
                Toast.makeText(mContext, "I don't want that", Toast.LENGTH_SHORT).show();
                return;
            }
            builder.setTitle("Sell " + name)
                    .setMessage("I'll take this for " + price + " gold, agreed?")
                    .setCancelable(false)
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            player.removeItemFromInventory(name);
                            mShopItems.add(name);
                            player.increaseGold(price);
                            setPlayerGold();
                            showShopItems();
                            showInventory();
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        } else {
            builder.setTitle("Buy " + name)
                    .setMessage("You can take it for " + curPrice + " gold, it's a fair price")
                    .setCancelable(false)
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    })
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            if (player.getGold() >= curPrice) {
                                player.addItemToInventory(name);
                                mShopItems.remove(name);
                                player.decreaseGold(curPrice);
                                setPlayerGold();
                                showShopItems();
                                showInventory();
                            } else {
                                Toast.makeText(mContext, "You don't have enough gold :(", Toast.LENGTH_SHORT).show();
                            }
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
    }
}
