package com.example.puzzle;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by sephirothus on 12.01.17.
 */
public class LogHistoryFragment extends Fragment {

    private TextView mLogText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.log_history, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mLogText = (TextView) getActivity().findViewById(R.id.log_text);
    }

    public void addRecord(String record, int textColor) {
        Spannable span = new SpannableString(record + "\n");
        span.setSpan(new ForegroundColorSpan(textColor), 0,
                record.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mLogText.append(span);
        final ScrollView scroll = (ScrollView) mLogText.getParent();
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void addDieAndReturnToTownRec() {
        addRecord("You died, but the Gods gave you another chance and you were brought back to your home town",
                Color.RED);
    }

    public void addNewLvlRec(int lvl) {
        addRecord("You've reached " + lvl + " level", Color.GREEN);
    }

    public void addEnemyMeetupRec(String name) {
        addRecord("<=== BATTLE START ===>", Color.RED);
        addRecord("You've met " + name, Color.RED);
    }

    public void addEndBattleRec(String playerResult) {
        addRecord(playerResult, Color.RED);
        addRecord("<=== BATTLE END ===>", Color.RED);
    }

    public void addPlayerHitEnemyRec(int dmg) {
        String text = "You hit enemy on " + dmg + " point(s)";
        if (dmg == 0) text = "You hit and missed";
        addRecord(text, Color.RED);
    }

    public void addEnemyHitPlayerRec(int dmg) {
        String text = "Enemy hit you on " + dmg + " point(s)";
        if (dmg == 0) text = "Enemy hit and missed";
        addRecord(text, Color.RED);
    }

    public void addGetGoldRewardRec(int count) {
        addRecord("Your reward is " + count + " gold", Color.YELLOW);
    }

    public void addTreasureFoundRec(String name) {
        addRecord("You open treasure chest and get " + name, Color.YELLOW);
    }

    public String addStatIncreaseRec(String playerStat, int statVal) {
        String text = "Your " + Config.getFullStatName(playerStat);
        if (statVal == 0) {
            text += " have been fully restored";
        } else {
            text += " increased by " + statVal;
        }
        addRecord(text, Color.CYAN);
        return text;
    }
}
