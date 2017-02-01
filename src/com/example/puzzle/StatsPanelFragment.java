package com.example.puzzle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by sephirothus on 12.01.17.
 */
public class StatsPanelFragment extends Fragment {

    private Player mPlayer;
    private TableLayout mStatsTable;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stats_panel, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPlayer = ((ExtendActivity) getActivity()).mPlayer;
        mStatsTable = (TableLayout) getActivity().findViewById(R.id.enemy_stats);
        getActivity().findViewById(R.id.lvl).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(
                        getActivity(),
                        "To get to the next level, you have to\n"
                                + ((ExtendActivity) getActivity()).mLvl.getTargetText(),
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

        changeLvlStat();
        changeHpStat();
        changeStrStat();
        changeStepsStat();
    }

    public void addEnemyStats(String enemyName, int enemyHp, int enemyStr) {
        changeStatText("enemy_stats_hp", enemyHp, Config.getShortStatName("hp"));
        changeStatText("enemy_stats_str", enemyStr, Config.getShortStatName("str"));
        ((TextView) getActivity().findViewById(R.id.enemy_name)).setText(enemyName);
        mStatsTable.setVisibility(View.VISIBLE);
    }

    public void changeEnemyHp(int hp) {
        changeStatText("enemy_stats_hp", hp, Config.getShortStatName("hp"));
    }

    public void removeEnemyStats() {
        mStatsTable.setVisibility(View.GONE);
    }

    public void changeStatText(String statName, int value, String statText) {
        int resId = getResources().getIdentifier(statName, "id", getActivity().getPackageName());
        TextView text = (TextView) getActivity().findViewById(resId);
        text.setText(statText + ": " + String.valueOf(value));
    }

    public StatsPanelFragment changeLvlStat() {
        changeStatText("lvl", ((ExtendActivity) getActivity()).mLvl.getLvl(), Config.getShortStatName("lvl"));
        return this;
    }

    public StatsPanelFragment changeHpStat() {
        changeStatText("hp", mPlayer.getHp(), Config.getShortStatName("hp"));
        return this;
    }

    public StatsPanelFragment changeStrStat() {
        changeStatText("str", mPlayer.getStr(), Config.getShortStatName("str"));
        return this;
    }

    public StatsPanelFragment changeMpStat() {
        changeStatText("mp", mPlayer.getMp(), Config.getShortStatName("mp"));
        return this;
    }

    public StatsPanelFragment changeStepsStat() {
        changeStatText("steps", mPlayer.getSteps(), Config.getShortStatName("steps"));
        return this;
    }
}
