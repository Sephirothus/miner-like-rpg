package com.example.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

/**
 * Created by konst on 22.08.16.
 */
public class BattleActivity extends Activity {

    private Battle mBattle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        GridView gridView = (GridView) findViewById(R.id.gridView);

        Intent intent = getIntent();
        Player player = (Player) intent.getSerializableExtra("player");
        Enemy enemy = (Enemy) intent.getSerializableExtra("enemy");

        Map map = new Map(this, gridView);
        map.create().setDmgPoints(enemy);
        mBattle = new Battle(this, player, enemy, gridView);
        mBattle.move();
    }

    public void battlePlayerMove(int dmg) {
        mBattle.playerMove(dmg);
    }
}
