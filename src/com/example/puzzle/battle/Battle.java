package com.example.puzzle.battle;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.example.puzzle.Config;
import com.example.puzzle.MainMap;
import com.example.puzzle.Player;
import com.example.puzzle.R;
import com.example.puzzle.activity.ExtendActivity;
import com.example.puzzle.unit.UnitEnemy;

import java.util.*;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Battle {

    private GridView mGridView;
    private CellBattleAdapter mAdapter;
    private Context mContext;

    private ArrayList<UnitEnemy> mEnemies = new ArrayList<>();
    private UnitEnemy mEnemy;
    private Player mPlayer;
    // 0 is player move, other numbers is enemies' move
    private int mTurn = 0;

    public final static int START_SEVERAL_FOES_PERCENT = 20;
    public final static int START_COUNT_FOES = 3;
    public final static int DMG_POINTS_PER_LINE = 3;

    Battle (Context context, ArrayList<UnitEnemy> enemies) {
        mContext = context;
        mEnemies = enemies;
        setEnemy(enemies.get(0));
        mPlayer = ((ExtendActivity) context).mPlayer;
        mGridView = (GridView) ((ExtendActivity) context).findViewById(R.id.gridView);
        mGridView.setNumColumns(MainMap.CELLS_PER_LINE);
        setCells();
        mAdapter = (CellBattleAdapter) mGridView.getAdapter();
    }

    public void setCells() {
        CellBattleAdapter adapter = new CellBattleAdapter(mContext, mEnemies);
        for (int pos = 0; pos < MainMap.BATTLE_TOTAL_CELLS; pos++) {
            adapter.add(pos, 0);
        }
        mGridView.setAdapter(adapter);
        setBattleClick();
    }

    public void setBattleClick() {
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // choose enemy
                if (view.findViewById(R.id.cell_img) != null) {
                    UnitEnemy enemy = getEnemyByPos(position);
                    if (enemy != null) {
                        setEnemy(enemy);
                    }
                } else if (mAdapter.isPlayerMove()) {
                    createChooser(position, view);
                } else {
                    moveActions(position, (new Random()).nextInt(MainMap.MAX_DMG_POINT) + 1, view);
                }
            }
        });
    }

    private void moveActions(Integer position, Integer num, View view) {
        mAdapter.set(position, num);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(num.toString());
        textView.setTextColor(Color.RED);

        ViewGroup.LayoutParams params = textView.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        params.width = ViewGroup.LayoutParams.MATCH_PARENT;
        textView.setLayoutParams(params);

        view.setBackgroundColor(MainMap.OPENED_CELL_COLOR);
    }

    private void createChooser(int cellPosition, View cellView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1);
        for (int i = 1; i <= MainMap.MAX_DMG_POINT; i++) {
            adapter.add(i);
        }
        View layout = (((ExtendActivity) mContext).getLayoutInflater()).inflate(R.layout.field, null);
        GridView grid = (GridView) layout.findViewById(R.id.gridView);
        if (grid.getParent() != null) {
            ((ViewGroup) grid.getParent()).removeView(grid);
        }
        grid.setNumColumns(DMG_POINTS_PER_LINE);
        grid.setAdapter(adapter);
        builder.setTitle("Choose a number")
                .setView(grid);
        final AlertDialog alert = builder.create();
        alert.show();
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                moveActions(cellPosition, position + 1, cellView);
                playerMove(cellPosition);
                alert.cancel();
            }
        });
    }

    public void firstMove() {
        if ((new Random()).nextInt(2) == 0) {
            takeTurn();
        }
    }

    public void takeTurn() {
        mTurn++;
        if (checkTurn()) {
            mAdapter.disableAdapter();
            mAdapter.setIsPlayerMove(false);
            if (!checkEmptyCells()) return;

            mGridView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    enemyMove();
                }
            }, 500);
        } else {
            if (!checkEmptyCells()) return;
            mAdapter.setIsPlayerMove(true);
            mAdapter.enableAdapter();
        }
    }

    private boolean checkTurn() {
        if (mTurn > mEnemies.size()) mTurn = 0;
        if (mTurn > 0) {
            setEnemy(mEnemies.get(mTurn - 1));
        }
        return mTurn > 0;
    }

    public void setEnemy(UnitEnemy enemy) {
        mEnemy = enemy;
        ((ExtendActivity) mContext).mStatsPanelFragment.addEnemyStats(
                enemy.getName(), enemy.getHp(), enemy.getStr()
        );
    }

    public UnitEnemy getEnemyByPos(int pos) {
        for (UnitEnemy enemy : mEnemies) {
            if (enemy.getPosition() == pos) return enemy;
        }
        return null;
    }

    private void killEnemy() {
        mEnemies.remove(mEnemy);
        ((ImageView) mGridView.getChildAt(mEnemy.getPosition()).findViewById(R.id.cell_img))
                .setImageResource(R.drawable.death);
        ((ExtendActivity) mContext).mLogHistoryFragment.addEnemyKillRec(mEnemy.getName());
        mPlayer.addKilledEnemy(mEnemy.getName());
    }

    public int openCell() {
        int pos = getFreeCellId();
        mGridView.performItemClick(mGridView.getChildAt(pos), pos, mAdapter.getItemId(pos));
        return pos;
    }

    public Integer getFreeCellId() {
        if (!checkEmptyCells()) return null;

        Random r = new Random();
        int pos = r.nextInt(MainMap.BATTLE_TOTAL_CELLS);
        View view = mGridView.getChildAt(pos);
        while (!MainMap.isCellEmpty(view)) {
            pos = r.nextInt(MainMap.BATTLE_TOTAL_CELLS);
            view = mGridView.getChildAt(pos);
        }
        return pos;
    }

    private boolean checkEmptyCells() {
        for (int i = 0; i < MainMap.BATTLE_TOTAL_CELLS; i++) {
            if (MainMap.isCellEmpty(mGridView.getChildAt(i))) {
                return true;
            }
        }
        continueBattle();
        return false;
    }

    public void enemyMove() {
        checkCells(openCell());
        Boolean check = mPlayer.checkHp();
        if (!check) {
            ((ExtendActivity) mContext).mLogHistoryFragment.addEndBattleRec("You lost :(");
            endBattle(true);
            return;
        }
        takeTurn();
    }

    public void playerMove(int cellPosition) {
        checkCells(cellPosition);
        Boolean check = mEnemy.checkHp();
        if (!check) {
            killEnemy();
            if (mEnemies.size() == 0) {
                mAdapter.disableAdapter();
                ((ExtendActivity) mContext).mLogHistoryFragment.addEndBattleRec("You won :)");
                mPlayer.lvlStatIncrease();
                endBattle(false);
                return;
            }
        }
        takeTurn();
    }

    private void checkCells(int pos) {
        final int realPos = pos + 1;
        checkCombo(createCellLine(new ArrayList<Integer>() {{
            add(realPos - MainMap.CELLS_PER_LINE * 2);
            add(realPos - MainMap.CELLS_PER_LINE);
            add(realPos);
            add(realPos + MainMap.CELLS_PER_LINE);
            add(realPos + MainMap.CELLS_PER_LINE * 2);
        }}), "vertical");

        checkCombo(createCellLine(new ArrayList<Integer>() {{
            for (int i = getMinRowNum(realPos); i <= getMaxRowNum(realPos); i++) {
                add(i);
            }
        }}), "horizontal");

        final int upperNum = realPos - MainMap.CELLS_PER_LINE * 2 - 2;
        final int upNum = realPos - MainMap.CELLS_PER_LINE - 1;
        final int lowNum = realPos + MainMap.CELLS_PER_LINE + 1;
        final int lowerNum = realPos + MainMap.CELLS_PER_LINE * 2 + 2;
        checkCombo(createCellLine(new ArrayList<Integer>() {{
            if (upperNum >= getMinRowNum(realPos - MainMap.CELLS_PER_LINE * 2)) add(upperNum);
            if (upNum >= getMinRowNum(realPos - MainMap.CELLS_PER_LINE)) add(upNum);
            add(realPos);
            if (lowNum <= getMaxRowNum(realPos + MainMap.CELLS_PER_LINE)) add(lowNum);
            if (lowerNum <= getMaxRowNum(realPos + MainMap.CELLS_PER_LINE * 2)) add(lowerNum);
        }}), "diagonal");

        final int secUpperNum = realPos - MainMap.CELLS_PER_LINE * 2 + 2;
        final int secUpNum = realPos - MainMap.CELLS_PER_LINE + 1;
        final int secLowNum = realPos + MainMap.CELLS_PER_LINE - 1;
        final int secLowerNum = realPos + MainMap.CELLS_PER_LINE * 2 - 2;
        checkCombo(createCellLine(new ArrayList<Integer>() {{
            if (secUpperNum <= getMaxRowNum(realPos - MainMap.CELLS_PER_LINE * 2)) add(secUpperNum);
            if (secUpNum <= getMaxRowNum(realPos - MainMap.CELLS_PER_LINE)) add(secUpNum);
            add(realPos);
            if (secLowNum >= getMinRowNum(realPos + MainMap.CELLS_PER_LINE)) add(secLowNum);
            if (secLowerNum >= getMinRowNum(realPos + MainMap.CELLS_PER_LINE * 2)) add(secLowerNum);
        }}), "diagonal");
    }

    private int getMinRowNum(int pos) {
        int row = (int) Math.ceil((float) pos / MainMap.CELLS_PER_LINE);
        return row * MainMap.CELLS_PER_LINE - MainMap.CELLS_PER_LINE + 1;
    }

    private int getMaxRowNum(int pos) {
        int row = (int) Math.ceil((float) pos / MainMap.CELLS_PER_LINE);
        return row * MainMap.CELLS_PER_LINE;
    }

    private TreeMap<Integer, Integer> createCellLine(ArrayList<Integer> cells) {
        TreeMap<Integer, Integer> newCells = new TreeMap<>();
        for (Integer cell : cells) {
            if (cell > 0 && cell <= MainMap.BATTLE_TOTAL_CELLS) {
                newCells.put(cell, mAdapter.getItem(getRealPos(cell)));
            }
        }
        return newCells;
    }

    private int getRealPos(int cellNum) {
        return cellNum - 1;
    }

    private void checkCombo(TreeMap<Integer, Integer> cellLine, String position) {
        // TODO change cycle to player combos
        for (HashMap<String, String> combo : Config.mBattleCombos) {
            List<String> pattern = Arrays.asList(combo.get("pattern").split(","));
            if (combo.get("position") != position) continue;

            ListIterator<String> iterator = pattern.listIterator();
            HashMap<Integer, Integer> cells = new HashMap<>();
            Integer prev = null;
            for (Integer cellId : cellLine.keySet()) {
                if (prev == null) {
                    prev = cellLine.get(cellId);
                    cells.put(cellId, cellLine.get(cellId));
                    continue;
                }
                if (!iterator.hasNext()) {
                    iterator = pattern.listIterator();
                }
                String curPattern = iterator.next();
                Boolean isFit = false;
                switch (curPattern) {
                    case "=":
                        if (prev == cellLine.get(cellId)) isFit = true;
                        break;
                    case "<":
                        if (prev + 1 == cellLine.get(cellId)) isFit = true;
                        break;
                    case ">":
                        if (prev - 1 == cellLine.get(cellId)) isFit = true;
                        break;
                }
                if (!isFit || cellLine.get(cellId) == 0) cells.clear();
                cells.put(cellId, cellLine.get(cellId));
                prev = cellLine.get(cellId);

                if (performCombo(cells, combo, cellLine)) {
                    break;
                }
            }
        }
    }

    private boolean performCombo(HashMap<Integer, Integer> cells, HashMap<String, String> combo, TreeMap<Integer, Integer> cellLine) {
        if (cells.size() == 3) {
            int pts = 0;
            for (Integer cell : cells.keySet()) {
                moveActions(getRealPos(cell), 0, mGridView.getChildAt(getRealPos(cell)));
                pts += cells.get(cell);
            }
            BattleUnitInterface attackUnit = mTurn == 0 ? mPlayer : mEnemy;
            BattleUnitInterface defendUnit = mTurn == 0 ? mEnemy : mPlayer;
            ((ExtendActivity) mContext).mLogHistoryFragment.addComboTypeRec(combo.get("title"), mTurn == 0);
            switch (combo.get("action")) {
                case "strike":
                    defendUnit.getHit(attackUnit.strike(pts));
                    break;
                case "heal":
                    attackUnit.addCurStat("hp", pts);
                    ((ExtendActivity) mContext).mLogHistoryFragment.addHealRec(pts);
                    break;
                case "block":
                    attackUnit.setBlock(pts);
                    ((ExtendActivity) mContext).mLogHistoryFragment.addBlockRec(pts);
                    break;
                case "clear_line":
                    for (Integer cellId : cellLine.keySet()) {
                        if (mAdapter.getItem(getRealPos(cellId)) > 0) {
                            moveActions(getRealPos(cellId), 0, mGridView.getChildAt(getRealPos(cellId)));
                        }
                    }
                    break;
            }
            return true;
        }
        return false;
    }

    public void endBattle(final Boolean isGameOver) {
        mGridView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isGameOver) {
                    ((ExtendActivity) mContext).gameOver();
                } else {
                    ((ExtendActivity) mContext).endBattle();
                }
            }
        }, 1000);
    }

    public void continueBattle() {
        mGridView.postDelayed(new Runnable() {
            @Override
            public void run() {
                ((ExtendActivity) mContext).startBattle(mEnemies);
            }
        }, 500);
    }
}
