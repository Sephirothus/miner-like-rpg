package com.example.puzzle.town;

import android.content.Context;
import com.example.puzzle.Config;
import com.example.puzzle.MainMap;
import com.example.puzzle.unit.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by sephirothus on 30.01.17.
 */
public class Town {

    public static String HOMETOWN_NAME = "Dusk town";
    public static int MIN_TOWN_EXIT = 1;
    public static int MAX_TOWN_EXIT = 3;
    public static int MIN_PATH = 3;
    public static int MAX_PATH = 10;

    HashMap<String, HashMap<String, String[]>> mLocations = new HashMap<>();
    private ArrayList<Integer> mPossibleExitCell = new ArrayList<>();
    private HashMap<String, TownCellAdapter> mTowns = new HashMap<>();

    public void generateTowns(Context context) {
        setPossibleExitCells();
        generateLocationConnections();
        createTownAdapters(context);
    }

    private void generateLocationConnections() {
        // TODO add full path connection, that one can walk to every adventure
        Random random = new Random();
        Object[] towns = Config.mTowns.keySet().toArray();
        for (String name : Config.mTowns.keySet()) {
            HashMap connections;
            int exitCount = random.nextInt(MAX_TOWN_EXIT - MIN_TOWN_EXIT) + MIN_TOWN_EXIT;
            if (mLocations.get(name) != null) {
                connections = mLocations.get(name);
                exitCount -= mLocations.get(name).size();
            } else {
                connections = new HashMap<>();
            }
            for (int i = 0; i < exitCount; i++) {
                String town;
                do {
                    town = towns[random.nextInt(towns.length)].toString();
                } while (town == name);
                int pathLen = random.nextInt(MAX_PATH - MIN_PATH) + MIN_PATH;
                ArrayList<String> pathLocations = new ArrayList<>(pathLen);
                for (int count = 0; count < pathLen; count++) {
                    pathLocations.add(getRandomPathLocation());
                }
                connections.put(town, pathLocations);

                HashMap reverseConnections;
                if (mLocations.get(town) != null) {
                    reverseConnections = mLocations.get(town);
                } else {
                    reverseConnections = new HashMap<>();
                }
                ArrayList<String> reversePathLocations = new ArrayList<>(pathLocations);
                Collections.reverse(reversePathLocations);
                reverseConnections.put(name, reversePathLocations);
                mLocations.put(town, reverseConnections);
            }
            mLocations.put(name, connections);
        }
    }

    public static String getRandomPathLocation() {
        Object[] locations = Config.mPathLocations.keySet().toArray();
        return locations[(new Random()).nextInt(locations.length)].toString();
    }

    private void createTownAdapters(Context context) {
        Random random = new Random();
        Unit.units = new Class[] {UnitHouse.class, UnitTownsman.class, UnitDecor.class};
        Class[] unitsOnePerField = new Class[] {UnitChurch.class, UnitMerchant.class};
        for (String name : Config.mTowns.keySet()) {
            int countUnits = random.nextInt(MainMap.MAX_UNITS - MainMap.MIN_UNITS) + MainMap.MIN_UNITS;
            TownCellAdapter adapter = new TownCellAdapter(context);
            for (int pos = 0; pos < MainMap.TOTAL_CELLS; pos++) {
                if (random.nextInt(2) == 1 && countUnits > 0) {
                    countUnits--;
                    adapter.add(pos, Unit.getRandomUnit(context, pos, name));
                    // or add main quest unit
                } else {
                    adapter.add(pos, new UnitEmpty());
                }
            }
            // add exit cells
            HashMap connections = mLocations.get(name);
            ArrayList<Integer> exitCells = new ArrayList<>();
            for (Object location : connections.keySet().toArray()) {
                int cell;
                do {
                    cell = mPossibleExitCell.get(random.nextInt(mPossibleExitCell.size()));
                } while (isValInList(exitCells, cell));
                exitCells.add(cell);
                UnitFieldExit exit = new UnitFieldExit(context, cell, name);
                exit.setLocation(location.toString());
                exit.setPathLocations((ArrayList<String>) connections.get(location));
                adapter.changeItem(cell, exit);
            }
            // changing two random units (except exits) to unitsOnePerField
            for (Class unit : unitsOnePerField) {
                int pos;
                do {
                    pos = random.nextInt(MainMap.TOTAL_CELLS);
                } while (isValInList(exitCells, pos));
                adapter.changeItem(pos, Unit.newInstance(unit, context, pos, name));
            }
            mTowns.put(name, adapter);
        }
    }

    private boolean isValInList(ArrayList<Integer> list, int val) {
        try {
            return list.get(val) != null;
        } catch (IndexOutOfBoundsException e) {
            return false;
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
