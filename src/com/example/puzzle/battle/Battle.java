package com.example.puzzle.battle;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import com.example.puzzle.Config;
import com.example.puzzle.MainMap;
import com.example.puzzle.Player;
import com.example.puzzle.R;
import com.example.puzzle.activity.ExtendActivity;
import com.example.puzzle.unit.UnitEnemy;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Battle {

    private GridView mGridView;
    private CellBattleAdapter mAdapter;
    private Context mContext;

    private ArrayList<UnitEnemy> mEnemies = new ArrayList<>();
    private UnitEnemy mEnemy;
    private Player mPlayer;
    private int mTurn = 0;

    final static int START_SEVERAL_FOES_PERCENT = 20;
    final static int START_COUNT_FOES = 3;

    Battle (Context context, UnitEnemy enemy) {
        mContext = context;
        mEnemies.add(enemy);
        if (Config.getDropPercent() <= START_SEVERAL_FOES_PERCENT) {
            //mEnemies.add(new UnitEnemy(mContext, ));
        }
        mPlayer = ((ExtendActivity) context).mPlayer;
        mGridView = (GridView) ((ExtendActivity) context).findViewById(R.id.gridView);
        mAdapter = (CellBattleAdapter) mGridView.getAdapter();
    }

    public void move() {
        if ((new Random()).nextInt(2) == 0) {
            mTurn = 1;
            mAdapter.disableAdapter();
            mGridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemyMove();
                }
            }, 500);
        }
        setTurn();
    }

    private void setTurn() {
        if (mTurn > mEnemies.size()) mTurn = 0;
        if (mTurn > 0) {
            mEnemy = mEnemies.get(mTurn - 1);
            mTurn++;
        }
    }

    public void playerMove(int dmg) {
        mEnemy.getHit(mPlayer.strike(dmg));
        Boolean check = mEnemy.checkHp();
        if (!check) {
            mAdapter.disableAdapter();
            ((ExtendActivity) mContext).mLogHistoryFragment.addEndBattleRec("You won :)");
            mPlayer.lvlStatIncrease();
            mPlayer.addKilledEnemy(mEnemy.getName());
            endBattle(false);
        } else enemyMove();
    }

    public void enemyMove() {
        mAdapter.setIsPlayerMove(false);
        mAdapter.disableAdapter();
        if (!checkEmptyCells()) return;

        Integer dmg = openCell();
        mPlayer.getHit(mEnemy.strike(dmg));
        Boolean check = mPlayer.checkHp();
        if (!check) {
            ((ExtendActivity) mContext).mLogHistoryFragment.addEndBattleRec("You lost :(");
            endBattle(true);
        } else {
            if (!checkEmptyCells()) return;
            mAdapter.setIsPlayerMove(true);
            mAdapter.enableAdapter();
        }
    }

    public int openCell() {
        Random r = new Random();
        int pos = r.nextInt(MainMap.BATTLE_TOTAL_CELLS);
        View view = mGridView.getChildAt(pos);
        while (!MainMap.isCellEmpty(view)) {
            pos = r.nextInt(MainMap.BATTLE_TOTAL_CELLS);
            view = mGridView.getChildAt(pos);
        }
        mGridView.performItemClick(view, pos, mAdapter.getItemId(pos));
        return mAdapter.getItem(pos);
    }

    public Integer getFreeCell() {
        if (!checkEmptyCells()) return null;

        Random r = new Random();
        int pos = r.nextInt(MainMap.BATTLE_TOTAL_CELLS);
        View view = mGridView.getChildAt(pos);
        while (!MainMap.isCellEmpty(view)) {
            pos = r.nextInt(MainMap.BATTLE_TOTAL_CELLS);
            view = mGridView.getChildAt(pos);
        }
        return pos;
    }

    private boolean checkEmptyCells() {
        for (int i = 0; i < MainMap.BATTLE_TOTAL_CELLS; i++) {
            if (MainMap.isCellEmpty(mGridView.getChildAt(i))) {
                return true;
            }
        }
        continueBattle();
        return false;
    }

    public void endBattle(final Boolean isGameOver) {
        mGridView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isGameOver) {
                    ((ExtendActivity) mContext).gameOver();
                } else {
                    ((ExtendActivity) mContext).endBattle();
                }
            }
        }, 1000);
    }

    public void continueBattle() {
        mGridView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((ExtendActivity) mContext).startBattle(mEnemy);
            }
        }, 500);
    }
}
