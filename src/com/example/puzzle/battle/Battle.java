package com.example.puzzle.battle;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import com.example.puzzle.MainMap;
import com.example.puzzle.Player;
import com.example.puzzle.R;
import com.example.puzzle.activity.ExtendActivity;
import com.example.puzzle.unit.UnitEnemy;

import java.util.Random;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Battle {

    private GridView mGridView;
    private CellBattleAdapter mAdapter;
    private Player mPlayer;
    private UnitEnemy mEnemy;
    private Context mContext;

    Battle (Context context, UnitEnemy enemy) {
        mContext = context;
        mEnemy = enemy;
        mPlayer = ((ExtendActivity) context).mPlayer;
        mGridView = (GridView) ((ExtendActivity) context).findViewById(R.id.gridView);
        mAdapter = (CellBattleAdapter) mGridView.getAdapter();
    }

    public void move() {
        if ((new Random()).nextInt(2) == 0) {
            mAdapter.disableAdapter();
            mGridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemyMove();
                }
            }, 500);
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
