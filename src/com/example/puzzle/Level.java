package com.example.puzzle;

import android.content.Context;
import android.widget.GridView;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by sephirothus on 01.02.17.
 */
public class Level {

    public static int ENEMIES_COUNT_PERCENT = 150;
    public static int STAT_COUNT_PERCENT = 50;

    private Context mContext;
    private ExtendActivity mActivity;
    private int mLvl = 1;
    protected String[] mLvlTargets;
    public HashMap<String, String> mCurTarget = new HashMap<>();

    Level(Context context, String[] lvlTargets) {
        mContext = context;
        mActivity = (ExtendActivity) mContext;
        mLvlTargets = lvlTargets;
        setLvlTarget();
    }

    public int getLvl() {
        return mLvl;
    }

    public void setLvl(int lvl) {
        mLvl = lvl;
    }

    public void checkIsLvlEnd() {
        if (checkTarget()) {
            GridView gridView = (GridView) mActivity.findViewById(R.id.gridView);
            gridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mActivity.nextLvl();
                }
            }, 1000);
        }
    }

    public void nextLvl() {
        mLvl++;
        mActivity.mStatsPanelFragment.changeLvlStat();
        mActivity.mLogHistoryFragment.addNewLvlRec(mLvl);
        mActivity.mPlayer.lvlStatIncrease();
        setLvlTarget();
    }

    public void setLvlTarget() {
        mCurTarget.clear();
        mCurTarget.put("method", mLvlTargets[(new Random()).nextInt(mLvlTargets.length)]);
        // call to initialize data in mCurTarget
        checkTarget();
    }

    public boolean checkTarget() {
        try {
            return (Boolean) getClass().getDeclaredMethod(mCurTarget.get("method")).invoke(this);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getTargetText() {
        String text = "";
        switch (mCurTarget.get("method")) {
            case "killEnemies":
                int countEnemies = Integer.parseInt(mCurTarget.get("count"));
                int startCountEnemies = Integer.parseInt(mCurTarget.get("start_number"));
                text = "kill " + (startCountEnemies + countEnemies - mActivity.mPlayer.getKilledEnemies())
                        + " enemies";
                break;
            case "raiseStat":
                int countStat = Integer.parseInt(mCurTarget.get("count"));
                int startCountStat = Integer.parseInt(mCurTarget.get("start_number"));
                text = "increase your " + Config.getFullStatName(mCurTarget.get("stat")) + " by " +
                        (startCountStat + countStat - mActivity.mPlayer.getStat(mCurTarget.get("stat")));
                break;
            case "useSteps":
                text = "use all your current steps";
                break;
        }
        return text;
    }

    private boolean killEnemies() {
        if (mCurTarget.get("count") == null) {
            mCurTarget.put("count", String.valueOf((new Random()).nextInt(mLvl * ENEMIES_COUNT_PERCENT / 100 + 1) + 1));
            mCurTarget.put("start_number", String.valueOf(mActivity.mPlayer.getKilledEnemies()));
        } else {
            int count = Integer.parseInt(mCurTarget.get("count"));
            int startNumber = Integer.parseInt(mCurTarget.get("start_number"));
            return (startNumber + count) <= mActivity.mPlayer.getKilledEnemies();
        }
        return false;
    }

    private boolean raiseStat() {
        if (mCurTarget.get("stat") == null) {
            Object[] stats = mActivity.mPlayer.getAllStats();
            mCurTarget.put("stat" , stats[(new Random()).nextInt(stats.length)].toString());
            mCurTarget.put("count", String.valueOf((new Random()).nextInt(mLvl * STAT_COUNT_PERCENT / 100 + 1) + 1));
            mCurTarget.put("start_number", String.valueOf(mActivity.mPlayer.getStat(mCurTarget.get("stat"))));
        } else {
            int count = Integer.parseInt(mCurTarget.get("count"));
            int startNumber = Integer.parseInt(mCurTarget.get("start_number"));
            return (startNumber + count) <= mActivity.mPlayer.getStat(mCurTarget.get("stat"));
        }
        return false;
    }

    private boolean useSteps() {
        if (mActivity.mPlayer.getSteps() <= 0) {
            mActivity.mPlayer.refreshSteps();
            return true;
        }
        return false;
    }
}
