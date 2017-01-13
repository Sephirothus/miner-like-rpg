package com.example.puzzle;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class MainActivity extends Activity {

    public Player mPlayer;

    private FragmentManager mFragmentManager;
    public StatsPanelFragment mStatsPanelFragment = new StatsPanelFragment();
    public FieldFragment mFieldFragment = new FieldFragment();
    public LogHistoryFragment mLogHistoryFragment = new LogHistoryFragment();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mPlayer = new Player(this);
        mFragmentManager = getFragmentManager();

        mFragmentManager.beginTransaction()
            .add(R.id.field, mFieldFragment)
            .add(R.id.stats_panel, mStatsPanelFragment)
            .add(R.id.log_history, mLogHistoryFragment)
            .commit();
    }
}
