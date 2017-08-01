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
    // 0 is player move, other numbers is enemies' move
    private int mTurn = 0;

    final static int START_SEVERAL_FOES_PERCENT = 20;
    final static int START_COUNT_FOES = 3;

    Battle (Context context, UnitEnemy enemy) {
        mContext = context;
        mEnemies.add(enemy);
        mEnemy = enemy;
        mPlayer = ((ExtendActivity) context).mPlayer;
        mGridView = (GridView) ((ExtendActivity) context).findViewById(R.id.gridView);
        mAdapter = (CellBattleAdapter) mGridView.getAdapter();
        // there can be more than one enemy
        if (Config.getDropPercent() <= START_SEVERAL_FOES_PERCENT) {
            int rand = (new Random()).nextInt(START_COUNT_FOES) + 1;
            while (--rand > 0) {
                UnitEnemy tmpEnemy = new UnitEnemy(mContext, getFreeCellId(), enemy.getLocation());
                mEnemies.add(tmpEnemy);
                mAdapter.add(tmpEnemy);
            }
            mGridView.setAdapter(mAdapter);
        }
    }

    public void firstMove() {
        if ((new Random()).nextInt(2) == 0) {
            mAdapter.disableAdapter();
            mGridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    takeTurn();
                }
            }, 500);
        }
    }

    public void takeTurn() {
        mTurn++;
        if (checkTurn()) {
            enemyMove();
        } else {
            if (!checkEmptyCells()) return;
            mAdapter.setIsPlayerMove(true);
            mAdapter.enableAdapter();
        }
    }

    private boolean checkTurn() {
        if (mTurn > mEnemies.size()) mTurn = 0;
        if (mTurn > 0) {
            mEnemy = mEnemies.get(mTurn - 1);
        }
        return mTurn > 0;
    }

    public void playerMove(int dmg) {
        // TODO: set mEnemy with chosen foe
        mEnemy.getHit(mPlayer.strike(dmg));
        Boolean check = mEnemy.checkHp();
        if (!check) {
            mEnemies.remove(mEnemy);
            // TODO: change enemy img to skull with bones
            if (mEnemies.size() == 0) {
                mAdapter.disableAdapter();
                ((ExtendActivity) mContext).mLogHistoryFragment.addEndBattleRec("You won :)");
                mPlayer.lvlStatIncrease();
                mPlayer.addKilledEnemy(mEnemy.getName());
                endBattle(false);
                return;
            }
        }
        takeTurn();
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
            return;
        }
        takeTurn();
    }

    public int openCell() {
        int pos = getFreeCellId();
        mGridView.performItemClick(mGridView.getChildAt(pos), pos, mAdapter.getItemId(pos));
        return mAdapter.getItem(pos);
    }

    public Integer getFreeCellId() {
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
