package com.example.puzzle;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Battle {

    private GridView mGridView;
    private CellBattleAdapter mAdapter;
    private Player mPlayer;
    private Enemy mEnemy;
    private Context mContext;

    Battle (Context context, Enemy enemy) {
        mContext = context;
        mEnemy = enemy;
        mPlayer = ((MainActivity) context).mPlayer;
        mGridView = ((MainActivity) context).mFieldFragment.mGridView;
        mAdapter = (CellBattleAdapter) mGridView.getAdapter();
    }

    public void move() {
        if ((new Random()).nextInt(2) == 0) {
            enemyMove();
        }
    }

    public void playerMove(int dmg) {
        mEnemy.getHit(mPlayer.strike(dmg));
        Boolean check = mEnemy.checkHp();
        if (!check) {
            Toast.makeText(mContext, "You won!", Toast.LENGTH_LONG).show();
            endBattle();
        } else enemyMove();
    }

    public void enemyMove() {
        mAdapter.setIsPlayerMove(false);
        Integer dmg = openCell();
        mPlayer.getHit(mEnemy.strike(dmg));
        Boolean check = mPlayer.checkHp();
        if (!check) {
            Toast.makeText(mContext, "You lost!", Toast.LENGTH_LONG).show();
            endBattle();
        }
        mAdapter.setIsPlayerMove(true);
    }

    public int openCell() {
        Random r = new Random();
        int pos = r.nextInt(Map.TOTAL_CELLS);
        /* TODO check if cell is closed
        while (cell.) {

        }*/
        View view = mAdapter.getView(pos, null, mGridView);
        view.performClick();
        return mAdapter.getItem(pos);
    }

    public void endBattle() {
        ((MainActivity) mContext).mFieldFragment.closeBattleField();
    }
}
