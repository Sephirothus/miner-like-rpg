package com.example.puzzle;

/**
 * Created by sephiroth on 28.08.16.
 */
public class Empty extends Unit {

    @Override
    public void action() {}

    @Override
    public String getTitle() {
        return "";
    }

    @Override
    public int getColor() {
        return 0;
    }
}
