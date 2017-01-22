package com.example.puzzle;

import android.content.Context;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

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
        mGridView = (GridView) ((MainActivity) context).findViewById(R.id.gridView);
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
            }, 1000);
        }
    }

    public void playerMove(int dmg) {
        mEnemy.getHit(mPlayer.strike(dmg));
        Boolean check = mEnemy.checkHp();
        if (!check) {
            mAdapter.disableAdapter();
            ((MainActivity) mContext).mLogHistoryFragment.addEndBattleRec("You won :)");
            mPlayer.lvlStatIncrease();
            endBattle();
        } else enemyMove();
    }

    public void enemyMove() {
        mAdapter.setIsPlayerMove(false);
        mAdapter.disableAdapter();
        Integer dmg = openCell();
        mPlayer.getHit(mEnemy.strike(dmg));
        Boolean check = mPlayer.checkHp();
        if (!check) {
            ((MainActivity) mContext).mLogHistoryFragment.addEndBattleRec("You lost :(");
            endBattle();
        } else {
            mAdapter.setIsPlayerMove(true);
            mAdapter.enableAdapter();
        }
    }

    public int openCell() {
        Random r = new Random();
        int pos = r.nextInt(MainMap.TOTAL_CELLS);
        View view = mGridView.getChildAt(pos);
        while (!isCellEmpty(view)) {
            pos = r.nextInt(MainMap.TOTAL_CELLS);
            view = mGridView.getChildAt(pos);
        }
        mGridView.performItemClick(view, pos, mAdapter.getItemId(pos));
        return mAdapter.getItem(pos);
    }

    public static boolean isCellEmpty(View view) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        return textView.getText().toString() == "" && view.findViewById(R.id.cell_img) == null;
    }

    public void endBattle() {
        mGridView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((MainActivity) mContext).endBattle();
            }
        }, 1000);
    }
}
