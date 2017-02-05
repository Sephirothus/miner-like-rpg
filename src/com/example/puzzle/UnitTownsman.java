package com.example.puzzle;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by sephirothus on 29.01.17.
 */
public class UnitTownsman extends Unit {

    private Context mContext;
    private int mPosition;
    private HashMap<String, HashMap<String, String>> mQuests = new HashMap<>();
    private ArrayList<String> mImgs = new ArrayList<String>(){{
        add("townsman_1");
        add("townsman_2");
        add("townsman_3");
        add("townsman_farmer");
        add("townsman_prayer");
    }};
    private Integer mCurImg;
    private HashMap<String, String> mTalks = new HashMap<String, String>(){{
            put("talkAboutTown", "Tell me about this town");
            put("talkRandomNews", "Got any news?");
            //put("talkGetQuest", "I'm a quest seeker, got any?");
    }};

    UnitTownsman(Context context, Integer lvl, Integer position) {
        mContext = context;
        mPosition = position;
        mCurImg = mContext.getResources().getIdentifier(
                mImgs.get((new Random()).nextInt(mImgs.size())),
                "drawable", mContext.getPackageName()
        );
        generateQuests();
    }

    private void generateQuests() {
        Random random = new Random();
        String questId = ((AdventureActivity) mContext).mDestinationTown + "_" + mPosition + "_";
        if (random.nextInt(2) == 1) {
            int questCount = random.nextInt(3) + 1;
            for (int i = 0; i < questCount; i++) {
                HashMap quest = (new Quest(mContext)).randomQuest();
                mQuests.put(questId + i, quest);
            }
        }
    }

    @Override
    public void action() {
        (new Talk(mContext, "Townsman", mTalks, mQuests)).create();
    }

    @Override
    public Integer getImg() {
        return mCurImg;
    }
}
