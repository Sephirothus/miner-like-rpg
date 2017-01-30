package com.example.puzzle;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by sephirothus on 30.01.17.
 */
public class Town {

    public static String HOMETOWN_NAME = "Dusk town";

    private ArrayList<Integer> mPossibleExitCell = new ArrayList<>();
    private HashMap<String, TownCellAdapter> mTowns = new HashMap<>();
    private String[] mTownNames = {"Dusk town", "Goodlight", "Village of Grass", "Serpent village", "Sunless",
            "Home of Farmers", "Old Keep"};

    public void generateTowns(Context context) {
        setPossibleExitCells();
        Random random = new Random();
        Unit.units = new Class[] {UnitHouse.class, UnitTownsman.class, UnitDecor.class};
        Class[] unitsOnePerField = new Class[] {UnitChurch.class, UnitMerchant.class};
        int lvl = ((ExtendActivity) context).getLvl();
        for (String name : mTownNames) {
            int countUnits = random.nextInt(MainMap.MAX_UNITS - MainMap.MIN_UNITS) + MainMap.MIN_UNITS;
            TownCellAdapter adapter = new TownCellAdapter(context);
            for (int pos = 0; pos < MainMap.TOTAL_CELLS; pos++) {
                if (random.nextInt(2) == 1 && countUnits > 0) {
                    countUnits--;
                    adapter.add(pos, Unit.getRandomUnit(context, lvl, pos));
                } else {
                    adapter.add(pos, new UnitEmpty());
                }
            }
            // add exit cell
            int exitCell = mPossibleExitCell.get(random.nextInt(mPossibleExitCell.size()));
            UnitFieldExit exit = new UnitFieldExit(context, lvl, exitCell);
            exit.setTownName(mTownNames[random.nextInt(mTownNames.length)]);
            adapter.changeItem(exitCell, exit);
            // changing two random units to unitsOnePerField
            for (Class unit : unitsOnePerField) {
                int pos;
                do {
                    pos = random.nextInt(MainMap.TOTAL_CELLS);
                } while (pos == exitCell);
                adapter.changeItem(pos, Unit.newInstance(unit, context, lvl, pos));
            }
            mTowns.put(name, adapter);
        }
    }

    private void setPossibleExitCells() {
        for (int i = 1; i < MainMap.CELLS_PER_LINE - 1; i++) {
            mPossibleExitCell.add(i);
        }
        for (int i = MainMap.TOTAL_CELLS - MainMap.CELLS_PER_LINE + 1; i < MainMap.TOTAL_CELLS - 1; i++) {
            mPossibleExitCell.add(i);
        }
        for (int i = 0; i < MainMap.TOTAL_CELLS; i += MainMap.CELLS_PER_LINE) {
            mPossibleExitCell.add(i);
        }
        for (int i = MainMap.CELLS_PER_LINE - 1; i < MainMap.TOTAL_CELLS; i += MainMap.CELLS_PER_LINE) {
            mPossibleExitCell.add(i);
        }
    }

    public TownCellAdapter getTownAdapter(String townName) {
        return mTowns.get(townName);
    }
}
