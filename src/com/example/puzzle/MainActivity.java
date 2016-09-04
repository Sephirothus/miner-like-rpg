package com.example.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.GridView;

import java.io.Serializable;

public class MainActivity extends Activity implements Serializable {

    private Player mPlayer;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mPlayer = new Player();
        Map map = new Map(this, (GridView) findViewById(R.id.gridView));
        map.create().setUnits();
    }

    public void startBattle(Enemy enemy) {
        Intent intent = new Intent(this, BattleActivity.class);
        intent.putExtra("enemy", enemy);
        intent.putExtra("player", mPlayer);
        startActivity(intent);
    }
}
