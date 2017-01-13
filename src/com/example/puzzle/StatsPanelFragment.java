package com.example.puzzle;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by sephirothus on 12.01.17.
 */
public class StatsPanelFragment extends Fragment {

    private Player mPlayer;
    private Map mMap;

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
        mPlayer = ((MainActivity) getActivity()).mPlayer;
        mMap = ((MainActivity) getActivity()).mFieldFragment.mMap;

        changeLvlStat();
        changeHpStat();
        changeStrStat();
        changeStepsStat();
    }

    public void changeStatText(String statName, int value, String statText) {
        int resId = getResources().getIdentifier(statName, "id", getActivity().getPackageName());
        TextView text = (TextView) getActivity().findViewById(resId);
        text.setText(statText + ": " + String.valueOf(value));
    }

    public StatsPanelFragment changeLvlStat() {
        changeStatText("lvl", mMap.getLvl(), "Lvl");
        return this;
    }

    public StatsPanelFragment changeHpStat() {
        changeStatText("hp", mPlayer.getHp(), "HP");
        return this;
    }

    public StatsPanelFragment changeStrStat() {
        changeStatText("str", mPlayer.getStr(), "Str");
        return this;
    }

    public StatsPanelFragment changeStepsStat() {
        changeStatText("steps", mPlayer.getSteps(), "Steps");
        return this;
    }
}
