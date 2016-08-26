package com.example.puzzle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Map map = new Map(getApplicationContext(), (GridView) findViewById(R.id.gridView));
        map.create().setUnits();
    }
}
