package com.example.puzzle;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

public class ArcadeActivity extends Activity {

    public Player mPlayer;
    private int mLvl = 1;

    private FragmentManager mFragmentManager;
    public StatsPanelFragment mStatsPanelFragment = new StatsPanelFragment();
    public FieldFragment mFieldFragment = new FieldFragment();
    public LogHistoryFragment mLogHistoryFragment = new LogHistoryFragment();
    public BattleFieldFragment mBattleFieldFragment;
    public DungeonFieldFragment mDungeonFieldFragment;
    private MerchantDialog mMerchantDialog;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.arcade);

        mPlayer = new Player(this);
        (new EquipmentDialog(this)).equipmentClick();
        mMerchantDialog = new MerchantDialog(this);
        mMerchantDialog.setShopItems().merchantClick();
        mFragmentManager = getFragmentManager();

        mFragmentManager.beginTransaction()
            .add(R.id.field, mFieldFragment)
            .add(R.id.stats_panel, mStatsPanelFragment)
            .add(R.id.log_history, mLogHistoryFragment)
            .commit();
    }

    public int getLvl() {
        return mLvl;
    }

    public void setLvl(int lvl) {
        mLvl = lvl;
    }

    public void checkIsLvlEnd() {
        if (mPlayer.getSteps() == 0) {
            GridView gridView = (GridView) findViewById(R.id.gridView);
            ((CellAdapter) gridView.getAdapter()).disableAdapter();
            gridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    nextLvl();
                }
            }, 1000);
        }
    }

    public void nextLvl() {
        mLvl++;
        mStatsPanelFragment.changeLvlStat();
        mLogHistoryFragment.addNewLvlRec(mLvl);
        mPlayer.lvlStatIncrease();
        mPlayer.refreshSteps();
        mFieldFragment.mMainMap.create().setUnits();
        mMerchantDialog.setShopItems();
    }

    public void startBattle(Enemy enemy) {
        mMerchantDialog.disableShop();
        mBattleFieldFragment = new BattleFieldFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("enemy", enemy);
        mBattleFieldFragment.setArguments(bundle);
        mStatsPanelFragment.addEnemyStats(enemy.getName(), enemy.getHp(), enemy.getStr());

        mFragmentManager.beginTransaction()
                .replace(R.id.field, mBattleFieldFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void endBattle() {
        mFragmentManager.beginTransaction()
                .replace(R.id.field, mFieldFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        mStatsPanelFragment.removeEnemyStats();
        mBattleFieldFragment = null;
        mMerchantDialog.enableShop();
    }

    public void startDungeon() {
        mMerchantDialog.disableShop();
        mDungeonFieldFragment = new DungeonFieldFragment();
        mFragmentManager.beginTransaction()
                .replace(R.id.field, mDungeonFieldFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    public void endDungeon() {
        mFragmentManager.beginTransaction()
                .replace(R.id.field, mFieldFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
        mDungeonFieldFragment = null;
        mMerchantDialog.enableShop();
    }
}
