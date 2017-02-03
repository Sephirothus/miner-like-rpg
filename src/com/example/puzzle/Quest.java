package com.example.puzzle;

import android.content.Context;

import java.util.Random;

/**
 * Created by konst on 03.02.17.
 */
public class Quest {

    public static int MAX_ENEMIES_COUNT = 10;
    public static int MIN_ENEMIES_COUNT = 1;
    public static int MAX_ITEMS_COUNT = 10;
    public static int MIN_ITEMS_COUNT = 1;

    private Context mContext;
    private Config mConf;
    private String[] mTypes = {"killEnemies", "foundItems", "killBoss"};

    Quest(Context context) {
        mContext = context;
        mConf = new Config(mContext);
    }

    public void mainLine() {

    }

    private void killEnemies() {
        mConf.randomEnemy();
        String name = mConf.getCurItemName();
        int count = (new Random()).nextInt(MAX_ENEMIES_COUNT) + MIN_ENEMIES_COUNT;
    }

    private void foundItems() {
        mConf.randomTreasure();
        String name = mConf.getCurItemName();
        int count = (new Random()).nextInt(MAX_ITEMS_COUNT) + MIN_ITEMS_COUNT;
    }
}
