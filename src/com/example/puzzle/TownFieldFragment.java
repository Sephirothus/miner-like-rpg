package com.example.puzzle;

/**
 * Created by sephirothus on 29.01.17.
 */
public class TownFieldFragment extends FieldFragment {

    @Override
    public void createPlayField() {
        mMainMap.create().setTownUnits();
    }
}
