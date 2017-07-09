package com.example.puzzle.battle;

/**
 * Created by sephiroth on 28.08.16.
 */
public interface BattleUnitInterface {

    public void getHit(int dmg);

    public int strike(int dmg);

    public boolean checkHp();

    public int getHp();

    public int getStr();
}
