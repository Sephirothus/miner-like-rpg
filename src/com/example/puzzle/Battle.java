package com.example.puzzle;

import android.content.Context;
import java.util.Random;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Battle {

    private CellBattleAdapter mAdapter;
    private Player mPlayer;
    private Enemy mEnemy;
    private Context mContext;

    Battle (Context context, Player player, Enemy enemy, CellBattleAdapter adapter) {
        mContext = context;
        mEnemy = enemy;
        mPlayer = player;
        mAdapter = adapter;
    }

    public void move() {
        if ((new Random()).nextInt(2) == 0) {
            enemyMove();
        }
    }

    public void playerMove(int dmg) {
        mEnemy.getHit(mPlayer.strike(dmg));
        if (!mEnemy.checkHp()) endBattle();
    }

    public void enemyMove() {
        Integer dmg = mAdapter.getItem(5);
        mPlayer.getHit(mEnemy.strike(dmg));
        if (!mPlayer.checkHp()) endBattle();
    }

    public void endBattle() {
        ((BattleActivity) mContext).finish();
    }
}
