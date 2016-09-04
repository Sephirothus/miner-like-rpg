package com.example.puzzle;

import android.content.Context;
import android.graphics.Color;
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

    Battle (Context context, Player player, Enemy enemy, GridView gridView) {
        mContext = context;
        mEnemy = enemy;
        mPlayer = player;
        mAdapter = (CellBattleAdapter) gridView.getAdapter();
        mGridView = gridView;
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
        Integer dmg = getClosedCell();
        mPlayer.getHit(mEnemy.strike(dmg));
        if (!mPlayer.checkHp()) {
            Toast.makeText(mContext, "You lost!", Toast.LENGTH_LONG).show();
            endBattle();
        }
    }

    public int getClosedCell() {
        Random r = new Random();
        int pos = r.nextInt(Map.TOTAL_CELLS);
        /* TODO check if cell is closed
        while (cell.) {

        }*/
        View view = mGridView.getChildAt(pos);
        return openCell(view, pos);
    }

    public Integer openCell(View view, int position) {
        TextView textView = (TextView) view.findViewById(R.id.title);
        if (textView.getText().toString() == "") {
            Integer dmg = mAdapter.getItem(position);
            textView.setText(dmg.toString());
            textView.setTextColor(Color.RED);
            view.setBackgroundColor(Map.OPENED_CELL_COLOR);
            return dmg;
        }
        return null;
    }

    public void endBattle() {
        ((BattleActivity) mContext).finish();
    }
}
