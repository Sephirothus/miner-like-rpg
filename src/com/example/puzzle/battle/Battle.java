package com.example.puzzle.battle;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
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

    public final static int START_SEVERAL_FOES_PERCENT = 20;
    public final static int START_COUNT_FOES = 3;

    Battle (Context context, ArrayList<UnitEnemy> enemies) {
        mContext = context;
        mEnemies = enemies;
        setEnemy(enemies.get(0));
        mPlayer = ((ExtendActivity) context).mPlayer;
        mGridView = (GridView) ((ExtendActivity) context).findViewById(R.id.gridView);
        mAdapter = (CellBattleAdapter) mGridView.getAdapter();
    }

    public void firstMove() {
        if ((new Random()).nextInt(2) == 0) {
            takeTurn();
        }
    }

    public void takeTurn() {
        mTurn++;
        if (checkTurn()) {
            mAdapter.disableAdapter();
            mAdapter.setIsPlayerMove(false);
            if (!checkEmptyCells()) return;

            mGridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemyMove();
                }
            }, 500);
        } else {
            if (!checkEmptyCells()) return;
            mAdapter.setIsPlayerMove(true);
            mAdapter.enableAdapter();
        }
    }

    private boolean checkTurn() {
        if (mTurn > mEnemies.size()) mTurn = 0;
        if (mTurn > 0) {
            setEnemy(mEnemies.get(mTurn - 1));
        }
        return mTurn > 0;
    }

    public void setEnemy(UnitEnemy enemy) {
        mEnemy = enemy;
        ((ExtendActivity) mContext).mStatsPanelFragment.addEnemyStats(
                enemy.getName(), enemy.getHp(), enemy.getStr()
        );
    }

    public UnitEnemy getEnemyByPos(int pos) {
        for (UnitEnemy enemy : mEnemies) {
            if (enemy.getPosition() == pos) return enemy;
        }
        return null;
    }

    public void playerMove(int dmg) {
        mEnemy.getHit(mPlayer.strike(dmg));
        Boolean check = mEnemy.checkHp();
        if (!check) {
            killEnemy();
            if (mEnemies.size() == 0) {
                mAdapter.disableAdapter();
                ((ExtendActivity) mContext).mLogHistoryFragment.addEndBattleRec("You won :)");
                mPlayer.lvlStatIncrease();
                endBattle(false);
                return;
            }
        }
        takeTurn();
    }

    public void enemyMove() {
        mPlayer.getHit(mEnemy.strike(openCell()));
        Boolean check = mPlayer.checkHp();
        if (!check) {
            ((ExtendActivity) mContext).mLogHistoryFragment.addEndBattleRec("You lost :(");
            endBattle(true);
            return;
        }
        takeTurn();
    }

    private void killEnemy() {
        mEnemies.remove(mEnemy);
        ((ImageView) mGridView.getChildAt(mEnemy.getPosition()).findViewById(R.id.cell_img))
                .setImageResource(R.drawable.death);
        ((ExtendActivity) mContext).mLogHistoryFragment.addEnemyKillRec(mEnemy.getName());
        mPlayer.addKilledEnemy(mEnemy.getName());
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
                ((ExtendActivity) mContext).startBattle(mEnemies);
            }
        }, 500);
    }
}
