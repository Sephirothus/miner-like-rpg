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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

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
    private HashMap<String, String> mTalks;
    private HashMap<String, HashMap<String, String>> mQuests;

    Talk(Context context,
         String name,
         HashMap<String, String> talks,
         HashMap<String, HashMap<String, String>> quests) {
            mContext = context;
            mInterlocutorName = name;
            mTalks = talks;
            mQuests = quests;
    }

    public void create() {
        mView = (((ExtendActivity) mContext).getLayoutInflater()).inflate(R.layout.dialog, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        (((ExtendActivity) mContext).mPlayer).setPlayerHeadImg(mView);
        ((ImageView) mView.findViewById(R.id.npc_head)).setImageResource(R.drawable.npc_head);
        LinearLayout choose = (LinearLayout) mView.findViewById(R.id.dialog_choose);
        final Talk curClass = this;
        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
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
                talkGetQuest(v.getTag().toString());
            }
        };
        for (Object talk : mTalks.keySet().toArray()) {
            TextView textView = new TextView(mContext);
            textView.setText("- " + mTalks.get(talk));
            textView.setTextSize(18);
            textView.setTag(talk);
            textView.setOnClickListener(onClick);
            choose.addView(textView);
        }
        for (Object quest : mQuests.keySet().toArray()) {
            TextView textView = new TextView(mContext);
            HashMap<String, String> questInfo = mQuests.get(quest);
            textView.setText(questInfo.get("title"));
            textView.setTextSize(18);
            textView.setTag(quest);
            textView.setOnClickListener(onClickQuest);
            choose.addView(textView);
        }

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
        addRecord(mTalks.get("talkAboutTown"), false);
        addRecord(Config.mTowns.get(((AdventureActivity) mContext).mDestinationTown), true);
    }

    public void talkRandomNews() {

    }

    public void talkGetQuest(String questId) {
        HashMap<String, String> questInfo = mQuests.get(questId);
        addRecord(questInfo.get("description"), true);
    }
}
