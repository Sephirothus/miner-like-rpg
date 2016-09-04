package com.example.puzzle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

/**
 * Created by konst on 22.08.16.
 */
public class BattleActivity extends Activity {

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
        Battle battle = new Battle(this, player, enemy, gridView);
        setListener(gridView, battle);
        battle.move();
    }

    private void setListener(GridView gridView, Battle battle) {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                battle.playerMove(battle.openCell(view, position));
            }
        });
    }
}
