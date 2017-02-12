package com.example.puzzle;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by sephirothus on 29.01.17.
 */
public class UnitTownsman extends Unit {

    private final static int MAX_QUESTS_COUNT = 3;
    private final static int MIN_QUESTS_COUNT = 1;

    private Context mContext;
    private int mPosition;
    private ArrayList<String> mImgs = new ArrayList<String>(){{
        add("townsman_1");
        add("townsman_2");
        add("townsman_3");
        add("townsman_farmer");
        add("townsman_prayer");
    }};
    private Integer mCurImg;

    public HashMap<String, HashMap<String, String>> mQuests = new HashMap<>();
    public HashMap<String, String> mTalks = new HashMap<String, String>(){{
            put("talkAboutTown", "Tell me about this town");
            put("talkRandomNews", "Got any news?");
    }};

    UnitTownsman(Context context, Integer position, String location) {
        mContext = context;
        mPosition = position;
        mCurImg = mContext.getResources().getIdentifier(
                mImgs.get((new Random()).nextInt(mImgs.size())),
                "drawable", mContext.getPackageName()
        );
        setLocation(location);
        generateQuests();
    }

    public void generateQuests() {
        Random random = new Random();
        if (random.nextInt(2) == 1) {
            int questCount = random.nextInt(MAX_QUESTS_COUNT) + MIN_QUESTS_COUNT;
            for (int i = 0; i < questCount; i++) {
                mQuests.put(Quest.generateQuestId(mLocation, mPosition + 1, i), (new Quest(mContext)).randomQuest());
            }
        }
    }

    @Override
    public void action() {
        (new Talk(mContext, "Townsman", this)).create();
    }

    @Override
    public Integer getImg() {
        return mCurImg;
    }
}
