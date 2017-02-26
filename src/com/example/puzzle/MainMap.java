package com.example.puzzle;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by konst on 22.08.16.
 */
public class MainMap {

    final static int TOTAL_CELLS = 30;
    final static int BATTLE_TOTAL_CELLS = 30;
    final static int MAX_UNITS = 20;
    final static int MIN_UNITS = 5;
    final static int CELLS_PER_LINE = 6;
    final static int OPENED_CELL_COLOR = Color.BLACK;
    final static int MAX_DMG_POINT = 9;
    final static int STEPS_TILL_NEXT_SCREEN = 5;

    private Player mPlayer;
    private GridView mGridView;
    private Context mContext;
    private Random mRandom;
    private ArrayList<Class> mDelayedCheckClasses = new ArrayList<Class>() {{
        add(UnitEnemy.class);
        //add(UnitDungeon.class);
    }};

    MainMap(Context context) {
        mRandom = new Random();
        mContext = context;
        mPlayer = ((ExtendActivity) mContext).mPlayer;
        mGridView = (GridView) ((ExtendActivity) mContext).findViewById(R.id.gridView);
    }

    public MainMap create() {
        mGridView.setNumColumns(CELLS_PER_LINE);
        return this;
    }

    public MainMap setLocationNameAndImage(String location) {
        ((ExtendActivity) mContext).findViewById(R.id.main_layout)
                .setBackgroundResource(Location.getImgByLocation(mContext, location));
        TextView textView = (TextView) ((ExtendActivity) mContext).findViewById(R.id.location_name);
        textView.setText(Config.mPathLocations.get(location));
        return this;
    }

    public MainMap setUnits() {
        String location = Town.getRandomPathLocation();
        setLocationNameAndImage(location);
        int countUnits = mRandom.nextInt(MAX_UNITS - MIN_UNITS) + MIN_UNITS;
        Unit.units = new Class[] {UnitEnemy.class, UnitTreasure.class};
        CellAdapter adapter = new CellAdapter(mContext);
        for (int pos = 0; pos < TOTAL_CELLS; pos++) {
            if (mRandom.nextInt(2) == 1 && countUnits > 0) {
                countUnits--;
                adapter.add(pos, Unit.getRandomUnit(mContext, pos, location));
            } else {
                adapter.add(pos, new UnitEmpty());
            }
        }
        mGridView.setAdapter(adapter);
        setMapClick();
        return this;
    }

    public MainMap setDmgPoints(UnitEnemy enemy) {
        CellBattleAdapter adapter = new CellBattleAdapter(mContext, enemy);
        for (int pos = 0; pos < BATTLE_TOTAL_CELLS; pos++) {
            if (mRandom.nextInt(2) == 1) {
                adapter.add(pos, mRandom.nextInt(MAX_DMG_POINT) + 1);
            } else {
                adapter.add(pos, 0);
            }
        }
        mGridView.setAdapter(adapter);
        setBattleClick();
        return this;
    }

    public MainMap setTownUnits(String townName) {
        TownCellAdapter adapter = ((AdventureActivity) mContext).mTown.getTownAdapter(townName);
        mGridView.setAdapter(adapter);
        TextView textView = (TextView) ((ExtendActivity) mContext).findViewById(R.id.location_name);
        textView.setText(townName);
        setTownMapClick();
        return this;
    }

    public MainMap setOutsideUnits() {
        int countUnits = mRandom.nextInt(MAX_UNITS - MIN_UNITS) + MIN_UNITS;
        Unit.units = new Class[] {UnitEnemy.class, UnitTreasure.class, UnitDungeon.class};
        CellAdapter adapter = new CellAdapter(mContext);
        for (int pos = 0; pos < TOTAL_CELLS; pos++) {
            if (mRandom.nextInt(2) == 1 && countUnits > 0) {
                countUnits--;
                adapter.add(pos, Unit.getRandomUnit(mContext, pos, ((AdventureActivity) mContext).mPathLocations.get(0)));
            } else {
                adapter.add(pos, new UnitEmpty());
            }
        }
        mGridView.setAdapter(adapter);
        setOutsideMapClick();
        return this;
    }

    public MainMap setTrapsAndTreasures() {
        return this;
    }

    public MainMap setLabyrinth() {
        return this;
    }

    public static boolean isCellEmpty(View view) {
        TextView textView = (TextView) view.findViewById(R.id.text);
        return textView.getText().toString() == "" && view.findViewById(R.id.cell_img) == null;
    }

    public Boolean checkEmptyCells() {
        for (int i = 0; i < MainMap.TOTAL_CELLS; i++) {
            if (isCellEmpty(mGridView.getChildAt(i))) {
                return true;
            }
        }
        return false;
    }

    public int countOpenCells() {
        int count = 0;
        for (int i = 0; i < MainMap.TOTAL_CELLS; i++) {
            if (!isCellEmpty(mGridView.getChildAt(i))) {
                count++;
            }
        }
        return count;
    }

    public void setTownMapClick() {
        final TownCellAdapter adapter = (TownCellAdapter) mGridView.getAdapter();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Unit unit = adapter.getItem(position);
                unit.action();
                ((ExtendActivity) mContext).mLvl.checkIsLvlEnd();
            }
        });
    }

    public void setOutsideMapClick() {
        final CellAdapter adapter = (CellAdapter) mGridView.getAdapter();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.findViewById(R.id.cell_img) == null) {
                    mPlayer.removeStep();
                    Unit unit = adapter.getItem(position);
                    unit.addUnitToCell(mContext, view, true);
                    unit.action();
                    if (!mDelayedCheckClasses.contains(unit.getClass())) {
                        differentChecks(((AdventureActivity) mContext));
                    } else {
                        disableNextLocButton();
                    }
                }
            }
        });
        Button button = (Button) ((ExtendActivity) mContext).findViewById(R.id.next_location);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AdventureActivity) mContext).exitField();
            }
        });
    }

    public void differentChecks(final ExtendActivity activity) {
        ((ExtendActivity) mContext).mLvl.checkIsLvlEnd();
        enableDisableNextLocButton();
        if (mPlayer.getSteps() == 0 || !checkEmptyCells()) {
            ((CellAdapter) mGridView.getAdapter()).disableAdapter();
            disableNextLocButton();
            mGridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    activity.exitField();
                }
            }, 1000);
        }
    }

    public void enableDisableNextLocButton() {
        if (countOpenCells() >= STEPS_TILL_NEXT_SCREEN) {
            enableNextLocButton();
        } else {
            disableNextLocButton();
        }
    }

    public void disableNextLocButton() {
        Button button = (Button) ((ExtendActivity) mContext).findViewById(R.id.next_location);
        if (button != null) button.setVisibility(View.GONE);
    }

    public void enableNextLocButton() {
        Button button = (Button) ((ExtendActivity) mContext).findViewById(R.id.next_location);
        if (button != null) button.setVisibility(View.VISIBLE);
    }

    public void setMapClick() {
        final CellAdapter adapter = (CellAdapter) mGridView.getAdapter();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view.findViewById(R.id.cell_img) == null) {
                    mPlayer.removeStep();
                    Unit unit = adapter.getItem(position);
                    unit.addUnitToCell(mContext, view, true);
                    unit.action();
                    if (!mDelayedCheckClasses.contains(unit.getClass())) {
                        differentChecks(((ArcadeActivity) mContext));
                    }
                }
            }
        });
    }

    public void setBattleClick() {
        final CellBattleAdapter adapter = (CellBattleAdapter) mGridView.getAdapter();
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.text);
                if (isCellEmpty(view)) {
                    Integer dmg = adapter.getItem(position);
                    textView.setText(dmg.toString());
                    textView.setTextColor(Color.RED);

                    ViewGroup.LayoutParams params = textView.getLayoutParams();
                    params.height = ViewGroup.LayoutParams.MATCH_PARENT;
                    params.width = ViewGroup.LayoutParams.MATCH_PARENT;
                    textView.setLayoutParams(params);

                    view.setBackgroundColor(MainMap.OPENED_CELL_COLOR);
                    if (adapter.isPlayerMove()) {
                        ((ExtendActivity) mContext).mBattleFieldFragment.mBattle.playerMove(dmg);
                    }
                }
            }
        });
    }
}
