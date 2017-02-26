package com.example.puzzle;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AlignmentSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

/**
 * Created by sephirothus on 04.02.17.
 */
public class Talk {

    private Context mContext;
    private View mView;
    private String mInterlocutorName;
    private int mInterlocutorColor = Color.GREEN;
    private UnitTownsman mUnit;

    Talk(Context context, String name, UnitTownsman unit) {
            mContext = context;
            mInterlocutorName = name;
            mUnit = unit;
    }

    public void create() {
        mView = (((ExtendActivity) mContext).getLayoutInflater()).inflate(R.layout.dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        ((ExtendActivity) mContext).mPlayer.setPlayerHeadImg(mView);
        ((ImageView) mView.findViewById(R.id.npc_head)).setImageResource(R.drawable.npc_head);
        setTalks();
        builder.setTitle("Hi there stranger, what you want?")
                .setView(mView)
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

    private void setTalks() {
        LinearLayout choose = (LinearLayout) mView.findViewById(R.id.dialog_choose);
        choose.removeAllViews();
        final Talk curClass = this;
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    showHideAcceptQuestButton(false);
                    curClass.getClass().getDeclaredMethod(v.getTag().toString()).invoke(curClass);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        };
        View.OnClickListener onClickQuest = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideAcceptQuestButton(false);
                talkGetQuest(v.getTag().toString());
            }
        };
        for (Object talk : mUnit.mTalks.keySet().toArray()) {
            TextView textView = new TextView(mContext);
            textView.setText("- " + mUnit.mTalks.get(talk));
            textView.setTextSize(15);
            textView.setTag(talk);
            textView.setOnClickListener(onClick);
            choose.addView(textView);
        }
        Player player = ((ExtendActivity) mContext).mPlayer;
        for (Object questId : mUnit.mQuests.keySet().toArray()) {
            TextView textView = new TextView(mContext);
            textView.setText(Quest.getQuestGiverString(player, questId.toString(), mUnit.mQuests.get(questId)));
            textView.setTextSize(15);
            textView.setTag(questId);
            textView.setOnClickListener(onClickQuest);
            choose.addView(textView);
        }
    }

    public void addRecord(String record, Boolean isInterlocutor) {
        TextView textView = (TextView) mView.findViewById(R.id.dialog_text);
        String name;
        int color;
        Layout.Alignment align;
        if (isInterlocutor) {
            name = mInterlocutorName + ":\n";
            color = mInterlocutorColor;
            align = Layout.Alignment.ALIGN_OPPOSITE;
        } else {
            name = "You" + ":\n";
            color = Color.YELLOW;
            align = Layout.Alignment.ALIGN_NORMAL;
        }
        int end = record.length() + name.length();

        Spannable span = new SpannableString(name + record + "\n");
        span.setSpan(new AlignmentSpan.Standard(align), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(color), name.length(), end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.append(span);
        final ScrollView scroll = (ScrollView) textView.getParent();
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    public void talkAboutTown() {
        addRecord(mUnit.mTalks.get("talkAboutTown"), false);
        addRecord(Config.mTowns.get(((AdventureActivity) mContext).mDestinationTown), true);
    }

    public void talkRandomNews() {

    }

    public void talkGetQuest(final String questId) {
        final Player player = ((ExtendActivity) mContext).mPlayer;
        if (!player.haveQuest(questId)) {
            final HashMap<String, String> questInfo = mUnit.mQuests.get(questId);
            addRecord(questInfo.get("description"), true);
            showHideAcceptQuestButton(true);
            (mView.findViewById(R.id.accept_quest)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    player.addQuest(questId, questInfo);
                    showHideAcceptQuestButton(false);
                    setTalks();
                    ((ExtendActivity) mContext).mLogHistoryFragment.addRecord(
                            "You've got a new quest - " + questInfo.get("title"), Color.MAGENTA
                    );
                }
            });
        } else {
            if (player.isQuestComplete(questId)) {
                player.completeQuestGetItem(questId);
                addRecord("Thank you", true);
                ((ExtendActivity) mContext).mLogHistoryFragment.addRecord(
                        "You've completed quest - " + (mUnit.mQuests.get(questId)).get("title"), Color.MAGENTA
                );
                player.getQuestReward(questId);
                mUnit.mQuests.remove(questId);
                player.removeQuest(questId);
                View view = (mView.findViewById(R.id.dialog_choose)).findViewWithTag(questId);
                ((ViewGroup) view.getParent()).removeView(view);
            } else {
                addRecord("How's your progress on this quest?", true);
                addRecord("I'm working on it", false);
            }
        }
    }

    private void showHideAcceptQuestButton(Boolean show) {
        (mView.findViewById(R.id.accept_quest)).setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
