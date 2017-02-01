package com.example.puzzle;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by sephirothus on 26.01.17.
 */
public class ExtendActivity extends Activity {

    public Player mPlayer;
    public Level mLvl;

    protected FragmentManager mFragmentManager;
    public StatsPanelFragment mStatsPanelFragment = new StatsPanelFragment();
    public LogHistoryFragment mLogHistoryFragment = new LogHistoryFragment();
    public FieldFragment mFieldFragment;
    public BattleFieldFragment mBattleFieldFragment;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
    }

    public void init() {
        mPlayer = new Player(this);
        (new EquipmentDialog(this)).equipmentClick();
        mFragmentManager = getFragmentManager();

        mFragmentManager.beginTransaction()
                .add(R.id.field, mFieldFragment)
                .add(R.id.stats_panel, mStatsPanelFragment)
                .add(R.id.log_history, mLogHistoryFragment)
                .commit();
    }

    public void nextLvl() {
        mLvl.nextLvl();
    }

    public void startBattle(UnitEnemy enemy) {
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
    }
}
